package com.zcy.restaurant.entity;

import com.zcy.restaurant.enums.EnvironmentStatusEnum;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @ Author: chunyang.zhang
 * @ Description:       餐桌环境信息
 * @ Date: Created in
 * @ Modified: By:
 */
@Entity
@Data
public class EnvironmentInfo {

    //环境Id(桌号)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer environmentId;

    //餐桌名称
    private String environmentName = "普通餐桌";

    //餐桌人数
    private Integer peopleQuantity;

    //餐桌状态(默认 0 ,无人使用)(暂时不用，单靠程序控制有没有人不现实)
    private Integer environmentStatus = EnvironmentStatusEnum.NOT_USED.getCode();

    //餐桌描述
    private String environmentDescription = "暂无描述";
}
