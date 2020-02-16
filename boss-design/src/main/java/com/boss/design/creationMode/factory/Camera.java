package com.boss.design.creationMode.factory;

/**
 * Created by yd on 2019/3/25.
 * 手机摄像头
 */
public interface Camera {
    void getCamera();
}

class HighCamera implements Camera {
    @Override
    public void getCamera() {
        System.out.println("生产高档手机摄像头");
    }
}

class LowCamera implements Camera {
    @Override
    public void getCamera() {
        System.out.println("生产抵挡手机摄像头");
    }
}
