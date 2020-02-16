package com.boss.cache.image.service;

import com.boss.cache.image.utils.Contants;
import com.boss.cache.image.utils.DateUtils;
import lombok.extern.log4j.Log4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
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

@Service
@Log4j
public class ImageFileService {

    public byte[] getImagesData(String imageUrl, String fileName, String width, String height, String encodedImageUrl) {
        InputStream inputStream = null;
        byte[] imageData = null;
        try {
            /** 1.点评&大智慧图片 */
            if (StringUtils.isBlank(width) && StringUtils.isBlank(height)) {
                URL url = new URL(encodedImageUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setReadTimeout(5000);
                connection.setConnectTimeout(5000);
                inputStream = connection.getInputStream();
                imageData = IOUtils.toByteArray(inputStream);
                return imageData;
            } else {
                /** 2.新闻图片 */
                String filePath = Contants.IMAGES_SAVE_PATH + DateUtils.getCurrentDateAsString() + "/" + fileName;
                File file = new File(filePath);
                if (file.exists()) {
                    log.info("Load images from a local directory filePath:" + filePath);
                    imageData = FileUtils.readFileToByteArray(file);
                    return imageData;
                } else {
                    URL url = new URL(imageUrl);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setReadTimeout(5000);
                    connection.setConnectTimeout(5000);
                    inputStream = connection.getInputStream();
                    imageData = IOUtils.toByteArray(inputStream);
                    FileUtils.writeByteArrayToFile(new File(filePath), imageData);
                    this.imageCompress(filePath, Integer.parseInt(width), Integer.parseInt(height));
                    return imageData;
                }
            }
        } catch (Exception e) {
            log.error("Invoke 3rd Api getImagesData error imageUrl:" + imageUrl + ",width:" + width + ",height:" + height, e);
            return null;
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void imageCompress(String filePath, Integer width, Integer height) {
        FileOutputStream out = null;
        try {
            File file = new File(filePath);
            BufferedImage src = ImageIO.read(file);

            BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

            tag.getGraphics().drawImage(src.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);
            out = new FileOutputStream(filePath);
            ImageIO.write(tag, "jpg", new File(filePath));
        } catch (IOException e) {
            log.error("imageCompress error {} ", e);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
