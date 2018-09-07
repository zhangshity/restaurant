package com.zcy.restaurant.service.impl;

import com.zcy.restaurant.dto.OrderDTO;
import com.zcy.restaurant.service.OrderService;
import com.zcy.restaurant.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class PayServiceImplTest {

    @Autowired
    private PayService payService;

    @Autowired
    private OrderService orderService;

    @Test
    public void create() {
        //OrderDTO orderDTO = new OrderDTO();
        OrderDTO orderDTO = orderService.findOne("1527579385149659152");
        payService.create(orderDTO);
    }

    @Test
    public void refund() {
        OrderDTO orderDTO = orderService.findOne("1527579385149659152");
        payService.refund(orderDTO);
    }
}