package com.boss.info.mybatis.service.impl;

import com.boss.info.mybatis.dao.WebsitesEntityMapper;
import com.boss.info.entity.WebsitesEntity;
import com.boss.info.mybatis.service.IWebsitesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * websites的请求service服务类
 */
@Service("websitesService")
public class WebsitesServiceImpl implements IWebsitesService {
    @Autowired
    WebsitesEntityMapper websitesEntityMapper;

    private Logger logger = LoggerFactory.getLogger(WebsitesServiceImpl.class);


    public int insert(WebsitesEntity record) throws SQLException {
        return websitesEntityMapper.insert(record);
    }

    /**
     * 根据ID查询列表
     *
     * @param id
     * @return
     */
    public WebsitesEntity selectByPrimaryKey(Integer id) throws SQLException {
        logger.info("开始查询id" + id);
        return websitesEntityMapper.selectByPrimaryKey(id);
    }


    /**
     * 查询列表
     *
     * @return
     * @throws SQLException
     */
    public List<WebsitesEntity> queryList() throws SQLException {
        return websitesEntityMapper.queryList();
    }
}
