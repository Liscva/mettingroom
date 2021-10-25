package com.liscva.mettingroom.entity.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class AreaInfo implements Serializable {

    /**
     * 区域ID主键
     */
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
