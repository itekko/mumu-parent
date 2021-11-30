package com.mumu.web.common.vo.in;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author ekko
 * @description
 * @create 2021-11-30 5:02 下午
 */
@Setter
@Getter
@ToString
public class BatchRemoveVO {

    @ApiModelProperty(value = "id集合")
    private List<Serializable> ids;
}
