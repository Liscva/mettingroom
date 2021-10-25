package com.liscva.mettingroom.config;

import com.liscva.framework.core.ThrowStatus;
import com.liscva.framework.core.connect.FailPublicConnect;
import com.liscva.framework.core.connect.FinalConnect;
import com.liscva.framework.core.exception.CoreException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author 李诗诚
 * @date 2020/7/7 12:01
 * controller统一异常处理,目前放到网关去了，这里暂时废弃
 */
@RestControllerAdvice
@Slf4j(topic = "controller统一异常处理")
public class ControllerExceptionHandleAdvice {

    @ExceptionHandler
    public FinalConnect handler(HttpServletRequest req, HttpServletResponse res, Exception e) {
        if (e instanceof CoreException) {
            CoreException ex = (CoreException) e;
            return new FailPublicConnect(ex.getThrowStatus(), ex.getMessage());
        } else if (e instanceof BindException) {
            BindException ex = (BindException) e;
            List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
            //多个错误，取第一个
            FieldError error = fieldErrors.get(0);
            String msg = error.getDefaultMessage();
            return new FailPublicConnect(ThrowStatus.ASSERT_ERR, msg);
        } else if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException ex = (MethodArgumentNotValidException) e;
            List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
            //多个错误，取第一个
            FieldError error = fieldErrors.get(0);
            String msg = error.getDefaultMessage();
            return new FailPublicConnect(ThrowStatus.ASSERT_ERR, msg);
        } else if (e instanceof HttpRequestMethodNotSupportedException) {
            HttpRequestMethodNotSupportedException ex = (HttpRequestMethodNotSupportedException) e;
            String method = ex.getMethod();
            return new FailPublicConnect(ThrowStatus.ASSERT_ERR, method+"请求方式未开放，请检查请求方式");
        }  else {
            log.error("存在非定义异常", e);
            return new FailPublicConnect(ThrowStatus.UNKNOWN_ERROR);
        }
    }
}