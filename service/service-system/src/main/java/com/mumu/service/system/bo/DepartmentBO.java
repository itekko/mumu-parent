package com.mumu.service.system.bo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 部门BO
 * @author ekko
 * @create 2021-12-02 17:29:17
 */
@Getter
@Setter
@ToString
public class DepartmentBO {

    /**
     * id
     */
    private String id;

    /**
     * 父级部门
     */
    private String pid;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 排序权重
     */
    private Integer orderNo;

    /**
     * 备注
     */
    private String remarks;

}

