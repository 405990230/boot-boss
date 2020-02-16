package com.boss.tools.base;

import com.boss.tools.ToolsServiceStarter;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ToolsServiceStarter.class)
@WebAppConfiguration
public class BaseTest {

    @Ignore
    @Test
    public void baseTest() {
    }

}