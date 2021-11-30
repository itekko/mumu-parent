package com.mumu.web.common.properties;

import com.mumu.common.constant.CommonConstants;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author ekko
 * @description
 * @create 2021-11-25 5:20 下午
 */
@ConfigurationProperties(prefix = "swagger")
@Getter
@Setter
public class SwaggerProperties {

    /**
     * 描述
     */
    private String description = "mumu快速开发平台";

    /**
     * 团队服务地址
     */
    private String termsOfServiceUrl = "http://127.0.0.1:8888";

    /**
     * 作者
     */
    private String contact = "ekko";

    /**
     * 版本
     */
    private String version = "2.x";

    /**
     * 分组
     */
    private String groupName = "mumu";

    /**
     * 扫描包路径
     */
    private String basePackage = CommonConstants.COMMON_PACKAGE;
}
