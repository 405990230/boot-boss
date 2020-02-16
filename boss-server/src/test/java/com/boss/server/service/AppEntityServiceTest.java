package com.boss.server.service;

import com.boss.common.json.JSONMapper;
import com.boss.server.base.BaseTest;
import com.boss.server.entity.AppEntity;
import com.boss.server.vo.AppElement;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
public class AppEntityServiceTest extends BaseTest {

    @Autowired
    private AppEntityService appEntityService;

    @Test
    public void testGetAllApps() {
        List<AppEntity> list = appEntityService.findApps(null, 1, 10);
        if (list == null || list.isEmpty())
            return;
        log.info("====================>" + JSONMapper.writeObjectAsString(list));
    }

    @Test
    public void testGetAppLists() {
        List<AppElement> list = appEntityService.getAppList();
        if (list == null || list.isEmpty())
            return;
        log.info("====================>" + JSONMapper.writeObjectAsString(list));
    }


}
