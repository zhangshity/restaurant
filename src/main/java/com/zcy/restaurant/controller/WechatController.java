package com.zcy.restaurant.controller;


import com.zcy.restaurant.config.ProjectUrlConfig;
import com.zcy.restaurant.enums.ExceptionEnum;
import com.zcy.restaurant.exception.SellException;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URLEncoder;

/**
 * @ Author: chunyang.zhang
 * @ Description:         微信网页授权相关。(导入威微信第三方SDK)
 * GitHub网址:
 * https://github.com/Wechat-Group/weixin-java-tools
 * @ Date: Created in
 * @ Modified: By:
 * 声明：本项目引用只是为了设计项目使用
 * 如有其它需求请自行fork，后果与本项目作者无关
 * MP:   media platform 媒体平台
 * 后改为 official account
 * <p>
 * <p>
 * MP_OAuth2网页授权使用说明:
 * https://github.com/Wechat-Group/weixin-java-tools/wiki/MP_OAuth2%E7%BD%91%E9%A1%B5%E6%8E%88%E6%9D%83
 * (所谓微信授权，就是微信客户端内访问第三方网站是，需要用户同意。
 * 于是就有了这么一个网址，这个网址映射你的验证代码，后面跟着参数?returnUrl=http://xxxxx.com
 * 通俗解释就是：？前是调用的你的判断方法  ?后是你的参数(这里要求是个URL网址)
 * <p>
 * <p>
 * 流程：
 * 用户在微信上点开网址，会有Oauth2.0给与选择是否授权。
 * 不同意，你GG
 * 同意，oauth2.0给你的验证程序一个code，换一个access_token的东西(里面包含poenid等)，
 * 然后你就有了用户信息   且能访问第三方URL网址
 * <p>
 * <p>
 * <p>
 * )
 */
@Controller
@RequestMapping("/wechat")
@Slf4j
public class WechatController {

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private WxMpService wxOpenService;

    @Autowired
    private ProjectUrlConfig projectUrlConfig;



    //=================微信授权获取openid======买家(Buyer)===================================

//    /**
//     * 1.用户同意授权，获取code
//     * 微信中打开官方验证网址：
//     * https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=%s#wechat_redirect
//     * (appid,redirect_uri,scope,state)4个参数要在验证网址中替换掉。
//     * (微信公众号id,重定向URL,请求类型,状态)
//     * <p>
//     * 跳转到 redirect_uri/?code=CODE&state=STATE(实际返回了redirect_uri,code,state)
//     */
//    //此方法传入returnUrl 得到 returnUrl?openid=oZxSYw5ldcxv6H0EU67GgSXOUrVg
//    @GetMapping("/authorize")
//    public String authorize(@RequestParam("returnUrl") String returnUrl) {
//        //1.配置(在config中完成)
//        //2.调用方法
//        /**oauth2buildAuthorizationUrl()方法参数只需要输入redirect_uri, scope, state这三个参数 appid会自动解析配置文件添加。
//         * 这个方法实际就是：把三个必须参数传入,appid配置文件自动解析,然后封装成填好参数的验证网址
//         * https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=%s#wechat_redirect
//         * 只不过这里重定向的网址是 另一方法的映射网址(相当于访问userInfo方法)*/
//        //String url = "http://www.baidu.com";
//        //String url = "http://zhangchunyang.natapp1.cc/sell/wechat/userInfo";
//        String url = projectUrlConfig.getWechatMpAuthorize() + "/wechat/userInfo";
//        String redirectUrl = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAuth2Scope.SNSAPI_USERINFO, URLEncoder.encode(returnUrl));
//        return "redirect:" + redirectUrl;
//        //如果用户同意授权，页面将跳转至 redirect_uri/?code=CODE&state=STATE
//        //实际跳转至http://zhangchunyang.natapp1.cc/sell/wechat/userInfo?code=CODE&state=STATE
//        //state起到了传导记录的作用，最开始想访问的网址一直记录在state中
//    }
//
//    //http://zhangchunyang.natapp1.cc/sell/wechat/qrUserInfo?code= &state=
//    @GetMapping("/userInfo")
//    public String userInfo(@RequestParam("code") String code,
//                           @RequestParam("state") String returnUrl) {//最开始想访问的网址一直记录在state中
//        WxMpOAuth2AccessToken wxMpOAuth2AccessToken;
//        try {
//            //通过用户许可得到code 换取accessToken(包含所有用户基本信息 如:openid等)
//            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
//        } catch (WxErrorException e) {
//            log.error("【微信网页授权】{}", e);
//            throw new SellException(ExceptionEnum.WX_MP_ERROR.getCode(), e.getError().getErrorMsg());
//        }
//        String openid = wxMpOAuth2AccessToken.getOpenId();
//
//        return "redirect:" + returnUrl + "?openid=" + openid;//return "redirect:/xxxxx.com"; springmvc重定向的用法
//    }



