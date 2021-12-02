package com.mumu.db.generator.mapper;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author ekko
 */
public interface GeneratorMapper {

    /**
     * 查询表信息列表
     * @return
     */
    @Select("select table_name tableName, engine, table_comment tableComment, create_time createTime from information_schema.tables where table_schema = (select database()) order by createTime desc")
    List<Map<String, Object>> list();

    /**
     * 统计数据库表的个数
     * @param map
     * @return
     */
    @Select("select count(*) from information_schema.tables where table_schema = (select database())")
    int count(Map<String, Object> map);

    /**
     * 查询某个表信息
     * @param tableName
     * @return
     */
    @Select("select table_name tableName, engine, table_comment tableComment, create_time createTime from information_schema.tables where table_schema = (select database()) and table_name = #{tableName}")
    Map<String, String> get(String tableName);

    /**
     * 查询表的列属性列表
     * @param tableName
     * @return
     */
    @Select("select column_name columnName, data_type dataType, column_comment columnComment, column_key columnKey, extra from information_schema.columns where table_name = #{tableName} and table_schema = (select database()) order by ordinal_position")
    List<Map<String, String>> listColumns(String tableName);
}
