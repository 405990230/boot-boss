package com.boss.server.dao;

import com.boss.server.base.BaseMapper;
import com.boss.server.entity.WebsitesEntity;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository("websitesEntityMapper")
public interface WebsitesEntityMapper extends BaseMapper<WebsitesEntity, String> {
    int deleteByPrimaryKey(Integer id);

    int insert(WebsitesEntity record);

    int insertSelective(WebsitesEntity record);

    WebsitesEntity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WebsitesEntity record);

    int updateByPrimaryKey(WebsitesEntity record);

    List<WebsitesEntity> queryList() throws SQLException;
}