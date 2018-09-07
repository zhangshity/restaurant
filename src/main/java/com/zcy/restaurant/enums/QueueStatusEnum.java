package com.zcy.restaurant.enums;


import lombok.Getter;

/**
 * @ Author: chunyang.zhang
 * @ Description:     排队 等待状态的枚举
 * @ Date: Created in
 * @ Modified: By:
 */
@Getter
public enum QueueStatusEnum {

    WAITING(0, "排号等待中..."),
    TIMEOUT(1, "排队号码过期...");

    private Integer code;

    private String message;

    QueueStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
