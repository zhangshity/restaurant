package com.zcy.restaurant.controller;

import com.zcy.restaurant.entity.EnvironmentInfo;
import com.zcy.restaurant.enums.ExceptionEnum;
import com.zcy.restaurant.exception.SellException;
import com.zcy.restaurant.form.EnvironmentForm;
import com.zcy.restaurant.service.EnvironmentInfoService;
import com.zcy.restaurant.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ Author: chunyang.zhang
 * @ Description:       EnvironmentInfo餐桌信息   Controller层
 * @ Date: Created in
 * @ Modified: By:
 */
@Controller
@RequestMapping("/environment")
@Slf4j
public class EnvironmentInfoController {

    @Autowired
    private EnvironmentInfoService environmentInfoService;


    //查找所有餐桌环境
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                             Map<String, Object> map) {
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        Page<EnvironmentInfo> environmentInfoPage = environmentInfoService.findAll(pageRequest);
        map.put("environmentInfoPage", environmentInfoPage);
        map.put("currentPage", page);
        map.put("size", size);
        return new ModelAndView("environment/list", map);
    }


    //占用餐桌
    @GetMapping("/inUse")
    public ModelAndView inUse(@RequestParam("environmentId") String environmentId,
                              Map<String, Object> map) {
        try {
            //EnvironmentInfo result = environmentInfoService.findOne(environmentInfo);
            environmentInfoService.inUse(Integer.parseInt(environmentId));
        } catch (SellException e) {
            log.error("【占用餐桌】发生异常{}", e);
            map.put("msg", e.getMessage());
            map.put("url", "/environment/list");
            return new ModelAndView("common/error", map);
        }
        map.put("msg", ExceptionEnum.ENVIRONMENT_ALTER_STATUS_SUCCESS.getMessage());
        map.put("url", "/environment/list");
        return new ModelAndView("common/success");
    }


    //空闲餐桌
    @GetMapping("/notUsed")
    public ModelAndView notUsed(@RequestParam("environmentId") String environmentId,
                                Map<String, Object> map) {
        try {
            //EnvironmentInfo result = environmentInfoService.findOne(environmentInfo);
            environmentInfoService.notUsed(Integer.parseInt(environmentId));
        } catch (SellException e) {
            log.error("【占用餐桌】发生异常{}", e);
            map.put("msg", e.getMessage());
            map.put("url", "/environment/list");
            return new ModelAndView("common/error", map);
        }
        map.put("msg", ExceptionEnum.ENVIRONMENT_ALTER_STATUS_SUCCESS.getMessage());
        map.put("url", "/environment/list");
        return new ModelAndView("common/success");
    }


    //废弃餐桌
    @GetMapping("/abadon")
    public ModelAndView abadon(@RequestParam("environmentId") String environmentId,
                               Map<String, Object> map) {
        try {
            environmentInfoService.abadon(Integer.parseInt(environmentId));
        } catch (SellException e) {
            log.error("【占用餐桌】发生异常{}", e);
            map.put("msg", e.getMessage());
            map.put("url", "/environment/list");
            return new ModelAndView("common/error", map);
        }
        map.put("msg", ExceptionEnum.ENVIRONMENT_ALTER_STATUS_SUCCESS.getMessage());
        map.put("url", "/environment/list");
        return new ModelAndView("common/success");
    }


    //添加商品信息
    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "environmentId", required = false) String environmentId,
                              Map<String, Object> map) {
        //如果有environmentId参数,为更新请求，抽取DB商品信息,传入页面
        if (environmentId != null) {
            EnvironmentInfo environmentInfo = environmentInfoService.findOne(Integer.parseInt(environmentId));
            map.put("environmentInfo", environmentInfo);
        }
//        //查询环境状态信息    //抽取DB类目信息,供类目select选择框使用
//        List<EnvironmentInfo> environmentInfoListSelect = environmentInfoService.findAll();
//        map.put("environmentInfoListSelect", environmentInfoListSelect);
        return new ModelAndView("environment/index", map);
    }


    //接index的传过来的商品信息,的保存、更新方法
    //增加餐桌环境
    @PostMapping("/save")
    public ModelAndView save(@Valid EnvironmentForm environmentForm,//页面传来的参数使用表单类存储he验证处理
                             BindingResult bindingResult,
                             Map<String, Object> map) {
        if (bindingResult.hasErrors()) { //判断穿过来的参数有没有错误，有就调到错误界面
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/environment/index");
            return new ModelAndView("common/error", map);
        }
        //表单没有错误就传值
        try {
            EnvironmentInfo environmentInfo = new EnvironmentInfo();
            //如果 environmentId 不为空,为已存在商品
            if (environmentForm.getEnvironmentId() != null) {
                environmentInfo = environmentInfoService.findOne(environmentForm.getEnvironmentId());

            }
            BeanUtils.copyProperties(environmentForm, environmentInfo);
            environmentInfoService.save(environmentInfo);
        } catch (SellException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/environment/index");
            return new ModelAndView("common/error", map);
        }
        map.put("msg", "更新商品数据成功！");
        map.put("url", "/environment/list");
        return new ModelAndView("common/success", map);
    }


//    //通过人数查找餐桌环境
//    @GetMapping("/find")
//    public ModelAndView findByPeopleQuantity(@RequestParam("peopleQuantity") Integer PeopleQuantity) {
//        List<EnvironmentInfo> environmentInfoList = environmentInfoService.findByPeople(PeopleQuantity);
//        Map<String, EnvironmentInfo> map = new HashMap<>();
//        return new ModelAndView("", map);
//    }
//
//
//
//    //删除餐桌环境
//    @GetMapping("/delete")
//    public ModelAndView delete(@RequestParam("environmentId") Integer environmentId) {
//
//    }
}
