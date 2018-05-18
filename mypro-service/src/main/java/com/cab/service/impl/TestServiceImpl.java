package com.cab.service.impl;

import com.cab.bean.entity.domains.Domains;
import com.cab.bean.view.TestBean;
import com.cab.common.framework.exception.ServiceException;
import com.cab.dao.mapper.domains.DomainsMapper;
import com.cab.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by admin on 2018/5/18.
 */
@Component
public class TestServiceImpl implements TestService {

    @Autowired
    private DomainsMapper domainsMapper;

    @Override
    public Domains test(int id) {
        Domains d = domainsMapper.selectByPrimaryKey(id);
        return d;
    }

    @Override
    public void testException() throws ServiceException {
        throw new ServiceException("service异常");
    }
}
