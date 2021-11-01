package com.liscva.mettingroom.entity.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserInfo implements Serializable {

    /**
     * 用户登录名
     */
    private String userAccount;

    /**
     * 用户编码
     */
    private Integer userCode;

    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 用户邮箱
     */
    private String userEmail;

    /**
     * 用户手机号 11位
     */
    private String userPhone;

    /**
     * 用户创建日期
     */
    private String createTime;

}
