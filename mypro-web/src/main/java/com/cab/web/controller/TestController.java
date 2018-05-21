package com.cab.web.controller;

import com.cab.bean.entity.test.Test;
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
    public Test test(HttpServletResponse response, int id) throws Exception {
        return testService.test(id);
    }
}