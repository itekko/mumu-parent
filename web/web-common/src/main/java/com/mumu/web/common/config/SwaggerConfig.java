package com.mumu.web.common.config;

import com.mumu.web.common.properties.SwaggerProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * @author ekko
 * @apiNote 轻量级的Swagger生成Api文档
 * @date 2021/10/28 11:15
 */
@Profile("dev")
@Configuration(proxyBeanMethods = false)
@EnableSwagger2WebMvc
@Slf4j
@EnableConfigurationProperties(SwaggerProperties.class)
public class SwaggerConfig {


    @Bean(value = "defaultApi2")
    public Docket defaultApi2(SwaggerProperties swaggerProperties) {
        log.info("启动swagger配置");
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .description(swaggerProperties.getDescription())
                        .termsOfServiceUrl(swaggerProperties.getTermsOfServiceUrl())
                        .version(swaggerProperties.getVersion())
                        .build())
                //分组名称
                .groupName(swaggerProperties.getGroupName())
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage(swaggerProperties.getBasePackage()))
                .paths(PathSelectors.any())
                .build();
    }

}

