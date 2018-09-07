package com.zcy.restaurant.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.net.ssl.SSLContext;

/**
 * @ Author: chunyang.zhang
 * @ Description:     只是用来引用
 *                         微信账号相关配置config
 *                          改动微信mpAppId、mpAppSecret只需要改配置文件
 * @ Date: Created in
 * @ Modified: By:
 */

@Data
@Component
@ConfigurationProperties(prefix = "wechat") //注解前缀。引用application.yaml配置文件里的属性
public class WechatAccountConfig {          //改动微信mpAppId、mpAppSecret只需要改配置文件
    //公众平台id
    private String mpAppId;
    //公众平台秘钥
    private String mpAppSecret;

    //开放平台id
    private String openAppId;
    //开放平台秘钥
    private String openAppSecret;


    /**
     * 商户号
     */
    private String mchId;

    /**
     * 商户密钥
     */
    private String mchKey;

    /**
     * 商户证书路径
     */
    private String keyPath;

    /**
     * 证书内容
     */
    //private SSLContext sslContext;

    //微信支付异步通知地址
    private String notifyUrl;
}
