package com.boss.design;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ZjDepartmentEquityVO {
    //日期
    private String date;
    //部门组别
    private String groupName;
    //今日总余额
    private BigDecimal todayBalance;

    public ZjDepartmentEquityVO(String date,String groupName,BigDecimal todayBalance){
        this.date = date;
        this.todayBalance =todayBalance;
        this.groupName =groupName;
    }
}
