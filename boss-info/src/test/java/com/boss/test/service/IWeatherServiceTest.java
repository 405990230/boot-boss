package com.boss.test.service;

import com.boss.info.bmw.pojo.json.ResponseDataItemPojo;
import com.boss.info.bmw.service.IWeatherService;
import com.boss.test.base.BaseTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by qxr4383 on 2018/5/9.
 */
@Slf4j
public class IWeatherServiceTest extends BaseTest {
    @Autowired
    private IWeatherService weatherService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private RestTemplate restTemplate;

//    @Value("#{configProperties['weather.api.gateway']}")
//    private String apiGateway;
//
//    @Value("#{configProperties['weather.api.user']}")
//    private String apiUser;
//
//    @Value("#{configProperties['weather.api.key']}")
//    private String apiKey;

    @Test
    public void testGetWeatherData() {
        try {
            List<ResponseDataItemPojo> list = weatherService.getCurrentWeather("41.893001", "123.463199", "zh", "cache");
            log.info(objectMapper.writeValueAsString(list));
        } catch (Exception e) {
            log.info("{}", e);
        }
    }


}
