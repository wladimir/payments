package com.modusbox.payment;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class SenderConfiguration {
    @Bean("senderTaskExecutor")
    public TaskExecutor exportTaskExecutor() {
        final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(50);
        executor.setMaxPoolSize(100);
        executor.setQueueCapacity(150);
        executor.setThreadNamePrefix("sender");
        executor.setAwaitTerminationSeconds(60);
        return executor;
    }
}
