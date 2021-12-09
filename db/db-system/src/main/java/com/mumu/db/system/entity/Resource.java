package com.mumu.db.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.mumu.db.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * 资源实体类
 * @author ekko
 * @create 2021-12-06 15:54:20
 */
@Getter
@Setter
@TableName("system_resource")
public class Resource extends BaseEntity {

    /**
     * 父ID
     */
    private String pid;

    /**
     * 权限名称
     */
    private String name;

    /**
     * css样式
     */
    private String cssClass;

    /**
     * 后台url地址
     */
    private String url;

    /**
     * http请求方法
     */
    private String httpMethod;

    /**
     * 前端路由地址
     */
    private String routePath;

    /**
     * 前端组件名称
     */
    private String componentName;

    /**
     * 前端组件路径
     */
    private String componentPath;

    /**
     * 权限标识
     */
    private String permission;


    /**
     * 排序权重
     */
    private Integer orderNo;

    /**
     * 类型(字典PERMISSION_TYPE)
     */
    private String type;

}

