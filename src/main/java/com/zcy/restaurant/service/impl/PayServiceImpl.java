package com.zcy.restaurant.service.impl;

import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundRequest;
import com.lly835.bestpay.model.RefundResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import com.zcy.restaurant.dto.OrderDTO;
import com.zcy.restaurant.enums.ExceptionEnum;
import com.zcy.restaurant.exception.SellException;
import com.zcy.restaurant.service.OrderService;
import com.zcy.restaurant.service.PayService;
import com.zcy.restaurant.utils.JsonUtil;
import com.zcy.restaurant.utils.MathVerifyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @ Author: chunyang.zhang
 * @ Description:       微信支付实现类
 * @ Date: Created in
 * @ Modified: By:
 */
@Service
@Slf4j
public class PayServiceImpl implements PayService {

    private static final String ORDER_NAME = "微信点餐订单";

    @Autowired
    private BestPayServiceImpl bestPayService;

    @Autowired
    private OrderService orderService;

    //发起微信支付
    @Override
    public PayResponse create(OrderDTO orderDTO) {

        PayRequest payRequest = new PayRequest();

        payRequest.setOpenid(orderDTO.getBuyerOpenid());
        payRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        payRequest.setOrderId(orderDTO.getOrderId());
        payRequest.setOrderName(ORDER_NAME);
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info("【微信支付】发起支付,request={}", JsonUtil.toJson(payRequest));

        PayResponse payResponse = bestPayService.pay(payRequest);
        log.info("【微信支付】发起支付,response={}", JsonUtil.toJson(payResponse));

        return payResponse;
    }


    //微信异步通知
    @Override
    public PayResponse notify(String notifyData) {
        //1验证签名(SDK)
        //2支付状态(SDK)
        //3支付金额
        //4支付人(支付人==下单人 ) 暂时不用


        PayResponse payResponse = bestPayService.asyncNotify(notifyData);
        log.info("【微信支付】异步通知,订单不存在。payResponse={}", JsonUtil.toJson(payResponse));
        //查询订单
        OrderDTO orderDTO = orderService.findOne(payResponse.getOrderId());
        //判断订单是否存在
        if (orderDTO == null) {
            log.error("微信支付】异步通知,订单不存在,orderId={}", payResponse.getOrderId());
            throw new SellException(ExceptionEnum.CART_EMPTY.ORDER_NOT_EXIST);
        }
        //判断金额是否一致
        if (!MathVerifyUtil.equals(orderDTO.getOrderAmount().doubleValue(), payResponse.getOrderAmount())) {
            log.error("微信支付】异步通知,订单金额不一致,orderId={}. 微信通知金额={},系统金额={}",
                    payResponse.getOrderId(),
                    payResponse.getOrderAmount(),
                    orderDTO.getOrderAmount());
            throw new SellException(ExceptionEnum.WX_PAY_NOTIFY_MONEY_VERIFY_ERROR);
        }
        //修改订单的支付状态
        orderService.pay(orderDTO);
        //返回给微信处理结果在Controller中

        return payResponse;
    }


    //微信退款
    @Override
    public RefundResponse refund(OrderDTO orderDTO) {
        RefundRequest refundRequest = new RefundRequest();
        refundRequest.setOrderId(orderDTO.getOrderId());
        refundRequest.setOrderAmount(orderDTO.getOrderAmount().doubleValue());
        refundRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        log.info("【微信退款】request={}", refundRequest);

        RefundResponse refundResponse = bestPayService.refund(refundRequest);
        log.info("【微信退款】response={}", refundResponse);

        return refundResponse;
    }

}
