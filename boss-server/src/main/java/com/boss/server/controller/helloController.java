package com.boss.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * Created by yd on 2018/12/24.
 */
@RestController
@RequestMapping("test")
public class helloController {

    @RequestMapping(value = "/hello/{param}", method = RequestMethod.GET)
    public String get(@PathVariable("param") String param) {
        return param;
    }

}
