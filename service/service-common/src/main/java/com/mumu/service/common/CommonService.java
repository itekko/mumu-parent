package com.mumu.service.common;

import com.baomidou.mybatisplus.core.metadata.IPage;

import java.io.Serializable;
import java.util.List;

/**
 * 公共服务类(CRUD)操作
 * @author ekko
 * @description
 * @create 2021-11-30 9:37 上午
 */
public interface CommonService <T>{

    /**
     * 新增
     * @param t
     * @return
     */
    Boolean save(T t);

    /**
     * 更新
     * @param t
     * @return
     */
    Boolean update(T t);

    /**
     * 删除
     * @param id
     * @return
     */
    Boolean remove(Serializable id);

    /**
     * 详情
     * @param id
     * @return
     */
    T get(Serializable id);

    /**
     * 分页
     * @param t
     * @param pageNum
     * @param pageSize
     * @return
     */
    IPage<T> page(T t, Integer pageNum, Integer pageSize);

    /**
     * 列表
     * @param t
     * @return
     */
    List<T> list(T t);
}
