package com.cab.service.impl;

import com.cab.bean.entity.order.Order;
import com.cab.bean.view.PageBean;
import com.cab.common.framework.utils.BarCodeUtil;
import com.cab.common.framework.utils.DocUtil;
import com.cab.common.framework.utils.PrintUtil;
import com.cab.dao.mapper.order.OrderMapper;
import com.cab.service.OrderService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2018/5/18.
 */
@SuppressWarnings("all")
@Component
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public Order getOrderByExpressNum(String expressNum) {
        return orderMapper.selectByExpressNum(expressNum);
    }

    @Override
    public int add(Order order) {
        return orderMapper.insertSelective(order);
    }

    @Override
    public int addBatch(List<Order> orders) {
        return orderMapper.insertBatch(orders);
    }

    @Override
    public PageBean<Order> selectScanOrderList(Order order, Integer page, Integer pageSize) {
        PageBean<Order> pageBean = new PageBean<Order>();
        Page<Order> pageInfo = null;
        if (page != null && page.intValue() > 0 && pageSize != null && pageSize.intValue() > 0) {
            pageInfo = PageHelper.startPage(page, pageSize, true);
        }
        List<Order> list = orderMapper.selectByModel(order);//
        if (pageInfo == null) {
            pageBean.setRows(list);
            return pageBean;
        } else {
            if(page > pageInfo.getPages()){
                return pageBean;
            }
            pageBean.setPageCount(pageInfo.getPages());
            pageBean.setCurrentPage(pageInfo.getPageNum());
            pageBean.setPageSize(pageInfo.getPageSize());
            pageBean.setTotal(pageInfo.getTotal());
            pageBean.setRows(list);
        }
        return pageBean;
    }

    public List<Order> selectOrderList(List<Integer> ids){
        return orderMapper.selectByIds(ids);
    }

    public int print(Order order) throws Exception{
        String barCode = BarCodeUtil.generateBarCode(105,50,order.getExpressNum());
        Map<String, Object> outData = new HashMap<String, Object>();
        outData.put("article", order.getName());
        outData.put("expressName", order.getExpressNum());
        String doc = DocUtil.html2doc(outData,order.getExpressNum());
        PrintUtil.print(doc);
        return 1;
    }
}
