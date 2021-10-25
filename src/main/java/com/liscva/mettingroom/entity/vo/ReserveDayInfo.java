package com.liscva.mettingroom.entity.vo;

import com.liscva.mettingroom.entity.po.MrReserve;
import lombok.Data;

@Data
public class ReserveDayInfo {

    /**
     * 开始时间
     */
    private String headTime;

    /**
     * 结束时间
     */
    private String tailTime;

    /**
     * 是否空闲
     */
    private Boolean free;

    /**
     * 预约Id
     */
    private Integer reserveId;

    /**
     * 会议信息
     */
    private MrReserve mrReserve;
}
