package com.zcy.restaurant.service.impl;

import com.zcy.restaurant.entity.SellerInfo;
import com.zcy.restaurant.repository.SellerInfoRepository;
import com.zcy.restaurant.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ Author: chunyang.zhang
 * @ Description:       实现类
 * @ Date: Created in
 * @ Modified: By:
 */
@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerInfoRepository sellerInfoRepository;

    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {
        SellerInfo sellerInfo = sellerInfoRepository.findByOpenid(openid);
        return sellerInfo;
    }
}
