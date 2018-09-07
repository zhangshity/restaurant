package com.zcy.restaurant.enums;

/**
 * @ Author: chunyang.zhang
 * @ Description:  返回给前段的错误提示
 * @ Date: Created in 15:22
 * @ Modified: By:
 * <p>
 * <p>
 * 实际为异常的 枚举类型~ 考虑换个名字
 */
public enum ExceptionEnum {

    SUCCESS(0, "成功"),
    PARAM_EORROR(1, "参数不正确"),
    PRODUCT_NOT_EXIST(10, "商品不存在"),
    PRODUCT_STOCK_ERROR(11, "商品库存不正确"),
    ORDER_NOT_EXIST(12, "订单不存在"),
    ORDERDETAIL_NOT_EXIST(13, "订单详情不存在"),
    ORDER_STATUS_ERROR(14, "订单状态不正确"),
    ORDER_UPDATE_FAIL(15, "订单更新失败"),
    ORDER_CANCEL_FAIL(16, "订单取消失败"),
    ORDER_DETAIL_EMPTY(17, "订单详情为空"),
    ORDER_FINISH_FAIL(18, "订单完成失败"),
    PAY_STATUS_ERROR(19, "支付状态不正确"),
    CART_EMPTY(20, "购物车为空"),
    ORDER_OWNER_ERROR(21, "该订单不属于当前用户"),
    WX_MP_ERROR(22, "微信公众号授权错误"),
    WX_PAY_NOTIFY_MONEY_VERIFY_ERROR(33, "微信支付异步通知金额校验错误"),
    ENVIRONMENT_NOT_EXIST(23, "餐桌环境不存在"),
    ENVIRONMENT_INCREASE_FAIL(24, "餐桌环境新增失败"),
    ENVIRONMENT_ALTER_STATUS_SUCCESS(34, "修改环境状态成功"),
    ENVIRONMENT_STATUS_ERROR(35, "环境状态错误"),
    //======订单状态(orderStatus)=============
    ORDER_CANCEL_SUCCESS(25, "订单取消成功"),
    OEDER_FINISH_SUCCESS(26, "订单完结成功"),
    //======商品状态(productStatus)============
    PRODUCT_STATUS_ERROR(27, "商品状态不正确"),
    PRODUCT_ON_SALE_FAIL(28, "商品上架失败"),
    PRODUCT_ON_SALE_SUCCESS(29, "商品上架成功"),
    PRODUCT_OFF_SALE_FAIL(30, "商品下架失败"),
    PRODUCT_OFF_SALE_SUCCESS(31, "商品下架成功"),
    //===
    LOGIN_FAIL(32, "登录失败,登录信息不正确"),
    LOGOUT_SUCCESS(33, "登出成功"),
    QUEUE_NOT_EXIST(36, "队列信息不存在"),
    STILL_HAVE_TABLE(37, "还有空闲餐桌,取号失败,请进店用餐"),
    EXIST_QUEUENUMBER(38,"重复排号");


    private Integer code;
    private String message;

    ExceptionEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

