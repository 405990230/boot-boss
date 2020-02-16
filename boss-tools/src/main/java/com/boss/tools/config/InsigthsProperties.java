package com.boss.tools.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;

@Component
@ConfigurationProperties
@Data
public class InsigthsProperties {
    private String application_id_weather;
    private String x_api_key_weather;
    private String application_id_news;
    private String x_api_key_news;
    private String application_id_dzhstock;
    private String x_api_key_dzhstock;
    private String application_id_dianping;
    private String x_api_key_dianping;
    private HashMap<String, String> insigthsMap;

    @PostConstruct
    public void init() {
        insigthsMap = new HashMap<>();
        insigthsMap.put("weather" + "_id", application_id_weather);
        insigthsMap.put("weather" + "_key", x_api_key_weather);

        insigthsMap.put("news" + "_id", application_id_news);
        insigthsMap.put("news" + "_key", x_api_key_news);

        insigthsMap.put("dzhstock" + "_id", application_id_dzhstock);
        insigthsMap.put("dzhstock" + "_key", x_api_key_dzhstock);

        insigthsMap.put("dianping" + "_id", application_id_dianping);
        insigthsMap.put("dianping" + "_key", x_api_key_dianping);
    }
}
