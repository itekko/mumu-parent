package com.mumu.service.system.bo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

/**
 * 用户BO
 * @author ekko
 * @create 2021-12-02 16:48:31
 */
@Getter
@Setter
@ToString
public class UserBO {

    /**
     * id
     */
    private String id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 角色集合
     */
    private Set<String> roles;

    /**
     * 头像
     */
    private String images;
}

