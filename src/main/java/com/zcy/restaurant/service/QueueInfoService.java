package com.zcy.restaurant.service;


import com.zcy.restaurant.entity.QueueInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @ Author: chunyang.zhang
 * @ Description:    排号  Service接口
 * @ Date: Created in
 * @ Modified: By:
 */
//接口中的方法默认都是public、abstract的
public interface QueueInfoService {

    //生成一个队列号码
    QueueInfo createQueue(String buyerPhone, Integer peopleQuantity);

    //修改队列状态(号码过期)
    QueueInfo queueTimeOut(String queueId);

    //通过手机查找队列号(卖家、买家)
    List<QueueInfo> findByBuyerPhone(String buyerPhone);

    //查找全部队列信息(卖家)
    Page<QueueInfo> findAll(Pageable pageable);

    //判断是否重复取号
    boolean isUniqueEmptyNumber(String buyerPhone);
}
