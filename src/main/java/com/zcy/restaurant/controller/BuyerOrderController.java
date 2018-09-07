package com.zcy.restaurant.controller;

import com.zcy.restaurant.VO.ResultVO;
import com.zcy.restaurant.converter.OrderFormToOrderDTOConverter;
import com.zcy.restaurant.dto.OrderDTO;
import com.zcy.restaurant.enums.ExceptionEnum;
import com.zcy.restaurant.exception.SellException;
import com.zcy.restaurant.form.OrderForm;
import com.zcy.restaurant.service.BuyerService;
import com.zcy.restaurant.service.OrderService;
import com.zcy.restaurant.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ Author: chunyang.zhang
 * @ Description:   订单的 controller
 * @ Date: Created in
 * @ Modified: By:
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private BuyerService buyerService;

    //创建订单
    @PostMapping("create")
    public ResultVO<Map<String, String>> create(@Valid OrderForm orderForm,
                                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {//bindind有错误抛异常
            log.error("【创建订单】参数不正确, orderForm={}", orderForm);
            throw new SellException(ExceptionEnum.PARAM_EORROR.getCode()
                    , bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDTO orderDTO = OrderFormToOrderDTOConverter.convert(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {//判断购物车是否为空
            log.error("【创建订单】购物车不能为空");
            throw new SellException(ExceptionEnum.CART_EMPTY);
        }
        OrderDTO createResult = orderService.create(orderDTO);

        Map<String, String> map = new HashMap<>();
        map.put("orderId", createResult.getOrderId());

        return ResultVOUtil.success(map);
    }


    //订单列表(主表的展示)
    @GetMapping("list")
    public ResultVO<List<OrderDTO>> list(@RequestParam("openid") String openid,
                                         @RequestParam(value = "page", defaultValue = "0") Integer page,
                                         @RequestParam(value = "size", defaultValue = "10") Integer size) {
        if (StringUtils.isEmpty(openid)) {
            log.error("【查询订单列表】openid为空");
            throw new SellException(ExceptionEnum.PARAM_EORROR);
        }
        //分页参数对象设置
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<OrderDTO> orderDTOPage = orderService.findList(openid, pageRequest);

        return ResultVOUtil.success(orderDTOPage.getContent());
    }


    //订单详情(附表的展示)
    @GetMapping("/detail")
    public ResultVO<OrderDTO> detail(@RequestParam("openid") String openid,
                                     @RequestParam("orderId") String orderId) {
        //TODO  不安全做法，改进
//        OrderDTO orderDTO = orderService.findOne(orderId);
//        if (orderDTO.getBuyerOpenid().equalsIgnoreCase(openid))
        OrderDTO orderDTO = buyerService.findOrderOne(openid, orderId);
        return ResultVOUtil.success(orderDTO);
    }


    //取消订单
    @PostMapping("/cancel")
    public ResultVO cancel(@RequestParam("openid") String openid,
                           @RequestParam("orderId") String orderId) {
//        //TODO  不安全做法，改进
//        OrderDTO orderDTO = orderService.findOne(orderId);
//        orderService.cancel(orderDTO);
        buyerService.cancelOrder(openid, orderId);
        return ResultVOUtil.success();
    }
}
