package com.hanker.hankerbatch.conf;

import com.hanker.hankerbatch.run.HankerJobA;
import com.hanker.hankerbatch.run.HankerJobB;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.*;

import static org.quartz.JobBuilder.newJob;

@Configuration
public class JobSetting {

    @Autowired
    private Scheduler scheduler;

    @PostConstruct
    public void start(){

        JobDetail jobDetailA = buildJobDetail(HankerJobA.class, new HashMap());
        JobDetail jobDetailB = buildJobDetail(HankerJobB.class, new HashMap());

        try{
            scheduler.scheduleJob(jobDetailA, buildJobTrigger("0/20 * * * * ?"));
            scheduler.scheduleJob(jobDetailB, buildJobTrigger("0/30 * * * * ?"));
        } catch(SchedulerException e){
            e.printStackTrace();
        }
    }

    public Trigger buildJobTrigger(String scheduleExp){
        return TriggerBuilder.newTrigger()
                .withSchedule(CronScheduleBuilder.cronSchedule(scheduleExp)).build();
    }

    public JobDetail buildJobDetail(Class job, Map params){
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.putAll(params);

        return newJob(job).usingJobData(jobDataMap).build();
    }
}
