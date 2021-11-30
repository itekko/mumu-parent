package com.mumu.web.common.interceptor;

import cn.hutool.extra.spring.SpringUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * 自定义拦截器-扩展springboot默认国际化功能，可以在request中添加"language"字段，自由切换国际化
 * @author ekko
 * @description
 * @create 2021-11-24 5:24 下午
 */
@AllArgsConstructor
@Slf4j
public class CustomizeLocaleChangeInterceptor extends LocaleChangeInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException {

        // request中获取参数"lang"的值
        String newLocale = request.getParameter(getParamName());

        // 如果从request中获取参数为空，从请求头"Accept-Language"中获取
        if(!StringUtils.hasLength(newLocale)){
            Locale locale = SpringUtil.getBean(LocaleResolver.class).resolveLocale(request);
            Assert.notNull(locale,"Configuration i18 error");
            newLocale = locale.toString();
        }
            if (newLocale != null && checkHttpMethods(request.getMethod())) {
                LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
                if (localeResolver == null) {
                    throw new IllegalStateException(
                            "No LocaleResolver found: not in a DispatcherServlet request?");
                }
                try {
                    localeResolver.setLocale(request, response, parseLocaleValue(newLocale));
                }
                catch (IllegalArgumentException ex) {
                    if (isIgnoreInvalidLocale()) {
                        if (logger.isDebugEnabled()) {
                            logger.debug("Ignoring invalid locale value [" + newLocale + "]: " + ex.getMessage());
                        }
                    }
                    else {
                        throw ex;
                    }
                }
            }
        // Proceed in any case.
        return true;
    }

    private boolean checkHttpMethods(String currentMethod) {
        String[] configuredMethods = getHttpMethods();
        if (ObjectUtils.isEmpty(configuredMethods)) {
            return true;
        }
        for (String configuredMethod : configuredMethods) {
            if (configuredMethod.equalsIgnoreCase(currentMethod)) {
                return true;
            }
        }
        return false;
    }
}
