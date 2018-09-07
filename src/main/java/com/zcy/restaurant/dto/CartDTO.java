package com.zcy.restaurant.dto;

import lombok.Data;
/**
 * @ Author: chunyang.zhang
 * @ Description: 前台购物车，DTO
 * @ Date: Created in 】
 * @ Modified: By:
 *              与orderDTO一样。 此为接收的前端 json 购物车数据的  数据传输对象(DTO)
 *                      前端 购物车 只有两个属性，
 *                                     即 商品Id(orderId)、商品数量(orderQuantity)
 *
 *                                     可以理解为前端的 实际 附表
 *
 */
@Data
public class CartDTO {

    //商品id
    private  String productId;

    //商品数量
    private Integer productQuantity;

    public CartDTO() {
    }

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
