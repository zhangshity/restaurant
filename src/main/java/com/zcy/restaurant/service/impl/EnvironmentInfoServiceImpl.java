package com.zcy.restaurant.service.impl;

import com.zcy.restaurant.entity.EnvironmentInfo;
import com.zcy.restaurant.entity.ProductInfo;
import com.zcy.restaurant.enums.EnvironmentStatusEnum;
import com.zcy.restaurant.enums.ExceptionEnum;
import com.zcy.restaurant.enums.ProductStatusEnum;
import com.zcy.restaurant.exception.SellException;
import com.zcy.restaurant.repository.EnvironmentInfoRepository;
import com.zcy.restaurant.service.EnvironmentInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ Author: chunyang.zhang
 * @ Description:   EnvironmentInfo  Service实现类
 * @ Date: Created in
 * @ Modified: By:
 */
@Service
@Slf4j
public class EnvironmentInfoServiceImpl implements EnvironmentInfoService {

    @Autowired
    private EnvironmentInfoRepository environmentInfoRepository;


    //查找所有餐桌环境信息
    @Override
    public Page<EnvironmentInfo> findAll(Pageable pageable) {
        return environmentInfoRepository.findAll(pageable);
    }

    //废弃！！！！
    //查找全部餐桌信息(修改餐桌信息下拉框使用)
    public List<EnvironmentInfo> findAll() {
        return environmentInfoRepository.findAll();
    }


    //查找一个餐桌信息
    public EnvironmentInfo findOne(Integer environmentId) {
        return environmentInfoRepository.findById(environmentId).orElse(null);
    }


    //通过人数查找餐桌
    @Override
    public List<EnvironmentInfo> findByPeople(Integer peopleQuantity) {
        List<EnvironmentInfo> environmentInfoList = environmentInfoRepository.findByPeopleQuantity(peopleQuantity);
        if (environmentInfoList == null) {
            log.error("【餐桌查找】不存在餐桌,peopleQuantity={}", peopleQuantity);
            throw new SellException(ExceptionEnum.ENVIRONMENT_NOT_EXIST);
        }
        return environmentInfoList;
    }


    //通过餐桌状态查找
    @Override
    public List<EnvironmentInfo> findByTableStatus(Integer environmentStatus) {
        List<EnvironmentInfo> environmentInfoList = environmentInfoRepository.findByEnvironmentStatus(environmentStatus);
        if (environmentInfoList == null) {
            log.error("【餐桌查找】不存在餐桌,environmentStatus={}", environmentStatus);
            throw new SellException(ExceptionEnum.ENVIRONMENT_NOT_EXIST);
        }
        return environmentInfoList;
    }


    //通过人数和餐桌状态查找(排号用)
    @Override
    public List<EnvironmentInfo> findByPeopleAndStatus(Integer peopleQuantity, Integer environmentStatus) {
        List<EnvironmentInfo> environmentInfoList = environmentInfoRepository.findByPeopleQuantityAndAndEnvironmentStatus(peopleQuantity, environmentStatus);
        //排号需要一个空信息来做判断,不能抛异常,一定要返回一个空对象
        //        if (environmentInfoList == null) {
//            log.error("【餐桌查找】不存在餐桌,peopleQuantity={},environmentStatus={}", peopleQuantity, environmentStatus);
//            throw new SellException(ExceptionEnum.ENVIRONMENT_NOT_EXIST);
//        }
        return environmentInfoList;
    }


    //增加餐桌信息
    @Override
    public EnvironmentInfo save(EnvironmentInfo environmentInfo) {
        return environmentInfoRepository.save(environmentInfo);
    }


    //占用餐桌
    @Override
    public EnvironmentInfo inUse(Integer environmentId) {
        EnvironmentInfo environmentInfo = environmentInfoRepository.findById(environmentId).orElse(null);
        if (environmentInfo == null) {
            log.error("【修改餐桌状态】 失败,无此餐桌信息 environmentId={}", environmentId);
            throw new SellException(ExceptionEnum.ENVIRONMENT_NOT_EXIST);
        }
        if (environmentInfo.getEnvironmentStatus().equals(EnvironmentStatusEnum.IN_USE.getCode())) {
            throw new SellException(ExceptionEnum.ENVIRONMENT_STATUS_ERROR);
        }
        environmentInfo.setEnvironmentStatus(EnvironmentStatusEnum.IN_USE.getCode());
        EnvironmentInfo updateResult = environmentInfoRepository.save(environmentInfo);
        return updateResult;
    }


    //空闲出餐桌
    @Override
    public EnvironmentInfo notUsed(Integer environmentId) {
        EnvironmentInfo environmentInfo = environmentInfoRepository.findById(environmentId).orElse(null);
        if (environmentInfo == null) {
            log.error("【修改餐桌状态】 失败,无此餐桌信息 environmentId={}", environmentId);
            throw new SellException(ExceptionEnum.ENVIRONMENT_NOT_EXIST);
        }
        if (environmentInfo.getEnvironmentStatus().equals(EnvironmentStatusEnum.NOT_USED.getCode())) {
            throw new SellException(ExceptionEnum.ENVIRONMENT_STATUS_ERROR);
        }
        environmentInfo.setEnvironmentStatus(EnvironmentStatusEnum.NOT_USED.getCode());
        EnvironmentInfo updateResult = environmentInfoRepository.save(environmentInfo);
        return updateResult;
    }


    //废弃餐桌
    @Override
    public EnvironmentInfo abadon(Integer environmentId) {
        EnvironmentInfo environmentInfo = environmentInfoRepository.findById(environmentId).orElse(null);
        if (environmentInfo == null) {
            log.error("【修改餐桌状态】 失败,无此餐桌信息 environmentId={}", environmentId);
            throw new SellException(ExceptionEnum.ENVIRONMENT_NOT_EXIST);
        }
        if (environmentInfo.getEnvironmentStatus().equals(EnvironmentStatusEnum.ABANDON.getCode())) {
            throw new SellException(ExceptionEnum.ENVIRONMENT_STATUS_ERROR);
        }
        environmentInfo.setEnvironmentStatus(EnvironmentStatusEnum.ABANDON.getCode());
        EnvironmentInfo updateResult = environmentInfoRepository.save(environmentInfo);
        return updateResult;
    }
}
