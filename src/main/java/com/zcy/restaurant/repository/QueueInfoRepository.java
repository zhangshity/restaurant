package com.zcy.restaurant.repository;


import com.zcy.restaurant.entity.QueueInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * @ Author: chunyang.zhang
 * @ Description:      排号持久层
 * @ Date: Created in
 * @ Modified: By:
 */
//泛型<数据库映射的实体类,主键>
public interface QueueInfoRepository extends JpaRepository<QueueInfo,String> {

    //通过买家手机查找，可以允许一个手机多次排号
    List<QueueInfo> findByBuyerPhone(String buyerPhone);

    //手机号码和状态 查找(判断是否重复取号用)
    List<QueueInfo> findByBuyerPhoneAndNumberStatus(String buyerPhone, Integer numberStatus);
}
