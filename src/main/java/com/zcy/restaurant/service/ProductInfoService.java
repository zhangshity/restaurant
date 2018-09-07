package com.zcy.restaurant.service;

import com.zcy.restaurant.dto.CartDTO;
import com.zcy.restaurant.entity.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @ Author: chunyang.zhang
 * @ Description:  商品(商品信息)  service层 接口
 * @ Date: Created in
 * @ Modified: By:
 */
//接口中的方法默认都是public、abstract的
public interface ProductInfoService {

    //通过Id查找一个商品
    ProductInfo findOne(String productId);

    //查找上架的商品(买家)
    List<ProductInfo> findUpAll();

    //查找所有商品(管理员)
    Page<ProductInfo> findAll(Pageable pageable);//Pageable实现分页

    //插入、更新
    ProductInfo save(ProductInfo productInfo);

    //加库存
    void increaseStock(List<CartDTO> cartDTOList);

    //减库存
    void decreaseStock(List<CartDTO> cartDTOList);

    //上架
    ProductInfo onSale(String productId);

    //下架
    ProductInfo offSale(String productId);
}
