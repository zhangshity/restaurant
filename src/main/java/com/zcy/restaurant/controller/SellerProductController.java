package com.zcy.restaurant.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.zcy.restaurant.entity.ProductCategory;
import com.zcy.restaurant.entity.ProductInfo;
import com.zcy.restaurant.enums.ExceptionEnum;
import com.zcy.restaurant.exception.SellException;
import com.zcy.restaurant.form.ProductForm;
import com.zcy.restaurant.service.ProductCategoryService;
import com.zcy.restaurant.service.ProductInfoService;
import com.zcy.restaurant.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @ Author: chunyang.zhang
 * @ Description:      卖家商品
 * @ Date: Created in
 * @ Modified: By:
 */
@Controller
@RequestMapping("/seller/product")
@Slf4j
public class SellerProductController {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ProductCategoryService productCategoryService;

    //查询所有商品
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                             Map<String, Object> map) {
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        Page<ProductInfo> productInfoPage = productInfoService.findAll(pageRequest);
        map.put("productInfoPage", productInfoPage);
        map.put("currentPage", page);
        map.put("size", size);
        return new ModelAndView("product/list", map);
    }

    //上架商品
    @GetMapping("/on_sale")
    public ModelAndView onSale(@RequestParam("productId") String productId,
                               Map<String, Object> map) {
        try {
            productInfoService.onSale(productId);
        } catch (SellException e) {
            log.error("【商品上架】发生异常{}", e);
            map.put("msg", e.getMessage());
            map.put("url", "/seller/product/list");
            return new ModelAndView("common/error", map);
        }
        map.put("msg", ExceptionEnum.PRODUCT_ON_SALE_SUCCESS.getMessage());
        map.put("url", "/seller/product/list");
        return new ModelAndView("common/success", map);

    }


    //下架商品
    @GetMapping("/off_sale")
    public ModelAndView offSale(@RequestParam("productId") String productId,
                                Map<String, Object> map) {
        try {
            productInfoService.offSale(productId);
        } catch (SellException e) {
            log.error("【商品下架】发生异常{}", e);
            map.put("msg", e.getMessage());
            map.put("url", "/seller/product/list");
            return new ModelAndView("common/error", map);
        }
        map.put("msg", ExceptionEnum.PRODUCT_OFF_SALE_SUCCESS.getMessage());
        map.put("url", "/seller/product/list");
        return new ModelAndView("common/success", map);

    }


    //添加商品信息
    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "productId", required = false) String productId,
                              Map<String, Object> map) {
        if (!StringUtils.isEmpty(productId)) {//如果有productId参数,抽取DB商品信息,传入页面
            ProductInfo productInfo = productInfoService.findOne(productId);
            map.put("productInfo", productInfo);
        }
        //查询类目    //如果没有productId参数,则不传商品信息,抽取DB类目信息,供类目选择框使用
        List<ProductCategory> productCategoryList = productCategoryService.findAll();
        map.put("productCategoryList", productCategoryList);
        return new ModelAndView("product/index", map);
    }

    //接index的传过来的商品信息,的保存、更新方法
    @PostMapping("/save")
    public ModelAndView save(@Valid ProductForm productForm,//页面传来的参数使用表单类存储he验证处理
                             BindingResult bindingResult,
                             Map<String, Object> map) {
        if (bindingResult.hasErrors()) { //判断穿过来的参数有没有错误，有就调到错误界面
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/seller/product/index");
            return new ModelAndView("common/error", map);
        }
        //表单没有错误就传值
        try {
            ProductInfo productInfo = new ProductInfo();
            //如果 productId 不为空,为新增商品
            if (!StringUtils.isEmpty(productForm.getProductId())) {
                productInfo = productInfoService.findOne(productForm.getProductId());

            } else {
                productForm.setProductId(KeyUtil.genUniqueKey());
            }
            BeanUtils.copyProperties(productForm, productInfo);
            productInfoService.save(productInfo);
        } catch (SellException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/seller/product/index");
            return new ModelAndView("common/error", map);
        }
        map.put("msg", "更新商品数据成功");
        map.put("url", "/seller/product/list");
        return new ModelAndView("common/success");
    }
}
