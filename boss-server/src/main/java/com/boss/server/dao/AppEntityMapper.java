package com.boss.server.dao;

import com.boss.server.base.BaseMapper;
import com.boss.server.entity.AppEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("appEntityMapper")
public interface AppEntityMapper extends BaseMapper<AppEntity, String> {

    List<AppEntity> getAppList(Map map);

    List<AppEntity> getAppByIdVer(Map map);

}