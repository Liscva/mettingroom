package com.liscva.mettingroom.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author 李诗诚
 * @since 2021-10-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class LoginDto implements Serializable {

    /**
     * 用户登录名
     */
    @NotBlank(message = "用户名不能为空！")
    private String userAccount;

    /**
     * 用户加密密码
     */
    @NotBlank(message = "用户名密码不能为空！")
    private String userPassword;

}