    //==========================借用微信公众平台======买家(Buyer)==========================
    @GetMapping("/authorize")
    public String authorize(@RequestParam("returnUrl") String returnUrl) {
        //1.配置(在config中完成)
        //2.调用方法
        //String url = "http://www.baidu.com";
        //String url = "http://zhangchunyang.natapp1.cc/sell/wechat/userInfo";
        String url = projectUrlConfig.getWechatMpAuthorize() + "/wechat/userInfo";
        String redirectUrl = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAuth2Scope.SNSAPI_USERINFO, URLEncoder.encode(returnUrl));
        return "redirect:" + redirectUrl;
    }
    //http://zhangchunyang.natapp1.cc/sell/wechat/qrUserInfo?code= &state=
    @GetMapping("/userInfo")
    public String userInfo(@RequestParam("code") String code,
                           @RequestParam("state") String returnUrl) {
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken;
        try {
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
        } catch (WxErrorException e) {
            log.error("【微信网页授权】{}", e);
            throw new SellException(ExceptionEnum.WX_MP_ERROR.getCode(), e.getError().getErrorMsg());
        }
        String openid = wxMpOAuth2AccessToken.getOpenId();

        return "redirect:" + returnUrl + "?openid=" + openid;
    }


    //=============================卖家(Seller)==========================================

//    @GetMapping("/qrAuthorize")
//    public String qrAuthorize(@RequestParam("returnUrl") String returnUrl) {
//        //String url = "http://zhangchunyang.natapp1.cc/sell/wechat/qrUserInfo";
//        //String url = "http://sell.springboot.cn/sell/qr/oTgZpwe9Wt5cAp6jnofdWxLaGA8s";
//
//        String url = projectUrlConfig.getWechatOpenAuthorize() + "/sell/wechat/qrUserInfo";
//        String redirectUrl = wxOpenService.buildQrConnectUrl(url, WxConsts.QrConnectScope.SNSAPI_LOGIN, URLEncoder.encode(returnUrl));
//        return "redirect:" + redirectUrl;
//    }
//    //http://zhangchunyang.natapp1.cc/sell/wechat/qrUserInfo?code= &state=
//    @GetMapping("/qrUserInfo")
//    public String qrUserInfo(@RequestParam("code") String code,
//                             @RequestParam("state") String returnUrl) {
//        WxMpOAuth2AccessToken wxMpOAuth2AccessToken;
//        try {
//            wxMpOAuth2AccessToken = wxOpenService.oauth2getAccessToken(code);
//        } catch (WxErrorException e) {
//            log.error("【微信网页授权】{}", e);
//            throw new SellException(ExceptionEnum.WX_MP_ERROR.getCode(), e.getError().getErrorMsg());
//        }
//        log.info("wxMpOAuth2AccessToken={}", wxMpOAuth2AccessToken);
//        String openid = wxMpOAuth2AccessToken.getOpenId();
//
//        return "redirect:" + returnUrl + "?openid=" + openid;
//    }


    //========================借用开放平台的授权=====卖家(Seller)==========================================
    @GetMapping("/qrAuthorize")
    public String qrAuthorize(@RequestParam("returnUrl") String returnUrl) {
        String url = "http://sell.springboot.cn/sell/qr/oTgZpwe9Wt5cAp6jnofdWxLaGA8s";
        String redirectUrl = wxOpenService.buildQrConnectUrl(url, WxConsts.QrConnectScope.SNSAPI_LOGIN, URLEncoder.encode(returnUrl));
        return "redirect:" + redirectUrl;
    }

    @GetMapping("/qrUserInfo")
    public String qrUserInfo(@RequestParam("code") String code) {
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken;
        try {
            wxMpOAuth2AccessToken = wxOpenService.oauth2getAccessToken(code);
        } catch (WxErrorException e) {
            log.error("【微信网页授权】{}", e);
            throw new SellException(ExceptionEnum.WX_MP_ERROR.getCode(), e.getError().getErrorMsg());
        }
        //log.info("wxMpOAuth2AccessToken={}", wxMpOAuth2AccessToken);
        String openid = wxMpOAuth2AccessToken.getOpenId();
        String returnUrl = projectUrlConfig.getSell() + "/seller/login";
        return "redirect:" + returnUrl + "?openid=" + openid;
    }



}

