package com.mumu.web.system.controller;

import cn.hutool.core.thread.ThreadUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mumu.db.common.builder.BaseBuilder;
import com.mumu.service.system.bo.DepartmentBO;
import com.mumu.service.system.service.DepartmentService;
import com.mumu.web.common.model.R;
import com.mumu.web.common.query.PageQuery;
import com.mumu.web.common.vo.in.BatchRemoveVO;
import com.mumu.web.system.vo.in.DepartmentQuery;
import com.mumu.web.system.vo.in.DepartmentSave;
import com.mumu.web.system.vo.in.DepartmentUpdate;
import com.mumu.web.system.vo.out.DepartmentDetail;
import com.mumu.web.system.vo.out.DepartmentList;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import static com.mumu.web.common.constant.WebConstants.*;

/**
 * 部门web层
 * @author ekko
 * @create 2021-12-02 17:29:17
 */
@Api(tags = "部门")
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("department")
public class DepartmentController {

    private final DepartmentService departmentService;

    /**
     * 列表
     * @param
     * @return
     */
    @ApiOperation(value = "列表")
    @PostMapping(LIST)
    public R<List<DepartmentList>> list(@RequestBody DepartmentQuery departmentQuery){
        return R.success(departmentService
                .list(BaseBuilder.copyProperties(departmentQuery, DepartmentBO.class))
                .stream()
                .map(it -> BaseBuilder.copyProperties(it,DepartmentList.class))
                .collect(Collectors.toList()));
    }

    /**
     * 意见反馈表列表(分页)
     * @param pageQuery 分页参数
     * @return
     */
    @ApiOperation(value = "列表(分页)")
    @PostMapping(PAGE)
    public R<IPage<DepartmentList>> page(@RequestBody PageQuery<DepartmentQuery> pageQuery){
        IPage<DepartmentBO> page = departmentService.page(BaseBuilder.copyProperties(pageQuery.getSearch(), DepartmentBO.class), pageQuery.getCurrent(), pageQuery.getSize());
        IPage<DepartmentList> result = new Page<>();
        BeanUtils.copyProperties(page,result);
        List<DepartmentBO> records = page.getRecords();
        result.setRecords(records.stream().map(it -> BaseBuilder.copyProperties(it,DepartmentList.class)).collect(Collectors.toList()));
        return R.success(result);
    }

    /**
     * 新增
     * @param departmentSave
     * @return
     */
    @ApiOperation(value = "新增")
    @PostMapping
    public R<Boolean> save(@RequestBody DepartmentSave departmentSave){
        return R.success(departmentService.save(BaseBuilder.copyProperties(departmentSave,DepartmentBO.class)));
    }

    /**
     * 更新
     * @param departmentUpdate
     * @return
     */
    @ApiOperation(value = "更新")
    @PutMapping
    public R<Boolean> update(@RequestBody DepartmentUpdate departmentUpdate){
        return R.success(departmentService.update(BaseBuilder.copyProperties(departmentUpdate,DepartmentBO.class)));
    }

    /**
     * 详情
     * @param id
     * @return
     */
    @ApiOperation(value = "详情")
    @GetMapping(DETAIL)
    public R<DepartmentDetail> get(@PathVariable("id") Serializable id){
        return R.success(BaseBuilder.copyProperties(departmentService.get(id),DepartmentDetail.class));
    }

    /**
     * 删除
     * @param  id
     * @return
     */
    @ApiOperation(value = "删除")
    @GetMapping(REMOVE)
    public R<Boolean> remove(@PathVariable("id") Serializable id){
        return R.success(departmentService.remove(id));
    }

    /**
     * 批量删除
     * @param  batchRemoveVO
     * @return
     */
    @ApiOperation(value = "批量删除")
    @PostMapping(BATCH_REMOVE)
    public R<Boolean> remove(@RequestBody BatchRemoveVO batchRemoveVO){
        return R.success(departmentService.batchRemove(batchRemoveVO.getIds()));
    }

    public static void main(String[] args) {
        ThreadUtil.execute(() -> System.out.println("test"));
    }



}
