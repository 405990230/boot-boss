package com.boss.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
@RequestMapping("/c1")
public class MyController {

    @GetMapping("/msg")
    @ResponseBody
    public String msg(){
        return "success";
    }

//    访问主页
    @GetMapping("/")
    public String index(){
        return "index";//视图名称
    }

    //输入日期参数
    @GetMapping("/date")
    @ResponseBody
    public Date date(Date myDate){
        return myDate;
    }
}
