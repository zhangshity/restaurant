package com.zcy.restaurant.enums;

import lombok.Getter;

/**
 * @ Author: chunyang.zhang
 * @ Description:    餐桌状态枚举
 * @ Date: Created in
 * @ Modified: By:
 */
@Getter
public enum EnvironmentStatusEnum {


    NOT_USED(0,"无人使用"),

    IN_USE(1,"正在使用中"),

    ABANDON(2,"废弃,不再使用"),
    ;

    private Integer code;

    private String message;

    EnvironmentStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


}
