package com.liscva.framework.core.extend.aspect;

import com.liscva.framework.core.ThrowStatus;
import com.liscva.framework.core.exception.CoreException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Slf4j
@Component
public class ExceptionAspect {


    //定义一个切入点
    @Pointcut("@annotation(com.liscva.framework.core.exception.Exception)")
    public void annotationPointCut() {

    }

    @Around("annotationPointCut()")
    public Object doBefore(ProceedingJoinPoint joinPoint) {
        try {
            Object obj = joinPoint.proceed();
            return obj;
        } catch (Throwable e) {
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            Method method = methodSignature.getMethod();
            Exception annotation = method.getAnnotation(Exception.class);
            ThrowStatus throwStatus = annotation.value();
            log.error(throwStatus.getReasonPhrase(),e);
            throw CoreException.build(throwStatus, e);
        }
    }
}