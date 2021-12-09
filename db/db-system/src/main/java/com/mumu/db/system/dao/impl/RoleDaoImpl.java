package com.mumu.db.system.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mumu.db.system.dao.RoleDao;
import com.mumu.db.system.entity.Role;
import com.mumu.db.system.mapper.RoleMapper;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 角色Dao层实现
 * @author ekko
 * @create 2021-12-02 16:48:31
 */
@Service
public class RoleDaoImpl extends ServiceImpl<RoleMapper, Role> implements RoleDao {

    /**
     * 根据userId获取role列表
     * @param id
     * @return
     */
    @Override
    public List<Role> getRoleByUserId(String id) {
        return baseMapper.selectListByUserId(id);
    }
}
