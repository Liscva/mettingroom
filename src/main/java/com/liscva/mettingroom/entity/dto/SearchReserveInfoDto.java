package com.liscva.mettingroom.entity.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class SearchReserveInfoDto {

    @NotNull(message = "查询的日期不能为空")
    private String day;

    @NotNull(message = "查询的区域不能为空")
    private String areaId;

}
