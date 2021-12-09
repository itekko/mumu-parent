package com.mumu.web.system.vo.out;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 角色资源中间列表
 * @author ekko
 * @create 2021-12-02 16:48:31
 */
@Getter
@Setter
@ToString
public class RoleResourceList {

    @ApiModelProperty(value = "id")
    private String id;

    /**
     * 角色ID
     */
    @ApiModelProperty(value = "角色ID")
    private String roleId;

    /**
     * 资源ID
     */
    @ApiModelProperty(value = "资源ID")
    private String resourceId;

}
