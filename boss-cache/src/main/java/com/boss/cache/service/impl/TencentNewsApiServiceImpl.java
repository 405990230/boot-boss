package com.boss.cache.service.impl;

import com.boss.cache.service.TencentNewsApiService;
import com.boss.common.json.JSONMapper;
import com.boss.common.news.vo.api.TencentNewsData;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service("tencentNewsApiService")
@Log4j
public class TencentNewsApiServiceImpl implements TencentNewsApiService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${news.api.gateway}")
    private String newsApiGateway;

    private static final String refer = "100000448";

    /**
     * 获取新闻内容列表
     *
     * @param channelId
     */
    @Override
    public TencentNewsData getNewsContentList(String channelId) {
        String url = "";
        try {
            url = newsApiGateway + "channelId=" + channelId + "&p=1&pageSize=20&refer=" + refer;
            ResponseEntity responseEntity = restTemplate.getForEntity(url, String.class);
            if (responseEntity != null) {
                if (responseEntity.getStatusCodeValue() == 200) {
                    TencentNewsData tencentNewsData = (TencentNewsData) JSONMapper.fromJson(responseEntity.getBody().toString(), TencentNewsData.class);
                    if (tencentNewsData != null && tencentNewsData.getStatus() == 0) {
                        return tencentNewsData;
                    }
                }
            }
        } catch (RestClientException e) {
            log.error("getNewsContentList error channelId:" + channelId + ",url:" + url, e);
        }
        return null;
    }

}
