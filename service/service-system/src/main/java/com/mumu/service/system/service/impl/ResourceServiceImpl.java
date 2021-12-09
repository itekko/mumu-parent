package com.mumu.service.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mumu.common.constant.CodeConstants;
import com.mumu.common.constant.CommonConstants;
import com.mumu.db.common.builder.BaseBuilder;
import com.mumu.db.system.dao.ResourceDao;
import com.mumu.db.system.entity.Resource;
import com.mumu.service.system.bo.ResourceBO;
import com.mumu.service.system.service.ResourceService;
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
 * 资源服务层实现
 * @author ekko
 * @create 2021-12-02 16:48:31
 */
@Slf4j
@Service
@AllArgsConstructor
public class ResourceServiceImpl implements ResourceService {

    private final ResourceDao resourceDao;

    /**
     * 新增
     * @param resourceBO
     * @return
     */
    @Override
    public Boolean save(ResourceBO resourceBO) {
        return resourceDao.save(BaseBuilder.copyProperties(resourceBO, Resource.class));
    }

    /**
     * 更新
     * @param resourceBO
     * @return
     */
    @Override
    public Boolean update(ResourceBO resourceBO) {
        Resource old = resourceDao.getById(resourceBO.getId());
        Assert.notNull(old,CodeConstants.DATA_NOT_EXIST);
        Resource resource = BaseBuilder.copyProperties(resourceBO, Resource.class);
        resource.setRevision(old.getRevision());
        return resourceDao.updateById(resource);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @Override
    public Boolean remove(Serializable id) {
        Assert.notNull(resourceDao.getById(id),CodeConstants.DATA_NOT_EXIST);
        return resourceDao.removeById(id);
    }

    @Override
    public Boolean batchRemove(List<Serializable> ids) {
        return resourceDao.removeByIds(ids);
    }

    /**
     * 查询
     * @param id
     * @return
     */
    @Override
    public ResourceBO get(Serializable id) {
        Resource resource = resourceDao.getById(id);
        Assert.notNull(resource,CodeConstants.DATA_NOT_EXIST);
        return BaseBuilder.copyProperties(resource,ResourceBO.class);
    }

    /**
     * 分页
     * @param resourceBO
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public IPage<ResourceBO> page(ResourceBO resourceBO, Integer pageNum, Integer pageSize) {
        Page<Resource> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Resource> queryWrapper = new QueryWrapper<>(BaseBuilder.copyProperties(resourceBO, Resource.class));
        queryWrapper.orderByDesc(CommonConstants.COMMON_TABLE_ID);
        page = resourceDao.page(page, queryWrapper);
        Page<ResourceBO> result = new Page<>();
        BeanUtils.copyProperties(page,result);
        List<Resource> records = page.getRecords();
        if(CollectionUtils.isEmpty(records)){
            result.setRecords(Collections.emptyList());
            return result;
        }

        result.setRecords(records.stream().map(it -> BaseBuilder.copyProperties(it,ResourceBO.class)).collect(Collectors.toList()));
        return result;
    }

    /**
     * 列表
     * @param resourceBO
     * @return
     */
    @Override
    public List<ResourceBO> list(ResourceBO resourceBO) {
        QueryWrapper<Resource> queryWrapper = new QueryWrapper<>(BaseBuilder.copyProperties(resourceBO, Resource.class));
        queryWrapper.orderByDesc(CommonConstants.COMMON_TABLE_ID);
        List<Resource> list = resourceDao.list(queryWrapper);
        return CollectionUtils.isEmpty(list) ? Collections.emptyList(): list.stream().map(it -> BaseBuilder.copyProperties(it,ResourceBO.class)).collect(Collectors.toList());
    }
}
