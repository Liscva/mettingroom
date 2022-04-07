package com.liscva.mettingroom.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.liscva.mettingroom.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
public class MrUser extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用户code
     */
    @TableId(value = "user_code", type = IdType.AUTO)
    private Integer userCode;

    /**
     * 用户登录名
     */
    private String userAccount;

    /**
     * 用户加密密码
     */
    private String userPassword;

    /**
     * 用户状态,默认0启动，1禁用,2删除
     */
    private Integer userStatus;


}
