package com.zcy.restaurant.repository;

import com.zcy.restaurant.entity.EnvironmentInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @ Author: chunyang.zhang
 * @ Description:          餐桌环境DAO
 * @ Date: Created in
 * @ Modified: By:
 */
public interface EnvironmentInfoRepository extends JpaRepository<EnvironmentInfo,Integer> {

    //通过人数查找餐桌信息
    List<EnvironmentInfo> findByPeopleQuantity(Integer peopleQuantity);

    //通过餐桌状态查找餐桌
    List<EnvironmentInfo> findByEnvironmentStatus(Integer environmentStatus);

    //通过(人数)和(餐桌状态)查找餐桌信息
    List<EnvironmentInfo> findByPeopleQuantityAndAndEnvironmentStatus(Integer peopleQuantity, Integer environmentStatus);

}
