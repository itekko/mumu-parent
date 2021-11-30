package com.mumu.service.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mumu.db.system.dao.RoleDao;
import com.mumu.service.system.bo.RoleBO;
import com.mumu.service.system.service.RoleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * 角色服务实现类
 * @author ekko
 * @description
 * @create 2021-11-30 9:53 上午
 */
@Slf4j
@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleDao roleDao;

    @Override
    public Boolean save(RoleBO roleBO) {

        return null;
    }

    @Override
    public Boolean update(RoleBO roleBO) {
        return null;
    }

    @Override
    public Boolean remove(Serializable id) {
        return null;
    }

    @Override
    public RoleBO get(Serializable id) {
        return null;
    }

    @Override
    public IPage<RoleBO> page(RoleBO roleBO, Integer pageNum, Integer pageSize) {
        return null;
    }

    @Override
    public List<RoleBO> list(RoleBO roleBO) {
        return null;
    }
}
