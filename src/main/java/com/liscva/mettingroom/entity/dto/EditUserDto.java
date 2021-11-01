package com.liscva.mettingroom.entity.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class EditUserDto {

    @NotNull(message = "用户登录名")
    private String userAccount;

    /**
     * 用户名称
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

}
