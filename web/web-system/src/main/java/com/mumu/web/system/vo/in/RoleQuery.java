package com.mumu.web.system.vo.in;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 角色查询
 * @author ekko
 * @create 2021-12-02 16:48:31
 */
@Getter
@Setter
@ToString
public class RoleQuery {

    /**
     * 角色编码
     */
    @ApiModelProperty(value = "角色编码")
    private String code;

    /**
     * 权限类型
     */
    @ApiModelProperty(value = "权限类型")
    private String permissionType;

    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称")
    private String name;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Integer status;

}
