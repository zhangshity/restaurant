package com.zcy.restaurant.service.impl;

import com.zcy.restaurant.entity.ProductCategory;
import com.zcy.restaurant.repository.ProductCategoryRepository;
import com.zcy.restaurant.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ Author: chunyang.zhang
 * @ Description:  类目(商品种类) service层的 实现类
 * @ Date: Created in
 * @ Modified: By:
 */

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    //ID查找类目
    @Override
    public ProductCategory findOne(Integer categoryId) {
        return productCategoryRepository.findById(categoryId).get();
    }

    //查找全部类目
    @Override
    public List<ProductCategory> findAll() {
        return productCategoryRepository.findAll();
    }

    //类型编号查找类目
    @Override
    public List<ProductCategory> findByCategoryType(List<Integer> categoryTypeList) {
        return productCategoryRepository.findByCategoryTypeIn(categoryTypeList);
    }


    //插入，更新 类目 service实现类
    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return productCategoryRepository.save(productCategory);
    }
}
