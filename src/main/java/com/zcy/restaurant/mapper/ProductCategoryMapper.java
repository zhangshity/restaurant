package com.zcy.restaurant.mapper;

import org.apache.ibatis.annotations.Insert;

import java.util.Map;

/**
 * @ Author: chunyang.zhang
 * @ Description:           mybatis的方式 dao
 * @ Date: Created in
 * @ Modified: By:
 */
public interface ProductCategoryMapper {

    @Insert("insert into product_category(category_name,category_type) values(#{category_name,jdbcType=VARCHAR},#{category_type,jdbcType=INTEGER})")
    int insertByMap(Map<String, Object> map);

}
