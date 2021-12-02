package com.mumu.db.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.mumu.db.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * 角色实体类
 * @author ekko
 * @description
 * @create 2021-11-29 7:30 下午
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
     * 角色名称
     */
    private String name;
}
