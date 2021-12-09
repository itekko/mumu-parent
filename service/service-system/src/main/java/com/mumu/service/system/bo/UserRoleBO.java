package com.mumu.service.system.bo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 用户角色中间BO
 * @author ekko
 * @create 2021-12-02 16:48:31
 */
@Getter
@Setter
@ToString
public class UserRoleBO {

    /**
     * id
     */
    private String id;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 角色ID
     */
    private String roleId;

}

