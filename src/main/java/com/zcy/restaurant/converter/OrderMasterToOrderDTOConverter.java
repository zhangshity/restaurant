package com.zcy.restaurant.converter;

import com.zcy.restaurant.dto.OrderDTO;
import com.zcy.restaurant.entity.OrderMaster;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderMasterToOrderDTOConverter{

    //静态方法 OrderMaster 转换成 OrderDTO
    public static OrderDTO convert(OrderMaster orderMaster){
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        return orderDTO;
    }


    //静态方法 List<OrderMaster> 转换成 List<OrderDTO>
    public static List<OrderDTO> convert(List<OrderMaster> orderMasterList){
        List<OrderDTO> orderDTOList = new ArrayList<>();
        //OrderDTO orderDTO = new OrderDTO();
        for (OrderMaster orderMaster:orderMasterList){
            OrderDTO orderDTO = new OrderDTO();
            BeanUtils.copyProperties(orderMaster,orderDTO);
            orderDTOList.add(orderDTO);
        }
        return orderDTOList;

        //lamda
//        return orderMasterList.stream().map(e ->convert(e)).collect(Collectors.toList());
    }
}
