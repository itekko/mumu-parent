package com.mumu.web.system.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 系统模块属性
 * @author ekko
 * @description
 * @create 2021-12-03 10:49 上午
 */
@ConfigurationProperties(prefix = "system")
@Data
public class SystemProperies {

    /**
     * 登录排除拦截URL Patterns
     */
    private String[] excludePathPatterns;
}
