package com.liscva.mettingroom.entity.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CurrUserReserveDayListSearchDto {

    /**
     * 月份
     */
    @NotBlank(message = "月份不能为空")
    private String month;

    /**
     * 用户ID
     */
    @NotBlank(message = "查询用户ID不能为空")
    private String userCode;
}
