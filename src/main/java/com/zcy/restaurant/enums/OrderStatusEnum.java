package com.zcy.restaurant.enums;


/**
 * @ Author: chunyang.zhang
 * @ Description:  订单状态码
 * @ Date: Created in 21:43 2018/5/18
 * @ Modified: By:
 */
public enum OrderStatusEnum implements CodeEnum {
    NEW(0, "新订单"),

    FINISH(1, "已完成"),

    CANCEL(2, "已取消");

    private Integer code;

    private String message;

    OrderStatusEnum(Integer code, String message) {
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


