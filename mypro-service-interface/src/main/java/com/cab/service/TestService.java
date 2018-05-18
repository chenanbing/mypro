package com.cab.service;

import com.cab.bean.entity.domains.Domains;
import com.cab.bean.view.TestBean;
import com.cab.common.framework.exception.ServiceException;

/**
 * Created by admin on 2018/5/18.
 */
public interface TestService {

    public Domains test(int id);

    public void testException() throws ServiceException;
}
