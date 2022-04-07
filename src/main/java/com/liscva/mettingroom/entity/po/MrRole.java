package com.liscva.mettingroom.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.liscva.mettingroom.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author 李诗诚
 * @since 2021-10-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MrRole extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 角色编码
     */
    @TableId(value = "role_code", type = IdType.AUTO)
    private Integer roleCode;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色状态 默认0启用， 1禁用，2删除
     */
    private Integer roleStatus;


}
