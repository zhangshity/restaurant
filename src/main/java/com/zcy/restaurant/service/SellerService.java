package com.zcy.restaurant.service;

import com.zcy.restaurant.entity.SellerInfo;

/**
 * @ Author: chunyang.zhang
 * @ Description:       卖家端 service
 * @ Date: Created in
 * @ Modified: By:
 */
//接口中的方法默认都是public、abstract的
public interface SellerService {

    //通过openid查询卖家信息
    SellerInfo findSellerInfoByOpenid(String openid);
}
