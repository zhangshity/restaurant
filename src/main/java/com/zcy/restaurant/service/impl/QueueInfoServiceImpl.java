package com.zcy.restaurant.service.impl;


import com.zcy.restaurant.entity.EnvironmentInfo;
import com.zcy.restaurant.entity.QueueInfo;
import com.zcy.restaurant.enums.EnvironmentStatusEnum;
import com.zcy.restaurant.enums.ExceptionEnum;
import com.zcy.restaurant.enums.QueueStatusEnum;
import com.zcy.restaurant.exception.SellException;
import com.zcy.restaurant.repository.QueueInfoRepository;
import com.zcy.restaurant.service.EnvironmentInfoService;
import com.zcy.restaurant.service.QueueInfoService;
import com.zcy.restaurant.utils.KeyUtil;
import com.zcy.restaurant.utils.QueueNumberCreateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ Author: chunyang.zhang
 * @ Description:       排号 service 实现类
 * @ Date: Created in
 * @ Modified: By:
 */
@Service
@Slf4j
public class QueueInfoServiceImpl implements QueueInfoService {


    @Autowired
    private QueueInfoRepository queueInfoRepository;

    @Autowired
    private EnvironmentInfoService environmentInfoService;


    //创建队列
    @Override
    public synchronized QueueInfo createQueue(String buyerPhone, Integer peopleQuantity) {
        //传入人数,生成队列号码
        String queueNumber = QueueNumberCreateUtil.simpleQueueNumberFormat(peopleQuantity);
        //判断餐桌是全被被占用
        List<EnvironmentInfo> environmentInfoList = environmentInfoService.findByPeopleAndStatus(
                peopleQuantity,//人数
                EnvironmentStatusEnum.NOT_USED.getCode());//餐桌状态(空桌),写死
        if (environmentInfoList.size() != 0) {
            log.info("【取号】失败!还有空闲位置!请进店用餐!");
            throw new SellException(ExceptionEnum.STILL_HAVE_TABLE);
        }
        //生成队列信息对象
        QueueInfo queueInfo = new QueueInfo();
        queueInfo.setQueueId(KeyUtil.genUniqueKey());
        queueInfo.setBuyerPhone(buyerPhone);
        queueInfo.setQueueNumber(queueNumber);
        //queueInfo.setNumberStatus(0);//默认为0,排队中
        //queueInfo.setCreateTime();//数据库默认自动生成
        QueueInfo resultQueueInfo = queueInfoRepository.save(queueInfo);
        return resultQueueInfo;
    }

    //修改队列状态(号码过期)
    public QueueInfo queueTimeOut(String queueId) {
        QueueInfo queueInfo = queueInfoRepository.findById(queueId).orElse(null);
        if (queueInfo == null) {
            log.error("【修改队列状态】队列信息不存在 queueId={}", queueId);
            throw new SellException(ExceptionEnum.QUEUE_NOT_EXIST);
        }
        queueInfo.setNumberStatus(QueueStatusEnum.TIMEOUT.getCode());

        return queueInfoRepository.save(queueInfo);
    }

    //通过手机查找队列号(买家)
    @Override
    public List<QueueInfo> findByBuyerPhone(String buyerPhone) {
        List<QueueInfo> queueInfoList = queueInfoRepository.findByBuyerPhone(buyerPhone);
        return queueInfoList;
    }


    //查找全部队列信息(卖家)
    @Override
    public Page<QueueInfo> findAll(Pageable pageable) {
        Page<QueueInfo> queueInfoPage = queueInfoRepository.findAll(pageable);
        return queueInfoPage;
    }


    //判断是否重复取号(号码状态为1(过期 或者 根本不存在)的电话才能取号)
    @Override
    public boolean isUniqueEmptyNumber(String buyerPhone) {
        List<QueueInfo> queueInfoList = queueInfoRepository
                .findByBuyerPhoneAndNumberStatus(buyerPhone, QueueStatusEnum.WAITING.getCode());
        if (queueInfoList.size() == 0) {//必须过期or不存在
            return true;
        }
        log.error("【取号】重复取号,buyerPhone",buyerPhone);
        throw new SellException(ExceptionEnum.EXIST_QUEUENUMBER);
        //return false;
    }

}
