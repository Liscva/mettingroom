package com.liscva.mettingroom;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = {
        "com.liscva.framework.core",
        "com.liscva.mettingroom",
})
@MapperScan("com.liscva.mettingroom.mapper")
@EnableScheduling
@Slf4j
public class MettingRoomApplication {

    public static void main(String[] args) {
        SpringApplication.run(MettingRoomApplication.class, args);
        log.info(MettingRoomApplication.class.getSimpleName() + " Start Success!");
    }

}
