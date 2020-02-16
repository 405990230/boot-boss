package com.boss.server.controller;

import com.boss.common.api.JsonResult;
import com.boss.common.status.StatusEnum;
import com.boss.common.util.ResponseUtils;
import com.boss.server.service.AppEntityService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

/**
 * /id5-server/vehicle/id5/rest/apps-cn/list/v1
 * /id5-server/vehicle/id5/admin/upload/app-cn
 * /id5-server/vehicle/id5/rest/apps-cn/app-cn/v1?app=carcloudjs&ver=0.0.1
 * /vehicle/id5/rest/apps-cn/delete/v1/*
 */
@RestController
@Slf4j
public class AppsController {

    @Autowired
    private AppEntityService appEntityService;

    @GetMapping(value = "/getlist")
    public JsonResult getAppsList() {
        JsonResult jsonResult = new JsonResult();
        try {
            jsonResult.setMsg(StatusEnum.SUCCESS.getValue());
            jsonResult.setData(appEntityService.getAppList());
            return jsonResult;
        } catch (Exception e) {
            log.error("AppsController getApps error{}", e);
            jsonResult.setMsg(StatusEnum.FAIL.getValue());
            return jsonResult;
        }
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String uploadApp(HttpServletRequest request) {
        try {
            if (!ServletFileUpload.isMultipartContent(request)) {
                log.error("The POST request is not Multipart/form-data");
                return StatusEnum.FAIL.getValue();
            }
            MultipartHttpServletRequest multipartHttpServletRequest = ((MultipartHttpServletRequest) request);
            Map<String, MultipartFile> getFileMap = multipartHttpServletRequest.getFileMap();
            String appId = appEntityService.processFiles(getFileMap);
            log.info("App [" + appId + "] deploy Success!");
            return StatusEnum.SUCCESS.getValue();
        } catch (Exception e) {
            log.error("AppsController upload error{}", e);
            return StatusEnum.FAIL.getValue();
        }
    }

    @GetMapping(value = "/download")
    public void downloadApp(HttpServletResponse response,
                            @RequestParam(value = "app", required = false) String appId,
                            @RequestParam(value = "ver", required = false) String version) throws IOException {
        response.setContentType("application/x-tar");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + appId + "-" + version + ".tar" + "\"");
        InputStream fileInputStream = null;
        OutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            fileInputStream = appEntityService.getFileStream(appId, version);
            if (fileInputStream != null) {
                byte[] bytes = new byte[2048];
                int len;
                while ((len = fileInputStream.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, len);
                }
            } else {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                String remark = "app " + appId + " download fail";
                outputStream.write(remark.getBytes());
            }
        } catch (Exception e) {
            log.error("AppsController download error{} ", e);
        } finally {
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public JsonResult deleteApp(@RequestParam(value = "app", required = false) String appId) {
        try {
            if (StringUtils.isEmpty(appId)) {
                return ResponseUtils.getResp(StatusEnum.FAIL.getValue(), "app is empty");
            }
            appEntityService.deleteAppEntity(appId);
            return ResponseUtils.getResp(StatusEnum.SUCCESS.getValue(), "app delete success");
        } catch (Exception e) {
            log.error("AppsController delete error{}", e);
            return ResponseUtils.getResp(StatusEnum.SUCCESS.getValue(), "app delete error");
        }
    }

}
