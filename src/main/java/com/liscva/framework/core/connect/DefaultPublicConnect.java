package com.liscva.framework.core.connect;

/**
 * @author
 * @date 2020/7/7 17:06
 */
public class DefaultPublicConnect<T> extends AbstractPublicConnect {

    protected DefaultPublicConnect(T data) {
        super();
        this.data = data;
        this.success = true;
    }

    protected DefaultPublicConnect(String msg) {
        super();
        this.msg = msg;
        this.success = true;
    }

    public static <T> FinalConnect<T> of(T data) {
        return new DefaultPublicConnect<T>(data);
    }

    public static <T> FinalConnect<T> ofMsg(String msg) {
        return new DefaultPublicConnect<T>(msg);
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public void setMsg(String msg) {
        this.msg = msg;
    }
}
