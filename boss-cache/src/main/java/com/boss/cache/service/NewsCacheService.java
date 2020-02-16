package com.boss.cache.service;

public interface NewsCacheService {

    /**
     * 新闻内容&图片缓存
     */
    void newsCache();

    /**
     * 新闻图片下载&图片存储
     *
     * @param tenImageUrl
     * @param fileName
     */
    byte[] imageDownloadStorage(String tenImageUrl, String fileName, String downLoadPath, String maxWidth, String maxHeight);

    /**
     * 定时删除前一天的图片目录
     */
    void imgDirClean();

    /**
     * 大智慧、大众点评图片下载
     *
     * @param imageUrl
     */
    byte[] imageDownload(String imageUrl);

}
