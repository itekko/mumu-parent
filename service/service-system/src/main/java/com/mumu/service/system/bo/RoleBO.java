package com.mumu.service.system.bo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 角色BO
 * @author ekko
 * @create 2021-12-02 16:48:31
 */
@Getter
@Setter
@ToString
public class RoleBO {

    /**
     * id
     */
    private String id;

    /**
     * 角色编码
     */
    private String code;

    /**
     * 权限类型
     */
    private String permissionType;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 状态
     */
    private Integer status;

}

