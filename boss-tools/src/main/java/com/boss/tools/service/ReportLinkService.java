package com.boss.tools.service;

import com.boss.tools.vo.ReadJsonBean;

import java.util.Map;

public interface ReportLinkService {
    ReadJsonBean getLogFromApplicationInsights(String requestUrl, String xApiKey, Map<String, String> map);
}
