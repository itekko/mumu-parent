package com.mumu.common.constant;

/**
 * 公共常量类
 * @author ekko
 * @description
 * @create 2021-11-25 9:33 上午
 */
public final class CommonConstants {



    private CommonConstants(){}

    /**
     * Spring公共扫描包
     */
    public static final String COMMON_PACKAGE = "com.mumu";

    /**
     * mybatis公共扫描包(Mapper)
     */
    public static final  String COMMON_MAPPER_PACKAGE = "com.mumu.db.*.mapper";

    /**
     * 所有表的主键名称
     */
    public static final String COMMON_TABLE_ID = "id";


}
