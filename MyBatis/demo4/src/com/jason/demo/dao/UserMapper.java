package com.jason.demo.dao;

import com.jason.demo.domain.Orders;
import com.jason.demo.vo.OrdersCustom;

import java.util.List;

/**
 * Created by cjs on 2016/9/23.
 * mapper接口的方法 实现dao
 */
public interface UserMapper {
    List<OrdersCustom> findOrdersUser();

    List<Orders> findOrdersAndOrderDetailResultMap();
}
