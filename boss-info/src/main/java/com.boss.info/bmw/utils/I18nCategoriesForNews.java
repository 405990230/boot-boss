package com.boss.info.bmw.utils;

import com.boss.info.bmw.constant.Constant;
import com.boss.info.bmw.pojo.json.I18nCategoriesItemPojo;
import com.boss.info.bmw.pojo.json.I18nCategoriesPojo;
import com.boss.info.bmw.pojo.json.ResponseCategoriesDataPojo;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j
@Component
public class I18nCategoriesForNews {


    private static Map<String, Map<String, String>> i18nCategoriesMap;
    private static Map<String, List<String>> nameListMap;

    @Autowired
    private ObjectMapper objectMapper;

    @PostConstruct
    public void initI18n() {
        InputStream inputStream = null;
        try {
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
            i18nCategoriesMap = new HashMap<>();
            nameListMap = new HashMap<>();
            /** 1. 读取中文主题 */
            inputStream = ClassUtils.class.getClassLoader().getResourceAsStream("json/news/zh.json");

            String evoZHCategories = IOUtils.toString(inputStream, "utf-8");
            I18nCategoriesPojo i18nCategoriesPojo = objectMapper.readValue(evoZHCategories, I18nCategoriesPojo.class);
            Map<String, String> oidMap = new HashMap<>();
            List<String> nameList = new ArrayList<>();
            for (I18nCategoriesItemPojo itemPojo : i18nCategoriesPojo.getItems()) {
                oidMap.put(itemPojo.getName(), itemPojo.getOid());
                nameList.add(itemPojo.getName());
            }
            i18nCategoriesMap.put(Constant.LANGUAGE_ZH_L, oidMap);
            nameListMap.put(Constant.LANGUAGE_ZH_L, nameList);
            /** 2. 读取英文主题 */
            inputStream = null;
            inputStream = ClassUtils.class.getClassLoader().getResourceAsStream("json/news/en.json");

            String evoENategories = IOUtils.toString(inputStream, "utf-8");
            I18nCategoriesPojo i18nCategoriesPojo2 = objectMapper.readValue(evoENategories, I18nCategoriesPojo.class);
            Map<String, String> oidMap2 = new HashMap<>();
            List<String> nameList2 = new ArrayList<>();
            for (I18nCategoriesItemPojo itemPojo : i18nCategoriesPojo2.getItems()) {
                oidMap2.put(itemPojo.getName(), itemPojo.getOid());
                nameList2.add(itemPojo.getName());
            }
            nameListMap.put(Constant.LANGUAGE_EN_L, nameList2);
            i18nCategoriesMap.put(Constant.LANGUAGE_EN_L, oidMap2);
        } catch (Exception e) {
            log.error("Parse Json failed!", e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    log.error("initI18nForEVO init finally error {} ", e);
                }
            }
        }
    }

    public List<ResponseCategoriesDataPojo> getChannelList() {
        List<ResponseCategoriesDataPojo> rtn = new ArrayList<ResponseCategoriesDataPojo>();
        for (String language : nameListMap.keySet()) {
            ResponseCategoriesDataPojo responseCategoriesDataPojo = new ResponseCategoriesDataPojo();
            responseCategoriesDataPojo.setLanguage(language);
            responseCategoriesDataPojo.setNames(nameListMap.get(language));
            rtn.add(responseCategoriesDataPojo);
        }
        return rtn;
    }

    public String getChannelId(String language, String name) {
        return i18nCategoriesMap.get(language).get(name);
    }

}
