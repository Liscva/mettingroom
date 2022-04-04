package com.liscva.framework.core;


import com.liscva.framework.core.exception.CoreException;

/**
 * @author 李诗诚
 * @date 2020/7/7 14:16
 * 定义所有状态码，以及状态码规范
 * 所有状态码均为7位长度的字符串
 * 00：未知
 * 01：SQL    不知道分类填0（1新增，2修改，3删除,4查询）
 * 02：找不到xxx  1参数，2对象，3数据库数据
 * 03：业务
 * 04：连接    不知道分类填0（1数据库，2中间件，3第三方，4鉴权中心）
 * 05：IO     （1上传，2下载，3删除，4格式化）
 * */
public enum ThrowStatus {

    OK("200", "状态OK"),

    /*********************
     * A类状态，用户操作 ：以A开头
     * 如用户参数传递有问题之类
     * ***********************/
    ASSERT_ERR("A021000", "参数检查异常！"),
    REQUEST_PARAM_NULL("A021001", "请求参数为空，不予执行"),
    USER_TIMEOUT("A022001", "用户登录超时,请重新登录"),
    /*********************
     * B类状态，程序代码状态 ：以B开头
     * 如sql执行失败之类的程序状态
     * ***********************/
    SYSCONFIG_NULL_ERROR("B023001", "系统参数不存在"),

    /*********************
     * C类状态，第三方模块状态 ：以C开头
     * 如数据库连不上，redis连不上之类
     * ***********************/


    UNKNOWN_ERROR("C000000", "未知异常");

    private final String value;
    private final String reasonPhrase;

    ThrowStatus(String value, String reasonPhrase) {
        this.value = value;
        this.reasonPhrase = reasonPhrase;
    }

    public String getValue() {
        return this.value;
    }

    public String getReasonPhrase() {
        return this.reasonPhrase;
    }

    public static String resolvePhrase(String code) throws CoreException {
        for (ThrowStatus status : values()) {
            if (status.getValue().equals(code) ) {
                return status.getReasonPhrase();
            }
        }
        throw new CoreException("无此状态码");
    }

    public static ThrowStatus resolve(String code) throws CoreException {
        for (ThrowStatus status : values()) {
            if (status.getValue().equals(code)) {
                return status;
            }
        }
        throw new CoreException("无此状态码");
    }
}
