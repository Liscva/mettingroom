package com.liscva.mettingroom.entity.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 查询当前用户预约天数集合信息的实体
 * @author liscva
 * @date 2021/11/1 10:48
 */
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

    /**
     * 会议室ID
     */
    @NotBlank(message = "会议室ID")
    private String areaId;
}
