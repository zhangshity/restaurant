package com.zcy.restaurant.service.impl;

import com.zcy.restaurant.dto.CartDTO;
import com.zcy.restaurant.entity.ProductInfo;
import com.zcy.restaurant.enums.ExceptionEnum;
import com.zcy.restaurant.enums.ProductStatusEnum;
import com.zcy.restaurant.exception.SellException;
import com.zcy.restaurant.repository.ProductInfoRepository;
import com.zcy.restaurant.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Override
    public ProductInfo findOne(String productId) {
        return productInfoRepository.findById(productId).orElse(null);
    }


    //查询上架ed商品（使用Product枚举列出，状态码，省记忆）
    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    //(商家)才需要查询 所有商品 (pageable)
    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return productInfoRepository.findAll(pageable);
    }

    //插入、更新商品信息
    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return productInfoRepository.save(productInfo);
    }


    //增加库存
    @Override
    @Transactional
    public void increaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            //从数据库中取出商品信息(通过商品编号)
            ProductInfo productInfo = productInfoRepository.findById(cartDTO.getProductId()).get();
            //如果库存中没有这个商品 抛异常
            if (productInfo == null) {
                throw new SellException(ExceptionEnum.PRODUCT_NOT_EXIST);
            }
            //加库存  = 数据库商品表 + 购物车里商品的数量
            Integer result = productInfo.getProductStock() + cartDTO.getProductQuantity();
            productInfo.setProductStock(result);
            productInfoRepository.save(productInfo);
        }

    }


    //减库存
    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            //从DB中取出 productInfo (通过productId)
            ProductInfo productInfo = productInfoRepository.findById(cartDTO.getProductId()).get();
            //如果库存中没有这个商品 抛异常
            if (productInfo == null) {
                throw new SellException(ExceptionEnum.PRODUCT_NOT_EXIST);
            }
            //减库。现库存 = 数据库商品表 - 购物车里的表
            Integer result = productInfo.getProductStock() - cartDTO.getProductQuantity();
            if (result < 0) {
                throw new SellException(ExceptionEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(result);
            productInfoRepository.save(productInfo);
        }
    }


    //商品上架
    @Override
    public ProductInfo onSale(String productId) {
        ProductInfo productInfo = productInfoRepository.findById(productId).orElse(null);
        if (productInfo == null) {
            throw new SellException(ExceptionEnum.PRODUCT_NOT_EXIST);
        }
        if (productInfo.getProductStatusEnum() == ProductStatusEnum.UP) {
            throw new SellException(ExceptionEnum.PRODUCT_STATUS_ERROR);
        }
        productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
        ProductInfo updateResult = productInfoRepository.save(productInfo);
        return updateResult;
    }


    //商品下架
    @Override
    public ProductInfo offSale(String productId) {
        ProductInfo productInfo = productInfoRepository.findById(productId).orElse(null);
        if (productInfo == null) {
            throw new SellException(ExceptionEnum.PRODUCT_NOT_EXIST);
        }
        if (productInfo.getProductStatusEnum() == ProductStatusEnum.DOWN) {
            throw new SellException(ExceptionEnum.PRODUCT_STATUS_ERROR);
        }
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        ProductInfo updateResult = productInfoRepository.save(productInfo);
        return updateResult;
    }


}
