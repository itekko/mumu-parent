package com.mumu.db.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mumu.db.system.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author ekko
 * @description
 * @create 2021-11-29 7:35 下午
 */
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 根据userId获取role列表
     * @param userId
     * @return
     */
    List<Role> selectListByUserId(@Param("userId") String userId);
}
