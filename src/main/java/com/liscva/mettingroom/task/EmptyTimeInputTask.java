package com.liscva.mettingroom.task;

import com.liscva.mettingroom.service.MrReserveDayTimeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;


/**
 * 在表中插入空闲的日期数据，方便后来人预约
 * @author liscva
 * @date 2021/8/30 9:40
 */
@Configuration
@Slf4j
public class EmptyTimeInputTask {

    @Autowired
    MrReserveDayTimeService mrReserveDayTimeService;

    @Scheduled(cron = "0 0 0 */1 * ?")
    private void configureTasks() {
        mrReserveDayTimeService.emptyTimeInput();
    }
}
