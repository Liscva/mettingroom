package com.liscva.mettingroom.entity.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class IncreaseAreaDto {

    @NotNull(message = "会议室名称不能为空")
    private String areaName;

    @NotNull(message = "会议室说明不能为空")
    private String areaExplication;

}
