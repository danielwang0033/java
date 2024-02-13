package com.coin.web;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan(basePackages = "com.coin")
@MapperScan({"com.coin.mapper", "com.coin.mapper.ext"})
@EnableScheduling
@EnableAsync
public class CoinWebApplication {

    private static final Logger logger = LoggerFactory.getLogger(CoinWebApplication.class);

    public static void main(String[] args) {
        try {
            SpringApplication.run(CoinWebApplication.class, args);
        } catch (Exception e) {
            if (!e.getClass().getName().equals("org.springframework.boot.devtools.restart.SilentExitExceptionHandler$SilentExitException")) {
                logger.error("CoinWebApplication-error", e);
            }
        }
    }
}
