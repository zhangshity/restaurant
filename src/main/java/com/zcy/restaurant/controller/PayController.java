package com.zcy.restaurant.controller;


import com.lly835.bestpay.model.PayResponse;
import com.zcy.restaurant.dto.OrderDTO;
import com.zcy.restaurant.enums.ExceptionEnum;
import com.zcy.restaurant.exception.SellException;
import com.zcy.restaurant.service.OrderService;
import com.zcy.restaurant.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.net.URLDecoder;
import java.util.Map;

/**
 * @ Author: chunyang.zhang
 * @ Description:        微信支付
 * @ Date: Created in
 * @ Modified: By:
 */

@Controller
//@RequestMapping("/pay")
@Slf4j
public class PayController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private PayService payService;



    //调用租借的公众号
    @GetMapping("/pay")
    public ModelAndView index(@RequestParam("openid") String openid,
                              @RequestParam("orderId") String orderId,
                              @RequestParam("returnUrl") String returnUrl,
                              Map<String, Object> map) {

        log.info("【openid】={}", openid);

        //String orderId = "1527781811075484002";//代理支付测试用
        //1.查询订单
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (orderDTO == null) {
            throw new SellException(ExceptionEnum.ORDER_NOT_EXIST);
        }
        //2.发起支付
        //orderDTO.setBuyerOpenid(openid); 不需要再设置

        PayResponse payResponse = payService.create(orderDTO);
        map.put("payResponse", payResponse);
        map.put("returnUrl", URLDecoder.decode(returnUrl));



        return new ModelAndView("pay/create", map);
    }




    //发起微信支付(已有商业资质的公众号)
    @GetMapping("/create")
    public ModelAndView create(@RequestParam("orderId") String orderId,
                               @RequestParam("returnUrl") String returnUrl,
                               Map<String, Object> map) {
        //1.查询订单
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (orderDTO == null) {
            throw new SellException(ExceptionEnum.ORDER_NOT_EXIST);
        }
        //2.发起支付
        PayResponse payResponse = payService.create(orderDTO);
        map.put("payResponse", payResponse);
        map.put("returnUrl", returnUrl);

        return new ModelAndView("pay/create", map);
    }



    //微信异步通知
    @PostMapping("/notify")
    public ModelAndView notify(@RequestBody String notifyData) {

        payService.notify(notifyData);

        //返回给微信处理结果
        //xml代码java中格式不好，写到模版里去
        return new ModelAndView("pay/success");
    }

}
