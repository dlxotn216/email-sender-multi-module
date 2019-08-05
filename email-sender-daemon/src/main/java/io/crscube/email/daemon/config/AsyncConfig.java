package io.crscube.email.daemon.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import software.amazon.awssdk.utils.ThreadFactoryBuilder;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by itaesu on 29/07/2019.
 *
 * @author Lee Tae Su
 */
@EnableAsync
@Configuration
public class AsyncConfig {
    @Value("${app.maxProcessCapacity}")
    private int capacity;

    @Bean(name = "sendEmailScheduler")
    public Executor sendEmailScheduler() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(capacity);
        executor.setMaxPoolSize(capacity);
        executor.setQueueCapacity(10);
        executor.setThreadNamePrefix("daemon-send-scheduler-");
        executor.setThreadGroupName("sendEmailScheduler");
        executor.setThreadFactory(new ThreadFactoryBuilder().threadNamePrefix("send-daemon").build());
        executor.initialize();
        return executor;
    }
}