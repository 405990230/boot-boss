package com.boss.test.service;

import com.boss.info.bmw.pojo.json.ResponseCategoriesDataPojo;
import com.boss.info.bmw.pojo.json.ResponseNewsListDataPojo;
import com.boss.info.bmw.service.INewsService;
import com.boss.test.base.BaseTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myself.platform.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by qxr4383 on 2018/5/9.
 */
@Slf4j
public class INewsServiceTest extends BaseTest {
    @Autowired
    private INewsService newsService;

    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    private RedisService redisService;

    @Test
    public void testGetCategoriesData() {
        try {
            List<ResponseCategoriesDataPojo> list = newsService.getNewsCategories();
            log.info(objectMapper.writeValueAsString(list));
        } catch (Exception e) {
            log.info("{}", e);
        }
    }

    @Test
    public void testGetNewsListByOid() {
        try {
            List<ResponseNewsListDataPojo> list = newsService.getNewsListByOid("en", "sport");
            log.info(objectMapper.writeValueAsString(list));
        } catch (Exception e) {
            log.info("{}", e);
        }
    }

    @Test
    public void testRedisService() {
        try {
            redisService.set("dwaefaef", 1213, 900l);
        } catch (Exception e) {
            log.info("{}", e);
        }
    }
}
