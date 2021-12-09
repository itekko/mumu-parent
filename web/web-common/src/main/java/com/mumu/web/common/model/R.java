package com.mumu.web.common.model;


import com.mumu.common.constant.CodeConstants;
import com.mumu.web.common.util.I18Utils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author：dcy
 * @description: 返回给前台的通用包装
 * @date: 2019/8/9 11:40
 */
@ToString
@Getter
@Setter
@ApiModel(value = "R", description = "返回数据包装")
public class R<T> {

    @ApiModelProperty(value = "响应状态码")
    private String code;

    @ApiModelProperty(value = "响应信息")
    private String message;

    @ApiModelProperty(value = "响应对象")
    private T data;

    public static <T> R<T> success(T data) {
        if (data instanceof Boolean && Boolean.FALSE.equals(data)) {
            return error(I18Utils.getMessage(CodeConstants.BUSY,null));
        }
        return restResult(CodeConstants.SUCCESS,I18Utils.getMessage(CodeConstants.SUCCESS,null), data);
    }

    public static <T> R<T> build(String code) {
        return restResult(code,I18Utils.getMessage(code,null), null);
    }

    public static <T> R<T> build(String code,String msg) {
        return restResult(code,msg, null);
    }



    public static <T> R<T> error(String msg) {
        return restResult(CodeConstants.FAIL, msg, null);
    }


    private static <T> R<T> restResult(String code, String msg, T data) {
        R<T> apiResult = new R<>();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMessage(msg);
        return apiResult;
    }
}
