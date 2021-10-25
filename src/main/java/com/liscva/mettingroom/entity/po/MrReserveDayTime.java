package com.liscva.mettingroom.entity.po;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 会议预约24小时预约表
 * </p>
 *
 * @author 李诗诚
 * @since 2021-08-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class MrReserveDayTime implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 预约的日期，如2021-08-26
     */
    private String tDayTime;

    /**
     * 当前时间点 如 08:30
     */
    private String tTimestamp;

    /**
     * 当前时间戳预约的ID
     */
    private Integer tReserveBeforeId;

    /**
     * 当前时间戳预约的ID
     */
    private Integer tReserveAfterId;

}
