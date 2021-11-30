package com.mumu.web.common.util;

import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Request工具类
 * @author ekko
 * @description
 * @create 2021-11-26 6:09 下午
 */
public final class RequestUtils {

    private RequestUtils(){}

    /**
     * 获取HttpServletRequest
     * @return
     */
    public static HttpServletRequest getHttpServletRequest() {
        // 获取controller层中request
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        Assert.notNull(attributes,"工具类只能在web环境下使用");
        return attributes.getRequest();
    }
}
