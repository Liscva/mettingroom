package com.liscva.mettingroom.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.liscva.framework.core.ThrowStatus;
import com.liscva.framework.core.exception.CoreException;
import com.liscva.mettingroom.entity.dto.DayTimeDto;
import com.liscva.mettingroom.entity.dto.SearchAreaDto;
import com.liscva.mettingroom.entity.dto.SearchReserveInfoDto;
import com.liscva.mettingroom.entity.po.MrArea;
import com.liscva.mettingroom.entity.po.MrConfig;
import com.liscva.mettingroom.entity.po.MrReserveDayTime;
import com.liscva.mettingroom.entity.vo.AreaInfo;
import com.liscva.mettingroom.entity.vo.ReserveDayInfo;
import com.liscva.mettingroom.mapper.MrAreaMapper;
import com.liscva.mettingroom.mapper.MrReserveDayTimeMapper;
import com.liscva.mettingroom.mapper.MrReserveMapper;
import com.liscva.mettingroom.service.MrConfigService;
import com.liscva.mettingroom.service.MrReserveDayTimeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 会议预约24小时预约表 服务实现类
 * </p>
 *
 * @author 李诗诚
 * @since 2021-08-30
 */
@Service
public class MrReserveDayTimeServiceImpl extends ServiceImpl<MrReserveDayTimeMapper, MrReserveDayTime> implements MrReserveDayTimeService {

    @Autowired
    MrConfigService mrConfigService;

    @Resource
    MrReserveMapper reserveMapper;

    @Resource
    MrAreaMapper mrAreaMapper;
    @Resource
    MrReserveDayTimeMapper mrReserveDayTimeMapper;

    @Override
    public void emptyTimeInput() {
        List<AreaInfo> mrAreas = mrAreaMapper.findAreaList(new SearchAreaDto());
        mrAreas.stream().forEach(item->{
            emptyTimeInput(item.getAreaId());
        });
    }

    @Override
    public void emptyTimeInput(int areaId) {
        MrConfig mrConfig = mrConfigService.queryMrConfigByConfigCode("advanceDayTime");
        emptyTimeInput(areaId,Integer.parseInt(mrConfig.getConfigValue()));
    }

    public synchronized void emptyTimeInput(int areaId,int dayCount) {
        List<MrReserveDayTime> dayList = new ArrayList<>();
        for (int i = 0; i <= dayCount; i++) {
            LocalDate localDate = LocalDate.now().plusDays(i);
            if (!hasDayTime(localDate,areaId)) {
                String day = localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                configDayList(day, dayList ,areaId);
            }
        }
        saveBatch(dayList);
    }


