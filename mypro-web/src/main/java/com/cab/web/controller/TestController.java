package com.cab.web.controller;

import com.cab.bean.entity.domains.Domains;
import com.cab.bean.view.TestBean;
import com.cab.common.framework.exception.ControllerException;
import com.cab.service.TestService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("test")
public class TestController {
    Logger logger = Logger.getLogger(this.getClass());

    @Resource
    private TestService testService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public Domains test(HttpServletResponse response, int id) throws Exception {
        return testService.test(id);
    }

    @RequestMapping(value = "/test1", method = RequestMethod.GET)
    @ResponseBody
    public String test1(HttpServletResponse response, String name) throws Exception {
        return name;
    }

    @RequestMapping(value = "/test2", method = RequestMethod.GET)
    @ResponseBody
    public TestBean test2(HttpServletResponse response, TestBean bean) throws Exception {

        return bean;
    }

    @RequestMapping(value = "/testException", method = RequestMethod.GET)
    public String testException(HttpServletResponse response, int id) throws Exception {

        if(id == 1){
            throw new ControllerException("controller异常");
        }
        if(id == 2){
            testService.testException();
        }

        return "index";
    }

    @RequestMapping(value = "/scan", method = RequestMethod.GET)
    public String scan(HttpServletResponse response) throws Exception {
        return "/print/scan";
    }

    @RequestMapping(value = "/saveScan")
    public String saveScan(HttpServletResponse response,String content) throws Exception {
        System.out.println(content+"---fgfgfg---------------");
        return "/print/scan";
    }

    @RequestMapping(value = "/print")
    public String print(HttpServletResponse response,String content) throws Exception {
        System.out.println(content+"---fgfgfg---------------");
        return "/print/scan";
    }


}