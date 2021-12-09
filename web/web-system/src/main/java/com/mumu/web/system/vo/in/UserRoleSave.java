package com.mumu.web.system.vo.in;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 用户角色中间新增
 * @author ekko
 * @create 2021-12-02 16:48:31
 */
@Getter
@Setter
@ToString
public class UserRoleSave {

    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID")
    private String userId;

    /**
     * 角色ID
     */
    @ApiModelProperty(value = "角色ID")
    private String roleId;

}
