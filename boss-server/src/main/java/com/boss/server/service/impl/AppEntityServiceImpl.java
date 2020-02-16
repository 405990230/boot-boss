package com.boss.server.service.impl;

import com.boss.server.dao.AppEntityMapper;
import com.boss.server.entity.AppEntity;
import com.boss.server.service.AppEntityService;
import com.boss.server.utils.JSONMapper;
import com.boss.server.utils.StreamUtils;
import com.boss.server.vo.AppAttributes;
import com.boss.server.vo.AppElement;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("appEntityService")
@Slf4j
public class AppEntityServiceImpl implements AppEntityService {

    //private static Logger log = Logger.getLogger(AppEntityServiceImpl.class);

    @Autowired
    private AppEntityMapper appEntityMapper;

    private AppEntity appEntity;

    @Value("${environment}")
    private String env;

    @Value("${app.download.url.base}")
    private String appDownLoadUrl;

    @Override
    public List<AppEntity> findApps(AppEntity appEntity, Integer pageNo, Integer pageSize) {
        Map map = new HashMap<>(16);
        map.put("start", (pageNo - 1) * pageSize);
        map.put("pageSize", pageSize);
        List<AppEntity> list = appEntityMapper.findResults(map);
        return list;
    }

    @Override
    public List<AppElement> getAppList() {
        List<AppElement> elements = new ArrayList<>();
        List<AppEntity> list = appEntityMapper.getAppList(null);
        if (list == null || list.isEmpty()) {
            log.warn("AppEntityServiceImpl getAppList return null");
            return null;
        }
        for (AppEntity entity : list) {
            AppElement element = new AppElement();
            element.setAttributes((List<AppAttributes>) JSONMapper.fromJson(entity.getAttributes(), Object.class));
            element.setDueDate("");
            element.setHash(entity.getHash());
            element.setPolicyUrl(entity.getPolicyurl());
            element.setSize(entity.getSize());
            element.setUrl(entity.getUrl());
            element.setVersion(entity.getVersion());

            elements.add(element);
        }
        return elements;
    }

    @Override
    public String processFiles(Map<String, MultipartFile> getFileMap) throws IOException {
        MultipartFile md5FileItem = null;
        MultipartFile jsonFileItem = null;
        MultipartFile targetFileItem = null;

        Iterator<Map.Entry<String, MultipartFile>> entries = getFileMap.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, MultipartFile> entry = entries.next();
            log.info("===================>Key = " + entry.getKey() + ", Value = " + entry.getValue().getOriginalFilename());
            String fieldName = entry.getKey();
            MultipartFile multipartFile = entry.getValue();

            if ("md5File".equals(fieldName)) {
                md5FileItem = multipartFile;
            } else if ("jsonFile".equals(fieldName)) {
                jsonFileItem = multipartFile;
            } else if ("tarFile".equals(fieldName)) {
                targetFileItem = multipartFile;
            }
        }

        if (md5FileItem == null || jsonFileItem == null || targetFileItem == null) {
            log.error("Missed essential files!");
            return null;
        }
        appEntity = new AppEntity();

        Boolean jsonResult = this.processJsonFile(jsonFileItem);
        Boolean targetResult = this.processTargetFile(targetFileItem);
        Boolean md5Result = this.processMD5File(md5FileItem);

