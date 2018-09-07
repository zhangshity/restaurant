package com.zcy.restaurant.service.impl;

import com.zcy.restaurant.converter.OrderMasterToOrderDTOConverter;
import com.zcy.restaurant.dto.OrderDTO;
import com.zcy.restaurant.entity.OrderDetail;
import com.zcy.restaurant.entity.OrderMaster;
import com.zcy.restaurant.enums.OrderStatusEnum;
import com.zcy.restaurant.enums.PayStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.criterion.Order;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderServiceImpl;

    private final String BUYER_OPENID = "1101110";

    private final String ORDER_ID = "1526727792262756411";


    @Test
    public void create() {

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("特朗普");
        orderDTO.setBuyerAddress("美国华盛顿D.C.");
        orderDTO.setBuyerPhone("18945673293");
        orderDTO.setBuyerOpenid(BUYER_OPENID);

        //购物车
        List<OrderDetail> orderDetailList = new ArrayList<>();

        OrderDetail o1 = new OrderDetail();
        o1.setProductId("1");
        o1.setProductQuantity(1);

        OrderDetail o2 = new OrderDetail();
        o2.setProductId("127");
        o2.setProductQuantity(2);

        orderDetailList.add(o1);
        orderDetailList.add(o2);


        orderDTO.setOrderDetailList(orderDetailList);

        OrderDTO result = orderServiceImpl.create(orderDTO);

        log.info("【创建订单】 result = {}", result);
        Assert.assertNotNull(result);
    }


    @Test
    public void findOne() {
        OrderDTO result = orderServiceImpl.findOne(ORDER_ID);
        log.info("【查询单个订单】= {}", result);
        Assert.assertEquals(ORDER_ID, result.getOrderId());

//        OrderDTO result = orderServiceImpl.findOne("111111");
//        log.info("【查询单个订单】= {}", result);
//        Assert.assertNotNull(result);
    }


    @Test
    public void findList() {
        PageRequest pageRequest = PageRequest.of(0, 2);
        Page<OrderDTO> orderDTOPage =
                orderServiceImpl.findList(BUYER_OPENID, pageRequest);

        Assert.assertNotEquals(0, orderDTOPage.getTotalElements());
    }


    @Test
    public void cancel() {
        OrderDTO orderDTO = orderServiceImpl.findOne(ORDER_ID);
        OrderDTO result = orderServiceImpl.cancel(orderDTO);
        Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(), result.getOrderStatus());
    }


    @Test
    public void finish() {
        OrderDTO orderDTO = orderServiceImpl.findOne(ORDER_ID);
        OrderDTO result = orderServiceImpl.finish(orderDTO);
        Assert.assertEquals(OrderStatusEnum.FINISH.getCode(), result.getOrderStatus());


    }

    @Test
    public void pay() {
        OrderDTO orderDTO = orderServiceImpl.findOne(ORDER_ID);
        OrderDTO result = orderServiceImpl.pay(orderDTO);
        Assert.assertEquals(PayStatusEnum.SUCCESS.getCode(), result.getPayStatus());
    }


    @Test
    public void list() {
        PageRequest pageRequest = PageRequest.of(0, 2);
        Page<OrderDTO> orderDTOPage = orderServiceImpl.findList(pageRequest);
        //Assert.assertNotEquals(0, orderDTOPage.getTotalElements());
        Assert.assertTrue("查询所有订单主表", orderDTOPage.getTotalElements() > 0);
    }
}