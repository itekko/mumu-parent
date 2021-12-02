package com.mumu.service.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mumu.common.constant.CodeConstants;
import com.mumu.common.constant.CommonConstants;
import com.mumu.db.common.builder.BaseBuilder;
import com.mumu.db.system.dao.RoleDao;
import com.mumu.db.system.entity.Role;
import com.mumu.service.system.bo.RoleBO;
import com.mumu.service.system.service.RoleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

    /**
     * 新增
     * @param roleBO
     * @return
     */
    @Override
    public Boolean save(RoleBO roleBO) {
        return roleDao.save(BaseBuilder.copyProperties(roleBO, Role.class));
    }

    /**
     * 更新
     * @param roleBO
     * @return
     */
    @Override
    public Boolean update(RoleBO roleBO) {
        Role old = roleDao.getById(roleBO.getId());
        Assert.notNull(old,CodeConstants.DATA_NOT_EXIST);
        Role role = BaseBuilder.copyProperties(roleBO, Role.class);
        role.setRevision(old.getRevision());
        return roleDao.updateById(role);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @Override
    public Boolean remove(Serializable id) {
        Assert.notNull(roleDao.getById(id),CodeConstants.DATA_NOT_EXIST);
        return roleDao.removeById(id);
    }

    @Override
    public Boolean batchRemove(List<Serializable> ids) {
        return roleDao.removeByIds(ids);
    }

    /**
     * 查询
     * @param id
     * @return
     */
    @Override
    public RoleBO get(Serializable id) {
        Role role = roleDao.getById(id);
        Assert.notNull(role,CodeConstants.DATA_NOT_EXIST);
        return BaseBuilder.copyProperties(role,RoleBO.class);
    }

    /**
     * 分页
     * @param roleBO
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public IPage<RoleBO> page(RoleBO roleBO, Integer pageNum, Integer pageSize) {
        Page<Role> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>(BaseBuilder.copyProperties(roleBO, Role.class));
        queryWrapper.orderByDesc(CommonConstants.COMMON_TABLE_ID);
        page = roleDao.page(page, queryWrapper);
        Page<RoleBO> result = new Page<>();
        BeanUtils.copyProperties(page,result);
        List<Role> records = page.getRecords();
        if(CollectionUtils.isEmpty(records)){
            result.setRecords(Collections.emptyList());
            return result;
        }

        result.setRecords(records.stream().map(it -> BaseBuilder.copyProperties(it,RoleBO.class)).collect(Collectors.toList()));
        return result;
    }

    /**
     * 列表
     * @param roleBO
     * @return
     */
    @Override
    public List<RoleBO> list(RoleBO roleBO) {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>(BaseBuilder.copyProperties(roleBO, Role.class));
        queryWrapper.orderByDesc(CommonConstants.COMMON_TABLE_ID);
        List<Role> list = roleDao.list(queryWrapper);
        return CollectionUtils.isEmpty(list) ? Collections.emptyList(): list.stream().map(it -> BaseBuilder.copyProperties(it,RoleBO.class)).collect(Collectors.toList());
    }
}
