package com.cab.service.impl;

import com.cab.bean.entity.test.Test;
import com.cab.dao.mapper.test.TestMapper;
import com.cab.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by admin on 2018/5/18.
 */
@SuppressWarnings("all")
@Component
public class TestServiceImpl implements TestService {

    @Autowired
    private TestMapper testMapper;

    @Override
    public Test test(int id) {
        Test test = testMapper.selectByPrimaryKey(id);
        return test;
    }
}
