package com.liscva.mettingroom.service;

import com.liscva.framework.core.connect.FinalConnect;
import com.liscva.mettingroom.entity.dto.CurrUserReserveDayListSearchDto;
import com.liscva.mettingroom.entity.dto.ReserveDto;
import com.liscva.mettingroom.entity.po.MrReserve;
import com.baomidou.mybatisplus.extension.service.IService;
import com.liscva.mettingroom.entity.vo.ReserveDayInfo;

import java.util.List;

/**
 * <p>
 * 预约表 服务类
 * </p>
 *
 * @author 李诗诚
 * @since 2021-09-23
 */
public interface MrReserveService extends IService<MrReserve> {

    /**
     *  预约会议室
     * @author liscva
     * @date 2021/9/23 16:01
     * @param reserveDto
     */
    void reserveMettingRoom(ReserveDto reserveDto);

    /**
     * 查询用户预约日期
     * @author liscva
     * @date 2021/10/14 10:30
     * @param currUserReserveDayListSearchDto
     * @return java.util.List<java.lang.String>
     */
    List<String> getCurrUserReserveDayList(CurrUserReserveDayListSearchDto currUserReserveDayListSearchDto);


}
