package com.mumu.service.generator.config;

import lombok.Data;

/**
 * @author ekko
 * @description
 * @create 2021-12-01 10:28 上午
 */
@Data
public class DbToJavaTypeMapper {

    /**
     * 数据库类型
     */
    private String dbType;

    /**
     * java类型
     */
    private String javaType;
}
