package com.zcy.restaurant.form;

import lombok.Data;

/**
 * @ Author: chunyang.zhang
 * @ Description:          存放(后台卖家端)传来的表单信息,用于表单信息的处理
 * @ Date: Created in
 * @ Modified: By:
 */
@Data
public class CategoryForm {

    /** 类目id. */
    private Integer categoryId;

    /** 类目名字. */
    private String categoryName;

    /** 类目编号 */
    private Integer categoryType;
}
