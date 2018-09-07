package com.zcy.restaurant.repository;

import com.zcy.restaurant.entity.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @ Author: chunyang.zhang
 * @ Description:
 * @ Date: Created in 11:33 2018/5/19
 * @ Modified: By:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    private final String OPENID = "110110";

    @Test
    public void saveTest() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("19334");
        orderMaster.setBuyerName("小明");
        orderMaster.setBuyerPhone("19253155559");
        orderMaster.setBuyerAddress("中国某地11");
        orderMaster.setBuyerOpenid("110110");
        orderMaster.setOrderAmount(new BigDecimal(6.5));
        //orderMaster.setOrderStatus(); //默认
        //orderMaster.setPayStatus();  //默认

        OrderMaster result = orderMasterRepository.save(orderMaster);

        Assert.assertNotNull(result);

    }

    @Test
    public void findByBuyerOpenid() {
        PageRequest pageRequest = PageRequest.of(0, 3);
        Page<OrderMaster> result = orderMasterRepository.findByBuyerOpenid(OPENID, pageRequest);
        Assert.assertNotEquals(0,result.getTotalElements());
        System.out.println(((Page) result).getTotalElements());
    }
}