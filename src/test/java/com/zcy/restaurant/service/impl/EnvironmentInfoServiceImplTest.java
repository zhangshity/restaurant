package com.zcy.restaurant.service.impl;

import com.zcy.restaurant.entity.EnvironmentInfo;
import com.zcy.restaurant.entity.ProductInfo;
import com.zcy.restaurant.enums.EnvironmentStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import sun.util.locale.provider.LocaleServiceProviderPool;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class EnvironmentInfoServiceImplTest {

    @Autowired
    private EnvironmentInfoServiceImpl environmentInfoServiceimpl;

    @Test
    public void findAll() {
        PageRequest pageRequest = PageRequest.of(0, 2);
        Page<EnvironmentInfo> environmentInfoPage = environmentInfoServiceimpl.findAll(pageRequest);

        Assert.assertNotEquals(0, environmentInfoPage.getTotalElements());
    }

    @Test
    public void findByPeople() {
        List<EnvironmentInfo> environmentInfoList = environmentInfoServiceimpl.findByPeople(16);
        log.info("【environmentInfoList】={}", environmentInfoList);
        Assert.assertNotNull(environmentInfoList);
    }

    @Test
    public void findByTableStatus() {
        List<EnvironmentInfo> environmentInfoList = environmentInfoServiceimpl.findByTableStatus(EnvironmentStatusEnum.NOT_USED.getCode());
        log.info("environmentInfoList   ={}", environmentInfoList);
        Assert.assertNotNull(environmentInfoList);
    }

    @Test
    public void findByPeopleAndStatus() {
        List<EnvironmentInfo> environmentInfoList = environmentInfoServiceimpl.findByPeopleAndStatus(4, EnvironmentStatusEnum.NOT_USED.getCode());
        log.info("environmentInfoList ={}", environmentInfoList);
        Assert.assertNotNull(environmentInfoList);
    }

    @Test
    public void save() {
        EnvironmentInfo environmentInfo = new EnvironmentInfo();
        environmentInfo.setEnvironmentName("卢浮宫");
        environmentInfo.setPeopleQuantity(16);
        //environmentInfo.setEnvironmentStatus(EnvironmentStatusEnum.NOT_USED.getCode());
        environmentInfo.setEnvironmentDescription("16人豪华包厢");

        EnvironmentInfo result = environmentInfoServiceimpl.save(environmentInfo);

        Assert.assertNotNull(result);
    }

    @Test
    public void inUse() {
        EnvironmentInfo environmentInfo = environmentInfoServiceimpl.inUse(1234569);
        log.info("EnvironmentInfo environmentInfo={}", environmentInfo);
        Assert.assertEquals(new Integer(1), environmentInfo.getEnvironmentStatus());
    }

    @Test
    public void notUesd() {
        EnvironmentInfo environmentInfo = environmentInfoServiceimpl.notUsed(1234569);
        log.info("EnvironmentInfo environmentInfo={}", environmentInfo);
        Assert.assertEquals(new Integer(0), environmentInfo.getEnvironmentStatus());
    }

    @Test
    public void abadon() {
        EnvironmentInfo environmentInfo = environmentInfoServiceimpl.abadon(1234567);
        log.info("EnvironmentInfo environmentInfo={}", environmentInfo);
        Assert.assertEquals(new Integer(2), environmentInfo.getEnvironmentStatus());

    }
}