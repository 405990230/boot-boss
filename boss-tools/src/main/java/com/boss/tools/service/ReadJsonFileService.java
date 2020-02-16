package com.boss.tools.service;

import com.boss.tools.vo.ExportBean;

public interface ReadJsonFileService {

    /**
     * 批量处理json文件
     */
    void readJsonList(String sourceFolderPath, String targetFolderPath, String[] headers);

    ExportBean readJson(String sourceJsonPath, String targetCsvPath, String[] headers);
}
