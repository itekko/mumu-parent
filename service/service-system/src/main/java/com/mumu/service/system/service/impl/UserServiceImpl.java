package com.mumu.service.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mumu.common.constant.CodeConstants;
import com.mumu.common.constant.CommonConstants;
import com.mumu.db.common.builder.BaseBuilder;
import com.mumu.db.system.dao.UserDao;
import com.mumu.db.system.entity.User;
import com.mumu.service.system.bo.UserBO;
import com.mumu.service.system.service.UserService;
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
 * 用户服务层实现
 * @author ekko
 * @create 2021-12-02 16:48:31
 */
@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    /**
     * 新增
     * @param userBO
     * @return
     */
    @Override
    public Boolean save(UserBO userBO) {
        return userDao.save(BaseBuilder.copyProperties(userBO, User.class));
    }

    /**
     * 更新
     * @param userBO
     * @return
     */
    @Override
    public Boolean update(UserBO userBO) {
        User old = userDao.getById(userBO.getId());
        Assert.notNull(old,CodeConstants.DATA_NOT_EXIST);
        User user = BaseBuilder.copyProperties(userBO, User.class);
        user.setRevision(old.getRevision());
        return userDao.updateById(user);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @Override
    public Boolean remove(Serializable id) {
        Assert.notNull(userDao.getById(id),CodeConstants.DATA_NOT_EXIST);
        return userDao.removeById(id);
    }

    @Override
    public Boolean batchRemove(List<Serializable> ids) {
        return userDao.removeByIds(ids);
    }

    /**
     * 查询
     * @param id
     * @return
     */
    @Override
    public UserBO get(Serializable id) {
        User user = userDao.getById(id);
        Assert.notNull(user,CodeConstants.DATA_NOT_EXIST);
        return BaseBuilder.copyProperties(user,UserBO.class);
    }

    /**
     * 分页
     * @param userBO
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public IPage<UserBO> page(UserBO userBO, Integer pageNum, Integer pageSize) {
        Page<User> page = new Page<>(pageNum, pageSize);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>(BaseBuilder.copyProperties(userBO, User.class));
        queryWrapper.orderByDesc(CommonConstants.COMMON_TABLE_ID);
        page = userDao.page(page, queryWrapper);
        Page<UserBO> result = new Page<>();
        BeanUtils.copyProperties(page,result);
        List<User> records = page.getRecords();
        if(CollectionUtils.isEmpty(records)){
            result.setRecords(Collections.emptyList());
            return result;
        }

        result.setRecords(records.stream().map(it -> BaseBuilder.copyProperties(it,UserBO.class)).collect(Collectors.toList()));
        return result;
    }

    /**
     * 列表
     * @param userBO
     * @return
     */
    @Override
    public List<UserBO> list(UserBO userBO) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>(BaseBuilder.copyProperties(userBO, User.class));
        queryWrapper.orderByDesc(CommonConstants.COMMON_TABLE_ID);
        List<User> list = userDao.list(queryWrapper);
        return CollectionUtils.isEmpty(list) ? Collections.emptyList(): list.stream().map(it -> BaseBuilder.copyProperties(it,UserBO.class)).collect(Collectors.toList());
    }
}
