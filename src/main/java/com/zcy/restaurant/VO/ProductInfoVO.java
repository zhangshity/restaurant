package com.zcy.restaurant.VO;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @ Author: chunyang.zhang
 * @ Description:  产品信息  试图对象VO (只包含部分) ，（ProductVO也要引用此对象）
 * @ Date: Created in 12:22 2018/5/18
 * @ Modified: By:
 */
@Data
public class ProductInfoVO {


    @JsonProperty("id")
    private  String productId;

    @JsonProperty("name")
    private String productName;

    @JsonProperty("price")
    private BigDecimal productPrice;

    @JsonProperty("desciption")
    private String productDescription;

    @JsonProperty("icon")
    private String productIcon;
}
