package com.cab.service.impl;

import com.cab.bean.entity.order.Order;
import com.cab.bean.entity.test.Test;
import com.cab.dao.mapper.order.OrderMapper;
import com.cab.dao.mapper.test.TestMapper;
import com.cab.service.OrderService;
import com.cab.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by admin on 2018/5/18.
 */
@SuppressWarnings("all")
@Component
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public int add(Order order) {
        return orderMapper.insertSelective(order);
    }
}
