package com.zcy.restaurant.config;

import com.lly835.bestpay.config.WxPayH5Config;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @ Author: chunyang.zhang
 * @ Description:       微信支付配置
 * @ Date: Created in
 * @ Modified: By:
 */
@Component
public class  WechatPayConfig {

    @Autowired
    private WechatAccountConfig wechatAccountConfig;

    @Bean
    public BestPayServiceImpl bestPayService() {

        BestPayServiceImpl bestPayServiceImpl = new BestPayServiceImpl();
        bestPayServiceImpl.setWxPayH5Config(wxPayH5Config());

        return bestPayServiceImpl;
    }

    @Bean
    public WxPayH5Config wxPayH5Config() {

        WxPayH5Config wxPayH5Config = new WxPayH5Config();

        wxPayH5Config.setAppId(wechatAccountConfig.getMpAppId());
        wxPayH5Config.setAppSecret(wechatAccountConfig.getMpAppSecret());
        wxPayH5Config.setMchId(wechatAccountConfig.getMchId());
        wxPayH5Config.setMchKey(wechatAccountConfig.getMchKey());
        wxPayH5Config.setKeyPath(wechatAccountConfig.getKeyPath());
        wxPayH5Config.setNotifyUrl(wechatAccountConfig.getNotifyUrl());

        return wxPayH5Config;
    }

}
