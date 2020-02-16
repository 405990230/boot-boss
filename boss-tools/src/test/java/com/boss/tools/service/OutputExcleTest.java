package com.boss.tools.service;

import com.boss.tools.base.BaseTest;
import com.boss.tools.contants.Contant;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class OutputExcleTest extends BaseTest {
    @Autowired
    OutputExcle outputExcle;

    @Test
    public void outputExcleForMonthRequest() throws Exception {
        String importFilePath = "/Users/qxr4383/Documents/work/logger/prod/omcReport/demo/data_request_omc.xlsx";
        String exportFilePath = "/Users/qxr4383/Documents/work/logger/prod/omcReport/";
        String month = "2019-07";
        outputExcle.outputExcleForMonthRequest(importFilePath, exportFilePath, month, Contant.REPORT_MONTH_RQUEST);
        outputExcle.outputExcleForMonthRequest(importFilePath, exportFilePath, month, Contant.REPORT_MONTH_USER);
    }
}
