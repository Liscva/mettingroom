package com.liscva.mettingroom.service;

import com.liscva.mettingroom.entity.dto.DayTimeDto;
import com.liscva.mettingroom.entity.po.MrReserveDayTime;
import com.baomidou.mybatisplus.extension.service.IService;
import com.liscva.mettingroom.entity.vo.ReserveDayInfo;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 * 会议预约24小时预约表 服务类
 * </p>
 *
 * @author 李诗诚
 * @since 2021-08-30
 */
public interface MrReserveDayTimeService extends IService<MrReserveDayTime> {

    void emptyTimeInput();

    void emptyTimeInput(int dayCount);

    boolean hasDayTime(LocalDate localDate);

    List<MrReserveDayTime> getDayTimeList(DayTimeDto dayTimeDto);

    void reserveDayTime(Integer reserveId,List<MrReserveDayTime> mrReserveDayTimes);

    List<ReserveDayInfo> getReserveInfoByDayTime(String day);
}
