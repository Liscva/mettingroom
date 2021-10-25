package com.liscva.mettingroom.entity.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ReserveDto {
    /**
     * 预约ID
     */
    private Integer reserveId;

    /**
     * 需要预约的区域
     */
    @NotNull(message = "预约区域不能为空")
    private Integer areaId;

    /**
     * 预约会议室要做的事情
     */
    @NotBlank(message = "预约备注不能为空")
    private String reserveTodo;

    /**
     * 需要预约的时间
     */
    @NotNull(message = "预约时间不能为空")
    private DayTimeDto dayTimeDto;

    /**
     * 预约人
     */
    @NotNull(message = "预约人不能为空")
    private ReserveUser reserveUser;
}
