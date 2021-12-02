/**
 * 
 */
package com.mumu.service.generator.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 代码生成服务类
 * @author ekko
 */
@Service
public interface GeneratorService {

	/**
	 * 查询表信息列表
	 * @return
	 */
	List<Map<String, Object>> list();

	/**
	 * 根据表列表生成二进制数组
	 * @param tableNames
	 * @return
	 */
	byte[] generatorCode(List<String> tableNames);
}
