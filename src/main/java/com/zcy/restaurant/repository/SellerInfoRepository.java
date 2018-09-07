package com.zcy.restaurant.repository;

import com.zcy.restaurant.entity.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ Author: chunyang.zhang
 * @ Description:     分布式  卖家信息DAO
 * @ Date: Created in
 * @ Modified: By:
 */
public interface SellerInfoRepository extends JpaRepository<SellerInfo, String> {

    SellerInfo findByOpenid(String openid);
}
