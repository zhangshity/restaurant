package com.zcy.restaurant.utils;

import java.util.Random;

/**
 * @ Author: chunyang.zhang
 * @ Description:    工具：生成随机自增唯一6位主键  = 当前时间 + 随机数
 * @ Date: Created in
 * @ Modified: By:
 */
public class KeyUtil {


    public static  synchronized String genUniqueKey() {//多线程还是会重复，加一个synchronized
        Random random = new Random();
        Integer randomNumber = random.nextInt(900000) + 100000;//随机生成6位数
        return System.currentTimeMillis() + String.valueOf(randomNumber);
    }
}
