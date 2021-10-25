package com.liscva.mettingroom;

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
public class MettingRoomApplication {

    public static void main(String[] args) {
        SpringApplication.run(MettingRoomApplication.class, args);
    }

}
