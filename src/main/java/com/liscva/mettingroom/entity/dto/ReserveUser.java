package com.liscva.mettingroom.entity.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ReserveUser {

    /**
     * 预约人ID
     */
    @NotNull(message = "预约人ID不能为空")
    private Integer userCode; //预约人ID

    /**
     * 预约人名称
     */
    @NotBlank(message = "预约人名不能为空")
    private String userName; //预约人名
}
