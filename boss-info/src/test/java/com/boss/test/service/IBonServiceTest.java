package com.boss.test.service;

import com.boss.test.base.BaseTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * Created by qxr4383 on 2018/5/9.
 */
@Slf4j
public class IBonServiceTest extends BaseTest {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    ObjectMapper objectMapper;


    //@Test
    public void testGetCategoriesData() {
        try {
            long time1 = System.currentTimeMillis();
            for (int i = 0; i < 1500; i++) {
                try {

                    String requestUrl = "https://omcbonweacedev.chinacloudsites.cn/weather/hello";
                    ResponseEntity<String> responseEntity = restTemplate.getForEntity(requestUrl, String.class);
                    log.info("weather :" + objectMapper.writeValueAsString(responseEntity.getStatusCodeValue() + " : " + responseEntity.getBody()));
                } catch (Exception e) {
                    log.info(e.getMessage());
                }

            }
            long time2 = System.currentTimeMillis();
            for (int i = 0; i < 1500; i++) {
                try {
                    String requestUrl = "https://omcbonnewcedev.chinacloudsites.cn/news/hello";
                    ResponseEntity<String> responseEntity = restTemplate.getForEntity(requestUrl, String.class);
                    log.info("news :" + objectMapper.writeValueAsString(responseEntity.getStatusCodeValue() + " : " + responseEntity.getBody()));
                } catch (Exception e) {
                    log.info(e.getMessage());
                }
            }
            long time3 = System.currentTimeMillis();
            for (int i = 0; i < 1500; i++) {
                try {
                    String requestUrl = "https://omcbondskcedev.chinacloudsites.cn/dzhstock/hello";
                    ResponseEntity<String> responseEntity = restTemplate.getForEntity(requestUrl, String.class);
                    log.info("dzhstock :" + objectMapper.writeValueAsString(responseEntity.getStatusCodeValue() + " : " + responseEntity.getBody()));
                } catch (Exception e) {
                    log.info(e.getMessage());
                }
            }
            long time4 = System.currentTimeMillis();
            log.info("weather : " + ((time2 - time1) / 1000) + "; news : " + ((time3 - time2) / 1000) + " ; dzhstock : " + ((time4 - time3) / 1000));
        } catch (Exception e) {
            log.info("{}", e);
        }
    }
}
