package com.zcy.restaurant.form;

import lombok.Data;

/**
 * @ Author: chunyang.zhang
 * @ Description:        （后台)餐桌信息 表单验证
 * @ Date: Created in
 */
@Data
public class EnvironmentForm {

    //环境Id(桌号)
    private Integer environmentId;

    //餐桌名称
    private String environmentName;

    //餐桌人数
    private Integer peopleQuantity;

    //餐桌状态
    private Integer environmentStatus;

    //餐桌描述
    private String environmentDescription;
}
