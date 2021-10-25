package com.liscva.mettingroom.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MrReserveDayTimeServiceTest {

    @Autowired
    MrReserveDayTimeService mrReserveDayTimeService;

    @Test
    void emptyTimeInput() {
        mrReserveDayTimeService.emptyTimeInput();
    }
}