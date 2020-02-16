package com.boss.cache.image.task;

import com.boss.cache.image.utils.Contants;
import lombok.extern.log4j.Log4j;
import org.apache.commons.io.FileUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Tongshan.Han@partner.readJson.com
 * @Description:
 * @date 2018/9/7 16:09
 */
@Service
@Log4j
public class DirectoryCleanTask {
    @Scheduled(cron = "* * 0/3 * * ?")
    public void imgDirClean() {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, -1);
            Date time = calendar.getTime();
            String date = new SimpleDateFormat("yyyy-MM-dd").format(time);
            String path = Contants.IMAGES_SAVE_PATH + date;
            File file = new File(path);
            if (file.isDirectory()) {
                FileUtils.deleteDirectory(new File(path));
            }
            log.info("DirectoryCleanTask imgDirClean file:" + file.getPath());
        } catch (Exception e) {
            log.error("DirectoryCleanTask imgDirClean error", e);
        }
    }

}
