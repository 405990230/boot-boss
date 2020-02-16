package com.boss.cache.service;

import com.boss.common.news.vo.api.TencentNewsData;
import org.springframework.http.ResponseEntity;

public interface TencentNewsApiService {

    /**
     * 2. 获取新闻内容列表
     *
     * @param channelId
     */
    TencentNewsData getNewsContentList(String channelId);


}
