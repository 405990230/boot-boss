package com.boss.tools.service;

import com.boss.tools.vo.ExportBean;

import java.util.List;
import java.util.Map;

public interface ReportHandleService {
    List<ExportBean> monthlyRequest(String timespan, String reportType);

    ExportBean requestNumByParam(Map<String, String> map);


}
