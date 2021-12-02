package com.mumu.web.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mumu.db.common.builder.BaseBuilder;
import com.mumu.service.system.bo.RoleBO;
import com.mumu.service.system.service.RoleService;
import com.mumu.web.common.model.R;
import com.mumu.web.common.query.PageQuery;
import com.mumu.web.common.vo.in.BatchRemoveVO;
import com.mumu.web.system.vo.in.RoleQuery;
import com.mumu.web.system.vo.in.RoleSave;
import com.mumu.web.system.vo.in.RoleUpdate;
import com.mumu.web.system.vo.out.RoleDetail;
import com.mumu.web.system.vo.out.RoleList;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ekko
 * @description
 * @create 2021-11-30 4:14 下午
 */
@Api(tags = "角色管理")
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("role")
public class RoleController{

    private final RoleService roleService;

    /**
     * 列表
     * @param
     * @return
     */
    @ApiOperation(value = "列表")
    @PostMapping("list")
    public R<List<RoleList>> list(@RequestBody RoleQuery roleQuery){
        return R.success(roleService
                .list(BaseBuilder.copyProperties(roleQuery, RoleBO.class))
                .stream()
                .map(it -> BaseBuilder.copyProperties(it,RoleList.class))
                .collect(Collectors.toList()));
    }

    /**
     * 意见反馈表列表(分页)
     * @param pageQuery 分页参数
     * @return
     */
    @ApiOperation(value = "列表(分页)")
    @PostMapping("page")
    public R<IPage<RoleList>> page(@RequestBody PageQuery<RoleQuery> pageQuery){
        IPage<RoleBO> page = roleService.page(BaseBuilder.copyProperties(pageQuery.getSearch(), RoleBO.class), pageQuery.getCurrent(), pageQuery.getSize());
        IPage<RoleList> result = new Page<>();
        BeanUtils.copyProperties(page,result);
        List<RoleBO> records = page.getRecords();
        result.setRecords(records.stream().map(it -> BaseBuilder.copyProperties(it,RoleList.class)).collect(Collectors.toList()));
        return R.success(result);
    }

    /**
     * 新增
     * @param roleSave
     * @return
     */
    @ApiOperation(value = "新增")
    @PostMapping
    public R<Boolean> save(@RequestBody RoleSave roleSave){
        return R.success(roleService.save(BaseBuilder.copyProperties(roleSave,RoleBO.class)));
    }

    /**
     * 更新
     * @param roleUpdate
     * @return
     */
    @ApiOperation(value = "更新")
    @PutMapping
    public R<Boolean> update(@RequestBody RoleUpdate roleUpdate){
        return R.success(roleService.update(BaseBuilder.copyProperties(roleUpdate,RoleBO.class)));
    }

    /**
     * 详情
     * @param id
     * @return
     */
    @ApiOperation(value = "详情")
    @GetMapping("get/{id}")
    public R<RoleDetail> get(@PathVariable("id") Serializable id){
        return R.success(BaseBuilder.copyProperties(roleService.get(id),RoleDetail.class));
    }

    /**
     * 意见反馈表删除
     * @param  id
     * @return
     */
    @ApiOperation(value = "删除")
    @GetMapping("remove/{id}")
    public R<Boolean> remove(@PathVariable("id") Serializable id){
        return R.success(roleService.remove(id));
    }

    /**
     * 批量删除
     * @param  batchRemoveVO
     * @return
     */
    @ApiOperation(value = "批量删除")
    @PostMapping("batchRemove")
    public R<Boolean> remove(@RequestBody BatchRemoveVO batchRemoveVO){
        return R.success(roleService.batchRemove(batchRemoveVO.getIds()));
    }



}
