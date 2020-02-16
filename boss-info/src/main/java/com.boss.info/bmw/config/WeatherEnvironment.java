package com.boss.info.bmw.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by qxr4383 on 2018/5/16.
 */
@Component
public class WeatherEnvironment {

    private static Logger logger = LoggerFactory.getLogger(WeatherEnvironment.class);
    private static final String PROPERTIES_FILE = "properties/weather_map.properties";
    private static Properties weatherMap = null;

    @PostConstruct
    public void init() {
        InputStream inputStream = null;
        try {
            try {
                inputStream = this.getClass().getClassLoader().getResourceAsStream(PROPERTIES_FILE);
                weatherMap = new Properties();
                weatherMap.load(inputStream);
            } catch (IOException e) {
                logger.error("No config file!", e);
            }
        } catch (Exception e) {
            logger.error("weather_map init error {} ", e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    logger.error("weather_map init finally error {} ", e);
                }
            }
        }
    }

    public static Properties getWeatherMap() {
        return weatherMap;
    }

}
