package com.zcy.restaurant.service;


import com.zcy.restaurant.dto.OrderDTO;

/**
 * @ Author: chunyang.zhang
 * @ Description:  买家
 * @ Date: Created in
 * @ Modified: By:
 */
//接口中的方法默认都是public、abstract的
public interface BuyerService {

    //查询一个订单
    OrderDTO findOrderOne(String openid,String orderId);

    //取消订单
    OrderDTO cancelOrder(String openid, String orderId);
}
