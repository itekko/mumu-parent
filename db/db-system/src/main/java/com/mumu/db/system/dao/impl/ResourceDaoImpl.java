package com.mumu.db.system.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mumu.db.system.dao.ResourceDao;
import com.mumu.db.system.entity.Resource;
import com.mumu.db.system.mapper.ResourceMapper;
import org.springframework.stereotype.Service;


/**
 * 资源Dao层实现
 * @author ekko
 * @create 2021-12-02 16:48:31
 */
@Service
public class ResourceDaoImpl extends ServiceImpl<ResourceMapper, Resource> implements ResourceDao {

}
