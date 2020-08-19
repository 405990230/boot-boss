package com.boss.design.behavioralModel.responsibility;

public class ResponsibilityTest {
    public static void main(String[] args) {
        LeaveRequest request = new LeaveRequest(20,"李三");

        AbstractLeaveHandler leaderLeaveHandler = new LeaderLeaveHandler("县令");
        ManagerLeaveHandler managerLeaveHandler = new ManagerLeaveHandler("知府");
        CEOLeaveHandler ceoLeaveHandler = new CEOLeaveHandler("京兆尹");

        leaderLeaveHandler.setNextHandler(managerLeaveHandler);
        managerLeaveHandler.setNextHandler(ceoLeaveHandler);

        leaderLeaveHandler.handlerRequest(request);


    }
}
