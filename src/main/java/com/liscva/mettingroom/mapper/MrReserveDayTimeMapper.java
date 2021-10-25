package com.liscva.mettingroom.mapper;

import com.liscva.mettingroom.entity.po.MrReserveDayTime;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 会议预约24小时预约表 Mapper 接口
 * </p>
 *
 * @author 李诗诚
 * @since 2021-08-30
 */
public interface MrReserveDayTimeMapper extends BaseMapper<MrReserveDayTime> {

    /**
     * 获取可插入空时间的日期
     * @author liscva
     * @date 2021/8/30 10:00
     * @param timeDay
     * @return java.util.List<java.lang.String>
     */
    List<String> selectEmptyTimeDay(List<String> timeDay);

}
