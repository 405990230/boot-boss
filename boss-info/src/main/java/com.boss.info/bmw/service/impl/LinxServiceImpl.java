package com.boss.info.bmw.service.impl;

import com.boss.info.bmw.pojo.api.NewsListAPIPojo;
import com.boss.info.bmw.pojo.api.WeatherApiPojo;
import com.boss.info.bmw.service.LinxService;
import com.boss.info.bmw.utils.CMSApiHelper;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * Created by qxr4383 on 2018/12/25.
 */
@Service("linxService")
public class LinxServiceImpl implements LinxService {
    private static final Logger logger = LoggerFactory.getLogger(LinxServiceImpl.class);

    @Value("${weather.api.gateway}")
    private String weatherApiGateway;

    @Value("${weather.api.user}")
    private String apiUser;

    @Value("${weather.api.key}")
    private String apiKey;

    @Value("${news.api.gateway}")
    private String newsApiGateway;

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public WeatherApiPojo getWeather(String lat, String lon, String i18n) {
        WeatherApiPojo weatherApiPojo = new WeatherApiPojo();
        try {
            String queryString = "cata=json&lat=" + lat + "&lng=" + lon + "&language=" + i18n + "&userid=" + apiUser;
            String key = CMSApiHelper.generateHASHKey(queryString, apiKey);
            String requestUrl = weatherApiGateway + "forecastByLocation?" + queryString + "&key=" + key;
            logger.info("I'm going to send request: " + requestUrl);

            ResponseEntity<String> responseEntity = restTemplate.getForEntity(requestUrl, String.class);

            if (responseEntity != null && responseEntity.getStatusCodeValue() == 200) {
                objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                weatherApiPojo = objectMapper.readValue(responseEntity.getBody(), WeatherApiPojo.class);
            } else {
                weatherApiPojo.setCode("-1");
            }
            return weatherApiPojo;
        } catch (Exception e) {
            logger.error("获取第三方天气API接口getWeather error {} ", e);
            weatherApiPojo.setCode("-1");
        }
        return weatherApiPojo;
    }


    @Override
    public NewsListAPIPojo getNewsList(String channelId) throws IOException {
        NewsListAPIPojo rtnPojo = new NewsListAPIPojo();
        try {
            String requestUrl = newsApiGateway + "channelId=" + channelId + "&p=1&pageSize=20&refer=100000448";
            logger.info("I'm going to send request: " + requestUrl);
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(requestUrl, String.class);
            if (responseEntity != null && responseEntity.getStatusCodeValue() == 200) {
                objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                rtnPojo = objectMapper.readValue(responseEntity.getBody(), NewsListAPIPojo.class);
            } else {
                rtnPojo.setStatus("-1");
            }
        } catch (Exception e) {
            logger.error("Error in get key", e);
        }
        return rtnPojo;
    }

}
