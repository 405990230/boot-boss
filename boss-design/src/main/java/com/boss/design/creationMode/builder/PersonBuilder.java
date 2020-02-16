package com.boss.design.creationMode.builder;

/**
 * Created by yd on 2019/3/26.
 * 抽象接口
 */
public interface PersonBuilder {
    void buildShoes();

    void buildTrousers();

    void buildJacket();

    //组装
    PersonDress buildPersonDress();
}
