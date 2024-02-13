package com.coin.service.config;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

@EnableAsync
@Configuration
@Slf4j
public class ConcurrencyConfig {

    @Bean("sysThreadPool")
    public TaskExecutor sysThreadPool() {
        int cpuNum = 8;
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2 * cpuNum);
        executor.setMaxPoolSize(4 * cpuNum);
        executor.setQueueCapacity(3 * cpuNum);
        executor.setKeepAliveSeconds(15);
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setThreadNamePrefix("task-thread-");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        executor.initialize();
        log.info(">>>>>>>>>sysThreadPool: {}", JSONUtil.toJsonStr(executor));
        return executor;
    }
}
