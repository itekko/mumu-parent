package com.mumu.web.system.vo.out;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 字典详情
 * @author ekko
 * @create 2021-12-06 15:54:20
 */
@Getter
@Setter
@ToString
public class DictionaryDetail {

    @ApiModelProperty(value = "id")
    private String id;

    /**
     * code编码
     */
    @ApiModelProperty(value = "code编码")
    private String code;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String name;

    /**
     * 值
     */
    @ApiModelProperty(value = "值")
    private String dictionaryValue;

    /**
     * 排序权重
     */
    @ApiModelProperty(value = "排序权重")
    private Integer orderNo;

    /**
     * 模块;分组使用,建议用英文、数字、下划线组合
     */
    @ApiModelProperty(value = "模块;分组使用,建议用英文、数字、下划线组合")
    private String module;

    /**
     * 父级ID;当为0时，代表顶级
     */
    @ApiModelProperty(value = "父级ID;当为0时，代表顶级")
    private String pid;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remarks;

}
