package com.zcy.restaurant.service;

import com.zcy.restaurant.entity.ProductCategory;

import java.util.List;

/**
 * @ Author: chunyang.zhang
 * @ Description: 类目(商品的种类) 的service 层 接口
 * @ Date: Created in
 * @ Modified: By:
 */
//接口中的方法默认都是public、abstract的
public interface ProductCategoryService {

    //通过Id查询一个类目(后台管理)
    ProductCategory findOne(Integer categoryId);

    //查询所有类目(后台管理)
    List<ProductCategory> findAll();

    //通过CategoryType查找类目(买家)
    List<ProductCategory> findByCategoryType(List<Integer> categoryTypeList);

    //保存
    ProductCategory save(ProductCategory productCategory);

}
