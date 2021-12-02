package com.mumu.service.generator.util;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.CharUtil;
import cn.hutool.core.util.StrUtil;
import com.mumu.db.generator.entity.Column;
import com.mumu.db.generator.entity.Table;
import com.mumu.service.generator.config.GeneratorProperies;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author ekko
 * @Description
 * @create 2021-10-13 7:29 下午
 */
@Slf4j
public class GenUtils {

    /**
     * 分隔符
     */
    public static final String STR_DELIMITER = "_";

    /**
     * 分隔符
     */
    public static final String REPLACE_ENMPLY = "表";

    /**
     * 实体类模版文件名
     */
    public static final String ENTITY_TEMPLATE_FILE_NAME = "entity.java.vm";

    /**
     * Mapper模版
     */
    public static final String MAPPER_TEMPLATE_FILE_NAME = "Mapper.java.vm";

    public static List<String> getTemplates() {
        List<String> templates = new ArrayList<>();
        templates.add("templates/" + ENTITY_TEMPLATE_FILE_NAME);
        templates.add("templates/" + MAPPER_TEMPLATE_FILE_NAME);
        templates.add("templates/Mapper.xml.vm");
        templates.add("templates/Dao.java.vm");
        templates.add("templates/DaoImpl.java.vm");
        templates.add("templates/BO.java.vm");
        templates.add("templates/Service.java.vm");
        templates.add("templates/ServiceImpl.java.vm");
        templates.add("templates/Controller.java.vm");

        templates.add("templates/Save.java.vm");
        templates.add("templates/Update.java.vm");
        templates.add("templates/List.java.vm");
        templates.add("templates/Query.java.vm");
        templates.add("templates/Detail.java.vm");

        return templates;
    }

