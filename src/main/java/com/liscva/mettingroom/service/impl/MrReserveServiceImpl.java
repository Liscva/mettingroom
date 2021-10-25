package com.liscva.mettingroom.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.liscva.framework.core.lang.Assert;
import com.liscva.mettingroom.entity.dto.CurrUserReserveDayListSearchDto;
import com.liscva.mettingroom.entity.dto.ReserveDto;
import com.liscva.mettingroom.entity.po.MrArea;
import com.liscva.mettingroom.entity.po.MrReserve;
import com.liscva.mettingroom.entity.po.MrReserveDayTime;
import com.liscva.mettingroom.mapper.MrAreaMapper;
import com.liscva.mettingroom.mapper.MrReserveMapper;
import com.liscva.mettingroom.service.MrReserveDayTimeService;
import com.liscva.mettingroom.service.MrReserveService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 预约表 服务实现类
 * </p>
 *
 * @author 李诗诚
 * @since 2021-09-23
 */
@Service
public class MrReserveServiceImpl extends ServiceImpl<MrReserveMapper, MrReserve> implements MrReserveService {

    @Resource
    MrReserveMapper mrReserveMapper;

    @Autowired
    MrReserveDayTimeService mrReserveDayTimeService;

    @Resource
    MrAreaMapper mrAreaMapper;

    @Override
    @Transactional
    public synchronized void reserveMettingRoom(ReserveDto reserveDto) {
        //1.查询是否存该会议室
        MrArea mrArea = mrAreaMapper.selectById(reserveDto.getAreaId());
        Assert.notNull(mrArea, "该会议室[{}]不存在,请联系管理员创建会议室", reserveDto.getAreaId());
        //2.插入预约记录并返回预约ID
        MrReserve mrReserve = reserveDtoToMrReserve(reserveDto);
        mrReserveMapper.insert(mrReserve);

        //3.更新对应的时间节点为已预约
        List<MrReserveDayTime> mrReserveDayTimeList = mrReserveDayTimeService.getDayTimeList(reserveDto.getDayTimeDto());
        Assert.notEmpty(mrReserveDayTimeList, "会议室在该时间段类未开放，请重新选择预约时间！");

        List<MrReserveDayTime> filterList = mrReserveDayTimeList.stream().filter(item -> item.getTReserveAfterId() != null || item.getTReserveBeforeId() != null).collect(Collectors.toList());
        Assert.isTrue(CollectionUtil.isEmpty(filterList) || filterList.size() <= 1, "该会议室时间段预约冲突,请重新检查预约时间");

        mrReserveDayTimeService.reserveDayTime(mrReserve.getReserveId(), mrReserveDayTimeList);
    }

    @Override
    public List<String> getCurrUserReserveDayList(CurrUserReserveDayListSearchDto currUserReserveDayListSearchDto) {
        return mrReserveMapper.getCurrUserReserveDayList(currUserReserveDayListSearchDto);
    }

    private MrReserve reserveDtoToMrReserve(ReserveDto reserveDto) {
        MrReserve mrReserve = new MrReserve();
        mrReserve.setReserveId(reserveDto.getReserveId());
        mrReserve.setReserveAreaId(reserveDto.getAreaId());
        mrReserve.setReserveTodo(reserveDto.getReserveTodo());
        mrReserve.setReserveUser(reserveDto.getReserveUser().getUserCode());
        mrReserve.setReserveUserName(reserveDto.getReserveUser().getUserName());
        LocalDateTime nowTime = LocalDateTime.now();
        mrReserve.setReserveTime(nowTime.toString());
        return mrReserve;
    }
}
