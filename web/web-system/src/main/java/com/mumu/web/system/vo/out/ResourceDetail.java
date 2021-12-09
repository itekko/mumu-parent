package com.mumu.web.system.vo.out;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 资源详情
 * @author ekko
 * @create 2021-12-02 16:48:31
 */
@Getter
@Setter
@ToString
public class ResourceDetail {

    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "父id")
    private String pid;

    /**
     * 权限名称
     */
    @ApiModelProperty(value = "权限名称")
    private String name;

    /**
     * css样式
     */
    @ApiModelProperty(value = "css样式")
    private String cssClass;

    /**
     * url地址
     */
    @ApiModelProperty(value = "url地址")
    private String url;

    /**
     * 权限标识
     */
    @ApiModelProperty(value = "权限标识")
    private String permission;


    /**
     * 排序权重
     */
    @ApiModelProperty(value = "排序权重")
    private String orderNo;

    /**
     * 类型
     */
    @ApiModelProperty(value = "类型")
    private String type;

}
