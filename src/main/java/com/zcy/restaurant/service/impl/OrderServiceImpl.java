package com.zcy.restaurant.service.impl;

import com.zcy.restaurant.converter.OrderMasterToOrderDTOConverter;
import com.zcy.restaurant.dto.CartDTO;
import com.zcy.restaurant.dto.OrderDTO;
import com.zcy.restaurant.entity.OrderDetail;
import com.zcy.restaurant.entity.OrderMaster;
import com.zcy.restaurant.entity.ProductInfo;
import com.zcy.restaurant.enums.ExceptionEnum;
import com.zcy.restaurant.enums.OrderStatusEnum;
import com.zcy.restaurant.enums.PayStatusEnum;
import com.zcy.restaurant.exception.SellException;
import com.zcy.restaurant.repository.OrderDetailRepository;
import com.zcy.restaurant.repository.OrderMasterRepository;
import com.zcy.restaurant.service.OrderService;
import com.zcy.restaurant.service.PayService;
import com.zcy.restaurant.service.ProductInfoService;
import com.zcy.restaurant.service.WebSocket;
import com.zcy.restaurant.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @ Author: chunyang.zhang
 * @ Description:  订单service 的实现类！！
 * @ Date: Created in 12:41
 * @ Modified: By: zcy 注解别再加错了！！放在实现类！！！不是service接口
 */

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private PayService payService;

    @Autowired
    private WebSocket webSocket;


    /**
     * 创建订单
     */
    @Override        //orderDTO是前端给的数据对象
    @Transactional   //事务
    public OrderDTO create(OrderDTO orderDTO) {
        //初始化订单ID(订单id生成工具)
        String orderId = KeyUtil.genUniqueKey();
        //初始化订单总金额 = 0 (for中计算叠加)
        BigDecimal orderAmount = new BigDecimal(0);
        //初始化购物车 list
        List<CartDTO> cartDTOList = new ArrayList<>();
        //1查询商品（数量价格）        //:前端json数据 从订单(主表)中抽取出(副表)的信息(List<OrderDetail>)
        for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {//:前  把副表List中对象遍历出来！！)
            //DB取出商品信息productInfo
            ProductInfo productInfo = productInfoService.findOne(orderDetail.getProductId());
            if (productInfo == null) {
                throw new SellException(ExceptionEnum.PRODUCT_NOT_EXIST);
            }
            //2计算订单总价
            //orderAmount = orderDetail.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity())).add(orderAmount);
            orderAmount = productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity())).add(orderAmount);
            //订单详情(附表)写入数据库DB(细节OrderDetail)
            BeanUtils.copyProperties(productInfo, orderDetail);//spring提供的实体类属性拷贝
            orderDetail.setDetailId(KeyUtil.genUniqueKey());//生成自增主键
            orderDetail.setOrderId(orderId);//必须用String定义书记主键，才能和主表用一个String对应
            orderDetailRepository.save(orderDetail);//for出一个附表存一个
            //扣库存
            CartDTO cartDTO = new CartDTO(orderDetail.getProductId(), orderDetail.getProductQuantity());
            cartDTOList.add(cartDTO);
        }
        //3订单主表写入数据库DB（主表OrderMaster）
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderId(orderId);//API中没有的属性(自己的主键生成工具)
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderAmount(orderAmount);//API中没有的属性(自己的计算算法)
        //Status被覆盖了！！！操你妈！要重新写回去
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());//API中没有的属性(设为默认值)
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());//API中没有的属性(设置为默认值)
        orderMasterRepository.save(orderMaster);
        //4减少库存（！！别漏）
