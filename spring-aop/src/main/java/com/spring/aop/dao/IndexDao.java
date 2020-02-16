package com.spring.aop.dao;

import com.spring.aop.anno.Luban;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository("indexDao")
public class IndexDao implements Dao{

    public void query(){
        System.out.println("query");
    }

    @Luban
    public void get(){
        System.out.println("get");
    }
}
