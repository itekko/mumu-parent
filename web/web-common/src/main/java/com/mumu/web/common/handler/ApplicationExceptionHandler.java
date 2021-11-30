package com.mumu.web.common.handler;



import com.mumu.common.constant.CodeConstants;
import com.mumu.web.common.model.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 异常处理器
 * @author ekko
 */
@Slf4j
@RestControllerAdvice
public class ApplicationExceptionHandler {

    /**
     * 参数校验异常
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public R<String> illegalArgumentException(IllegalArgumentException e) {
        log.warn("全局异常处理:IllegalArgumentException[{}]:{}",e);
        return R.build(e.getMessage());
    }

    /**
     * 全局异常
     */
    @ExceptionHandler(Exception.class)
    public R<String> exception(Exception e) {
        log.warn("全局异常处理:Exception[{}]:{}",e);
        return R.build(CodeConstants.FAIL);
    }

    /**
     * 方法参数错误
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R<String> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.warn("全局异常处理:MethodArgumentNotValidException[{}]:{}",e);
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        String message = allErrors.stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(";"));
        return R.build(CodeConstants.ARGS_ERROR, message);
    }








}
