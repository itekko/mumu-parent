package com.mumu.db.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import com.mumu.db.common.entity.BaseEntity;

/**
 * 字典实体类
 * @author ekko
 * @create 2021-12-06 15:54:20
 */
@Getter
@Setter
@TableName("system_dictionary")
public class Dictionary extends BaseEntity {

    /**
     * code编码
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 值
     */
    private String dictionaryValue;

    /**
     * 排序权重
     */
    private Integer orderNo;

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

