package com.zcy.restaurant.service.impl;

import com.zcy.restaurant.entity.ProductInfo;
import com.zcy.restaurant.enums.ProductStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceImplTest {

    @Autowired
    private ProductInfoServiceImpl productInfoServiceImpl;

    @Test
    public void findOne() {
        ProductInfo result = productInfoServiceImpl.findOne("1");
        //Assert.assertNotNull(result);
        System.out.println(result);
        Assert.assertEquals("1", result.getProductId());
    }

    @Test
    public void findUpAll() {
        List<ProductInfo> productInfoList = productInfoServiceImpl.findUpAll();
        Assert.assertNotEquals(0, productInfoList.size());

    }

    //记住分页
    @Test
    public void findAll() {
        //PageRequest继承Pageable接口

        //PageRequest pageRequest = new PageRequest(0,2);
        //Page<ProductInfo> productInfoPage = productInfoServiceImpl.findAll(pageRequest);
        //PageRequest.of(0,2); //of静态方法代替过时的PageRequest();

        PageRequest pageRequest = PageRequest.of(0, 2);
        Page<ProductInfo> productInfoPage = productInfoServiceImpl.findAll(pageRequest);
        //System.out.println(productInfoPage.getTotalElements());

        Assert.assertNotEquals(0, productInfoPage.getTotalElements());
    }

    @Test
    public void save() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("22");
        productInfo.setProductName("serviceImpl粥");
        productInfo.setProductPrice(new BigDecimal(3.85));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("好吃");
        productInfo.setProductIcon("http://xxxx&&&&&.jpg");
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        productInfo.setCategoryType(2);
        ProductInfo result = productInfoServiceImpl.save(productInfo);

        Assert.assertNotNull(result);
    }

    @Test
    public void onSale() {
        ProductInfo productInfo = productInfoServiceImpl.onSale("124");
        Assert.assertNotNull(productInfo);
    }

    @Test
    public void offSale() {
        ProductInfo productInfo = productInfoServiceImpl.offSale("124");
        Assert.assertNotNull(productInfo);
    }
}