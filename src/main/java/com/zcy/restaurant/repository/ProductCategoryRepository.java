package com.zcy.restaurant.repository;

import com.zcy.restaurant.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @ Author: chunyang.zhang
 * @ Description:  类目(商品的种类)的 DAO 层 接口
 * @ Date: Created in 21:51 2018/5/16
 * @ Modified: By:
 */
//JpaRepository<数据库表映射对象,主键类型>     (详细ctrl单击查看)
public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {

    //方法的命名一定要正确，遵循Hibernate的规范
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
