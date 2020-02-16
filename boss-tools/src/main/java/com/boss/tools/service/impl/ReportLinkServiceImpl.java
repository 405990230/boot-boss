package com.boss.tools.service.impl;

import com.alibaba.fastjson.JSON;
import com.boss.tools.service.ReportLinkService;
import com.boss.tools.vo.ReadJsonBean;
import com.boss.tools.vo.RequestBean;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Slf4j
@Service("reportLinkService")
public class ReportLinkServiceImpl implements ReportLinkService {
    @Autowired
    private RestTemplate restTemplate;

    public ReadJsonBean getLogFromApplicationInsights(String requestUrl, String xApiKey, Map<String, String> map) {
        ReadJsonBean readJsonBean = new ReadJsonBean();
        try {
            log.info("GET : I'm going to send request : " + requestUrl);
            //headers
            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.setContentType(MediaType.APPLICATION_JSON);
            requestHeaders.add("x-api-key", xApiKey);
            //body
            HttpEntity<Map<String, String>> requestEntity = new HttpEntity<>(map, requestHeaders);
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(requestUrl, requestEntity, String.class);
            if (responseEntity != null && responseEntity.getStatusCodeValue() == 200) {
                readJsonBean = JSON.parseObject(responseEntity.getBody(), ReadJsonBean.class);
            } else {
                log.info("没有数据！");
            }
        } catch (Exception e) {
            log.error("{}", e);
        }
        return readJsonBean;
    }
}
