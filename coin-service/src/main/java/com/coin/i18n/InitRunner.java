package com.coin.i18n;

import com.coin.service.DictI18nService;
import com.coin.service.DictService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Order(1)
@Slf4j
public class InitRunner implements CommandLineRunner {

    @Resource
    private DictService dictService;
    @Resource
    private DictI18nService dictI18nService;

    @Override
    public void run(String... args) {
        dictService.refreshXssConfig();
        dictI18nService.refresh();
    }
}
