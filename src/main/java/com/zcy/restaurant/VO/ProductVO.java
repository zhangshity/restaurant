package com.zcy.restaurant.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @ Author: chunyang.zhang
 * @ Description:  商品类目  视图对象 VO(只包含部分，不是全部，还包含部分的商品(菜品)信息）
 * @ Date: Created in 11:56 2018/5/18
 * @ Modified: By:
 */
@Data
public class ProductVO {

    @JsonProperty("name") //注解：返回给前台以这名字
    private String categoryName; //不用name，容易混淆，依靠注解区别返回

    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods") //对象型数据，存在另一个视图对象 ProductInfoVO 中
    private List<ProductInfoVO> productInfoVOList;

}
