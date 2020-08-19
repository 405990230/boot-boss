package com.boss.javabasic;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Task {
    public static void main(String[] args) {
        //60秒内推送10次
        pushLoginCount("活跃人数",10,60,10);
    }


    /**
     *  比如均匀推送登入人数
     * @param type  推送的服务类型
     * @param lastCount   起始数量
     * @param second   多少秒
     * @param count   推送多少次
     */
    //@Async
    public static void pushLoginCount(String type, long lastCount, long second, long count) {
        log.info("Task :pushLoginCount 服务:{}:本轮{}秒内发送{}次",type,second,count);
        long sleepTime = (long)((double) second/count*1000);
        try{
            for(int i = 1;i<=count;i++){
                Thread.sleep(sleepTime);
                lastCount++;
                log.info("pushLoginCount ：发送次数：{},发送数据：{}",i,type+"_"+lastCount);
            }
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
