package com.zcy.restaurant.enums;

import lombok.Getter;

/**
 * @ Author: chunyang.zhang
 * @ Description:  支付状态 枚举
 * @ Date: Created in 22:01 2018/5/18
 * @ Modified: By:
 */
//lombok注解
@Getter
public enum PayStatusEnum implements CodeEnum {

    WAIT(0, "等待支付"),
    SUCCESS(1, "支付成功");

    private Integer code;

    private String message;

    PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
