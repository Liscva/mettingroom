package com.liscva.mettingroom.entity.dto;

import com.liscva.mettingroom.entity.po.MrUser;
import com.liscva.mettingroom.entity.po.MrUserInfo;
import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class RegisterUser {

    @NotNull(message = "用户信息不能为空")
    private MrUser mrUser;

    @NotNull(message = "用户信息不能为空")
    private MrUserInfo mrUserInfo;
}
