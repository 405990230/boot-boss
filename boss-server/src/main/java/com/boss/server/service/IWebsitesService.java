package com.boss.server.service;


import com.boss.server.entity.WebsitesEntity;

import java.sql.SQLException;
import java.util.List;

/**
 * Websites的请求服务service.
 */
public interface IWebsitesService {

    /**
     * Websites插入数据
     *
     * @param record
     * @return
     * @throws SQLException
     */
    int insert(WebsitesEntity record) throws SQLException;

    /**
     * 根据ID查询列表
     *
     * @param id
     * @return
     */
    WebsitesEntity selectByPrimaryKey(Integer id) throws SQLException;

    /**
     * 查询列表
     *
     * @return
     * @throws SQLException
     */
    List<WebsitesEntity> queryList() throws SQLException;
}
