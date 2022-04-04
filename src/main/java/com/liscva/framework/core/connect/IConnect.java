package com.liscva.framework.core.connect;

/**
 * @author 李诗诚
 * @date 2020/7/7 14:10
 * 定义微服务之间的交互接口
 */
public interface IConnect {
    /**
     * 获取交互的代码
     * @return HTTP状态码
     */
    String getCode();


    /**
     * 获取交互的文字消息
     * @return 提示消息
     */
    String getMsg();


}
