package com.zcy.restaurant.constant;
/**
 * @ Author: chunyang.zhang
 * @ Description:           Redis常量
 * @ Date: Created in
 * @ Modified: By:
 */
public interface RedisConstant {

    String TOKEN_PREFIX = "token_%s";

    Integer EXPIRE = 7200;  //过期时间2小时(7200秒)

}
