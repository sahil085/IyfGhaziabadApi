package com.IyfGZB.schedulers;

import com.IyfGZB.services.SchedularService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * Created by sahil on 3/8/18.
 */
@EnableScheduling
public class Schedulers {

    @Autowired
    SchedularService schedularService;
    @Scheduled(cron = "0 0 23 ? * SUN *")
    public void sendAttendanceSheet(){
        schedularService.sendSeminarAttendanceSheet();
    }
}
