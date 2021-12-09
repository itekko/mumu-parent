package com.mumu.service.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mumu.common.constant.CodeConstants;
import com.mumu.common.constant.CommonConstants;
import com.mumu.db.common.builder.BaseBuilder;
import com.mumu.db.system.dao.DepartmentDao;
import com.mumu.db.system.entity.Department;
import com.mumu.service.system.bo.DepartmentBO;
import com.mumu.service.system.service.DepartmentService;
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
 * 部门服务层实现
 * @author ekko
 * @create 2021-12-02 17:29:17
 */
@Slf4j
@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentDao departmentDao;

    /**
     * 新增
     * @param departmentBO
     * @return
     */
    @Override
    public Boolean save(DepartmentBO departmentBO) {
        return departmentDao.save(BaseBuilder.copyProperties(departmentBO, Department.class));
    }

    /**
     * 更新
     * @param departmentBO
     * @return
     */
    @Override
    public Boolean update(DepartmentBO departmentBO) {
        Department old = departmentDao.getById(departmentBO.getId());
        Assert.notNull(old,CodeConstants.DATA_NOT_EXIST);
        Department department = BaseBuilder.copyProperties(departmentBO, Department.class);
        department.setRevision(old.getRevision());
        return departmentDao.updateById(department);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @Override
    public Boolean remove(Serializable id) {
        Assert.notNull(departmentDao.getById(id),CodeConstants.DATA_NOT_EXIST);
        return departmentDao.removeById(id);
    }

    @Override
    public Boolean batchRemove(List<Serializable> ids) {
        return departmentDao.removeByIds(ids);
    }

    /**
     * 查询
     * @param id
     * @return
     */
    @Override
    public DepartmentBO get(Serializable id) {
        Department department = departmentDao.getById(id);
        Assert.notNull(department,CodeConstants.DATA_NOT_EXIST);
        return BaseBuilder.copyProperties(department,DepartmentBO.class);
    }

    /**
     * 分页
     * @param departmentBO
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public IPage<DepartmentBO> page(DepartmentBO departmentBO, Integer pageNum, Integer pageSize) {
        Page<Department> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Department> queryWrapper = new QueryWrapper<>(BaseBuilder.copyProperties(departmentBO, Department.class));
        queryWrapper.orderByDesc(CommonConstants.COMMON_TABLE_ID);
        page = departmentDao.page(page, queryWrapper);
        Page<DepartmentBO> result = new Page<>();
        BeanUtils.copyProperties(page,result);
        List<Department> records = page.getRecords();
        if(CollectionUtils.isEmpty(records)){
            result.setRecords(Collections.emptyList());
            return result;
        }

        result.setRecords(records.stream().map(it -> BaseBuilder.copyProperties(it,DepartmentBO.class)).collect(Collectors.toList()));
        return result;
    }

    /**
     * 列表
     * @param departmentBO
     * @return
     */
    @Override
    public List<DepartmentBO> list(DepartmentBO departmentBO) {
        QueryWrapper<Department> queryWrapper = new QueryWrapper<>(BaseBuilder.copyProperties(departmentBO, Department.class));
        queryWrapper.orderByDesc(CommonConstants.COMMON_TABLE_ID);
        List<Department> list = departmentDao.list(queryWrapper);
        return CollectionUtils.isEmpty(list) ? Collections.emptyList(): list.stream().map(it -> BaseBuilder.copyProperties(it,DepartmentBO.class)).collect(Collectors.toList());
    }
}
