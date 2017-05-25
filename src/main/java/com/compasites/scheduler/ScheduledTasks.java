package com.compasites.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by Sobhan Babu on 06-06-2016.
 */
@Component
public class ScheduledTasks {

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() throws Exception {
        System.out.println("------------Starting the batch job---------------");
    }
}
