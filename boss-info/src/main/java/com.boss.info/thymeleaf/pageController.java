package com.boss.info.thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by qxr4383 on 2019/3/15.
 */
@Controller
public class pageController {
    /**
     * 首页
     *
     * @return
     */
    @RequestMapping("/")
    public String page() {
        return "system/index";
    }


    /**
     * 跳转
     *
     * @return
     */
    @RequestMapping("/redirect")
    public String page2() {
        return "redirect/redirect";
    }


    /**
     * 视图
     *
     * @param model
     * @return
     */
    @RequestMapping("/model")
    public String page3(Model model) {
        model.addAttribute("name", "seawater");
        model.addAttribute("boss", "you will be a boss!");
        return "hello";
    }
}
