package com.mumu.db.system.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mumu.db.system.dao.DictionaryDao;
import com.mumu.db.system.entity.Dictionary;
import com.mumu.db.system.mapper.DictionaryMapper;
import org.springframework.stereotype.Service;


/**
 * 字典Dao层实现
 * @author ekko
 * @create 2021-12-01 17:54:49
 */
@Service
public class DictionaryDaoImpl extends ServiceImpl<DictionaryMapper, Dictionary> implements DictionaryDao {

}
