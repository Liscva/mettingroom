package com.liscva.framework.core.connect;


import com.liscva.framework.core.ThrowStatus;

/**
 * @author 李诗诚
 * @date 2020/7/7 17:06
 */
public class FailPublicConnect extends FinalConnect {

    public FailPublicConnect(ThrowStatus status) {
        this.code = status.getValue();
        this.msg = status.getReasonPhrase();
        this.success = false;
    }

    public FailPublicConnect(ThrowStatus status, String msg) {
        this.code = status.getValue();
        this.msg = msg;
        this.success = false;
    }
}
