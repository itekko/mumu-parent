package com.mumu.service.system.bo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 角色BO
 * @author ekko
 * @description
 * @create 2021-11-30 9:50 上午
 */
@Getter
@Setter
@ToString
public class RoleBO {

    /**
     * 角色编码
     */
    private String code;

    /**
     * 角色名称
     */
    private String name;
}