//        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream().map(e ->
//                new CartDTO(e.getProductId(), e.getProductQuantity())
//        ).collect(Collectors.toList());
        //lanmada表达式简化(一般用于两个list的属性copy)
        productInfoService.decreaseStock(cartDTOList);


        // 发送websocket消息
        webSocket.sendMessage(orderDTO.getOrderId());
        return orderDTO;
    }

    //查询单个订单 (卖家、买家 都需要)
    /**
     * 查询单个订单
     *///主附表都要
    @Override
    public OrderDTO findOne(String orderId) {
        //DB拿出(通过订单Id查找)订单 主表
        OrderMaster orderMaster = orderMasterRepository.findById(orderId).orElse(null);
        if (orderMaster == null) {
            throw new SellException(ExceptionEnum.ORDER_NOT_EXIST);
        }
        //DB拿出(通过订单Id查找)订单 附表
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetailList)) {
            throw new SellException(ExceptionEnum.ORDERDETAIL_NOT_EXIST);
        }
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }


    //查询订单主表 (卖家后台调用)
    /**
     * 查询订单列表(通过Openid)
     */ //只要主表
    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {

        //*:Page可以理解为一个可以控制页数的List,只不过需要多一个页面控制参数Pageable
        //*:Pageable也是一个接口，其实现类为PageRequest(其主要作用就是设置参数page,size)

        //1.从DB中取出订单 主表 的List 并加分页信息(Page<OrderMaster>)
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findByBuyerOpenid(buyerOpenid, pageable);

        //2.通过自己写的转换器,转换主表POJO到DTO  //*:Page的 getContent()把page对象转换成list对象
        List<OrderDTO> orderDTOList = OrderMasterToOrderDTOConverter
                .convert(orderMasterPage.getContent());


        //*:Page接口 的实现类 PageImpl 作用: 把List对象 转换为 Page对象
        //*:PageImpl 实现类的构造方法有三个参数(要转换的List对象,分页控制接口pageable,每页的元素数量size)
        //3.把OrderDTO List对象 转换成 page对象
        Page<OrderDTO> orderDTOPage = new PageImpl<>(orderDTOList, pageable, orderMasterPage.getTotalElements());

        //不用查详情，查看订单列表的时候不显示详情

        return orderDTOPage;
    }


    /**
     * 取消订单
     */
    @Override
    @Transactional
    public OrderDTO cancel(OrderDTO orderDTO) {
        //方法2
        OrderMaster orderMaster = new OrderMaster();
        //方法1
        //OrderMaster orderMaster = orderMasterRepository.findById(orderDTO.getOrderId()).get();//DB拿出订单主表
        //判断订单状态(只有状态为 NEW (新下单)的订单可以取消)
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【取消订单】订单状态不正确 orderId={} , orderStatus={}",
                    orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ExceptionEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        //方法2
        orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        BeanUtils.copyProperties(orderDTO, orderMaster);
        //方法1
        //orderMaster.setOrderStatus(OrderStatusEnum.CANCEL.getCode());

        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if (updateResult == null) {//判断订单状态是否修改成功(sava返回一个修改后的对象)
            log.error("【取消订单】取消失败, save返回为空 orderMaster={}", orderMaster);
            throw new SellException(ExceptionEnum.ORDER_CANCEL_FAIL);
        }
        //返还库存
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【取消订单】订单中无商品详情, orderDTO={}", orderDTO);
            throw new SellException(ExceptionEnum.ORDER_DETAIL_EMPTY);
        }
        //把List<orderDTO> 附表中的两个参数(productId，productQuantity) 传入List<CartDTO>中
        List<CartDTO> cartDTOList = new ArrayList<>();
        List<OrderDetail> orderDetailList = orderDTO.getOrderDetailList();
        for (OrderDetail orderDetail : orderDetailList) {
            CartDTO cartDTO = new CartDTO();
            cartDTO.setProductId(orderDetail.getProductId());
            cartDTO.setProductQuantity(orderDetail.getProductQuantity());
            cartDTOList.add(cartDTO);
        }
        productInfoService.increaseStock(cartDTOList);
        //lamba方法(上面两个list复制数据的另一个方法)
//        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream()
//                .map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
//                .collect(Collectors.toList());
//        productInfoService.increaseStock(cartDTOList);
        //如果支付，退钱
        if (orderDTO.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())) {

            payService.refund(orderDTO);
        }
        return orderDTO;
    }


    /**
     * 完成订单
     */
    @Override
    @Transactional
    public OrderDTO finish(OrderDTO orderDTO) {
        //判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【完结订单】订单状态不正确, orderId={}, orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ExceptionEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setOrderStatus(OrderStatusEnum.FINISH.getCode());
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster finish = orderMasterRepository.save(orderMaster);
        if (finish == null) {
            log.error("【完结订单】完结失败,save返回为空 orderMaster={}", orderMaster);
            throw new SellException(ExceptionEnum.ORDER_FINISH_FAIL);
        }
        return orderDTO;
    }


    /**
     * 支付订单
     */
    @Override
    @Transactional
    public OrderDTO pay(OrderDTO orderDTO) {
        //查询订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【支付订单】订单状态不正确,orderID={}, orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ExceptionEnum.ORDER_STATUS_ERROR);
        }
        //判断支付状态
        if (!orderDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCode())) {
            log.error("【支付订单】支付状态不正确 orderId={},payStatus={}", orderDTO.getOrderId(), orderDTO.getPayStatus());
            throw new SellException(ExceptionEnum.PAY_STATUS_ERROR);
        }
        //修改订单支付状态
        OrderMaster orderMaster = new OrderMaster();
        orderDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster finish = orderMasterRepository.save(orderMaster);
        if (finish == null) {
            log.error("【支付订单】支付失败, save返回为空 orderMaster={}", orderMaster);
            throw new SellException(ExceptionEnum.ORDER_UPDATE_FAIL);
        }
        return orderDTO;
    }


    //查询所有订单
    @Override
    public Page<OrderDTO> findList(Pageable pageable) {
        //DB查找主表
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findAll(pageable);
        //转换Page<主表> ->List<主表> ->List<DTO>， 相当于(把List<DTO> = List<主表>)
        List<OrderDTO> orderDTOList = OrderMasterToOrderDTOConverter
                .convert(orderMasterPage.getContent());

        Page<OrderDTO> orderDTOPage = new PageImpl<>(orderDTOList, pageable,
                orderMasterPage.getTotalElements());

        return orderDTOPage;
    }
}
