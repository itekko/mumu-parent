package com.mumu.service.system.bo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 资源BO
 * @author ekko
 * @create 2021-12-02 16:48:31
 */
@Getter
@Setter
@ToString
public class ResourceBO {

    /**
     * id
     */
    private String id;

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
     * url地址
     */
    private String url;

    /**
     * 权限标识
     */
    private String permission;

    /**
     * 值
     */
    private String dictionaryValue;

    /**
     * 排序权重
     */
    private String orderNo;

    /**
     * 类型
     */
    private String type;

}

