package com.liscva.mettingroom.mapper;

import com.liscva.mettingroom.entity.dto.CurrUserReserveDayListSearchDto;
import com.liscva.mettingroom.entity.po.MrReserve;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 预约表 Mapper 接口
 * </p>
 *
 * @author 李诗诚
 * @since 2021-09-23
 */
public interface MrReserveMapper extends BaseMapper<MrReserve> {

    List<String> getCurrUserReserveDayList(CurrUserReserveDayListSearchDto currUserReserveDayListSearchDto);
}
