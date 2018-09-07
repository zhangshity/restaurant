package com.zcy.restaurant.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zcy.restaurant.enums.ProductStatusEnum;
import com.zcy.restaurant.utils.EnumUtil;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ Author: chunyang.zhang
 * @ Description: 商品(商品信息)     的  POJO  实体类
 * @ Date: Created in 14:01
 * @ Modified: By:
 */
@Entity
@Data
@DynamicUpdate
public class ProductInfo {

    /** 商品id. */
    @Id
    private String productId;

    /** 商品名称. */
    private  String productName;

    /** 商品价格. */
    //注意类型要和数据库的decimal对应，用bigdeciaml
    private BigDecimal productPrice;

    /** 商品库存. */
    private Integer productStock;

    /** 商品描述. */
    private String productDescription;

    /** 商品图片. */
    private  String productIcon;

    /** 商品状态. */
    //0正常1下架
    private Integer productStatus = ProductStatusEnum.UP.getCode();

    /** 类目编号.*/
    private Integer categoryType;

    //创建时间
    private Date createTime;

    //修改时间
    private Date updateTime;

    @JsonIgnore
    public ProductStatusEnum getProductStatusEnum(){
        return EnumUtil.getByCode(productStatus, ProductStatusEnum.class);
    }
}
