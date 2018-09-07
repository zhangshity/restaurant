package com.zcy.restaurant.service;

import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundResponse;
import com.zcy.restaurant.dto.OrderDTO;

/**
 * @ Author: chunyang.zhang
 * @ Description:    微信支付Service层，逻辑都在这里
 * @ Date: Created in
 * @ Modified: By:
 */
//接口中的方法默认都是public、abstract的
public interface PayService {

    //发起微信支付
    PayResponse create(OrderDTO orderDTO);

    //微信异步通知
    PayResponse notify(String notifyData);

    //微信退款
    RefundResponse refund(OrderDTO orderDTO);
}
