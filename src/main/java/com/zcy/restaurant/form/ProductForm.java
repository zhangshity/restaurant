package com.zcy.restaurant.form;

import lombok.Data;

import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @ Author: chunyang.zhang
 * @ Description:       存放(后台卖家端)传来的表单信息,用于表单信息的处理
 * @ Date: Created in
 * @ Modified: By:
 */
@Data
public class ProductForm {

    /** 商品id. */
    private String productId;  //表单没有，不用删，并不影响

    /** 商品名称. */
    private  String productName;

    /** 商品价格. */
    private BigDecimal productPrice;

    /** 商品库存. */
    private Integer productStock;

    /** 商品描述. */
    private String productDescription;

    /** 商品图片. */
    private  String productIcon;

    /** 商品状态. */
    //private Integer productStatus; //表单没有，需要删，否则传值出错

    /** 类目编号.*/
    private Integer categoryType;
}
