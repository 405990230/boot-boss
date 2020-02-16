package com.boss.info.bmw.controller;

import com.boss.info.entity.WebsitesEntity;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public void save(@RequestBody WebsitesEntity websitesEntity) {
    }

}
