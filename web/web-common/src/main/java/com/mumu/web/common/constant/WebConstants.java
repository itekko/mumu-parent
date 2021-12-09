package com.mumu.web.common.constant;

/**
 * 请求常量
 * @author ekko
 * @description
 * @create 2021-11-25 7:44 下午
 */
public final class WebConstants {

    private WebConstants(){}

    /**
     * 列表请求
     */
    public static final String LIST = "list";

    /**
     * 分页
     */
    public static final String PAGE = "page";

    /**
     * 详情
     */
    public static final String DETAIL = "get/{id}";

    /**
     * 删除
     */
    public static final String REMOVE = "remove/{id}";

    /**
     * 批量
     */
    public static final String BATCH_REMOVE = "batchRemove";
}
