package com.mumu.db.system.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mumu.db.system.dao.UserRoleDao;
import com.mumu.db.system.entity.UserRole;
import com.mumu.db.system.mapper.UserRoleMapper;
import org.springframework.stereotype.Service;


/**
 * 用户角色中间Dao层实现
 * @author ekko
 * @create 2021-12-02 16:48:31
 */
@Service
public class UserRoleDaoImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleDao {

}
