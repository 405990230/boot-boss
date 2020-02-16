package com.boss.info.bmw.service;

import com.boss.common.exception.BusinessException;
import com.boss.info.bmw.pojo.json.ResponseCategoriesDataPojo;
import com.boss.info.bmw.pojo.json.ResponseNewsListDataPojo;

import java.io.IOException;
import java.util.List;

/**
 * Created by qxr4383 on 2018/1/17.
 */
public interface INewsService {

    /**
     * get news Categories and add redies cache
     * spring bean
     *
     * @return
     */
    List<ResponseNewsListDataPojo> getNewsListByOid(String language, String name) throws Exception;


    /**
     * get news Categories
     *
     * @return
     */
    List<ResponseCategoriesDataPojo> getNewsCategories() throws Exception;

    /**
     * Get a list of news messages by channelId and languag
     *
     * @param language
     * @param name
     * @return
     * @throws IOException
     * @throws BusinessException
     */
    List<ResponseNewsListDataPojo> getNewsList(String language, String name)
            throws IOException, BusinessException;

}
