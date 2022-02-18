package com.mumu.web.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mumu.db.common.builder.BaseBuilder;
import com.mumu.service.system.bo.UserBO;
import com.mumu.service.system.service.UserService;
import com.mumu.web.common.model.R;
import com.mumu.web.common.query.PageQuery;
import com.mumu.web.common.vo.in.BatchRemoveVO;
import com.mumu.web.system.vo.in.UserQuery;
import com.mumu.web.system.vo.in.UserSave;
import com.mumu.web.system.vo.in.UserUpdate;
import com.mumu.web.system.vo.out.UserDetail;
import com.mumu.web.system.vo.out.UserList;
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
 * 用户web层
 * @author ekko
 * @create 2021-12-02 16:48:31
 */
@Api(tags = "用户")
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    /**
     * 列表
     * @param
     * @return
     */
    @ApiOperation(value = "列表")
    @PostMapping(LIST)
    public R<List<UserList>> list(@RequestBody UserQuery userQuery){
        return R.success(userService
                .list(BaseBuilder.copyProperties(userQuery, UserBO.class))
                .stream()
                .map(it -> BaseBuilder.copyProperties(it,UserList.class))
                .collect(Collectors.toList()));
    }

    /**
     * 意见反馈表列表(分页)
     * @param pageQuery 分页参数
     * @return
     */
    @ApiOperation(value = "列表(分页)")
    @PostMapping(PAGE)
    public R<IPage<UserList>> page(@RequestBody PageQuery<UserQuery> pageQuery){
        IPage<UserBO> page = userService.page(BaseBuilder.copyProperties(pageQuery.getSearch(), UserBO.class), pageQuery.getPageNo(), pageQuery.getPageSize());
        IPage<UserList> result = new Page<>();
        BeanUtils.copyProperties(page,result);
        List<UserBO> records = page.getRecords();
        result.setRecords(records.stream().map(it -> BaseBuilder.copyProperties(it,UserList.class)).collect(Collectors.toList()));
        return R.success(result);
    }

    /**
     * 新增
     * @param userSave
     * @return
     */
    @ApiOperation(value = "新增")
    @PostMapping
    public R<Boolean> save(@RequestBody UserSave userSave){
        return R.success(userService.save(BaseBuilder.copyProperties(userSave,UserBO.class)));
    }

    /**
     * 更新
     * @param userUpdate
     * @return
     */
    @ApiOperation(value = "更新")
    @PutMapping
    public R<Boolean> update(@RequestBody UserUpdate userUpdate){
        return R.success(userService.update(BaseBuilder.copyProperties(userUpdate,UserBO.class)));
    }

    /**
     * 详情
     * @param id
     * @return
     */
    @ApiOperation(value = "详情")
    @GetMapping(DETAIL)
    public R<UserDetail> get(@PathVariable("id") Serializable id){
        return R.success(BaseBuilder.copyProperties(userService.get(id),UserDetail.class));
    }

    /**
     * 删除
     * @param  id
     * @return
     */
    @ApiOperation(value = "删除")
    @GetMapping(REMOVE)
    public R<Boolean> remove(@PathVariable("id") Serializable id){
        return R.success(userService.remove(id));
    }

    /**
     * 批量删除
     * @param  batchRemoveVO
     * @return
     */
    @ApiOperation(value = "批量删除")
    @PostMapping(BATCH_REMOVE)
    public R<Boolean> remove(@RequestBody BatchRemoveVO batchRemoveVO){
        return R.success(userService.batchRemove(batchRemoveVO.getIds()));
    }



}
