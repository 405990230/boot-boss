package com.boss.info.bmw.service;

import com.boss.info.bmw.pojo.api.NewsListAPIPojo;
import com.boss.info.bmw.pojo.api.WeatherApiPojo;

import java.io.IOException;

/**
 * Created by qxr4383 on 2018/12/25.
 */
public interface LinxService {

    WeatherApiPojo getWeather(String lat, String lon, String i18n);

    /**
     * URL that generates third party requests
     *
     * @param channelId
     * @return
     * @throws IOException
     */
    NewsListAPIPojo getNewsList(String channelId) throws IOException;
}
