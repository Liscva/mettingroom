package com.liscva.mettingroom.config;

import com.liscva.mettingroom.service.MrReserveDayTimeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author liscva
 **/
@Component
@Slf4j
public class EmptyTimeRunner implements ApplicationRunner {

    @Autowired
    MrReserveDayTimeService mrReserveDayTimeService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("加载会议可预约时间");
//        mrReserveDayTimeService.emptyTimeInput();
    }
}