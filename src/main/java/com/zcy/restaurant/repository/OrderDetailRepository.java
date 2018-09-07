package com.zcy.restaurant.repository;

import com.zcy.restaurant.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @ Author: chunyang.zhang
 * @ Description: 订单详情表 DAO
 * @ Date: Created in 11:14 2018/5/19
 * @ Modified: By:
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail,String> {
    List<OrderDetail> findByOrderId (String orderId);
}
