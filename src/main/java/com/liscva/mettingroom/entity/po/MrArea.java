package com.liscva.mettingroom.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.liscva.mettingroom.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 区域表
 * </p>
 *
 * @author 李诗诚
 * @since 2021-08-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MrArea extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 区域ID主键
     */
    @TableId(value = "area_id", type = IdType.AUTO)
    private Integer areaId;

    /**
     * 区域名称
     */
    private String areaName;

    /**
     * 区域说明文字
     */
    private String areaExplication;
}
