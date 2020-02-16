package com.boss.server.service;

import com.boss.server.entity.AppEntity;
import com.boss.server.vo.AppElement;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface AppEntityService {

    List<AppEntity> findApps(AppEntity appEntity, Integer pageNo, Integer pageSize);

    List<AppElement> getAppList();

    String processFiles(Map<String, MultipartFile> getFileMap) throws IOException;

    InputStream getFileStream(String appId, String version);

    void deleteAppEntity(String appId) throws Exception;

}
