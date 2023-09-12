package com.security.springbootsecurity;

import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
@MapperScan("com.security.springbootsecurity.mapper")
public class SpringbootSecurityApplication {

    public static void main(String[] args) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        SpringApplication.run(SpringbootSecurityApplication.class, args);
        stopwatch.stop();
        log.info("项目启动耗时:{}s",stopwatch.elapsed().getSeconds());
    }

}
