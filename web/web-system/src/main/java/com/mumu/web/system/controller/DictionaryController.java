package com.mumu.web.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mumu.db.common.builder.BaseBuilder;
import com.mumu.service.system.bo.DictionaryBO;
import com.mumu.service.system.service.DictionaryService;
import com.mumu.web.common.model.R;
import com.mumu.web.common.query.PageQuery;
import com.mumu.web.common.vo.in.BatchRemoveVO;
import com.mumu.web.system.vo.in.DictionaryQuery;
import com.mumu.web.system.vo.in.DictionarySave;
import com.mumu.web.system.vo.in.DictionaryUpdate;
import com.mumu.web.system.vo.out.DictionaryDetail;
import com.mumu.web.system.vo.out.DictionaryList;
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
 * 字典web层
 * @author ekko
 * @create 2021-12-02 16:48:31
 */
@Api(tags = "字典")
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("dictionary")
public class DictionaryController {

    private final DictionaryService dictionaryService;

    /**
     * 列表
     * @param
     * @return
     */
    @ApiOperation(value = "列表")
    @PostMapping(LIST)
    public R<List<DictionaryList>> list(@RequestBody DictionaryQuery dictionaryQuery){
        return R.success(dictionaryService
                .list(BaseBuilder.copyProperties(dictionaryQuery, DictionaryBO.class))
                .stream()
                .map(it -> BaseBuilder.copyProperties(it,DictionaryList.class))
                .collect(Collectors.toList()));
    }

    /**
     * 意见反馈表列表(分页)
     * @param pageQuery 分页参数
     * @return
     */
    @ApiOperation(value = "列表(分页)")
    @PostMapping(PAGE)
    public R<IPage<DictionaryList>> page(@RequestBody PageQuery<DictionaryQuery> pageQuery){
        IPage<DictionaryBO> page = dictionaryService.page(BaseBuilder.copyProperties(pageQuery.getSearch(), DictionaryBO.class), pageQuery.getPageNo(), pageQuery.getPageSize());
        IPage<DictionaryList> result = new Page<>();
        BeanUtils.copyProperties(page,result);
        List<DictionaryBO> records = page.getRecords();
        result.setRecords(records.stream().map(it -> BaseBuilder.copyProperties(it,DictionaryList.class)).collect(Collectors.toList()));
        return R.success(result);
    }

    /**
     * 新增
     * @param dictionarySave
     * @return
     */
    @ApiOperation(value = "新增")
    @PostMapping
    public R<Boolean> save(@RequestBody DictionarySave dictionarySave){
        return R.success(dictionaryService.save(BaseBuilder.copyProperties(dictionarySave,DictionaryBO.class)));
    }

    /**
     * 更新
     * @param dictionaryUpdate
     * @return
     */
    @ApiOperation(value = "更新")
    @PutMapping
    public R<Boolean> update(@RequestBody DictionaryUpdate dictionaryUpdate){
        return R.success(dictionaryService.update(BaseBuilder.copyProperties(dictionaryUpdate,DictionaryBO.class)));
    }

    /**
     * 详情
     * @param id
     * @return
     */
    @ApiOperation(value = "详情")
    @GetMapping(DETAIL)
    public R<DictionaryDetail> get(@PathVariable("id") Serializable id){
        return R.success(BaseBuilder.copyProperties(dictionaryService.get(id),DictionaryDetail.class));
    }

    /**
     * 删除
     * @param  id
     * @return
     */
    @ApiOperation(value = "删除")
    @GetMapping(REMOVE)
    public R<Boolean> remove(@PathVariable("id") Serializable id){
        return R.success(dictionaryService.remove(id));
    }

    /**
     * 批量删除
     * @param  batchRemoveVO
     * @return
     */
    @ApiOperation(value = "批量删除")
    @PostMapping(BATCH_REMOVE)
    public R<Boolean> remove(@RequestBody BatchRemoveVO batchRemoveVO){
        return R.success(dictionaryService.batchRemove(batchRemoveVO.getIds()));
    }



}
