package com.zcy.restaurant.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zcy.restaurant.entity.OrderDetail;
import com.zcy.restaurant.enums.OrderStatusEnum;
import com.zcy.restaurant.enums.PayStatusEnum;
import com.zcy.restaurant.utils.EnumUtil;
import com.zcy.restaurant.utils.serializer.DateToLongSerializer;
import lombok.Data;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
/**
 * @ Author: chunyang.zhang
 * @ Description:  订单数据传输对象 （可以理解为真正意义上的订单表对象）
 *                  这才是 现实中使用的 订单表对象
 *                             因为订单主表包含好几个订单细节表(细节表里有商品)
 *
 *                             前端传来的json对象就是由此DTO接收处理
 * @ Date: Created in
 * @ Modified: By:  zcy
 */
@Data
//@JsonInclude(JsonInclude.Include.NON_NULL) //不往前端返回值为 null 字段,方便前端处理
public class OrderDTO {

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

    //订单状态
    private Integer orderStatus;

    //支付状态
    private Integer payStatus;

    //创建时间
    @JsonSerialize(using = DateToLongSerializer.class)
    private Date createTime;

    //更新时间
    @JsonSerialize(using = DateToLongSerializer.class)
    private Date updateTime;

    //附表
    //订单细节 (因为一个订单有多个细节，即为了方便对应
    //@Transient //不会因为不能跟数据库表没法对应而出错
    List<OrderDetail> orderDetailList;

    @JsonIgnore//RESTful 对象转换Json忽略此方法
    //方法(订单状态)：通过枚举code得到对应文字说明
    public OrderStatusEnum getOrderStatusEnum() {
        return EnumUtil.getByCode(orderStatus, OrderStatusEnum.class);
    }

    @JsonIgnore//RESTful 对象转换Json忽略此方法
    //方法(支付状态)：通过枚举code得到对应文字说明
    public PayStatusEnum getPayStatusEnum() {
        return EnumUtil.getByCode(payStatus,PayStatusEnum.class);
    }

}
