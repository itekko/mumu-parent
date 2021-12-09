package com.mumu.db.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import com.mumu.db.common.entity.BaseEntity;

/**
 * 角色实体类
 * @author ekko
 * @create 2021-12-06 15:54:16
 */
@Getter
@Setter
@TableName("system_role")
public class Role extends BaseEntity {

    /**
     * 角色编码
     */
    private String code;

    /**
     * 权限类型(字典DATA_PERMISSION_TYPE)
     */
    private Integer permissionType;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 状态(字典STATUS)
     */
    private Integer status;

}

