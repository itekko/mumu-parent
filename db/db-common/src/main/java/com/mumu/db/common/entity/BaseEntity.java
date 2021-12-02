package com.mumu.db.common.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 公共实体类
 * @author ekko
 * @description
 * @create 2021-11-24 11:21 上午
 */
@Getter
@Setter
public class BaseEntity {

    public static final String CREATE_BY = "create_by";

    public static final String CREATE_DATE = "create_date";

    public static final String UPDATE_BY = "update_by";

    public static final String UPDATE_DATE = "update_date";

    public static final String DEL_FLAG = "delete_flag";

    /**
     * id
     */
    @TableId
    private String id;

    /**
     * 创建者
     */
    // @TableField(fill = FieldFill.INSERT)
    private String createdBy;

    /**
     * 创建时间
     */
    private LocalDateTime createdTime;

    /**
     * 更新者
     */
    //@TableField(fill = FieldFill.UPDATE)
    private String updatedBy;

    /**
     * 更新时间
     */
    private LocalDateTime updatedTime;

    /**
     * 删除标识（0：正常；1：已删除）
     */
    @TableField(select = false)
    @TableLogic
    private String deleteFlag;

    @Version
    private Integer revision;



}

