package com.zcy.restaurant.enums;

/**
 * @ Author: chunyang.zhang
 * @ Description:  商品状态的 枚举 (ProductStatus)
 * @ Date: Created in 09:40 2018/5/18
 * @ Modified: By:
 */
public enum ProductStatusEnum implements CodeEnum {
    UP(0, "商品上架ing..."),
    DOWN(1, "商品下架ed...");

    private Integer code;

    private String message;

    ProductStatusEnum(Integer code, String message) {
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
