package com.boss.tools.service;

import com.boss.tools.base.BaseTest;
import com.boss.tools.contants.Contant;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

public class ReportLinkServiceTest extends BaseTest {

    @Autowired
    ReportHandleService reportHandleService;

    @Test
    public void requestNumForWeather() {
        Map<String, String> map = new HashMap<>();
        map.put("query", "customEvents|project timestamp,customDimensions|where customDimensions.PATH contains 'weather/evo'|summarize Count=count() by bin(timestamp,1d)|top 10000 by timestamp asc ");
        map.put("timespan", "2019-08-01/2019-09-01");
        map.put("app", "weather");
        reportHandleService.requestNumByParam(map);
    }
}