    private void configDayList(String day, List<MrReserveDayTime> dayList, int areaId) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
            String mettingBeginTime = mrConfigService.queryMrConfigValueByConfigCode("mettingBeginTime");
            String mettingEndTime = mrConfigService.queryMrConfigValueByConfigCode("mettingEndTime");
            String intervalTime = mrConfigService.queryMrConfigValueByConfigCode("intervalTime");
            Calendar now = Calendar.getInstance();
            Date beginDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(day + " " + mettingBeginTime);
            Date endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(day + " " + mettingEndTime);
            now.setTime(beginDate);
            Calendar end = Calendar.getInstance();
            end.setTime(endDate);
            do {
                MrReserveDayTime mrReserveDayTime = new MrReserveDayTime();
                mrReserveDayTime.setTDayTime(day);
                String timestamp = simpleDateFormat.format(now.getTime());
                mrReserveDayTime.setTTimestamp(timestamp);
                mrReserveDayTime.setTAreaId(areaId);
                dayList.add(mrReserveDayTime);
                now.add(Calendar.MINUTE, Integer.parseInt(intervalTime));
            } while (now.before(end)||now.equals(end));
        }catch (Exception e){
            throw CoreException.build(ThrowStatus.SYSCONFIG_NULL_ERROR,e);
        }
    }

    @Override
    public boolean hasDayTime(LocalDate localDate,int areaId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("t_day_time", localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        queryWrapper.eq("t_area_id", areaId);
        List<MrReserveDayTime> mrReserveDayTimes = mrReserveDayTimeMapper.selectList(queryWrapper);
        return CollectionUtil.isNotEmpty(mrReserveDayTimes);
    }

    @Override
    public List<MrReserveDayTime> getDayTimeList(DayTimeDto dayTimeDto) {
        String mettingBeginTime = dayTimeDto.getStartTime();
        if(StrUtil.isEmpty(mettingBeginTime))
            mettingBeginTime = mrConfigService.queryMrConfigValueByConfigCode("mettingBeginTime");
        String mettingEndTime = dayTimeDto.getEndTime();
        if(StrUtil.isEmpty(mettingEndTime))
            mettingEndTime = mrConfigService.queryMrConfigValueByConfigCode("mettingEndTime");
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("t_day_time",dayTimeDto.getDay());
        queryWrapper.ge("t_timestamp",mettingBeginTime);
        queryWrapper.le("t_timestamp",mettingEndTime);
        return mrReserveDayTimeMapper.selectList(queryWrapper);
    }

    @Override
    public void reserveDayTime(Integer reserveId, List<MrReserveDayTime> mrReserveDayTimes) {
        for (int i = 0; i < mrReserveDayTimes.size(); i++) {
            MrReserveDayTime mrReserveDayTime = mrReserveDayTimes.get(i);
            if(i==0)
                mrReserveDayTime.setTReserveAfterId(reserveId);
            else if(i==mrReserveDayTimes.size()-1)
                mrReserveDayTime.setTReserveBeforeId(reserveId);
            else {
                mrReserveDayTime.setTReserveBeforeId(reserveId);
                mrReserveDayTime.setTReserveAfterId(reserveId);
            }
            UpdateWrapper updateWrapper = new UpdateWrapper();
            updateWrapper.eq("t_day_time",mrReserveDayTime.getTDayTime());
            updateWrapper.eq("t_timestamp",mrReserveDayTime.getTTimestamp());
            mrReserveDayTimeMapper.update(mrReserveDayTime,updateWrapper);
        }
    }



    @Override
    public List<ReserveDayInfo> getReserveInfoByDayTime(SearchReserveInfoDto searchReserveInfoDto) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("t_day_time",searchReserveInfoDto.getDay());
        queryWrapper.eq("t_area_id",searchReserveInfoDto.getAreaId());
        List<MrReserveDayTime> mrReserveDayTimes = mrReserveDayTimeMapper.selectList(queryWrapper);
        List<ReserveDayInfo> reserveDayInfoList = new ArrayList<>();
        ReserveDayInfo reserveDayInfo = null;
        for (int i = 0; i < mrReserveDayTimes.size(); i++) {
            MrReserveDayTime mrReserveDayTime = mrReserveDayTimes.get(i);
            boolean isfree = mrReserveDayTime.getTReserveAfterId()==null&&mrReserveDayTime.getTReserveBeforeId()==null;
            if(i==0){
                reserveDayInfo = resetReserveDayInfo(mrReserveDayTime);
                continue;
            }
            if(reserveDayInfo.getFree()==isfree&&(isfree||mrReserveDayTime.getTReserveBeforeId()==reserveDayInfo.getReserveId())){
                reserveDayInfo.setTailTime(mrReserveDayTime.getTTimestamp());
            }
            if(mrReserveDayTime.getTReserveBeforeId()!=mrReserveDayTime.getTReserveAfterId()){
                reserveDayInfo.setTailTime(mrReserveDayTime.getTTimestamp());
                reserveDayInfoList.add(reserveDayInfo);
                reserveDayInfo = resetReserveDayInfo(mrReserveDayTime);
            }
            if(i==mrReserveDayTimes.size()-1){
                reserveDayInfoList.add(reserveDayInfo);
            }
        }
        reserveDayInfoList.stream().forEach(item->{
            if(item.getReserveId()!=null){
                item.setMrReserve(reserveMapper.selectById(item.getReserveId()));
            }
        });
        return reserveDayInfoList;
    }


    private ReserveDayInfo resetReserveDayInfo( MrReserveDayTime mrReserveDayTime){
        ReserveDayInfo reserveDayInfo = new ReserveDayInfo();
        reserveDayInfo.setHeadTime(mrReserveDayTime.getTTimestamp());
        reserveDayInfo.setReserveId(mrReserveDayTime.getTReserveAfterId());
        reserveDayInfo.setFree(mrReserveDayTime.getTReserveAfterId()==null);
        return reserveDayInfo;
    }
}
