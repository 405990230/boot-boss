package com.boss.server.service;

import com.boss.common.id.SnowflakeIdWorker;
import com.boss.server.base.BaseTest;
import com.boss.server.entity.WebsitesEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by qxr4383 on 2018/5/9.
 */
@Slf4j
public class IWebsitesServiceTest extends BaseTest {
    @Autowired
    IWebsitesService websitesService;

    @Test
    public void testInsert() throws SQLException {
        WebsitesEntity web = new WebsitesEntity();
        web.setId(1);
        web.setUrl("https://www.taobao.com");
        web.setAlexa(2);
        web.setCountry("中国");
        web.setName("淘宝");
        log.info("成功插入数据" + ":" + websitesService.insert(web) + "条");
        web.setId(2);
        web.setUrl("https://www.Google.com");
        web.setAlexa(5);
        web.setCountry("美国");
        web.setName("谷歌");
        log.info("成功插入数据" + ":" + websitesService.insert(web) + "条");

    }

    @Test
    public void testQueryById() throws SQLException {
        WebsitesEntity web = websitesService.selectByPrimaryKey(1);
        log.info(web.getName() + ":" + web.getUrl());

    }

    @Test
    public void tesQueryList() throws SQLException {
        List<WebsitesEntity> list = websitesService.queryList();
        for (WebsitesEntity web : list) {
            log.info(web.getName() + ":" + web.getUrl());
        }
    }
}