        if (jsonResult && targetResult && md5Result) {
            this.appEntity.setUrl(appDownLoadUrl +
                    "?app=" + this.appEntity.getAppid() +
                    "&ver=" + this.appEntity.getVersion());

            //log.info("Recieved new App target file: " + this.appEntity);
            AppEntity ae = appEntityMapper.selectByPrimaryKey(this.appEntity.getAppid());
            if (ae == null) {
                appEntityMapper.insert(appEntity);
            } else {
                appEntityMapper.updateByPrimaryKey(appEntity);
            }
        } else {
            throw new IOException("File process failed!");
        }
        return this.appEntity.getAppid();
    }

    @Override
    public InputStream getFileStream(String appId, String version) {
        Map map = new HashMap(16);
        map.put("appid", appId);
        map.put("version", version);
        List<AppEntity> list = appEntityMapper.getAppByIdVer(map);
        if (list == null || list.isEmpty()) {
            log.warn("can not get record from db appId:" + appId + ",version:" + version);
            return null;
        }
        AppEntity entity = list.get(0);
        return new ByteArrayInputStream(entity.getTarget());
    }

    @Override
    public void deleteAppEntity(String appId) throws Exception {
        Integer result = appEntityMapper.deleteByPrimaryKey(appId);
        if (result != 1) {
            throw new Exception("delete app from db error, appId:" + appId);
        }
    }

    private Boolean processMD5File(MultipartFile md5File) throws IOException {
        String md5FileStr = Inputstr2Str_Reader(md5File.getInputStream(), "UTF-8");
        Pattern pattern = Pattern.compile("\\b\\w{32}\\b");

        Matcher matcher = pattern.matcher(md5FileStr);

        Boolean containMD5 = matcher.find();

        if (containMD5) {
            String md5String = matcher.group();
            log.info("[MD5] " + md5String);
            this.appEntity.setHash(md5String);
        } else {
            log.error("No MD5 in file [" + md5File.getName() + "]: " + md5File.toString());
        }
        return containMD5;
    }

    @SuppressWarnings("unchecked")
    private Boolean processJsonFile(MultipartFile jsonFile) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(org.codehaus.jackson.map.DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Map<String, Object> manifestMap = new HashMap<String, Object>();
        manifestMap = objectMapper.readValue(jsonFile.getInputStream(), new TypeReference<HashMap<String, Object>>() {
        });
        List<Map<String, Object>> staticAttributeList = (List<Map<String, Object>>) manifestMap.get("static");
        Map<String, Object> dynamicAttributeMap = (Map<String, Object>) manifestMap.get("dynamic");

        // Set Attribute
        this.appEntity.setAttributes(objectMapper.writeValueAsString(staticAttributeList));

        // Get appid
        String appid = null;
        for (Map<String, Object> staticAttribute : staticAttributeList) {
            if ("id".equals(staticAttribute.get("key"))) {
                appid = (String) staticAttribute.get("value");
            }
        }

        if (appid == null || appid.length() <= 0) {
            log.error("No appid url.");
            return false;
        }

        this.appEntity.setAppid(appid);

        // Get policyUrl

        Map<String, Map<String, String>> policyUrlMap = (Map<String, Map<String, String>>) dynamicAttributeMap.get("policyUrl");

        getPolicyUrl(policyUrlMap, "CN");

        if (this.appEntity.getPolicyurl() == null) {
            log.info("No specific policyUrl for CN Market! Take default policy url.");
            getPolicyUrl(policyUrlMap, "default");
        }

        if (this.appEntity.getPolicyurl() == null) {
            log.error("No policy url.");
            return false;
        }

        return true;

    }

    private Boolean processTargetFile(MultipartFile targetFile) throws IOException {

        Pattern pattern = Pattern.compile("\\d*\\.\\d*\\.\\d*");
        Matcher matcher = pattern.matcher(targetFile.getOriginalFilename());

        if (matcher.find()) {
            this.appEntity.setVersion(matcher.group());
        } else {
            return false;
        }

        this.appEntity.setTarget(StreamUtils.toByteArray(targetFile.getInputStream()));
        this.appEntity.setSize((int) (targetFile.getSize() / 1024));

        return true;
    }

    private void getPolicyUrl(Map<String, Map<String, String>> policyUrlMap, String market) {
        if (policyUrlMap.get(market) != null) {
            String majorUrl = policyUrlMap.get(market).get(env);
            if (majorUrl != null && majorUrl.length() > 0) {
                this.appEntity.setPolicyurl(majorUrl);
            }
        }
    }

    public static String Inputstr2Str_Reader(InputStream in, String encode) {
        String str = "";
        try {
            if (encode == null || encode.equals("")) {
                encode = "utf-8";
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, encode));
            StringBuffer sb = new StringBuffer();

            while ((str = reader.readLine()) != null) {
                sb.append(str).append("\n");
            }
            return sb.toString();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return str;
    }

}
