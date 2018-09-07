package com.zcy.restaurant.repository;

import com.zcy.restaurant.entity.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

//单元测试
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Test
    //测试查询
    public void findByIdTest() {
        ProductCategory productCategory = productCategoryRepository.findById(1).get();
        System.out.println(productCategory);
    }

//    @Test
//    //测试插入
//    public void saveTest(){
//        ProductCategory productCategory = new ProductCategory();
//
//        productCategory.setCategoryName("女生最爱");
//        productCategory.setCategoryType(3);
//        productCategoryRepository.save(productCategory);
//    }

//    @Test
//    @Transactional  //事务注解：数据在测试中都不会添加进去，数据库回滚
//    //测试修改
//    public void updateTest() {
//
////        ProductCategory  productCategory = productCategoryRepository.findById(2).get();
////        productCategory.setCategoryType(8);
//
//
////        ProductCategory productCategory = new ProductCategory();
////
////        productCategory.setCategoryId(2);
////        productCategory.setCategoryName("男生最爱");
////        productCategory.setCategoryType(4);
//
//        ProductCategory productCategory = new ProductCategory("11测试", 6);
//
//        ProductCategory result = productCategoryRepository.save(productCategory);
//        Assert.assertNotNull(result);
//        //Assert.assertEquals(null,result); //两个断言一样
//    }


    @Test
    public void findByCategoryTypeInTest() {

        List<Integer> list = Arrays.asList(2, 3, 4);
        List<ProductCategory> result = productCategoryRepository.findByCategoryTypeIn(list);
        Assert.assertNotEquals(0,result.size());
    }

}