package com.zcy.restaurant.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @ Author: chunyang.zhang
 * @ Description: 订单详情表  数据库映射实体类
 * @ Date: Created in 10:49
 * @ Modified: By:
 */
@Entity
@Data
public class OrderDetail {

    //细节Id
    @Id
    private String detailId;

    //订单Id
    private String orderId;

    //商品Id
    private String productId;

    //商品名称
    private String productName;

    //商品价格
    private BigDecimal productPrice;

    //商品数量
    private  Integer productQuantity;

    //商品图片
    private  String productIcon;
}
