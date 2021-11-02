package com.liscva.mettingroom.controller;


import com.liscva.framework.core.connect.DefaultPublicConnect;
import com.liscva.framework.core.connect.FinalConnect;
import com.liscva.mettingroom.entity.dto.SearchReserveInfoDto;
import com.liscva.mettingroom.service.MrReserveDayTimeService;
import com.liscva.mettingroom.service.MrReserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * 会议预约24小时预约表 前端控制器
 *
 * @author 李诗诚
 * @since 2021-08-30
 */
@RestController
@RequestMapping("/mrReserveDayTime")
public class MrReserveDayTimeController {
    @Autowired
    MrReserveDayTimeService mrReserveDayTimeService;
    /**
     * 获取制定日期的预约信息
     * @author liscva
     * @date 2021/10/14 11:21
     * @param day
     * @return com.liscva.framework.core.connect.FinalConnect
     */
    @GetMapping("/getReserveInfoByDayTime.htm")
    public FinalConnect getReserveInfoByDayTime(SearchReserveInfoDto searchReserveInfoDto){
        return DefaultPublicConnect.of(mrReserveDayTimeService.getReserveInfoByDayTime(searchReserveInfoDto));
    }
}
