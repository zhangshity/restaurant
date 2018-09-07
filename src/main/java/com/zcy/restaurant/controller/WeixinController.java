package com.zcy.restaurant.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @ Author: chunyang.zhang
 * @ Description:     微信支付网页授权 请求
 * @ Date: Created in
 * @ Modified: By:              完全按照微信官网文档开发。
 *
 *
 *
 * @                                     引入第三方微信SDK后废弃。当做测试
 * @                                     方便理解SDK的原理
 *
 *
 *
 * @ 先在浏览器键入下面的微信授权地址模版，并传入参数
 * @ https://open.weixin.qq.com/connect/oauth2/authorize?
 * @                                                     appid=%s
 * @                                                    &redirect_uri=%s
 * @                                                    &response_type=code
 * @                                                    &scope=%s
 * @                                                    &state=%s#wechat_redirect
 * @
 * @       返回格式: redirect_uri?code=CODE&state=STATE
 *
 *
 *
 *
 *      实际操作：https://open.weixin.qq.com/connect/oauth2/authorize?
 *                                                       appid=wx73e40c28bc65456c
 *                                                      &redirect_uri=http://zhangchunyang.natapp1.cc/weixin/auth
 *                                                      &response_type=code
 *                                                      &scope=snsapi_base
 *                                                      &state=STATE#wechat_redirect
 *
 *     返回 http://zhangchunyang.natapp1.cc/weixin/auth?code=asd83h95asd12&state=STATE
 *     即下面的方法 带个参数->
 *
 */
@RestController
@RequestMapping("/weixin")
@Slf4j
public class WeixinController {

    @GetMapping("/auth")
    public void auth(@RequestParam("code") String code) {
        log.info("进入auth方法。。。。。。。。");
        log.info("code={}",code);
        //第二步,code换取access_token (打开微信官方第二个网址输入参数appid,code,grant_type)
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?" +
                "appid=wx73e40c28bc65456c" +
                "&secret=6c380b1b256337edb289d7b960e6a8ef" +
                "&code=" + code +
                "&grant_type=authorization_code";
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);
        log.info("response={}",response);
    }
}
