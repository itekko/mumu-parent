package com.mumu.service.system.bo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 角色资源中间BO
 * @author ekko
 * @create 2021-12-02 16:48:31
 */
@Getter
@Setter
@ToString
public class RoleResourceBO {

    /**
     * id
     */
    private String id;

    /**
     * 角色ID
     */
    private String roleId;

    /**
     * 资源ID
     */
    private String resourceId;

}

