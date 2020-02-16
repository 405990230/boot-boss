package com.boss.cache.job;

import com.boss.cache.service.NewsCacheService;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.PersistJobDataAfterExecution;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

/**
 * @author Tongshan.Han@partner.readJson.com
 * @Description:
 * @date 2018/9/7 16:09
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class QuartzNewsJob implements Job, Serializable {

    @Autowired
    private NewsCacheService newsCacheService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        newsCacheService.newsCache();
    }

}
