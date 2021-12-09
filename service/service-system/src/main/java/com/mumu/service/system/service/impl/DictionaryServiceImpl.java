package com.mumu.service.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mumu.common.constant.CodeConstants;
import com.mumu.common.constant.CommonConstants;
import com.mumu.db.common.builder.BaseBuilder;
import com.mumu.db.system.dao.DictionaryDao;
import com.mumu.db.system.entity.Dictionary;
import com.mumu.service.system.bo.DictionaryBO;
import com.mumu.service.system.service.DictionaryService;
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
 * 字典服务层实现
 * @author ekko
 * @create 2021-12-02 16:48:31
 */
@Slf4j
@Service
@AllArgsConstructor
public class DictionaryServiceImpl implements DictionaryService {

    private final DictionaryDao dictionaryDao;

    /**
     * 新增
     * @param dictionaryBO
     * @return
     */
    @Override
    public Boolean save(DictionaryBO dictionaryBO) {
        return dictionaryDao.save(BaseBuilder.copyProperties(dictionaryBO, Dictionary.class));
    }

    /**
     * 更新
     * @param dictionaryBO
     * @return
     */
    @Override
    public Boolean update(DictionaryBO dictionaryBO) {
        Dictionary old = dictionaryDao.getById(dictionaryBO.getId());
        Assert.notNull(old,CodeConstants.DATA_NOT_EXIST);
        Dictionary dictionary = BaseBuilder.copyProperties(dictionaryBO, Dictionary.class);
        dictionary.setRevision(old.getRevision());
        return dictionaryDao.updateById(dictionary);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @Override
    public Boolean remove(Serializable id) {
        Assert.notNull(dictionaryDao.getById(id),CodeConstants.DATA_NOT_EXIST);
        return dictionaryDao.removeById(id);
    }

    @Override
    public Boolean batchRemove(List<Serializable> ids) {
        return dictionaryDao.removeByIds(ids);
    }

    /**
     * 查询
     * @param id
     * @return
     */
    @Override
    public DictionaryBO get(Serializable id) {
        Dictionary dictionary = dictionaryDao.getById(id);
        Assert.notNull(dictionary,CodeConstants.DATA_NOT_EXIST);
        return BaseBuilder.copyProperties(dictionary,DictionaryBO.class);
    }

    /**
     * 分页
     * @param dictionaryBO
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public IPage<DictionaryBO> page(DictionaryBO dictionaryBO, Integer pageNum, Integer pageSize) {
        Page<Dictionary> page = new Page<>(pageNum, pageSize);
        QueryWrapper<Dictionary> queryWrapper = new QueryWrapper<>(BaseBuilder.copyProperties(dictionaryBO, Dictionary.class));
        queryWrapper.orderByDesc(CommonConstants.COMMON_TABLE_ID);
        page = dictionaryDao.page(page, queryWrapper);
        Page<DictionaryBO> result = new Page<>();
        BeanUtils.copyProperties(page,result);
        List<Dictionary> records = page.getRecords();
        if(CollectionUtils.isEmpty(records)){
            result.setRecords(Collections.emptyList());
            return result;
        }

        result.setRecords(records.stream().map(it -> BaseBuilder.copyProperties(it,DictionaryBO.class)).collect(Collectors.toList()));
        return result;
    }

    /**
     * 列表
     * @param dictionaryBO
     * @return
     */
    @Override
    public List<DictionaryBO> list(DictionaryBO dictionaryBO) {
        QueryWrapper<Dictionary> queryWrapper = new QueryWrapper<>(BaseBuilder.copyProperties(dictionaryBO, Dictionary.class));
        queryWrapper.orderByDesc(CommonConstants.COMMON_TABLE_ID);
        List<Dictionary> list = dictionaryDao.list(queryWrapper);
        return CollectionUtils.isEmpty(list) ? Collections.emptyList(): list.stream().map(it -> BaseBuilder.copyProperties(it,DictionaryBO.class)).collect(Collectors.toList());
    }
}
