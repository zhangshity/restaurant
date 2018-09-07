package com.zcy.restaurant.controller;

import com.zcy.restaurant.entity.EnvironmentInfo;
import com.zcy.restaurant.entity.QueueInfo;
import com.zcy.restaurant.enums.ExceptionEnum;
import com.zcy.restaurant.exception.SellException;
import com.zcy.restaurant.form.QueueInfoForm;
import com.zcy.restaurant.service.QueueInfoService;
import com.zcy.restaurant.utils.QueueNumberCreateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.querydsl.QuerydslUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ Author: chunyang.zhang
 * @ Description:
 * @ Date: Created in
 * @ Modified: By:
 */
@Controller
@RequestMapping("/queue")
@Slf4j
public class QueueInfoController {

    @Autowired
    private QueueInfoService queueInfoService;


    //买家or卖家取号(都调到一个单独的取号界面)
    @GetMapping("/getNumber")
    public ModelAndView getNumber(@RequestParam(value = "buyerPhone", required = false) String buyerPhone,
                                  Map<String, Object> map) {
        map.put("buyerPhone", buyerPhone);
        return new ModelAndView("queue/getNumber");
    }

    //验证取号界面传来的值,创建订单队列
    @PostMapping("/create")
    public ModelAndView createNumber(@Valid QueueInfoForm queueInfoForm,//页面传来的参数使用表单类存储he验证处理
                               BindingResult bindingResult,
                               Map<String, Object> map) {
        if (bindingResult.hasErrors()) { //判断穿过来的参数有没有错误，有就调到错误界面
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/queue/getNumber");
            return new ModelAndView("common/error", map);
        }
        //表单没有错误后，先判断是否正在排号
        try {
            Boolean result = queueInfoService.isUniqueEmptyNumber(queueInfoForm.getBuyerPhone());
        } catch (SellException e) {
            map.put("msg", e.getMessage());
            map.put("url", "/queue/myNumber?buyerPhone=" + queueInfoForm.getBuyerPhone());
            return new ModelAndView("common/error", map);
        }
        //传值写入数据库
        QueueInfo queueInfo;
        try {//service的createQueue()方法判断出错(还有空桌),捕获异常
            queueInfo = queueInfoService.createQueue(queueInfoForm.getBuyerPhone(),
                    queueInfoForm.getPeopleQuantity());
        } catch (SellException e) {
            log.error("【创建队列号码】发生异常:buyerPhone={},peopleQuantity={}", queueInfoForm.getBuyerPhone(), queueInfoForm.getPeopleQuantity());
            map.put("msg", e.getMessage());
            map.put("url", "/queue/getNumber");
            return new ModelAndView("common/error", map);
        }
        //调用完service方法再次验证:是否创建了,队列信息对象
        if (queueInfo == null) {
            log.error("【创建队列号码】发生异常:buyerPhone={},peopleQuantity={}", queueInfoForm.getBuyerPhone(), queueInfoForm.getPeopleQuantity());
            map.put("msg", "【创建队列号码】创建队列controller层异常");
            map.put("url", "/queue/number");
            return new ModelAndView("common/error", map);
        }
        map.put("msg", "【取号】成功！");
        map.put("url", "/queue/myNumber?buyerPhone=" + queueInfo.getBuyerPhone());
        return new ModelAndView("common/success");
    }


    @GetMapping("/myNumber")
    public ModelAndView myNumber(@RequestParam(value = "buyerPhone", required = false) String buyerPhone,
                                 Map<String, Object> map) {

        //TODO 判断日期，不是今天的不显示，抽取号码中的 " "前的日期做判断

        List<QueueInfo> queueInfoList = queueInfoService.findByBuyerPhone(buyerPhone);
        //把一个号码对应的所有排号对象遍历出来过滤，只留下最新的
        Collections.reverse(queueInfoList);
        QueueInfo queueInfo = queueInfoList.get(0);
        //队列号码类型判断,给页面传输计数器信息
        String queueNumberString = queueInfo.getQueueNumber();
        String cut = queueNumberString.substring(25, 26);
        if ("A".equals(cut)) {
            map.put("counter", QueueNumberCreateUtil.Acount);
        } else if ("B".equals(cut)) {
            map.put("counter", QueueNumberCreateUtil.Bcount);
        } else if ("C".equals(cut)) {
            map.put("counter", QueueNumberCreateUtil.Ccount);
        }
        map.put("queueInfo", queueInfo);

        return new ModelAndView("queue/myNumber", map);
    }


    //查找所有队列环境
    @GetMapping("/list")
    public ModelAndView sellerList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                   @RequestParam(value = "size", defaultValue = "15") Integer size,
                                   Map<String, Object> map) {
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        Page<QueueInfo> queueInfoPage = queueInfoService.findAll(pageRequest);
//        List<QueueInfo> queueInfoList = queueInfoPage.getContent();
//        Collections.reverse(queueInfoList);
//        map.put("queueInfoList", queueInfoList);
        map.put("queueInfoPage", queueInfoPage);
        map.put("currentPage", page);
        map.put("size", size);
        return new ModelAndView("queue/list", map);
    }

    //取消队列
    @GetMapping("/cancelNumber")
    public ModelAndView cancel(@RequestParam("queueId") String queueId,
                               Map<String, Object> map) {
        QueueInfo queueInfo;
        try {
            queueInfo = queueInfoService.queueTimeOut(queueId);
        } catch (SellException e) {
            log.error("【取消队列号码】发生异常:queueId={}", queueId);
            map.put("msg", e.getMessage());
            map.put("url", "/queue/list");
            return new ModelAndView("common/error", map);
        }
        //队列计数器-1
        String queueNumberString = queueInfo.getQueueNumber();
        String cut = queueNumberString.substring(25, 26);
        if (cut == "A") {
            QueueNumberCreateUtil.Acount -= 1;
        } else if (cut == "B") {
            QueueNumberCreateUtil.Bcount -= 1;
        } else if (cut == "C") {
            QueueNumberCreateUtil.Ccount -= 1;
        }
        map.put("msg", "取消排号成功!");
        map.put("url", "/queue/list");
        return new ModelAndView("common/success", map);
    }


}
