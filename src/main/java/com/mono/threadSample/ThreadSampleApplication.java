package com.mono.threadSample;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.context.request.RequestContextListener;

@SpringBootApplication
@EnableAsync
public class ThreadSampleApplication {

    public static void main(String[] args) {
        SpringApplication sa = new SpringApplication(ThreadSampleApplication.class);
        sa.setLogStartupInfo(false);
        sa.setBannerMode(Banner.Mode.OFF);
        sa.run(args);
    }

    @Bean("threadPoolTaskExecutor")
    public TaskExecutor getAsyncExecutor() {
        ContextAwarePoolExecutor executor = new ContextAwarePoolExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(20);
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setThreadNamePrefix("mcd-async-");
        executor.initialize();
        return executor;
    }

    @Bean
    public RequestContextListener requestContextListener() {
        return new RequestContextListener();
    }

}
