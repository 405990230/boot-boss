package com.boss.info.bmw.service.impl;

import com.boss.info.bmw.service.ReportLinkService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Slf4j
@Service("reportLinkService")
public class ReportLinkServiceImpl implements ReportLinkService {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private RestTemplate restTemplate;

    public String getApplicationinsights(String requestUrl) {
        try {
            log.info("GET : I'm going to send request : " + requestUrl);
            //headers
            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.setContentType(MediaType.APPLICATION_JSON);
            requestHeaders.add("x-api-key", "zumaz6gkafz6zdlj4s5fz15gxkifwd0csw4lse0p");
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(requestUrl, String.class);
            if (responseEntity != null && responseEntity.getStatusCodeValue() == 200) {
                objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            } else {
                log.info("没有数据！");
            }
        } catch (Exception e) {
            log.error("{}", e);
        }
        return null;
    }
}
