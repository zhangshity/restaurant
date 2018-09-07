package com.zcy.restaurant.service;


import com.zcy.restaurant.dto.OrderDTO;
import com.zcy.restaurant.entity.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @ Author: chunyang.zhang
 * @ Description:  订单 service
 * @ Date: Created in
 * @ Modified: By:
 */
//接口中的方法默认都是public、abstract的
public interface OrderService {

    //创建订单(买家)
    OrderDTO create(OrderDTO orderDTO);

    //查询单个订单(主表+附表)
    OrderDTO findOne(String orderId);

    //查询订单列表通过买家openid
    Page<OrderDTO> findList(String buyerOpenid , Pageable pageable);

    //取消订单
    OrderDTO cancel(OrderDTO orderDTO);

    //完成订单
    OrderDTO finish(OrderDTO orderDTO);

    //支付订单(买家)
    OrderDTO pay(OrderDTO orderDTO);

    //查询所有订单列表(卖家)
    Page<OrderDTO> findList(Pageable pageable);
}
