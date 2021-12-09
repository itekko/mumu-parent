package com.mumu.web.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mumu.db.common.builder.BaseBuilder;
import com.mumu.service.system.bo.ResourceBO;
import com.mumu.service.system.service.ResourceService;
import com.mumu.web.common.model.R;
import com.mumu.web.common.query.PageQuery;
import com.mumu.web.common.vo.in.BatchRemoveVO;
import com.mumu.web.system.vo.in.ResourceQuery;
import com.mumu.web.system.vo.in.ResourceSave;
import com.mumu.web.system.vo.in.ResourceUpdate;
import com.mumu.web.system.vo.out.ResourceDetail;
import com.mumu.web.system.vo.out.ResourceList;
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
 * 资源web层
 * @author ekko
 * @create 2021-12-02 16:48:31
 */
@Api(tags = "资源")
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("resource")
public class ResourceController {

    private final ResourceService resourceService;

    /**
     * 列表
     * @param
     * @return
     */
    @ApiOperation(value = "列表")
    @PostMapping(LIST)
    public R<List<ResourceList>> list(@RequestBody ResourceQuery resourceQuery){
        return R.success(resourceService
                .list(BaseBuilder.copyProperties(resourceQuery, ResourceBO.class))
                .stream()
                .map(it -> BaseBuilder.copyProperties(it,ResourceList.class))
                .collect(Collectors.toList()));
    }

    /**
     * 意见反馈表列表(分页)
     * @param pageQuery 分页参数
     * @return
     */
    @ApiOperation(value = "列表(分页)")
    @PostMapping(PAGE)
    public R<IPage<ResourceList>> page(@RequestBody PageQuery<ResourceQuery> pageQuery){
        IPage<ResourceBO> page = resourceService.page(BaseBuilder.copyProperties(pageQuery.getSearch(), ResourceBO.class), pageQuery.getCurrent(), pageQuery.getSize());
        IPage<ResourceList> result = new Page<>();
        BeanUtils.copyProperties(page,result);
        List<ResourceBO> records = page.getRecords();
        result.setRecords(records.stream().map(it -> BaseBuilder.copyProperties(it,ResourceList.class)).collect(Collectors.toList()));
        return R.success(result);
    }

    /**
     * 新增
     * @param resourceSave
     * @return
     */
    @ApiOperation(value = "新增")
    @PostMapping
    public R<Boolean> save(@RequestBody ResourceSave resourceSave){
        return R.success(resourceService.save(BaseBuilder.copyProperties(resourceSave,ResourceBO.class)));
    }

    /**
     * 更新
     * @param resourceUpdate
     * @return
     */
    @ApiOperation(value = "更新")
    @PutMapping
    public R<Boolean> update(@RequestBody ResourceUpdate resourceUpdate){
        return R.success(resourceService.update(BaseBuilder.copyProperties(resourceUpdate,ResourceBO.class)));
    }

    /**
     * 详情
     * @param id
     * @return
     */
    @ApiOperation(value = "详情")
    @GetMapping(DETAIL)
    public R<ResourceDetail> get(@PathVariable("id") Serializable id){
        return R.success(BaseBuilder.copyProperties(resourceService.get(id),ResourceDetail.class));
    }

    /**
     * 删除
     * @param  id
     * @return
     */
    @ApiOperation(value = "删除")
    @GetMapping(REMOVE)
    public R<Boolean> remove(@PathVariable("id") Serializable id){
        return R.success(resourceService.remove(id));
    }

    /**
     * 批量删除
     * @param  batchRemoveVO
     * @return
     */
    @ApiOperation(value = "批量删除")
    @PostMapping(BATCH_REMOVE)
    public R<Boolean> remove(@RequestBody BatchRemoveVO batchRemoveVO){
        return R.success(resourceService.batchRemove(batchRemoveVO.getIds()));
    }

}
