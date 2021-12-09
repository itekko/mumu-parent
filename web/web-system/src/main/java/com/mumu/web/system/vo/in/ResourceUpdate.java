package com.mumu.web.system.vo.in;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 资源更新
 * @author ekko
 * @create 2021-12-02 16:48:31
 */
@Getter
@Setter
@ToString
public class ResourceUpdate {

    @ApiModelProperty(value = "父id")
    private String id;

    /**
     * 父ID
     */
    @ApiModelProperty(value = "id")
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
     * http请求方法
     */
    @ApiModelProperty(value = "http请求方法")
    private String httpMethod;

    /**
     * 前端路由地址
     */
    @ApiModelProperty(value = "前端路由地址")
    private String routePath;

    /**
     * 前端组件名称
     */
    @ApiModelProperty(value = "前端组件名称")
    private String componentName;

    /**
     * 前端组件路径
     */
    @ApiModelProperty(value = "前端组件路径")
    private String componentPath;

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
