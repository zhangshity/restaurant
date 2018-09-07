package com.zcy.restaurant.utils;

import com.zcy.restaurant.enums.CodeEnum;

/**
 * @ Author: chunyang.zhang
 * @ Description:           枚举工具类(通过code的到枚举)
 * @ Date: Created in
 * @ Modified: By:
 */
public class EnumUtil {

    //泛型返回类型 getByCode(int code , 泛型类)
    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass) {
        for (T each : enumClass.getEnumConstants()) {
            if (code.equals(each.getCode())) {
                return each;
            }
        }
        return null;
    }
}
