package com.zcy.restaurant.utils;

/**
 * @ Author: chunyang.zhang
 * @ Description:           订单金额核算，小数点差异
 * @ Date: Created in
 * @ Modified: By:          用于微信支付 订单金额核算部分
 */
public class MathVerifyUtil {

    //订单金额 精度的范围
    public static final Double MONEY_RANGE = 0.01;

    //比较两个Double金额是否相等
    public static Boolean equals(Double d1, Double d2) {
        Double result = Math.abs(d1 - d2);
        if (result < MONEY_RANGE) {
            return true;
        } else {
            return false;
        }
    }

}
