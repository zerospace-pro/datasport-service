package com.zerospace.datasport.utils.exception;

import com.zerospace.datasport.utils.protocol.Result;
import com.zerospace.datasport.utils.protocol.ResultState;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 <p>
 Controller抛出的异常拦截处理器
 </p>
 @author nathan
 @version 2019-07-14 */
@RestControllerAdvice
@Slf4j
public class ControllerExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Result<Object> handleException(Exception e) {
        log.error(e.getMessage(), e);
        return Result.error(e.getMessage());
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result<?> HttpRequestMethodNotSupportedException(Exception e) {
        log.error(e.getMessage(), e);
        return Result.error(ResultState.NO_AUTHORIZATION, "没有权限，请联系管理员授权");
    }
}
