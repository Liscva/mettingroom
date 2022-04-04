package com.liscva.framework.core.connect;


import java.io.Serializable;

/**
 * @param <T> 任意返回对象
 *            用于保存微服务之间传递的数据
 * @author 李诗诚
 * @date 2020/7/7 15:25
 */
public class ConnectData<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 返回的数据对象
     */
    protected T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

