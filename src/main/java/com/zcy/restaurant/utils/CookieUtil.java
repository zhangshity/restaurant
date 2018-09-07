package com.zcy.restaurant.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @ Author: chunyang.zhang
 * @ Description:
 * @ Date: Created in
 * @ Modified: By:
 */
public class CookieUtil {


    //设置cookie
    public static void set(HttpServletResponse response, //服务器response
                           String name,             //cookie名称
                           String value,            //cookie值
                           int maxAge) {            //过期时间
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);

    }

    //得到cookid值
    public static Cookie get(HttpServletRequest request,
                             String name) {
        Map<String, Cookie> cookieMap = readCookieMap(request);
        if (cookieMap.containsKey(name)) {
            return cookieMap.get(name);
        } else {
            return null;
        }
    }

    //获取cookie数组遍历的私有方法，仅供上面get调用(原理：将cookie封装成map)
    private static Map<String, Cookie> readCookieMap(HttpServletRequest request) {
        Map<String, Cookie> cookieMap = new HashMap<>();

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        return cookieMap;
    }

}
