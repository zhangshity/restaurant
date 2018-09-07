package com.zcy.restaurant.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zcy.restaurant.entity.OrderDetail;
import org.junit.Test;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class JsonUtilTest {

    @Test
    public void toJson() {

        OrderDetail orderDetail = new OrderDetail();

        orderDetail.setDetailId("1234589");
        orderDetail.setOrderId("190221");
        orderDetail.setProductId("193");
        orderDetail.setProductName("大三炸弹");
        orderDetail.setProductPrice(new BigDecimal(21.99));
        orderDetail.setProductQuantity(100);
        orderDetail.setProductIcon("http://asdasdasdaad.jpg");


        //不设置Gsonbuilder的 的基本转换
//        Gson gson = new Gson();
//        String g1 = gson.toJson(orderDetail);
//        System.out.println(g1);


        //=============GsonBuilder的作用(设置板式，然后Gson对象由gsonbuilder创建，
        // 转换的时候就成了setPrettyPriting指定的格式)============

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        String s1 = gson.toJson(orderDetail);

        System.out.println(s1);

    }
}