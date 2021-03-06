package com.mumu.db.generator.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 列的属性
 * @author ekko
 */
@Getter
@Setter
@ToString
public class Column {

    /**
     * 列名
     */
    private String columnName;
    /**
     * 列名类型
     */
    private String dataType;
    /**
     * 列名备注
     */
    private String comments;

    /**
     * 属性名称(第一个字母大写)，如：user_name => UserName
     */
    private String attrName;
    /**
     * 属性名称(第一个字母小写)，如：user_name => userName
     */
    private String attrAname;

    /**
     * 属性类型
     */
    private String attrType;

    /**
     * auto_increment
     */
    private String extra;

}
