package com.mumu.web.system.vo.in;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

/**
 * @author ekko
 * @description
 * @create 2021-12-03 6:37 下午
 */
@Getter
@Setter
@ToString
public class UserInfoVO {

    @ApiModelProperty(value = "id")
    private String id;
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String username;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "角色集合")
    private Set<String> roles;
}
