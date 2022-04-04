package com.liscva.framework.core.connect;

/**
 * @author 李诗诚
 * @date 2020/7/7 15:44
 * 抽象链接DTO，扩展设置返回代码和返回信息的功能，默认实现default PublicConnect
 */
public abstract class AbstractPublicConnect extends FinalConnect {

    /**
     * 设置code
     */
    public abstract void setCode(String code);

    /**
     * 设置message
     */
    public abstract void setMsg(String msg);
}
