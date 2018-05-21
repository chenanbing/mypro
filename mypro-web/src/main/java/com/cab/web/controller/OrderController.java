package com.cab.web.controller;

import com.cab.bean.entity.order.Order;
import com.cab.bean.entity.test.Test;
import com.cab.service.OrderService;
import com.cab.service.TestService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Controller
@RequestMapping("order")
public class OrderController {
    Logger logger = Logger.getLogger(this.getClass());

    @Resource
    private OrderService orderService;


    @RequestMapping(value = "/addOrderPage")
    public String addOrderPage(HttpServletResponse response) throws Exception {
        return "order/addOrder";
    }

    @RequestMapping(value = "/addOrder")
    @ResponseBody
    public int addOrder(HttpServletResponse response, Order order) throws Exception {
        order.setCreateTime(new Date());
        return orderService.add(order);
    }
}