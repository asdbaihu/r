package com.crawler.r.task;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;


@Component
@EnableScheduling
public class TaskPool {

    @Bean
    public TaskScheduler taskScheduler(){
        ThreadPoolTaskScheduler pool=new ThreadPoolTaskScheduler();
        pool.setPoolSize(10);
        pool.setThreadNamePrefix("r-task");
        return pool;
    }

}
