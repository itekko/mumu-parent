package com.mumu.web.system.vo.in;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 部门新增
 * @author ekko
 * @create 2021-12-02 17:29:17
 */
@Getter
@Setter
@ToString
public class DepartmentSave {

    /**
     * 父级部门
     */
    @ApiModelProperty(value = "父级部门")
    private String pid;

    /**
     * 部门名称
     */
    @ApiModelProperty(value = "部门名称")
    private String name;

    /**
     * 排序权重
     */
    @ApiModelProperty(value = "排序权重")
    private Integer orderNo;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remarks;

}
