package com.liscva.framework.core.exception;


import com.liscva.framework.core.ThrowStatus;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author liscva
 * @date 2021年04月26日 16:05
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Exception {

    ThrowStatus value() default ThrowStatus.UNKNOWN_ERROR;

}

