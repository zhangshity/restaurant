package com.zcy.restaurant.service.impl;

import com.zcy.restaurant.dto.OrderDTO;
import com.zcy.restaurant.enums.ExceptionEnum;
import com.zcy.restaurant.exception.SellException;
import com.zcy.restaurant.service.BuyerService;
import com.zcy.restaurant.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ Author: chunyang.zhang
 * @ Description:
 * @ Date: Created in
 * @ Modified: By:
 */
@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private OrderService orderService;

    @Override
    public OrderDTO findOrderOne(String openid, String orderId) {
        return checkOrderOwner(openid, orderId);
    }

    @Override
    public OrderDTO cancelOrder(String openid, String orderId) {
        OrderDTO orderDTO = checkOrderOwner(openid, orderId);
        if (orderDTO == null){
            log.error("【取消订单】查不到该订单,oederId={}",orderId);
            throw new SellException(ExceptionEnum.ORDER_NOT_EXIST);
        }
        return orderService.cancel(orderDTO);
    }


    //公用私有方法，检查订单所有者
    private OrderDTO checkOrderOwner(String openid, String orderId) {
        //DB中取出 一个主表和附表(OrderDTO)
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (orderDTO == null) {
            return null;
        }
        //判断前端传入openid 和 DB中取出的是否相等(是否自己的订单)
        if (orderDTO.getBuyerOpenid().equalsIgnoreCase(openid)) {
            log.error("【查询订单】订单openid不一致 openid={}, orderDTO={}", openid, orderDTO);
            throw new SellException(ExceptionEnum.ORDER_OWNER_ERROR);
        }
        return orderDTO;
    }

}
