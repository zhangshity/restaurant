package com.zcy.restaurant.service;

import com.zcy.restaurant.entity.EnvironmentInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @ Author: chunyang.zhang
 * @ Description:   EnvironmentInfo  Service接口
 * @ Date: Created in
 * @ Modified: By:
 */
//接口中的方法默认都是public、abstract的
public interface EnvironmentInfoService {

    //查找全部餐桌信息(卖家管理)
    Page<EnvironmentInfo> findAll(Pageable pageable);

    //(废弃)查找全部餐桌信息(修改餐桌信息下拉框使用)
    List<EnvironmentInfo> findAll();

    //查找一个餐桌信息
    EnvironmentInfo findOne(Integer environmentId);

    //通过人数查找餐桌信息(卖家管理)
    List<EnvironmentInfo> findByPeople(Integer peopleQuantity);

    //通过餐桌状态查找(卖家管理)
    List<EnvironmentInfo> findByTableStatus(Integer environmentStatus);

    //通过人数和餐桌状态查找(排号)
    List<EnvironmentInfo> findByPeopleAndStatus(Integer peopleQuantity, Integer environmentStatus);

    //增加修改环境信息(卖家)
    EnvironmentInfo save(EnvironmentInfo environmentInfo);


    //占用餐桌
    EnvironmentInfo inUse(Integer environmentId);

    //空闲出餐桌
    EnvironmentInfo notUsed(Integer environmentId);

    //废弃餐桌
    EnvironmentInfo abadon(Integer environmentId);


}
