package com.mumu.service.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mumu.common.constant.CodeConstants;
import com.mumu.common.constant.CommonConstants;
import com.mumu.db.common.builder.BaseBuilder;
import com.mumu.db.system.dao.RoleResourceDao;
import com.mumu.db.system.entity.RoleResource;
import com.mumu.service.system.bo.RoleResourceBO;
import com.mumu.service.system.service.RoleResourceService;
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
 * 角色资源中间服务层实现
 * @author ekko
 * @create 2021-12-02 16:48:31
 */
@Slf4j
@Service
@AllArgsConstructor
public class RoleResourceServiceImpl implements RoleResourceService {

    private final RoleResourceDao roleResourceDao;

    /**
     * 新增
     * @param roleResourceBO
     * @return
     */
    @Override
    public Boolean save(RoleResourceBO roleResourceBO) {
        return roleResourceDao.save(BaseBuilder.copyProperties(roleResourceBO, RoleResource.class));
    }

    /**
     * 更新
     * @param roleResourceBO
     * @return
     */
    @Override
    public Boolean update(RoleResourceBO roleResourceBO) {
        RoleResource old = roleResourceDao.getById(roleResourceBO.getId());
        Assert.notNull(old,CodeConstants.DATA_NOT_EXIST);
        RoleResource roleResource = BaseBuilder.copyProperties(roleResourceBO, RoleResource.class);
        roleResource.setRevision(old.getRevision());
        return roleResourceDao.updateById(roleResource);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @Override
    public Boolean remove(Serializable id) {
        Assert.notNull(roleResourceDao.getById(id),CodeConstants.DATA_NOT_EXIST);
        return roleResourceDao.removeById(id);
    }

    @Override
    public Boolean batchRemove(List<Serializable> ids) {
        return roleResourceDao.removeByIds(ids);
    }

    /**
     * 查询
     * @param id
     * @return
     */
    @Override
    public RoleResourceBO get(Serializable id) {
        RoleResource roleResource = roleResourceDao.getById(id);
        Assert.notNull(roleResource,CodeConstants.DATA_NOT_EXIST);
        return BaseBuilder.copyProperties(roleResource,RoleResourceBO.class);
    }

    /**
     * 分页
     * @param roleResourceBO
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public IPage<RoleResourceBO> page(RoleResourceBO roleResourceBO, Integer pageNum, Integer pageSize) {
        Page<RoleResource> page = new Page<>(pageNum, pageSize);
        QueryWrapper<RoleResource> queryWrapper = new QueryWrapper<>(BaseBuilder.copyProperties(roleResourceBO, RoleResource.class));
        queryWrapper.orderByDesc(CommonConstants.COMMON_TABLE_ID);
        page = roleResourceDao.page(page, queryWrapper);
        Page<RoleResourceBO> result = new Page<>();
        BeanUtils.copyProperties(page,result);
        List<RoleResource> records = page.getRecords();
        if(CollectionUtils.isEmpty(records)){
            result.setRecords(Collections.emptyList());
            return result;
        }

        result.setRecords(records.stream().map(it -> BaseBuilder.copyProperties(it,RoleResourceBO.class)).collect(Collectors.toList()));
        return result;
    }

    /**
     * 列表
     * @param roleResourceBO
     * @return
     */
    @Override
    public List<RoleResourceBO> list(RoleResourceBO roleResourceBO) {
        QueryWrapper<RoleResource> queryWrapper = new QueryWrapper<>(BaseBuilder.copyProperties(roleResourceBO, RoleResource.class));
        queryWrapper.orderByDesc(CommonConstants.COMMON_TABLE_ID);
        List<RoleResource> list = roleResourceDao.list(queryWrapper);
        return CollectionUtils.isEmpty(list) ? Collections.emptyList(): list.stream().map(it -> BaseBuilder.copyProperties(it,RoleResourceBO.class)).collect(Collectors.toList());
    }
}
