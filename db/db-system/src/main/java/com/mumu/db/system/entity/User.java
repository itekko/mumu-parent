package com.mumu.db.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import com.mumu.db.common.entity.BaseEntity;

/**
 * 用户实体类
 * @author ekko
 * @create 2021-12-06 15:54:20
 */
@Getter
@Setter
@TableName("system_user")
public class User extends BaseEntity {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 状态(字典STATUS)
     */
    private Integer status;

}