    /**
     *
     * @param table
     * @param columns
     * @param zip
     * @param generatorProperies
     */
    public static void generatorCode(Map<String, String> table, List<Map<String, String>> columns,
                                     ZipOutputStream zip, GeneratorProperies generatorProperies) {
        // 配置信息
        // 表信息
        Table tableDO = new Table();
        tableDO.setTableName(table.get("tableName"));
        String tableComment = table.get("tableComment");
        if(StringUtils.hasLength(tableComment)){
            tableComment = tableComment.replace(REPLACE_ENMPLY,"");
        }
        tableDO.setComments(tableComment);
        // 表名转换成Java类名
        String className = tableToJava(tableDO.getTableName(), generatorProperies.getTablePrefix(), generatorProperies.getAutoRemovePre());
        tableDO.setClassName(className);
        tableDO.setAclassName(StringUtils.uncapitalize(className));

        // 列信息
        List<Column> columsList = new ArrayList<>();
        List<Column> commonColums = new ArrayList<>();
        Set<String> dataTypes = new HashSet<>(), columnNames = new HashSet<>();
        List<String> baseColumnNames = generatorProperies.getCommonFields();
        for (Map<String, String> column : columns) {
            columnNames.add(column.get("columnName"));
            Column columnDO = new Column();
            columnDO.setColumnName(column.get("columnName"));
            columnDO.setDataType(column.get("dataType"));
            columnDO.setComments(column.get("columnComment"));
            columnDO.setExtra(column.get("extra"));
            // 列名转换成Java属性名
            String attrName = columnToJava(columnDO.getColumnName());
            columnDO.setAttrName(attrName);
            columnDO.setAttrAname(StringUtils.uncapitalize(attrName));

            // 列的数据类型，转换成Java类型
            String attrType = generatorProperies.getMappers().stream().filter(it -> StrUtil.equals(columnDO.getDataType(),it.getDbType())).findFirst().get().getJavaType();
            columnDO.setAttrType(attrType);

            // 是否主键
            if ("PRI".equalsIgnoreCase(column.get("columnKey")) && tableDO.getPk() == null) {
                tableDO.setPk(columnDO);
            }

            if(!CollectionUtils.isEmpty(baseColumnNames) && baseColumnNames.contains(column.get("columnName"))) {
                commonColums.add(columnDO);
            }else{
                columsList.add(columnDO);
                dataTypes.add(column.get("dataType"));
            }
        }
        tableDO.setColumns(columsList);

        // 没主键，则第一个字段为主键
        if (tableDO.getPk() == null) {
            tableDO.setPk(tableDO.getColumns().get(0));
        }

        // 设置velocity资源加载器
        Properties prop = new Properties();
        prop.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        Velocity.init(prop);

        // 封装模板数据
        Map<String, Object> map = new HashMap<>(16);
        map.put("tableName", tableDO.getTableName());
        map.put("comments", tableDO.getComments());
        map.put("pk", tableDO.getPk());
        map.put("className", tableDO.getClassName());
        map.put("classname", tableDO.getAclassName());
        String pack = generatorProperies.getImportPackage();
        map.put("pathName", pack.substring(pack.lastIndexOf(".") + 1));
        map.put("columns", tableDO.getColumns());
        map.put("commonColumns", commonColums);
        map.put("package", pack);
        map.put("author", generatorProperies.getAuthor());
        map.put("email", generatorProperies.getEmail());
        map.put("datetime", DateUtil.format(new Date(), DatePattern.NORM_DATETIME_PATTERN));
        // 字段特性
        map.put("hasBigDecimal", dataTypes.contains("decimal"));
        //时间类型的包含3种，date、datetime、timestamp
        if (dataTypes.contains("date") || dataTypes.contains("datetime") || dataTypes.contains("timestamp")) {
            map.put("hasDatetime", 1);
            map.put("usedjdk8TimeType",generatorProperies.getUsedjdk8TimeType());

        }
        map.put("hasDeleted", columnNames.contains("deleted"));
        map.put("hasVersion", columnNames.contains("version"));
        map.put("hasCreateAt", columnNames.contains("createAt"));
        map.put("hasCreateBy", columnNames.contains("createBy"));
        map.put("hasUpdateAt", columnNames.contains("updateAt"));
        map.put("hasUpdateBy", columnNames.contains("updateBy"));
        map.put("moduleName",generatorProperies.getModuleName());
        map.put("commonFields",generatorProperies.getCommonFields());
        if(StrUtil.isNotBlank(generatorProperies.getSuperDo())){
            map.put("hasBaseDO",1);
            map.put("superDo",generatorProperies.getSuperDo());
            map.put("superDoName",getLastName(generatorProperies.getSuperDo()));
        }
        if(StrUtil.isNotBlank(generatorProperies.getSuperMapper())){
            map.put("hasSuperMapper",1);
            map.put("superMapper",generatorProperies.getSuperMapper());
            map.put("superMapperName",getLastName(generatorProperies.getSuperMapper()));
        }
        if(StrUtil.isNotBlank(generatorProperies.getSuperService())){
            map.put("hasSuperService",1);
            map.put("superService",generatorProperies.getSuperService());
            map.put("superServiceName",getLastName(generatorProperies.getSuperService()));
        }
        if(StrUtil.isNotBlank(generatorProperies.getSuperServiceImpl())){
            map.put("hasSuperServiceImpl",1);
            map.put("superServiceImpl",generatorProperies.getSuperServiceImpl());
            map.put("superServiceImplName",getLastName(generatorProperies.getSuperServiceImpl()));
        }
        if(StrUtil.isNotBlank(generatorProperies.getSuperController())){
            map.put("hasSuperController",1);
            map.put("superController",generatorProperies.getSuperController());
            map.put("superControllerName",getLastName(generatorProperies.getSuperController()));
        }
        VelocityContext context = new VelocityContext(map);

        // 获取模板列表
        List<String> templates = getTemplates();
        for (String template : templates) {
            // 渲染模板
            StringWriter sw = new StringWriter();
            Template tpl = Velocity.getTemplate(template, "UTF-8");
            tpl.merge(context, sw);

            try {
                List<String> split = StrUtil.split(template, CharUtil.SLASH);
                // 添加到zip
                String fileName = getFileName(split.get(split.size() - 1), generatorProperies.getModuleName(), tableDO.getClassName(), pack);
                zip.putNextEntry(new ZipEntry(fileName));
                IOUtils.write(sw.toString(), zip, "UTF-8");
                IOUtils.closeQuietly(sw);
                zip.closeEntry();
            } catch (IOException e) {
                log.warn(e.getMessage());
                log.info("渲染模板失败，表名：" + tableDO.getTableName());
            }
        }
    }

    private static String getLastName(String name) {
        List<String> list = StrUtil.split(name, CharUtil.DOT);
        return list.get(list.size() -1);
    }

