package com.boss.info.bmw.service.impl;

import com.boss.common.exception.BusinessException;
import com.boss.common.id.SnowflakeIdWorker;
import com.boss.info.bmw.enums.NewsImageSizeEnum;
import com.boss.info.bmw.pojo.api.NewsItemPojo;
import com.boss.info.bmw.pojo.api.NewsListAPIPojo;
import com.boss.info.bmw.pojo.api.PictureItemPojo;
import com.boss.info.bmw.pojo.json.*;
import com.boss.info.bmw.service.INewsService;
import com.boss.info.bmw.service.LinxService;
import com.boss.info.bmw.utils.I18nCategoriesForNews;
import com.myself.platform.service.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("newsService")
public class NewsServiceImp implements INewsService {

    private static Logger logger = LoggerFactory.getLogger(NewsServiceImp.class);


    @Autowired
    private LinxService linxService;

    @Autowired
    private I18nCategoriesForNews i18nCategoriesForNews;

    @Autowired
    private RedisService redisService;

    public List<ResponseCategoriesDataPojo> getNewsCategories() throws Exception {
        return i18nCategoriesForNews.getChannelList();
    }

    /**
     * 使用redis
     *
     * @param language
     * @param name
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public List<ResponseNewsListDataPojo> getNewsListByOid(String language, String name) throws Exception {
        String categoryOid = i18nCategoriesForNews.getChannelId(language, name);
        List<ResponseNewsListDataPojo> rtnList = (List<ResponseNewsListDataPojo>) redisService.get(categoryOid);
        if (rtnList == null) {
            rtnList = getNewsList(language, name);
            redisService.set(categoryOid, rtnList, 900l);
        } else {
            logger.info("get redis key :  " + categoryOid + " success");
        }
        return rtnList;
    }


    public List<ResponseNewsListDataPojo> getNewsList(String language, String name)
            throws IOException, BusinessException {
        String categoryOid = i18nCategoriesForNews.getChannelId(language, name);
        if (categoryOid == null) {
            IOException e = new IOException("readJson Cannot match categories [" + name + "]");
            throw e;
        }
        NewsListAPIPojo apiPojo = linxService.getNewsList(categoryOid);
        List<ResponseNewsListDataPojo> rtnList = new ArrayList<>();
        if ("0".equals(apiPojo.getStatus())) {
            ResponseNewsListDataPojo dataPojo = parseNewsListResponse(apiPojo);
            dataPojo.setOid(name);
            rtnList.add(dataPojo);
        } else {
            BusinessException e = new BusinessException(apiPojo.getStatus());
            throw e;
        }
        return rtnList;
    }


    /**
     * Data conversion
     *
     * @param apiPojo
     * @return
     * @throws UnsupportedEncodingException
     */
    public ResponseNewsListDataPojo parseNewsListResponse(NewsListAPIPojo apiPojo) throws UnsupportedEncodingException {
        ResponseNewsListDataPojo dataPojo = new ResponseNewsListDataPojo();
        List<ResponseNewsItemPojo> itemsPojo = new ArrayList<>();
        SnowflakeIdWorker idWorker = new SnowflakeIdWorker(2, 2);
        for (NewsItemPojo apiItemPojo : apiPojo.getItem()) {
            ResponseNewsItemPojo itemPojo = new ResponseNewsItemPojo();
            itemPojo.setTitle(apiItemPojo.getTitle());
            List<String> paragrah = new ArrayList<>();
            paragrah.add(apiItemPojo.getContent() + "--腾讯新闻\n");
            itemPojo.setParagraphs(paragrah);

            SimpleDateFormat srcDateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat tarDateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss+0800");

            String srcDateStr = apiItemPojo.getPubDate();
            String tarDateStr = "2000-01-01T00:00:00+0800";
            try {
                Date date = srcDateFormatter.parse(srcDateStr);
                tarDateStr = tarDateFormatter.format(date);
                itemPojo.setTimestamp(tarDateStr);

                PictureItemPojo pictureItemPojo = apiItemPojo.getPictures().get(0);
                List<ResponseImagesListPojo> imageListPojo = new ArrayList<>();
                String thumbImageUrlParam = URLEncoder.encode(pictureItemPojo.getUrl() + "/" + idWorker.nextId(), "UTF-8") + ".jpg";
                String fullImageUrlParam = URLEncoder.encode(pictureItemPojo.getUrl() + "/" + idWorker.nextId(), "UTF-8") + ".jpg";
                boolean isThumbEmpty = thumbImageUrlParam == null || thumbImageUrlParam.length() < 5;
                boolean isFullEmpty = fullImageUrlParam == null || fullImageUrlParam.length() < 5;
                if (isThumbEmpty && isFullEmpty) {
                    itemPojo.setImages(null);
                } else {
                    if (isThumbEmpty) {
                        thumbImageUrlParam = fullImageUrlParam;
                    } else if (isFullEmpty) {
                        fullImageUrlParam = thumbImageUrlParam;
                    }
                    ResponseImagesItemPojo thumbImageItem = new ResponseImagesItemPojo();
                    ResponseImagesItemPojo fullImageItem = new ResponseImagesItemPojo();
                    thumbImageItem.setLink(thumbImageUrlParam);
                    thumbImageItem.setX(NewsImageSizeEnum.THUMB_IMAGE_X.getNewsImageSizeEnum());
                    thumbImageItem.setY(NewsImageSizeEnum.THUMB_IMAGE_Y.getNewsImageSizeEnum());
                    fullImageItem.setLink(fullImageUrlParam);
                    fullImageItem.setX(NewsImageSizeEnum.FULL_IMAGE_X.getNewsImageSizeEnum());
                    fullImageItem.setY(NewsImageSizeEnum.FULL_IMAGE_Y.getNewsImageSizeEnum());
                    ResponseImagesListPojo imagesList = new ResponseImagesListPojo();
                    imagesList.setFull(fullImageItem);
                    imagesList.setThumb(thumbImageItem);
                    imageListPojo.add(imagesList);

                    itemPojo.setImages(imageListPojo);
                }
            } catch (ParseException e) {
                logger.warn("readJson Parse date: " + srcDateStr + "error", e);
            } catch (Exception e) {
                logger.error("parse picture exception :" + e);
            }

            itemsPojo.add(itemPojo);
        }
        dataPojo.setItems(itemsPojo);
        return dataPojo;
    }

}
