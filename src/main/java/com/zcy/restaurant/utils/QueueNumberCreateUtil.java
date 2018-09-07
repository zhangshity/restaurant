package com.zcy.restaurant.utils;

import com.zcy.restaurant.repository.QueueInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @ Author: chunyang.zhang
 * @ Description:           生成随机自增队列号
 * @ Date: Created in
 * @ Modified: By:          直接拿系统今天的时间当做号码，精确到秒，人工速度不可能重复
 * @ 因为前面标有日期，所以也可以判定是哪天的，且编号唯一
 * 2018年6月5更改优化。废弃以上描述，更改如下：
 * 根据餐桌的人数进行队列的生成，A B C 三种队列。并记录其中的
 */
public class QueueNumberCreateUtil {

    //队列号码总数量(用于队列号码的生成,有人排队就+1,过了24:00就清零)
    private static Integer numberCount = 0;

    //每中餐桌的排队量,用于判断多少人等待(有人排+1,有人走-1)
    //4人桌(小桌)数量
    public static Integer Acount = 0;
    //8人桌(中桌)数量
    public static Integer Bcount = 0;
    //16人(大桌包厢)数量
    public static Integer Ccount = 0;

    //时间清零(config定时器配置调用）
    public static void clearTime() {
        Acount = 0;
        Bcount = 0;
        Ccount = 0;
    }


    //简单的队列号码生成工具
    public static String simpleQueueNumberFormat(Integer peopleQuantity) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(" yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String systemDate = simpleDateFormat.format(date);

        String queueNumber = null;
        if (peopleQuantity == 4) {
            numberCount += 1;
            Acount += 1;
            queueNumber = "A" + String.valueOf(numberCount);

        } else if (peopleQuantity == 8) {
            numberCount += 1;
            Bcount += 1;
            queueNumber = "B" + String.valueOf(numberCount);
        } else if (peopleQuantity == 16) {
            numberCount += 1;
            Ccount += 1;
            queueNumber = "C" + String.valueOf(numberCount);
        }

        return (queueNumber == null) ? "error!queueNumber is null" : systemDate + " 号码: " + queueNumber;
    }




    //唯一的时间队列号码                            //简单方案排队号码生成方案,其他方案失败就定这个
    public static String uniqueTimeQueueNumber() {
        //生成随机6位数
        //Random random = new Random();
        //Integer randomNumber = random.nextInt(9000) + 1000;//随机生成4位数
        //String randomNumberString = String.valueOf(randomNumber);
        //系统当前时间
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(" yyyy-MM-dd HHmmss");
        Date date = new Date();
        String systemDate = simpleDateFormat.format(date);
        //return System.currentTimeMillis() + String.valueOf(randomNumber);
        return systemDate;
    }
}
