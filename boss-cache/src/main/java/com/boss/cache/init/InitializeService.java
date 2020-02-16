package com.boss.cache.init;

import com.boss.cache.entity.TaskInfo;
import com.boss.cache.service.JobAndTriggerService;
import lombok.extern.log4j.Log4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
@Order(value = 1)
@Log4j
public class InitializeService implements CommandLineRunner {

    @Value("${quarz.backend.service.enable.caching}")
    private boolean startFlag;

    @Value("${news.cache.cronExpression}")
    private String cronExpression;

    @Autowired
    private JobAndTriggerService jobAndTriggerService;

    @Override
    public void run(String... strings) {
        try {
            /** 1.存储目录全部清除 */
            String path = this.getClass().getResource("/").getPath();
            File baseDir = new File(path);
            if (baseDir.isDirectory()) {
                File[] files = baseDir.listFiles();
                for (File file : files) {
                    if (file.getName().matches("\\d{4}-\\d{1,2}-\\d{1,2}")) {
                        log.info("==========存储目录全部清除getName==============>" + file.getName());
                        FileUtils.deleteDirectory(new File(file.getPath()));
                    }
                }
            }
            if (startFlag) {
                /** 2.缓存缓存 */
                TaskInfo info = new TaskInfo();
                info.setDataType("NEWS");
                info.setJobName("BONAPP_JOB_CACHE_NEWS");
                info.setJobGroup("BONAPP_JOB_GROUP_NEWS");
                info.setCronExpression(cronExpression);
                info.setJobStatus(0);
                jobAndTriggerService.addJob(info);
                /** 3.存储目录定时清除JOB */
                TaskInfo dirTask = new TaskInfo();
                dirTask.setDataType("DIRECTORY");
                dirTask.setJobName("BONAPP_JOB_DIRECTORY_IMAGES");
                dirTask.setJobGroup("BONAPP_JOB_GROUP_DIRECTORY");
                dirTask.setCronExpression("0 0 */3 * * ?");//每3小时执行一次
                //dirTask.setCronExpression("0 */2 * * * ?");//为测试方便临时调整为2分钟执行一次,发布到服务器上需删除此行代码
                dirTask.setJobStatus(0);
                jobAndTriggerService.addJob(dirTask);
            } else {
                log.warn("application.properties配置文件中未设置开启定时任务!");
            }
        } catch (Exception e) {
            log.error("InitializeService run error ", e);
        }
    }
}
