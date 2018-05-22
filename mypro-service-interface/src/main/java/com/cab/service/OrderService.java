package com.cab.service;

import com.cab.bean.entity.order.Order;
import com.cab.bean.view.PageWrapper;

import java.util.List;

/**
 * Created by Administrator on 2018/5/22 0022.
 */
public interface OrderService {
    public int add(Order order);

    public int addBatch(List<Order> orders);

    public PageWrapper<Order> selectOrderList(Order order, Integer page, Integer pageSize);

    public List<Order> selectOrderList(List<Integer> ids);

    public int print(Order order) throws Exception;

}
