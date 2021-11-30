package com.mumu.web.common.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mumu.service.common.CommonService;
import com.mumu.web.common.model.R;
import com.mumu.web.common.query.PageQuery;
import java.io.Serializable;
import java.util.List;

/**
 * @author ekko
 * @desc 顶级Controller
 * @param <M>
 * @param <T>
 */
public interface BaseController<M extends CommonService, T>  {


    /**
     * 列表查询
     * @param m
     * @param t
     * @return
     */
    default R<List<T>> list(M m, T t){
        Wrapper<T> wrapper = new QueryWrapper<>(t);
        return R.success(m.list(wrapper));
    }

    /**
     * 列表查询(分页)
     * @param m
     * @param pageQuery
     * @return
     */
    default  R<IPage<T>> page(M m, PageQuery<T> pageQuery){
        return R.success(m.page(pageQuery.getSearch(),pageQuery.getCurrent(),pageQuery.getSize()));
    }

    /**
     * 新增
     * @param m
     * @param t
     * @return
     */
    default R<Boolean> save(M m, T t){
        return R.success(m.save(t));
    }

    /**
     * 修改
     * @param m
     * @param t
     * @return
     */
    default R<Boolean> update(M m, T t){
        return R.success(m.update(t));
    }

    /**
     * 详情
     * @param m
     * @param id
     * @return
     */
    default R<T> get(M m, Serializable id){
        return R.success((T)m.get(id));
    }

    /**
     * 删除
     * @param m
     * @param id
     * @return
     */
    default R<Boolean> remove(M m,Serializable id){
        return R.success(m.remove(id));
    }


}
