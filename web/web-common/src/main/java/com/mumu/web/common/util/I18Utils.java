package com.mumu.web.common.util;

import cn.hutool.extra.spring.SpringUtil;
import org.springframework.web.servlet.LocaleResolver;

/**
 * 国际化工具类
 * @author ekko
 * @description
 * @create 2021-11-26 6:11 下午
 */
public final class I18Utils {

    private I18Utils(){}

    /**
     * 国家化处理message
     * @param code
     * @param args
     * @return
     */
    public static String getMessage(String code,Object[] args){
        return SpringUtil.getApplicationContext().getMessage(code,args,
                SpringUtil.getBean(LocaleResolver.class).resolveLocale(RequestUtils.getHttpServletRequest()));
    }
}
