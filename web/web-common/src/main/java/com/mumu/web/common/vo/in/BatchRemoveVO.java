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
 * @create 2021-11-30 5:02 ä¸å
 */
@Setter
@Getter
@ToString
public class BatchRemoveVO {

    @ApiModelProperty(value = "idéå")
    private List<Serializable> ids;
}
