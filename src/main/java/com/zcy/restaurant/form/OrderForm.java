package com.zcy.restaurant.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @ Author: chunyang.zhang
 * @ Description:   此实体类用于(前端.移动端.微信客户端的)表单验证
 * @ Date:
 * @ Modified: By:
 */
@Data
public class OrderForm {

    //买家姓名
    @NotEmpty(message = "姓名必须填写")
    private String name;

    //买家手机号
    @NotEmpty(message = "手机号不能为空")
    private String phone;

    //买家地址
    @NotEmpty(message = "地址不能为空")
    private String address;

    //买家openid
    @NotEmpty(message = "openid必须填写")
    private String openid;

    //购物车信息
    @NotEmpty(message = "购物车不能为空")
    private String items;
}
