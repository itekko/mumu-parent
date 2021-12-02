package com.mumu.service.generator.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author ekko
 *  代码生成器属性类
 */
@ConfigurationProperties(prefix = "generator")
@Data
public class GeneratorProperies {

    /**
     * 需要生成代码的表名列表
     */
    private List<String> tables;

    /**
     * 代码生成路径
     */
    private String outPutPath;

    /**
     * 表前缀
     */
    private String tablePrefix;

    /**
     * 是否自动移除表前缀
     */
    private String autoRemovePre;

    /**
     * 父级包
     */
    private String importPackage;

    /**
     * 作者
     */
    private String author;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 模块
     */
    private String moduleName;

    /**
     * 数据库与java映射关系
     */
    private List<DbToJavaTypeMapper> mappers;

    /**
     * 公共字段
     */
    private List<String> commonFields;

    /**
     * 父BO
     */
    private String superDo;

    /**
     * 父Mapper
     */
    private String superMapper;

    /**
     * 父Service
     */
    private String superService;

    /**
     * 父ServiceImpl
     */
    private String superServiceImpl;

    /**
     * 父Controller
     */
    private String superController;

    /**
     * 是否用jdk8时间类型,是：1，否：0
     */
    private Integer usedjdk8TimeType = 0;

}

