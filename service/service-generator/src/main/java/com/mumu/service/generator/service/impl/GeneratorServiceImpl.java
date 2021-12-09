package com.mumu.service.generator.service.impl;


import com.mumu.db.generator.mapper.GeneratorMapper;
import com.mumu.service.generator.config.GeneratorProperies;
import com.mumu.service.generator.service.GeneratorService;
import com.mumu.service.generator.util.GenUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

/**
 * 代码生成服务类-实现
 * @author ekko
 */
@Service
@Slf4j
@AllArgsConstructor
@EnableConfigurationProperties(GeneratorProperies.class)
public class GeneratorServiceImpl implements GeneratorService {

    private final GeneratorMapper generatorMapper;

    private final GeneratorProperies generatorProperies;
    /**
     * 查询表信息列表
     * @return
     */
    @Override
    public List<Map<String, Object>> list() {
        return generatorMapper.list();
    }

    /**
     * 根据表列表生成二进制数组
     * @param tableNames
     * @return
     */
    @Override
    public byte[] generatorCode(List<String> tableNames) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        for (String tableName : tableNames) {
            // 查询表信息
            Map<String, String> table = generatorMapper.get(tableName);
            // 查询列信息
            List<Map<String, String>> columns = generatorMapper.listColumns(tableName);
            // 生成代码
            GenUtils.generatorCode(table, columns, zip,generatorProperies);
        }
        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }

}
