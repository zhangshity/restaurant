package com.zcy.restaurant.handler;

import com.zcy.restaurant.config.ProjectUrlConfig;
import com.zcy.restaurant.exception.SellerAuthorizeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ Author: chunyang.zhang
 * @ Description:          异常补捕获 handler
 * @ Date: Created in
 * @ Modified: By:
 */
@ControllerAdvice //统一异常处理注解
public class SellExceptionHandler {

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    //拦截登录异常
    //自己的号http://zhangchunyang.natapp1.cc/wechat/qrAuthorize?returnUrl=http://zhangchunyang.natapp1.cc/seller/login
    //借的号http://zhangchunyang.natapp1.cc/wechat/qrAuthorize?returnUrl=http://zhangchunyang.natapp1.cc/wechat/qrUserInfo
    @ExceptionHandler(value = SellerAuthorizeException.class)
    public ModelAndView handlerAuthorizeException() {
        return new ModelAndView("redirect:"
                .concat(projectUrlConfig.getWechatOpenAuthorize())
                .concat("/wechat/qrAuthorize")
                .concat("?returnUrl=")
                .concat(projectUrlConfig.getSell())
                .concat("/wechat/qrUserInfo")) ;
    }
}
