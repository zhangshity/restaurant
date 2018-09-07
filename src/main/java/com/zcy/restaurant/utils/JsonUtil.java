package com.zcy.restaurant.utils;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @ Author: chunyang.zhang
 * @ Description: Json格式转换工具
 * @ Date: Created in
 * @ Modified: By:
 */
public class JsonUtil {

    public  static String toJson(Object object){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        return gson.toJson(object);
    }
}
