package com.zcy.restaurant.service.impl;

import com.zcy.restaurant.entity.QueueInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class QueueInfoServiceImplTest {

    @Autowired
    private QueueInfoServiceImpl queueInfoServiceImpl;

//    @Test
//    public void createQueueNumber() {
//        QueueInfo queueInfo = queueInfoServiceImpl.createQueueNumber("15253152959");
//        Assert.assertNotNull(queueInfo);
//    }


    @Test
    public void createQueue() {
        QueueInfo queueInfo = queueInfoServiceImpl.createQueue("15253152959",
                16);
        log.info("queueInfo={}", queueInfo);
        Assert.assertNotNull(queueInfo);

    }

    @Test
    public void queueTimeOut() {
        QueueInfo queueInfo = queueInfoServiceImpl.queueTimeOut("1528190693845563455");
        log.info("[][][]={}", queueInfo);
        Assert.assertNotNull(queueInfo);
    }

    @Test
    public void findByBuyerPhone() {
        List<QueueInfo> queueInfoList = queueInfoServiceImpl.findByBuyerPhone("15253152951");
        log.info("[][][][][]={}", queueInfoList);
        Assert.assertNotNull(queueInfoList);
    }

//    @Test
//    public void findAll() {
//        List<QueueInfo> queueInfoList = queueInfoServiceImpl.findAll();
//        log.info("[][][][][]={}", queueInfoList);
//        Assert.assertNotNull(queueInfoList);
//    }
}