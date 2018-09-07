package com.zcy.restaurant.repository;

import com.zcy.restaurant.entity.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ Author: chunyang.zhang
 * @ Description: 订单表DAO层
 * @ Date: Created in 11:01 2018/5/19
 * @ Modified: By:
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster,String> {

    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);
}
