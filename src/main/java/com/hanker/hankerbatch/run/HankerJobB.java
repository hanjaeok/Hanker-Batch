package com.hanker.hankerbatch.run;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Component
public class HankerJobB extends QuartzJobBean {
    private static final Logger log = LoggerFactory.getLogger(HankerJobB.class);

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("작업 스케쥴러 B");
    }
}