    /**
     * 列名转换成Java属性名
     */
    public static String columnToJava(String columnName) {
        if(columnName.contains(STR_DELIMITER)){
            return WordUtils.capitalizeFully(columnName, new char[] { '_' }).replace(STR_DELIMITER, "");
        }else {
            return WordUtils.uncapitalize(columnName);
        }
    }

    /**
     * 表名转换成Java类名
     */
    public static String tableToJava(String tableName, String tablePrefix, String autoRemovePre) {
        boolean removeTablePrefix = "true".equals(autoRemovePre);
        if (removeTablePrefix && StrUtil.isNotBlank(tablePrefix) && tableName.startsWith(tablePrefix)) {
            tableName = tableName.substring(tablePrefix.length());
        }
        if(tableName.contains(STR_DELIMITER)){
            return WordUtils.capitalizeFully(tableName, new char[] { '_' }).replace(STR_DELIMITER, "");
        }else {
            return StringUtils.capitalize(tableName);
        }
    }


    /**
     *
     * @param template
     * @param moduleName
     * @param className
     * @param packageName
     * @return
     */
    public static String getFileName(String template, String moduleName, String className, String packageName) {
        String packagePath = "main" + File.separator + "java" + File.separator;
        if (StrUtil.isNotBlank(packageName)) {
            packagePath += packageName.replace(".", File.separator) + File.separator;
        }

        if (StrUtil.equals("BO.java.vm",template)) {
            return packagePath + "service" + File.separator + moduleName + File.separator +"bo"+ File.separator + className + "BO.java";
        }

        if (StrUtil.equals("Save.java.vm",template)) {
            return packagePath + "web" + File.separator + moduleName + File.separator + "vo"+  File.separator + "in" + File.separator + className + "Save.java";
        }

        if (StrUtil.equals("Update.java.vm",template)) {
            return packagePath + "web" + File.separator + moduleName + File.separator + "vo"+  File.separator + "in" + File.separator + className + "Update.java";
        }

        if (StrUtil.equals("Query.java.vm",template)) {
            return packagePath + "web" + File.separator + moduleName + File.separator + "vo"+  File.separator + "in" + File.separator + className + "Query.java";
        }

        if (StrUtil.equals("Detail.java.vm",template)) {
            return packagePath + "web" + File.separator + moduleName + File.separator + "vo"+  File.separator + "out" + File.separator + className + "Detail.java";
        }

        if (StrUtil.equals("List.java.vm",template)) {
            return packagePath + "web" + File.separator + moduleName + File.separator + "vo"+  File.separator + "out" + File.separator + className + "List.java";
        }

        if (StrUtil.equals("entity.java.vm",template)) {
            return packagePath + "db" + File.separator + moduleName + File.separator + "entity" + File.separator + className + ".java";
        }

        if (StrUtil.equals("Mapper.java.vm",template)) {
            return packagePath + "db" + File.separator + moduleName + File.separator + "mapper" + File.separator + className + "Mapper.java";
        }

        if (StrUtil.equals("Dao.java.vm",template)) {
            return packagePath + "db" + File.separator + moduleName + File.separator + "dao" + File.separator + className + "Dao.java";
        }

        if (StrUtil.equals("DaoImpl.java.vm",template)) {
            return packagePath + "db" + File.separator + moduleName + File.separator + "dao" + File.separator+"impl" +File.separator+ className + "DaoImpl.java";
        }

        if (StrUtil.equals("Service.java.vm",template)) {
            return packagePath + "service" + File.separator + moduleName + File.separator + "service" + File.separator + className + "Service.java";
        }

        if (StrUtil.equals("ServiceImpl.java.vm",template)) {
            return packagePath + "service" + File.separator + moduleName + File.separator + "service" + File.separator+ "impl" + File.separator + className + "ServiceImpl.java";
        }

        if (StrUtil.equals("Controller.java.vm",template)) {
            return packagePath + "controller" + File.separator + moduleName + File.separator + className + "Controller.java";
        }

        if (StrUtil.equals("Mapper.xml.vm",template)) {
            return "main" + File.separator + "resources" + File.separator + "mapper" + File.separator  + moduleName
                    + File.separator + className + "Mapper.xml";
        }


        if (StrUtil.equals("menu.sql.vm",template)) {
            return className.toLowerCase() + "_menu.sql";
        }

        return null;
    }


}


