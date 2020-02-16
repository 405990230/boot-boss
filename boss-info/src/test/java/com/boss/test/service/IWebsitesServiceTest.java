package com.boss.test.service;

import com.boss.info.entity.WebsitesEntity;
import com.boss.info.mybatis.service.IWebsitesService;
import com.boss.test.base.BaseTest;
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