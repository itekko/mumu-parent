package com.mumu.web.system.config;

import cn.dev33.satoken.interceptor.SaRouteInterceptor;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author ekko
 * @description
 * @create 2021-12-03 10:13 上午
 */
@EnableConfigurationProperties(SystemProperies.class)
@Configuration
@AllArgsConstructor
public class SaTokenConfig implements WebMvcConfigurer {

    private final SystemProperies systemProperies;

    /**
     * 注册拦截器
      */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册Sa-Token的路由拦截器
        registry.addInterceptor(new SaRouteInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(systemProperies.getExcludePathPatterns());
    }
}
