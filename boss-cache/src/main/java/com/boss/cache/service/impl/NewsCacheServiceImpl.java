package com.boss.cache.service.impl;

import com.boss.cache.image.utils.DateUtils;
import com.boss.cache.service.NewsCacheService;
import com.boss.cache.service.TencentNewsApiService;
import com.boss.common.id.SnowflakeIdWorker;
import com.boss.common.json.JSONMapper;
import com.boss.common.news.vo.api.PicturesData;
import com.boss.common.news.vo.api.TencentItem;
import com.boss.common.news.vo.api.TencentNewsData;
import com.boss.common.news.vo.hu.*;
import com.myself.platform.service.RedisService;
import lombok.extern.log4j.Log4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author Tongshan.Han@partner.readJson.com
 * @Description:
 * @date 2018/10/8 10:51
 */
@Service
@Log4j
public class NewsCacheServiceImpl implements NewsCacheService {

    @Autowired
    private RedisService redisService;

    @Autowired
    private TencentNewsApiService tencentNewsApiService;

    @Value("classpath:news/evo/zh.json")
    private Resource categories;

    @Value("${news.cache.duration}")
    private String duration;

    /**
     * 定时删除前一天的图片目录
     */
    @Override
    public void imgDirClean() {
        log.info("<=========定时删除前一天的图片目录JOB================>");
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, -1);
            Date time = calendar.getTime();
            String date = new SimpleDateFormat("yyyy-MM-dd").format(time);
            String path = this.getClass().getResource("/").getPath() + date;
            File file = new File(path);
            if (file.isDirectory()) {
                log.info("定时删除前一天的图片目录, 目录名称为:" + file.getName() + ",目录路径为:" + file.getPath());
                FileUtils.deleteDirectory(new File(path));
            }
        } catch (Exception e) {
            log.error("NewsCacheServiceImpl imgDirClean error", e);
        }
    }

    @Override
    public byte[] imageDownload(String imageUrl) {
        InputStream in = null;
        byte[] bytes = null;
        try {
            in = getUrlStream(imageUrl);
            bytes = IOUtils.toByteArray(in);
        } catch (Exception e) {
            log.error("dzh & dp download images error imageUrl:" + imageUrl, e);
            return null;
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return bytes;
    }

    /**
     * 图片下载&图片存储
     *
     * @param tenImageUrl
     * @param fileName
     */
    @Override
    public byte[] imageDownloadStorage(String tenImageUrl, String fileName, String downLoadPath, String maxWidth, String maxHeight) {
        InputStream in = null;
        byte[] bytes = null;
        try {
            if (StringUtils.isNotBlank(maxWidth) && StringUtils.isNotBlank(maxHeight)) {
                in = getUrlStream(tenImageUrl);
                bytes = IOUtils.toByteArray(in);
                File file = new File(downLoadPath);
                if (!file.exists()) {
                    file.mkdir();
                }
                String filePath = downLoadPath + "/" + fileName;
                FileUtils.writeByteArrayToFile(new File(filePath), bytes);
                redisService.set(tenImageUrl + "&" + maxWidth + "&" + maxHeight, fileName, 24 * 60 * 60L);
                /** 用压缩后的图片覆盖原来的图片*/
                this.imageCompress(filePath, Integer.parseInt(maxWidth), Integer.parseInt(maxHeight));
            }
            return bytes;
        } catch (Exception e) {
            log.error("imageDownloadStorage error fileName:" + fileName + ",tenImageUrl:" + tenImageUrl, e);
            return null;
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 根据车型,主题类型把需要的数据缓存在redis中
     * 腾讯频道: {"news", "world", "finance", "tech", "sports", "ent"}
     */
    @Override
    public void newsCache() {
        /**
         * 1.构建MUG消息体
         * */
        try {
            String cateData = IOUtils.toString(categories.getInputStream(), Charset.forName("UTF-8"));
            Category category = (Category) JSONMapper.fromJson(cateData, Category.class);
            List<CategoryItem> categoryItemList = category.getItems();
            for (CategoryItem categoryItem : categoryItemList) {
                TencentNewsData tencentNewsData = tencentNewsApiService.getNewsContentList(categoryItem.getOid());
                if (tencentNewsData == null || tencentNewsData.getItem() == null || tencentNewsData.getItem().isEmpty()) {
                    log.warn("get tencent api data return null, channelId:" + categoryItem.getOid());
                    continue;
                }
                /** 构建news消息体 */
                NewsData newsData = this.buildEvoData(tencentNewsData, categoryItem.getChannel());
                if (newsData == null || newsData.getItems() == null || newsData.getItems().isEmpty()) {
                    log.warn("buildEvoData return null, channelId:" + categoryItem.getOid());
                    continue;
                }
                redisService.set(categoryItem.getChannel(), JSONMapper.writeObjectAsString(newsData), Long.parseLong(duration));
            }
        } catch (Exception e) {
            log.error("NewsCacheServiceImpl newsCache error {} ", e);
        }
    }

    private String getImgId(String picUrl, Integer width, Integer height, String picId) {
        String key = picUrl + "&" + width + "&" + height;
        if (redisService.exists(key)) {
            String fileName = (String) redisService.get(key);
            String imageId = fileName.substring(0, fileName.indexOf("."));
            return imageId;
        }
        return picId;
    }


    private InputStream getUrlStream(String imageUrl) throws Exception {
        URL url = new URL(imageUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setReadTimeout(30000);
        connection.setConnectTimeout(30000);
        return connection.getInputStream();
    }

    /**
     * 图片下载&存储&缓存&压缩
     */
    private void imageStorageCache(List<ImageCompressionData> imageCompressList) {
        InputStream in = null;
        try {
            String date = DateUtils.getCurrentDateAsString();
            String path = this.getClass().getResource("/").getPath() + date;
            for (ImageCompressionData data : imageCompressList) {
                String imageId = data.getImageId();
                String fileName = imageId + ".jpg";
                String imageUrl = data.getImgUrl();
                String filePath = path + "/" + fileName;
                String key = imageUrl + "&" + data.getWidth() + "&" + data.getHeight();
                /** 1.先判断图片是否缓存过 */
                if (redisService.exists(key)) {
                    String oldFileName = (String) redisService.get(key);
                    log.info("===================>缓存中存在对应的key,imageUrl:" + imageUrl + ",imageId:" + imageId);
                    if (StringUtils.isNotBlank(oldFileName)) {
                        File file = new File(path + "/" + oldFileName);
                        if (file.exists()) {
                            /** 2.缓存中存在对应的key且本地目录中已存在对应的图片文件 */
                            log.info("===================>缓存中存在对应的key且此图片本地已存储:" + imageUrl + ",imageId:" + imageId);
                            continue;
                        } else {
                            log.info("===================>缓存中存在对应的key但本地未存储对应的图片:" + imageUrl + ",imageId:" + imageId);
                            /** 3.缓存中存在对应的key,但是本地目录不存在对应的图片文件 */
                            in = getUrlStream(imageUrl);
                            byte[] bytes = IOUtils.toByteArray(in);

                            FileUtils.writeByteArrayToFile(new File(filePath), bytes);
                            /** 用压缩后的图片覆盖原来的图片-size(width,height) */
                            this.imageCompress(filePath, data.getWidth(), data.getHeight());
                        }
                    } else {
                        log.warn("缓存中key-imageUrl存在,对应的value却为空, imageUrl:" + imageUrl + ",oldFileName:" + oldFileName);
                    }
                } else {
                    log.info("===================>缓存中不存在对应的key,imageUrl:" + imageUrl + ",imageId:" + imageId);
                    /** 4.图片本地存储 */
                    in = getUrlStream(imageUrl);
                    byte[] bytes = IOUtils.toByteArray(in);

                    FileUtils.writeByteArrayToFile(new File(filePath), bytes);

                    /** 2.图片数据缓存 */
                    redisService.set(key, fileName, 24 * 60 * 60L);
                    /** 用压缩后的图片覆盖原来的图片*/
                    this.imageCompress(filePath, data.getWidth(), data.getHeight());
                }
            }
        } catch (Exception e) {
            log.error("imageStorageCache error {} ", e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

//    private void imageCompress(String filePath, Integer width, Integer height) throws Exception {
//        FileOutputStream out = null;
//        try {
//            File file=new File(filePath);
//            BufferedImage src = javax.imageio.ImageIO.read(file);
//
//            BufferedImage tag= new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//
//            tag.getGraphics().drawImage(src.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);
//            out = new FileOutputStream(filePath);
//            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//            encoder.encode(tag);
//            out.close();
//        } catch (IOException e) {
//            log.error("imageCompress error {} ", e);
//        } finally {
//            if (out!=null){
//                out.close();
//            }
//        }
//    }

    private void imageCompress(String filePath, Integer width, Integer height) throws Exception {
        FileOutputStream out = null;
        try {
            File file = new File(filePath);
            BufferedImage src = ImageIO.read(file);

            BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

            tag.getGraphics().drawImage(src.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);
            out = new FileOutputStream(filePath);
            ImageIO.write(tag, "jpg", new File(filePath));
            out.close();
        } catch (IOException e) {
            log.error("imageCompress error {} ", e);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    private NewsData buildEvoData(TencentNewsData tencentNewsData, String channelId) {
        try {
            NewsData newsData = new NewsData();
            SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);

            List<ItemsData> items = new ArrayList<>();
            newsData.setOid(channelId);
            List<TencentItem> tencentItemList = tencentNewsData.getItem();
            for (TencentItem tencentItem : tencentItemList) {
                ItemsData itemsData = new ItemsData();
                itemsData.setTitle(tencentItem.getTitle());
                itemsData.setTimestamp(formatTDate(tencentItem.getPubDate()));
                List<String> paragraphs = new ArrayList<>();
                paragraphs.add(tencentItem.getContent() + "--腾讯新闻\n");
                itemsData.setParagraphs(paragraphs);
                PicturesData picturesData = null;
                String fullImageUrlParam = null;
                String thumbImageUrlParam = null;

                String imgId1 = idWorker.nextId().toString();
                String imgId2 = idWorker.nextId().toString();
                String imgUrl1 = "";
                String imgUrl2 = "";
                if (tencentItem.getPictures() != null && tencentItem.getPictures().size() > 0) {
                    picturesData = tencentItem.getPictures().get(0);
                    imgId1 = this.getImgId(picturesData.getUrl(), 135, 135, imgId1);
                    fullImageUrlParam = URLEncoder.encode(picturesData.getUrl() + "/" + imgId1, "UTF-8") + ".jpg";
                    imgUrl1 = picturesData.getUrl();
                } else {
                    log.warn("buildEvoData getPictures is null, channelId:" + channelId);
                    imgId1 = this.getImgId(tencentItem.getSmallpicurl(), 135, 135, imgId1);
                    fullImageUrlParam = URLEncoder.encode(tencentItem.getSmallpicurl() + "/" + imgId1, "UTF-8") + ".jpg";
                    imgUrl1 = tencentItem.getSmallpicurl();
                }
                imgId2 = this.getImgId(tencentItem.getSmallpicurl(), 324, 225, imgId2);
                thumbImageUrlParam = URLEncoder.encode(tencentItem.getSmallpicurl() + "/" + imgId2, "UTF-8") + ".jpg";
                //imgUrl2 = tencentItem.getSmallpicurl();

                boolean isThumbEmpty = thumbImageUrlParam == null || thumbImageUrlParam.length() < 5;
                boolean isFullEmpty = fullImageUrlParam == null || fullImageUrlParam.length() < 5;
                if (isFullEmpty && isThumbEmpty) {
                    itemsData.setImages(null);
                } else {
                    List<ImagesData> images = new ArrayList<>();

                    ThumbData thumb = new ThumbData();
                    thumb.setLink(fullImageUrlParam);
                    thumb.setX(135);
                    thumb.setY(135);
                    FullData full = new FullData();
                    full.setLink(fullImageUrlParam);
                    full.setX(324);
                    full.setY(225);
                    ImagesData imagesData = new ImagesData();
                    imagesData.setThumb(thumb);
                    imagesData.setFull(full);

                    images.add(imagesData);
                    itemsData.setImages(images);
                    /**
                     * 图片缓存, 需要压缩成三种不同的尺寸
                     * */
                    /** (1).135*135 */
                    List<ImageCompressionData> imageCompressList = new ArrayList();
                    ImageCompressionData imageCompressionData1 = new ImageCompressionData();
                    imageCompressionData1.setImgUrl(imgUrl1);
                    imageCompressionData1.setImageId(imgId1);
                    imageCompressionData1.setHeight(135);
                    imageCompressionData1.setWidth(135);
                    imageCompressList.add(imageCompressionData1);
                    /** (2).324*225 */
                    ImageCompressionData imageCompressionData2 = new ImageCompressionData();
                    imageCompressionData2.setImgUrl(imgUrl1);
                    imageCompressionData2.setImageId(imgId2);
                    imageCompressionData2.setHeight(225);
                    imageCompressionData2.setWidth(324);
                    imageCompressList.add(imageCompressionData2);
                    this.imageStorageCache(imageCompressList);
                }
                items.add(itemsData);
            }
            newsData.setItems(items);
            return newsData;
        } catch (Exception e) {
            log.error("buildEvoData error {} ", e);
            return null;
        }
    }


    private String formatTDate(String pubDate) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = formatter.parse(pubDate);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss+0800");
            return sdf.format(date);
        } catch (Exception e) {
            log.error("formatTDate error {} ", e);
        }
        return null;
    }

}
