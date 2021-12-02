package com.mumu.service.system.bo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 字典BO
 * @author ekko
 * @create 2021-12-01 17:54:49
 */
@Getter
@Setter
@ToString
public class DictionaryBO {

    /**
     * id
     */
    private String id;

    /**
     * 名称
     */
    private String name;

    /**
     * 值
     */
    private String dictionaryValue;

    /**
     * 模块;分组使用,建议用英文、数字、下划线组合
     */
    private String module;

    /**
     * 父级ID;当为0时，代表顶级
     */
    private String pid;

    /**
     * 备注
     */
    private String remarks;

}

