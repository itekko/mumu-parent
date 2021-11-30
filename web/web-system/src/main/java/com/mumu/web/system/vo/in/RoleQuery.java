package com.mumu.web.system.vo.in;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author ekko
 * @description
 * @create 2021-11-30 4:38 下午
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
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称")
    private String name;
}
