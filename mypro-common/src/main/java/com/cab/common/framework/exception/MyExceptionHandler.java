package com.cab.common.framework.exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 2018/5/18.
 */
public class MyExceptionHandler implements HandlerExceptionResolver {

    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
                                         Exception ex) {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("ex", ex);

        // 根据不同错误转向不同页面
        if (ex instanceof ServiceException) {
            return new ModelAndView("exception", model);
        } else if (ex instanceof ControllerException) {
            return new ModelAndView("exception", model);
        } else {
            return new ModelAndView("500", model);
        }
    }

}
