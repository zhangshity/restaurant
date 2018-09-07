package com.zcy.restaurant.entity;

import com.zcy.restaurant.enums.QueueStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * @ Author: chunyang.zhang
 * @ Description:        排号实体类
 * @ Date: Created in
 * @ Modified: By:
 */

@Entity
@DynamicUpdate
@Data
public class QueueInfo {

    //排队单Id
    @Id
    private String queueId;

    //买家手机
    private String buyerPhone;

    //排队者的队列号码
    private String queueNumber;

    //队列号码的状态(默认状态是:正在等待...   0 )
    private Integer numberStatus = QueueStatusEnum.WAITING.getCode();

    //队列号码的创建时间(取号时间)
    private Date createTime;
}
