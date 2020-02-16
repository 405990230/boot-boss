package com.boss.common.util;

import com.boss.common.api.JsonResult;

/**
 * Created by qxr4383 on 2019/4/12.
 */
public class ResponseUtils {
    public static JsonResult getResp(String status, String msg) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setStatus(status);
        jsonResult.setMsg(msg);
        return jsonResult;
    }
}
