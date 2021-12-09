package com.mumu.db.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import com.mumu.db.common.entity.BaseEntity;

/**
 * 用户部门实体类
 * @author ekko
 * @create 2021-12-06 15:54:20
 */
@Getter
@Setter
@TableName("system_user_department")
public class UserDepartment extends BaseEntity {

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 部门ID
     */
    private String departmentId;

}

