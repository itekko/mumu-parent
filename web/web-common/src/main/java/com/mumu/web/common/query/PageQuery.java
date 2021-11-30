package com.mumu.web.common.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author ekko
 * 分页查询Query类
 * @param <T>
 */
@ApiModel(value = "分页查询对象")
@Data
public class PageQuery<T> {

    /**
     * 默认当前页
     */
    public static final int DEFAULT_CURRENT = 1;

    /**
     * 每页显示条数
     */
    public static final int DEFAULT_SIZE = 20;

    /**
     * 查询数据实体
     */
    @ApiModelProperty(value = "查询数据入参")
    private T search;

    /**
     * 当前页
     */
    @ApiModelProperty(value = "当前页")
    private Integer current = DEFAULT_CURRENT;

    /**
     * 每页显示条数
     */
    @ApiModelProperty(value = "每页显示条数")
    private Integer size = DEFAULT_SIZE;





}
