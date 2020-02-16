package com.boss.tools.service;

import com.boss.tools.base.BaseTest;
import com.boss.tools.contants.Contant;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ReadJsonFileServiceTest extends BaseTest {
    @Autowired
    ReadJsonFileService readJsonFileService;

    @Test
    public void readJsonList() {
        String[] headers = {"VIN", "COUNT"};
        readJsonFileService.readJsonList(Contant.SOURCE_JSON_PATH, Contant.TARGET_CSV_PATH, headers);
    }
}
