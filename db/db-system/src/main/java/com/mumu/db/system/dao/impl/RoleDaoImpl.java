package com.mumu.db.system.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mumu.db.system.dao.RoleDao;
import com.mumu.db.system.entity.Role;
import com.mumu.db.system.mapper.RoleMapper;
import org.springframework.stereotype.Service;


/**
 * 角色Dao层实现
 * @author ekko
 * @description
 * @create 2021-11-29 7:40 下午
 */
@Service
public class RoleDaoImpl extends ServiceImpl<RoleMapper, Role> implements RoleDao {

}
