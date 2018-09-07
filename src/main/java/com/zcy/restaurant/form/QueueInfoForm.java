package com.zcy.restaurant.form;

import lombok.Data;

import java.util.Date;

/**
 * @ Author: chunyang.zhang
 * @ Description:          页面传来创建的队列信息 验证表单
 * @ Date: Created in
 * @ Modified: By:
 */
@Data
public class QueueInfoForm {
    //排队单Id
//    private String queueId;





    //表单传入的买家手机号码
    private String buyerPhone;

    //表单传入的人数
    private Integer peopleQuantity;







    //排队者的队列号码
//    private String queueNumber;

    //队列号码的状态
//    private Integer numberStatus;

    //队列号码的创建时间(取号时间)
//    private Date createTime;
}
