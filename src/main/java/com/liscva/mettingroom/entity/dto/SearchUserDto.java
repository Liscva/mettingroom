package com.liscva.mettingroom.entity.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SearchUserDto {

    /**
     * 用户名
     */
    private String userName;
}
