package com.boss.cache.service;


import com.boss.cache.entity.TaskInfo;

import java.util.List;

/**
 * @author Tongshan.Han@partner.readJson.com
 * @Description:
 * @date 2018/9/10 14:16
 */
public interface JobAndTriggerService {

    /**
     * 保存定时任务
     *
     * @param taskInfo
     */
    void addJob(TaskInfo taskInfo);

    /**
     * 修改定时任务
     *
     * @param taskInfo
     */
    void updateJob(TaskInfo taskInfo);

    /**
     * 删除一个job
     */
    void deleteJob(TaskInfo taskInfo);

    /**
     * 暂停一个job
     */
    void pauseJob(TaskInfo taskInfo);

    /**
     * 恢复一个job
     */
    void resumejob(TaskInfo taskInfo);

    /**
     * 获取所有计划中的任务列表
     *
     * @return
     */
    List<TaskInfo> getAllJob();

    /**
     * 所有正在运行的job
     *
     * @return
     */
    List<TaskInfo> getRunningJob();

    /**
     * 暂停调度中所有的job任务
     */
    void pauseAll();

    /**
     * 恢复调度中所有的job的任务
     */
    void resumeAll();

}
