package com.boss.cache.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TaskInfo implements Serializable {

    private static final long serialVersionUID = 1959615199601368787L;

    private Long id;

    private String dataType;

    private String jobName;

    private String jobGroup;

    private String cronExpression;

    private Integer jobStatus;

    private String beanName;

    private String methodName;

    private Date createTime;

    private String updateTime;

    private String remark;


}