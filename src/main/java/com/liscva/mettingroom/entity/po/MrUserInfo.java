package com.liscva.mettingroom.entity.po;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.liscva.mettingroom.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户信息扩展表
 * </p>
 *
 * @author 李诗诚
 * @since 2021-10-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MrUserInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用户编码
     */
    @TableId(value = "user_code", type = IdType.ID_WORKER_STR)
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


}
