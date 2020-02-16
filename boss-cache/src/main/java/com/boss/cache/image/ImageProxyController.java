package com.boss.cache.image;

import com.boss.cache.image.common.Imageinitialization;
import com.boss.cache.image.service.ImageFileService;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/image")
@Log4j
public class ImageProxyController {
    @Autowired
    private Imageinitialization imageinitialization;

    @Autowired
    private ImageFileService imageFileService;

    @RequestMapping(value = {"/info", "/bonappnews/info"})
    public void getImage(HttpServletRequest request, HttpServletResponse response,
                         @RequestParam(value = "url", required = false) String encodedImageUrl,
                         @RequestParam(value = "maxWidth", required = false) String maxWidth,
                         @RequestParam(value = "maxHeight", required = false) String maxHeight) throws Exception {
        boolean status = false;
        Long time1 = null, time2 = null, time3 = null, time4;
        try {
            time1 = System.currentTimeMillis();
            response.setContentType("image/png");
            response.setHeader("Access-Control-Allow-Origin", "*");
            /** 1.请求URL为空直接返回404 */
            if (StringUtils.isBlank(encodedImageUrl)) {
                status = true;
            } else {
                /** 2.url decode */
                encodedImageUrl = URLDecoder.decode(encodedImageUrl, "UTF-8");
                String imageUrl = "";
                String fileName = "";
                if (request.getRequestURI().contains("/bonappnews")) {
                    /** 3.news image url  */
                    imageUrl = encodedImageUrl.substring(0, encodedImageUrl.lastIndexOf("/") + 1);
                    fileName = encodedImageUrl.substring(imageUrl.lastIndexOf("/") + 1);
                }
                time2 = System.currentTimeMillis();
                byte[] imageData = imageFileService.getImagesData(imageUrl, fileName, maxWidth, maxHeight, encodedImageUrl);
                time3 = System.currentTimeMillis();
                if (imageData == null) {
                    status = true;
                } else {
                    response.getOutputStream().write(imageData);
                }
            }
        } catch (Exception e) {
            status = true;
            log.error("getImage error encodedImageUrl:" + encodedImageUrl, e);
        } finally {
            if (status) {
                response.getOutputStream().write(imageinitialization.getPng404());
            }
        }
        time4 = System.currentTimeMillis();
        Map<String, String> map = new HashMap<>();
        map.put("PATH", request.getRequestURI());
        map.put("PARAM", maxWidth + "&" + maxHeight);
        map.put("HTTPCODE", String.valueOf(status));
        map.put("RESULTCODE", Integer.toString(response.getStatus()));
        map.put("URL", encodedImageUrl);
        map.put("SPENTTIME", Long.toString(time3 - time2) + "--" + Long.toString(time4 - time1));
        log.info(map);
        response.flushBuffer();
    }

    /**
     * Providing heartbeat interface service
     *
     * @param
     * @return
     * @modify time : 2018-05-10 09:50 AM
     */
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String getHeartbeatMessage(HttpServletResponse response) {
        return "image Server Runs Normally";
    }

    //    @RequestMapping(value = {"/info","/bonappnews/info"})
//    public void getImage( HttpServletRequest request, HttpServletResponse response,
//                         @RequestParam(value="url", required=false) String encodedImageUrl,
//                         @RequestParam(value="maxWidth", required=false) String maxWidth,
//                         @RequestParam(value="maxHeight", required=false) String maxHeight) throws Exception{
//        long spentTime1=0,spentTime2=0,time1,time2,time3,httpCode = 211;
//        try{
//            if(encodedImageUrl != null) {
//                String imageUrl = URLDecoder.decode(encodedImageUrl, "UTF-8");
//                if(request.getRequestURI().contains("/bonappnews")){
//                    imageUrl = imageUrl.substring(0,imageUrl.lastIndexOf("/"));
//                }
//                time1 = System.currentTimeMillis();
//                HttpResponse imageDataResponse = imageURLService.getImage(imageUrl);
//                time2 = System.currentTimeMillis();
//                spentTime1 = time2 - time1;
//                httpCode = imageDataResponse.getStatusLine().getStatusCode();
//                if(imageDataResponse.getStatusLine().getStatusCode() == 200){
//                    response.setContentType(imageDataResponse.getFirstHeader("Content-Type").getValue());
//                    response.setHeader("Access-Control-Allow-Origin", "*");
//                    if(maxWidth != null && maxHeight != null){
//                        ImageUtil.compressImage(imageDataResponse.getEntity().getContent(), response.getOutputStream(), Integer.parseInt(maxWidth), Integer.parseInt(maxHeight));
//                    }else{
//                        imageDataResponse.getEntity().writeTo(response.getOutputStream());
//                    }
//                } else{
//                    logger.warn( "Fetching image:" + imageUrl + " failed with status: " + imageDataResponse.getStatusLine().getStatusCode());
//                    response.setStatus(imageDataResponse.getStatusLine().getStatusCode());
//                }
//                time3 = System.currentTimeMillis();
//                spentTime2 = time3 - time1;
//            }
//        } catch (Exception e){
//            response.setStatus(615);
//            logger.error("3rd party server error",e );
//        } finally {
//            if(response.getStatus() != 200){
//                response.setContentType("image/png");
//                response.setHeader("Access-Control-Allow-Origin", "*");
//                response.getOutputStream().write(imageinitialization.getPng404());
//            }
//            Map<String,String> map = new HashMap<>();
//            map.put("PATH",request.getRequestURI());
//            map.put("PARAM",maxWidth+"&"+maxHeight);
//            map.put("HTTPCODE",Long.toString(httpCode));
//            map.put("RESULTCODE",Integer.toString(response.getStatus()));
//            map.put("URL",encodedImageUrl);
//            map.put("SPENTTIME",Long.toString(spentTime1)+"--"+Long.toString(spentTime2));
//            logger.trackEvent("image",map);
//        }
//        response.flushBuffer();
//    }

}
