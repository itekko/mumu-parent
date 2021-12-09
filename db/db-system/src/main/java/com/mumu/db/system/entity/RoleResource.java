package com.mumu.db.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import com.mumu.db.common.entity.BaseEntity;

/**
 * 角色资源中间实体类
 * @author ekko
 * @create 2021-12-06 15:54:20
 */
@Getter
@Setter
@TableName("system_role_resource")
public class RoleResource extends BaseEntity {

    /**
     * 角色ID
     */
    private String roleId;

    /**
     * 资源ID
     */
    private String resourceId;

}

