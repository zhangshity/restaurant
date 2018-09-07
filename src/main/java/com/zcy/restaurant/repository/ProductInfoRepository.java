package com.zcy.restaurant.repository;

import com.zcy.restaurant.entity.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @ Author: chunyang.zhang
 * @ Description:  商品信息   的 DAO 层接口
 * @ Date: Created in 14:40 2018/5/17
 * @ Modified: By:
 */
//JpaRepository<数据库表映射对象,主键类型>     (详细ctrl单击查看)
public interface ProductInfoRepository extends JpaRepository<ProductInfo,String> {

    //通过商品状态查询商品信息
    List<ProductInfo> findByProductStatus(Integer productStatus);
}
