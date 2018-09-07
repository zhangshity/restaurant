package com.zcy.restaurant.repository;

import com.zcy.restaurant.entity.OrderDetail;
import com.zcy.restaurant.entity.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    public void saveTest() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("1234589");
        orderDetail.setOrderId("190221");
        orderDetail.setProductId("193");
        orderDetail.setProductName("大三炸弹");
        orderDetail.setProductPrice(new BigDecimal(21.99));
        orderDetail.setProductQuantity(100);
        orderDetail.setProductIcon("http://asdasdasdaad.jpg");

        OrderDetail result = orderDetailRepository.save(orderDetail);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByOrderIdTest() {

        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId("190221");
        Assert.assertNotEquals(0,orderDetailList.size());
    }

    @Test
    public void findByOrderId() {
    }
}