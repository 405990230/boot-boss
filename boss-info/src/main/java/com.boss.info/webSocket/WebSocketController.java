package com.boss.info.webSocket;

import com.boss.info.webSocket.util.ApiReturnObject;
import com.boss.info.webSocket.util.ApiReturnUtil;
import com.boss.info.webSocket.util.BasePath;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequestMapping("/web")
@Controller
public class WebSocketController {

    //页面请求
    @GetMapping("/socket/{cid}")
    public String socket(@PathVariable String cid, Model model) {
        model.addAttribute("cid", cid);
        return "socket/socket";
    }

    //推送数据接口
    @ResponseBody
    @RequestMapping("/socket/push/{cid}")
    public ApiReturnObject pushToWeb(@PathVariable String cid, String message) {
        try {
            WebSocketServer.sendInfo(message, cid);
        } catch (IOException e) {
            e.printStackTrace();
            return ApiReturnUtil.error(cid + "#" + e.getMessage());
        }
        return ApiReturnUtil.success(cid);
    }

    @ResponseBody
    @GetMapping("/index")
    @ApiOperation(value = "index", notes = "返回json数据")
    @ApiImplicitParam(name = "data", value = "object内容", required = true, dataType = "String")
    public ApiReturnObject index(String data) {
        if (StringUtils.isEmpty(data)) {
            data = "hello spring-cloud-study";
        }
        return ApiReturnUtil.success(data);
    }

    //    @ApiOperation(value="socket", notes="访问socket页面")
//    @GetMapping("/socket/{cid}")
//    public ModelAndView  sockethtml(@PathVariable String cid){
//        ModelAndView mav=new ModelAndView("socket/socket");
//        mav.addObject("cid", cid);
//        return mav;
//    }
    @ApiOperation(value = "basePath", notes = "获取basepath")
    @GetMapping("/basepath")
    @ResponseBody
    public ApiReturnObject basePath(HttpServletRequest request) {
        return ApiReturnUtil.success(BasePath.getBasePath(request));
    }

    @GetMapping("/getData/{uid}")
    @ResponseBody
    public ApiReturnObject getData(@PathVariable String uid, String data) {
        System.out.println("#spring-cloud-study-demo#");
        System.out.println("uid->" + uid + ",data->" + data);
        Map<String, String> map = new HashMap<>();
        map.put(uid, data);
        map.put("feign", "远程调用微服务");
        map.put("demo", "本地微服务");
        return ApiReturnUtil.success(map);
    }
}
