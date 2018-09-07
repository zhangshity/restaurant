package com.zcy.restaurant.controller;


import com.zcy.restaurant.VO.ProductInfoVO;
import com.zcy.restaurant.VO.ProductVO;
import com.zcy.restaurant.VO.ResultVO;
import com.zcy.restaurant.entity.ProductCategory;
import com.zcy.restaurant.entity.ProductInfo;
import com.zcy.restaurant.service.ProductCategoryService;
import com.zcy.restaurant.service.ProductInfoService;
import com.zcy.restaurant.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ Author: chunyang.zhang
 * @ Description:  买家商品 controller
 * @ Date: Created in 11:24 2018/5/18
 * @ Modified: By:
 */

@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ProductCategoryService productCategoryService;



    //@RequestMapping(value = "/list",method = RequestMethod.GET)
    @GetMapping("/list")
    public ResultVO list() {

        //1查询所有的上架商品
        List<ProductInfo> productInfoList = productInfoService.findUpAll();

        //2查询需要的类目(一次性查询)
        List<Integer> categoryTypeList = new ArrayList<>(); //代表上架Type int数组
        for (ProductInfo productInfo : productInfoList) {//把info中的Type拿出来放入查询类目方法
            //List list = productInfo.getCategoryType();
            //categoryTypeList.add(list);
            categoryTypeList.add(productInfo.getCategoryType());
        }

        List<ProductCategory> productCategoryList = productCategoryService.findByCategoryType(categoryTypeList);

        //3数据拼装
        List<ProductVO> productVOList = new ArrayList<>();

        for (ProductCategory productCategory : productCategoryList) {
            ProductVO productVO = new ProductVO();

            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();

            for (ProductInfo productInfo : productInfoList) {
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {

                    ProductInfoVO productInfoVO = new ProductInfoVO();
//                    productInfoVO.setProductId(productInfo.getProductId());
//                    productInfoVO.setProductName(productInfo.getProductName());
//                    productInfoVO.setProductPrice(productInfo.getProductPrice());
//                    productInfoVO.setProductDescription(productInfo.getProductDescription());
//                    productInfoVO.setProductIcon(productInfo.getProductIcon());
                    BeanUtils.copyProperties(productInfo, productInfoVO);

                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);

            productVOList.add(productVO);
        }
//        //ResultVOUtil公用类，静态方法封装，简练代码，
//        ResultVO resultVO = new ResultVO();
//        resultVO.setData(productVOList);
//        resultVO.setCode(0);
//        resultVO.setMsg("成功");


////        ProductInfoVO productInfoVO = new ProductInfoVO();
////        productInfoVO.setProductId();
////        productInfoVO.setProductName();
////        productInfoVO.setProductPrice();
////        productInfoVO.setProductDescription();
////        productInfoVO.setProductIcon();
//
//        ProductVO productVO = new ProductVO();
////        productVO.setCategoryName();
////        productVO.setCategoryType();
//        productVO.setProductInfoVOList(Arrays.asList(productInfoVO));
//
//
//        ResultVO resultVO = new ResultVO();
//        resultVO.setCode(0);//1 ResultVO 属性设置
//        resultVO.setMsg("成功");//2 ResultVO 属性设置
//        resultVO.setData(Arrays.asList(productVO));//3 ResultVO 属性设置
//
//        return resultVO;
//    }
        //return resultVO;
        return ResultVOUtil.success(productVOList);
    }
}
