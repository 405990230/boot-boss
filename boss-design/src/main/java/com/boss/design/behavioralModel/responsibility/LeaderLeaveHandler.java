package com.boss.design.behavioralModel.responsibility;

/**
 * @description: 直接主管处理类
 */
public class LeaderLeaveHandler extends AbstractLeaveHandler{
    public LeaderLeaveHandler(String name) {
        this.handlerName = name;
    }

    @Override
    public void handlerRequest(LeaveRequest request) {
        if(request.getLeaveDays() <= this.MIN){
            System.out.println("直接主管:" + handlerName + ",已经处理;流程结束。");
            return;
        }

        if(null != this.nextHandler){
            this.nextHandler.handlerRequest(request);
        }else{
            System.out.println("审批拒绝！");
        }

    }
}
