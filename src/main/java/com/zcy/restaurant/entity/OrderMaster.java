package com.zcy.restaurant.entity;


import com.zcy.restaurant.enums.OrderStatusEnum;
import com.zcy.restaurant.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @ Author: chunyang.zhang
 * @ Description: 订单 主表（映射POJO类）
 * @ Date: Created in 21:34
 * @ Modified: By:
 */

@Entity
@Data
@DynamicUpdate
public class OrderMaster {

    @Id
    //订单的Id
    private String orderId;

    //买家姓名
    private String buyerName;

    //买家手机号
    private String buyerPhone;

    //买家地址
    private String buyerAddress;

    //买家Openid
    private String buyerOpenid;

    //订单总金额
    private BigDecimal orderAmount;

    //订单状态(默认新下单，0，枚举表示)
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();

    //支付状态(默认未支付，0，枚举表示)
    private Integer payStatus = PayStatusEnum.WAIT.getCode();

    //创建时间
    private Date createTime;

    //更新时间
    private Date updateTime;

//    //订单细节 (因为一个订单有多个细节，即为了方便对应
//    @Transient //不会因为不能跟数据库表没法对应而出错
//    private List<OrderDetail> orderDetailList;
}
