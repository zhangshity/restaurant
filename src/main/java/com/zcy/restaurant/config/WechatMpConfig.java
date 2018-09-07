package com.zcy.restaurant.config;


import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @ Author: chunyang.zhang
 * @ Description:      微信公众号 config
 * @ Date: Created in
 * @ Modified: By:
 */
@Component
public class WechatMpConfig {

    //引用微信账号配置类里的属性(从yaml中加载) WechatAccountConfig
    @Autowired
    private WechatAccountConfig wechatAccountConfig;

    @Bean
    public WxMpService wxMpService() {
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxMpConfigStorage());
        return wxMpService;
    }

    //WxMpConfigStorage 是 WxMpService 的配置类
    //类似Page 的 pageable  分别实现类 PageImpl 和 PageRequest
    //WxMpService 实现类 WxMpServiceImpl,
    //WxMpConfigStorage 实现类 WxMpInMemoryConfigStorage

    //调用WxMpService 的 vx
    @Bean
    public WxMpConfigStorage wxMpConfigStorage() {
        WxMpInMemoryConfigStorage wxMpConfigStorage = new WxMpInMemoryConfigStorage();
        wxMpConfigStorage.setAppId(wechatAccountConfig.getMpAppId());
        wxMpConfigStorage.setSecret(wechatAccountConfig.getMpAppSecret());
        return wxMpConfigStorage;
    }
}
