package com.liscva.framework.core.connect;

import com.liscva.framework.core.ThrowStatus;
import lombok.Data;

/**
 * @author 李诗诚
 * @date 2020/7/7 14:14
 */
@Data
public class FinalConnect<T> extends ConnectData implements IConnect {

    /**
     * 返回的状态码
     */
    protected String code = ThrowStatus.OK.getValue();

    /**
     * 返回的消息
     */
    protected String msg;

    /**
     * 请求是否成功
     */
    protected boolean success;

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }

}
