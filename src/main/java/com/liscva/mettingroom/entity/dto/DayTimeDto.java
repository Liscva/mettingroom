package com.liscva.mettingroom.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;

@Data
public class DayTimeDto {

    /**
     * 哪一天
     */
    @NotBlank(message = "具体日期不能为空")
    private String day;

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;
}
