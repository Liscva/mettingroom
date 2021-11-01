package com.liscva.mettingroom.entity.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class DeleteUserDto {

    @NotNull(message = "用户账户不能为空")
    private String userAccount;

    private Boolean softDelete = true;
}
