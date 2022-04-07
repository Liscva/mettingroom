package com.liscva.mettingroom.controller;


import com.liscva.framework.core.connect.DefaultPublicConnect;
import com.liscva.framework.core.connect.FinalConnect;
import com.liscva.framework.security.annotation.CheckLogin;
import com.liscva.mettingroom.entity.dto.CurrUserReserveDayListSearchDto;
import com.liscva.mettingroom.entity.dto.ReserveDto;
import com.liscva.mettingroom.service.MrReserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 预约表 前端控制器
 *
 * @author 李诗诚
 * @since 2021-09-23
 */
@RestController
@RequestMapping("/mrReserve")
@CheckLogin
public class MrReserveController {

    @Autowired
    MrReserveService mrReserveService;

    /**
     * 预约会议室功能
     * @author 李诗诚
     * @date 2021/9/23 15:18
     * @param reserveDto 会议室预约传输实体
     * @return com.liscva.framework.core.connect.FinalConnect
     */
    @PostMapping("/reserveMettingRoom.htm")
    public FinalConnect reserveMettingRoom(@Valid @RequestBody ReserveDto reserveDto){
        mrReserveService.reserveMettingRoom(reserveDto);
        return DefaultPublicConnect.of("预约成功！");
    }



    /**
     * 获取当前用户在这个月所预约的日期集合
     * @author liscva
     * @date 2021/10/14 10:24
     * @param currUserReserveDayListSearchDto
     * @return com.liscva.framework.core.connect.FinalConnect
     */
    @GetMapping("/getCurrUserReserveDayList.htm")
    public FinalConnect getCurrUserReserveDayList(@Valid CurrUserReserveDayListSearchDto currUserReserveDayListSearchDto){
        return DefaultPublicConnect.of(mrReserveService.getCurrUserReserveDayList(currUserReserveDayListSearchDto));
    }



}
