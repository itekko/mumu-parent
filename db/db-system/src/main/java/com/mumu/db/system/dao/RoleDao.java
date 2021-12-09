package com.mumu.db.system.dao;


import com.baomidou.mybatisplus.extension.service.IService;
import com.mumu.db.system.entity.Role;

import java.util.List;

/**
 * 角色Dao层
 * @author ekko
 * @create 2021-12-02 16:48:31
 */
public interface RoleDao extends IService<Role> {

    /**
     * 根据userId获取role列表
     * @param id
     * @return
     */
    List<Role> getRoleByUserId(String id);
}
