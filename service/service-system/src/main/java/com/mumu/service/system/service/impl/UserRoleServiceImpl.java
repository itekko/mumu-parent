package com.mumu.service.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mumu.common.constant.CodeConstants;
import com.mumu.common.constant.CommonConstants;
import com.mumu.db.common.builder.BaseBuilder;
import com.mumu.db.system.dao.UserRoleDao;
import com.mumu.db.system.entity.UserRole;
import com.mumu.service.system.bo.UserRoleBO;
import com.mumu.service.system.service.UserRoleService;
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
 * 用户角色中间服务层实现
 * @author ekko
 * @create 2021-12-02 16:48:31
 */
@Slf4j
@Service
@AllArgsConstructor
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleDao userRoleDao;

    /**
     * 新增
     * @param userRoleBO
     * @return
     */
    @Override
    public Boolean save(UserRoleBO userRoleBO) {
        return userRoleDao.save(BaseBuilder.copyProperties(userRoleBO, UserRole.class));
    }

    /**
     * 更新
     * @param userRoleBO
     * @return
     */
    @Override
    public Boolean update(UserRoleBO userRoleBO) {
        UserRole old = userRoleDao.getById(userRoleBO.getId());
        Assert.notNull(old,CodeConstants.DATA_NOT_EXIST);
        UserRole userRole = BaseBuilder.copyProperties(userRoleBO, UserRole.class);
        userRole.setRevision(old.getRevision());
        return userRoleDao.updateById(userRole);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @Override
    public Boolean remove(Serializable id) {
        Assert.notNull(userRoleDao.getById(id),CodeConstants.DATA_NOT_EXIST);
        return userRoleDao.removeById(id);
    }

    @Override
    public Boolean batchRemove(List<Serializable> ids) {
        return userRoleDao.removeByIds(ids);
    }

    /**
     * 查询
     * @param id
     * @return
     */
    @Override
    public UserRoleBO get(Serializable id) {
        UserRole userRole = userRoleDao.getById(id);
        Assert.notNull(userRole,CodeConstants.DATA_NOT_EXIST);
        return BaseBuilder.copyProperties(userRole,UserRoleBO.class);
    }

    /**
     * 分页
     * @param userRoleBO
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public IPage<UserRoleBO> page(UserRoleBO userRoleBO, Integer pageNum, Integer pageSize) {
        Page<UserRole> page = new Page<>(pageNum, pageSize);
        QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>(BaseBuilder.copyProperties(userRoleBO, UserRole.class));
        queryWrapper.orderByDesc(CommonConstants.COMMON_TABLE_ID);
        page = userRoleDao.page(page, queryWrapper);
        Page<UserRoleBO> result = new Page<>();
        BeanUtils.copyProperties(page,result);
        List<UserRole> records = page.getRecords();
        if(CollectionUtils.isEmpty(records)){
            result.setRecords(Collections.emptyList());
            return result;
        }

        result.setRecords(records.stream().map(it -> BaseBuilder.copyProperties(it,UserRoleBO.class)).collect(Collectors.toList()));
        return result;
    }

    /**
     * 列表
     * @param userRoleBO
     * @return
     */
    @Override
    public List<UserRoleBO> list(UserRoleBO userRoleBO) {
        QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>(BaseBuilder.copyProperties(userRoleBO, UserRole.class));
        queryWrapper.orderByDesc(CommonConstants.COMMON_TABLE_ID);
        List<UserRole> list = userRoleDao.list(queryWrapper);
        return CollectionUtils.isEmpty(list) ? Collections.emptyList(): list.stream().map(it -> BaseBuilder.copyProperties(it,UserRoleBO.class)).collect(Collectors.toList());
    }
}
