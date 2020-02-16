package com.boss.tools.service.impl;


import com.boss.tools.config.InsigthsProperties;
import com.boss.tools.contants.Contant;
import com.boss.tools.service.ReportHandleService;
import com.boss.tools.service.ReportLinkService;
import com.boss.tools.vo.ExportBean;
import com.boss.tools.vo.ReadJsonBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service("reportHandleService")
@Slf4j
public class ReportHandleServiceImpl implements ReportHandleService {
    @Autowired
    ReportLinkService reportLinkService;
    @Autowired
    InsigthsProperties insigthsProperties;

    public List<ExportBean> monthlyRequest(String timespan, String reportType) {
        List<ExportBean> list = new ArrayList<>();
        ExportBean weather_evo = requestNumByParam(ProcessingMap("weather/evo", timespan, reportType));
        ExportBean weather_mgu = requestNumByParam(ProcessingMap("weather/mgu", timespan, reportType));

        ExportBean news_evo = requestNumByParam(ProcessingMap("news/evo/bmw/infos", timespan, reportType));
        ExportBean news_mgu = requestNumByParam(ProcessingMap("news/mgu/bmw/infos", timespan, reportType));

        ExportBean dzh_evo = requestNumByParam(ProcessingMap("dzhstock/evo", timespan, reportType));
        ExportBean dzh_mgu = requestNumByParam(ProcessingMap("dzhstock/mgu", timespan, reportType));

        ExportBean dp_evo = requestNumByParam(ProcessingMap("dianping/evo", timespan, reportType));

        list.add(weather_evo);
        list.add(weather_mgu);
        list.add(news_evo);
        list.add(news_mgu);
        list.add(dzh_evo);
        list.add(dzh_mgu);
        list.add(dp_evo);
        return list;

    }

    public ExportBean requestNumByParam(Map<String, String> map) {
        String applicationId = insigthsProperties.getInsigthsMap().get(map.get("app") + "_id");
        String xApiKey = insigthsProperties.getInsigthsMap().get(map.get("app") + "_key");
        String requestUrl = Contant.APPLICATION_INSIGHTS_PATH + applicationId + "/query";
        ReadJsonBean readJsonBean = reportLinkService.getLogFromApplicationInsights(requestUrl, xApiKey, map);
        ExportBean exportBean = new ExportBean();
        exportBean.setLists(readJsonBean.getTables().get(0).getRows());
        System.out.println(map.get("app") + "月请求天数为：" + exportBean.getLists().size());
        //WriteCsv.writeCSV(path,listVin_list,headers);
        return exportBean;
    }


    public HashMap<String, String> ProcessingMap(String urlPath, String timespan, String reportType) {
        HashMap<String, String> map = new HashMap<>();
        map.put("query", ProcessingQuery(urlPath, reportType));
        map.put("timespan", ProcessingTimespan(timespan));
        if (urlPath.contains("weather")) {
            map.put("app", "weather");
        } else if (urlPath.contains("news")) {
            map.put("app", "news");
        } else if (urlPath.contains("dzhstock")) {
            map.put("app", "dzhstock");
        } else if (urlPath.contains("dianping")) {
            map.put("app", "dianping");
        }
        return map;
    }

    public String ProcessingQuery(String urlPath, String reportType) {
        String query = "";
        if (Contant.REPORT_MONTH_RQUEST.equals(reportType)) {
            query = "customEvents|project timestamp,customDimensions|where customDimensions.PATH contains '" + urlPath +
                    "'|summarize Count=count() by bin(timestamp,1d)|top 10000 by timestamp asc ";
        } else if (Contant.REPORT_MONTH_USER.equals(reportType)) {
            query = "customEvents |project timestamp ,customDimensions |where customDimensions.PATH contains'" + urlPath +
                    "'|summarize Count=count() by bin(timestamp,1d),tostring(customDimensions.VIN)| " +
                    "summarize Count=count() by bin(timestamp,1d)| top 10000 by timestamp asc ";
        }
        return query;
    }

    public String ProcessingTimespan(String timespan) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        try {
            Date date = sdf.parse(timespan);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.MONTH, 1);
            timespan = timespan + "/" + sdf.format(calendar.getTime());
        } catch (Exception e) {

        }
        log.info("-----timespan:" + timespan);
        return timespan;
    }

}
