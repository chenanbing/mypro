package com.cab.web.controller;

import com.cab.bean.entity.order.Order;
import com.cab.bean.view.PageWrapper;
import com.cab.common.framework.enums.UploadConfig;
import com.cab.common.framework.utils.ExcelUtil;
import com.cab.service.OrderService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.Executor;

@Controller
@RequestMapping("order")
public class OrderController {
    Logger logger = Logger.getLogger(this.getClass());

    @Resource
    private OrderService orderService;

    @Resource
    private Executor taskExecutor;


    @RequestMapping(value = "/addOrderPage")
    public String addOrderPage(HttpServletResponse response) throws Exception {
        return "order/addOrder";
    }

    @RequestMapping(value = "/importOrderPage")
    public String importOrderPage(HttpServletResponse response) throws Exception {
        return "order/importOrder";
    }

    @RequestMapping(value = "/scanBarCodePage")
    public String scanBarCodePage(HttpServletResponse response) throws Exception {
        return "order/scanBarCode";
    }










    @RequestMapping(value = "/scanBarCode")
    @ResponseBody
    public int scanBarCode(HttpServletResponse response, String expressNum) throws Exception {
        Order order = orderService.getOrderByExpressNum(expressNum);
        if(order == null){
            return 0;
        }
        PrintRunner printRunner = new PrintRunner(orderService,order);
        taskExecutor.execute(printRunner);
        return 1;
    }

    @RequestMapping(value = "/addOrder")
    @ResponseBody
    public int addOrder(HttpServletResponse response, Order order) throws Exception {
        order.setCreateTime(new Date());
        return orderService.add(order);
    }

    @RequestMapping(value = "/getOrderList")
    @ResponseBody
    public List<Order> getOrderList(HttpServletResponse response, Integer page, Integer pageSize) throws Exception {
        PageWrapper<Order> pageWrapper = orderService.selectOrderList(new Order(),page,pageSize);
        return pageWrapper.getResult();
    }

    @RequestMapping(value = "/printOrder")
    @ResponseBody
    public int printOrder(HttpServletResponse response, List<Integer> ids) throws Exception {
        List<Order> orderList = orderService.selectOrderList(ids);
        if(CollectionUtils.isEmpty(orderList)){
            return 0;
        }
        Map<Integer, Order> orderMap = new HashMap<Integer, Order>();
        for(Order order : orderList ){
            orderMap.put(order.getId(),order);
        }
        for(Integer id : ids){
            Order order = orderMap.get(id);
            if(order == null){
                continue;
            }
            PrintRunner printRunner = new PrintRunner(orderService,order);
            taskExecutor.execute(printRunner);
        }
        return 1;
    }

    @ResponseBody
    @RequestMapping(value = "importExel")
    public int importExel(HttpServletRequest request) throws Exception{
        //将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        //检查form中是否有enctype="multipart/form-data"
        if (!multipartResolver.isMultipart(request)) {
            return 0;
        }
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        List<Map<String, List<List<Object>>>> execlData = new ArrayList<Map<String, List<List<Object>>>>();//可多传
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
            MultipartFile file = entity.getValue();
            //获取上传文件后缀
            String fileName = file.getOriginalFilename();
            String suffix = fileName.substring(file.getOriginalFilename().lastIndexOf(".") + 1);
            if (!UploadConfig.isAllow(suffix, file.getSize())) {
                return 0;
            }
            if (!ExcelUtil.checkEnd(file.getOriginalFilename())) {
                return 0;
            }
            InputStream is = file.getInputStream();
            Map<String, List<List<Object>>> titleAndDataMap = ExcelUtil.getExcelData(is, fileName);//单个excel获取的数据
            execlData.add(titleAndDataMap);
        }
        if(execlData.isEmpty()){
            return 0;
        }
        List<Order> orders = new ArrayList<Order>();
        for(Map<String, List<List<Object>>> titleAndDataMap :  execlData){
            List<List<Object>> dataList = titleAndDataMap.get("datas");
            if(CollectionUtils.isEmpty(dataList)){
                continue;
            }
            for(List<Object> cellList : dataList){
                Order order = buildOrder(cellList);
                if(order == null){
                    continue;
                }
                orders.add(order);
            }
        }

        if(CollectionUtils.isEmpty(orders)){
            return 0;
        }
        orderService.addBatch(orders);
        return 1;
    }

    private Order buildOrder(List<Object> cellList){
        if(CollectionUtils.isEmpty(cellList)){
            return null;
        }
        Order order = new Order();
        order.setBoxNum(Integer.parseInt(cellList.get(0).toString()));
        order.setName(cellList.get(1).toString());
        order.setBrand(cellList.get(2).toString());
        order.setSpecs(cellList.get(3).toString());
        order.setNum(Integer.parseInt(cellList.get(4).toString()));
        order.setPrice(new BigDecimal(cellList.get(5).toString()));
        order.setTotalPrice(new BigDecimal(cellList.get(6).toString()));
        order.setNetWeight(cellList.get(7).toString());
        order.setGrossWeight(cellList.get(8).toString());
        order.setRecipientName(cellList.get(9).toString());
        order.setRecipientPhone(cellList.get(10).toString());
        order.setRecipientAddress(cellList.get(11).toString());
        order.setRecipientId(cellList.get(12).toString());
        order.setOrderNum(cellList.get(13).toString());
        order.setExpressNum(cellList.get(14).toString());
        order.setCreateTime(new Date());
        order.setUpdateTime(new Date());
        return order;
    }


    private class PrintRunner implements Runnable {
        private OrderService orderService;
        private Order order;
        public PrintRunner(OrderService orderService, Order order) {
            this.orderService = orderService;
            this.order = order;
        }
        @Override
        public void run() {
            try {
                orderService.print(order);
            }catch (Exception e){
                logger.error("",e);
            }
        }
    }
}