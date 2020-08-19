package com.boss.design.behavioralModel.responsibility;

import lombok.Builder;
import lombok.Data;

@Data
public class LeaveRequest {
    /**天数*/
    private int leaveDays;

    /**姓名*/
    private String name;

    public LeaveRequest(int leaveDays,String name){
        this.leaveDays = leaveDays;
        this.name = name;
    }
}
