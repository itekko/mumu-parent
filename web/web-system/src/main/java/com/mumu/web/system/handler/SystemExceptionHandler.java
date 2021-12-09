package com.mumu.web.system.handler;


import cn.dev33.satoken.exception.NotLoginException;
import com.mumu.web.common.model.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * system模块异常处理器
 * @author ekko
 */
@Slf4j
@RestControllerAdvice
public class SystemExceptionHandler {

    /**
     * 参数校验异常
     */
    @ExceptionHandler(NotLoginException.class)
    public R<String> notLoginException(NotLoginException e) {
        log.warn("登录异常:notLoginException[{}]:{}",e);
        return R.build(e.getType());
    }

}
