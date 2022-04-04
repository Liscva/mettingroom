package com.liscva.framework.core.exception;

import com.liscva.framework.core.ThrowStatus;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 李诗诚
 * @date 2020/7/7 12:01
 */
@Slf4j
@Data
public class CoreException extends RuntimeException {

    private ThrowStatus throwStatus;

    protected CoreException() {

    }

    public CoreException(String s) {
        super(s);
        log.error(s);
    }

    protected CoreException(String s, Throwable throwable) {
        super(s, throwable);
        log.error(s);
    }

    protected CoreException(ThrowStatus throwStatus, String s, Throwable throwable) {
        this(s, throwable);
        setThrowStatus(throwStatus);
    }


    protected CoreException(final ThrowStatus throwStatus) {
        this(throwStatus.getReasonPhrase());
        setThrowStatus(throwStatus);
    }

    protected CoreException(final ThrowStatus throwStatus, String s) {
        this(s);
        setThrowStatus(throwStatus);
    }

    protected CoreException(final ThrowStatus throwStatus, Throwable throwable) {
        this(throwStatus.getReasonPhrase(), throwable);
        setThrowStatus(throwStatus);
    }

    public static CoreException build(final ThrowStatus throwStatus, Throwable throwable) {
        if (throwable instanceof CoreException) {
            throw (CoreException) throwable;
        }
        throw new CoreException(throwStatus, throwable);
    }

    @Deprecated
    public static CoreException build(String s, Throwable throwable) {
        if (throwable instanceof CoreException) {
            throw (CoreException) throwable;
        }
        throw new CoreException(ThrowStatus.UNKNOWN_ERROR, s, throwable);
    }


    public static CoreException build(final ThrowStatus throwStatus, String s) {
        throw new CoreException(throwStatus, s);
    }

    public static CoreException build(final ThrowStatus throwStatus) {
        throw new CoreException(throwStatus);
    }

    @Deprecated
    public static CoreException build(String s) {
        throw new CoreException(ThrowStatus.UNKNOWN_ERROR, s);
    }


}
