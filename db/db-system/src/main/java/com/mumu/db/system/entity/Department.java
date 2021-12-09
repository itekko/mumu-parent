package com.mumu.db.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import com.mumu.db.common.entity.BaseEntity;

/**
 * 部门实体类
 * @author ekko
 * @create 2021-12-06 15:54:20
 */
@Getter
@Setter
@TableName("system_department")
public class Department extends BaseEntity {

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

