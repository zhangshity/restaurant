package com.zcy.restaurant.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @ Author: chunyang.zhang
 * @ Description:      分布式 卖家信息类
 * @ Date: Created in
 * @ Modified: By:
 */
@Entity
@Data
public class SellerInfo {

    @Id
    private String sellerId;

    private String username;

    private String password;

    private String openid;
}
