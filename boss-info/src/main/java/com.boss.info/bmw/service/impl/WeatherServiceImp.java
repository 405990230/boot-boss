package com.boss.info.bmw.service.impl;

import com.boss.common.exception.BusinessException;
import com.boss.info.bmw.config.WeatherEnvironment;
import com.boss.info.bmw.constant.Constant;
import com.boss.info.bmw.constant.WeatherConstant;
import com.boss.info.bmw.pojo.api.*;
import com.boss.info.bmw.pojo.json.*;
import com.boss.info.bmw.service.IWeatherService;
import com.boss.info.bmw.service.LinxService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("weatherService")
public class WeatherServiceImp implements IWeatherService {

    private static Logger logger = LoggerFactory.getLogger(WeatherServiceImp.class);
    @Autowired
    private LinxService linxService;

    /**
     * value：缓存位置名称，不能为空，如果使用EHCache，就是ehcache.xml中声明的cache的name
     * key：缓存的key，默认为空，既表示使用方法的参数类型及参数值作为key，支持SpEL
     * condition：触发条件，只有满足条件的情况才会加入缓存，默认为空，既表示全部都加入缓存，支持SpEL
     */
    //@Cacheable(value="cacheWeather", key="#cacheKey")
    public List<ResponseDataItemPojo> getCurrentWeather(String lat, String lon, String language, String cacheKey) throws BusinessException {
        logger.info("cacheWeather --- weatherInfo of cacheKey : " + cacheKey);
        List<ResponseDataItemPojo> rtnList = null;
        String i18n = "zh_CN";
        if ("en".equals(language)) {
            i18n = "en_US";
        }
        language = i18n;
        WeatherApiPojo apiPojo = linxService.getWeather(lat, lon, i18n);
        if (Constant.SUCCESS.equals(apiPojo.getCode())) {
            rtnList = parseWeatherApiPojo(apiPojo);
            String indexDescriptionString = generateDescription(apiPojo.getData().get(0).getIndex(), i18n);
            List<ResponseIndexPojo> indicesList = parseIndexList(apiPojo.getData().get(0).getIndex(), i18n);

            // add AQI to indicesList
            ResponseIndexPojo aqiPojo = new ResponseIndexPojo();
            WeatherAirPojo weatherAir = apiPojo.getData().get(0).getWeatherAir();
            if (weatherAir != null) {
                String type = WeatherConstant.WEATHER_AQI_EN;
                indexDescriptionString += type + "," + weatherAir.getDetail();
                if (language.equals(Constant.LANGUAGE_ZH_CN)) {
                    type = WeatherConstant.WEATHER_AQI_CN;
                    indexDescriptionString += type + ":" + weatherAir.getDetail();
                }
                aqiPojo.setType(type);
                int aqi = Integer.parseInt(weatherAir.getAqi());
                aqiPojo.setDescription(aqi + " " + weatherAir.getDesc());
                aqiPojo.setColor(parseWeatherAqiColor(aqi));
                indicesList.add(aqiPojo);
            }
            // add Current Weather
            CurrentWeatherPojo currentWeather = getCurrentWeather(apiPojo);

            rtnList.get(0).setIndexDescription(indexDescriptionString);
            rtnList.get(0).setCurrentWeather(currentWeather);
            rtnList.get(0).setIndices(indicesList);
        } else {
            BusinessException excepiton = new BusinessException(apiPojo.getCode());
            throw excepiton;
        }
        return rtnList;
    }

    private List<ResponseDataItemPojo> parseWeatherApiPojo(WeatherApiPojo apiPojo) {
        List<ResponseDataItemPojo> rtnPojoList = new ArrayList<ResponseDataItemPojo>();
        for (int idx = 0; idx < apiPojo.getData().get(0).getForecast().size(); ++idx) {
            ForcastApiPojo apiPojoItem = apiPojo.getData().get(0).getForecast().get(idx);
            ResponseDataItemPojo newRtnPojoItem = new ResponseDataItemPojo();
            newRtnPojoItem.setOid("day" + (rtnPojoList.size() + 1));

            // change date format
            String tarDateStr = getSrcDateStr(apiPojoItem);
            newRtnPojoItem.setTimestamp(tarDateStr);

            // set period
            //morning
            ResponsePeriodPojo morningPojo = getResponsePeriodPojo
                    (apiPojoItem.getDayWeather(), apiPojoItem.getDayTemperature(), apiPojoItem.getDayWindPower(), apiPojoItem.getDayWindDirection());
            newRtnPojoItem.setMorning(morningPojo);

            //evening
            ResponsePeriodPojo eveningPojo = getResponsePeriodPojo
                    (apiPojoItem.getNightWeather(), apiPojoItem.getNightTemperature(), apiPojoItem.getNightWindPower(), apiPojoItem.getNightWindDirection());
            newRtnPojoItem.setEvening(eveningPojo);
            newRtnPojoItem.setNight(eveningPojo);


            rtnPojoList.add(newRtnPojoItem);
        }

        return rtnPojoList;
    }

    private int centigradeToF(float centigrade) {
        return (int) Math.round(32 + centigrade * 1.8);
    }

    private String generateDescription(IndicesApiPojo indicesApiPojo, String i18n) {
        StringBuffer rtnBuffer = new StringBuffer("");
        for (IndexApiPojo apiPojo : indicesApiPojo.getIndices()) {
            String typeKey = "INDEX_TYPE_" + apiPojo.getType() + "_" + i18n;
            rtnBuffer.append(WeatherEnvironment.getWeatherMap().getProperty(typeKey));
            rtnBuffer.append(":").append(apiPojo.getDesc()).append(":")
                    .append(apiPojo.getDetail());
        }
        return rtnBuffer.toString();
    }

    private List<ResponseIndexPojo> parseIndexList(IndicesApiPojo indicesApiPojo,
                                                   String i18n) {
        List<ResponseIndexPojo> rtnList = new ArrayList<ResponseIndexPojo>();
        for (IndexApiPojo apiPojo : indicesApiPojo.getIndices()) {
            ResponseIndexPojo newPojo = new ResponseIndexPojo();
            String typeKey = "INDEX_TYPE_" + apiPojo.getType() + "_" + i18n;
            newPojo.setType(WeatherEnvironment.getWeatherMap().getProperty(typeKey));
            newPojo.setColor(WeatherEnvironment.getWeatherMap().getProperty(
                    "INDEX_COLOR_" + apiPojo.getType() + "_" + apiPojo.getLevel()));
            newPojo.setDescription(apiPojo.getDesc());
            rtnList.add(newPojo);
        }
        return rtnList;
    }

    public CurrentWeatherPojo getCurrentWeather(WeatherApiPojo apiPojo) {
        // add Current Weather
        CurrentWeatherPojo currentWeather = apiPojo.getData().get(0).getCurrentWeather();
        currentWeather.setWeather(WeatherEnvironment.getWeatherMap().getProperty("WEATHER_" + currentWeather.getWeather()));
        float f = Float.parseFloat(currentWeather.getTemperature());
        String temperature = String.valueOf((int) f);
        currentWeather.setTemperature(temperature);
        ResponseTemperaturePojo realTimeTemperature = getResponseTemperaturePojo(temperature);
        currentWeather.setRealTimeTemperature(realTimeTemperature);
        f = Float.parseFloat(currentWeather.getHumidity());
        String humidity = String.valueOf((int) f);
        currentWeather.setHumidity(humidity);
        // set sunrise,sunset,currenttime
        ForcastApiPojo forcast = apiPojo.getData().get(0).getForecast().get(0);
        currentWeather.setSunRiseTime(forcast.getSunRiseTime());
        currentWeather.setSunSetTime(forcast.getSunSetTime());
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("HH:mm");
        String currentTime = format.format(date);
        currentWeather.setCurrentTime(currentTime);
        currentWeather.setWindPower("0".equals(currentWeather.getWindPower()) ? "微风" : currentWeather.getWindPower() + "级");
        return currentWeather;
    }

    public ResponseTemperaturePojo getResponseTemperaturePojo(String temperature) {
        ResponseTemperaturePojo tempPojo = new ResponseTemperaturePojo();
        float centigrade = Float.parseFloat(temperature);
        tempPojo.setC((int) centigrade);
        tempPojo.setF(centigradeToF(centigrade));
        return tempPojo;
    }

    public String getSrcDateStr(ForcastApiPojo apiPojoItem) {
        // change date format
        String srcDateStr = apiPojoItem.getTime();
        Pattern pattern = Pattern.compile("\\b\\w{4}-\\w{2}-\\w{2}");
        Matcher matcher = pattern.matcher(srcDateStr);
        Boolean containMD5 = matcher.find();

        if (containMD5) {
            srcDateStr = matcher.group();
        } else {
            logger.error("Wrong date format: " + srcDateStr);
        }
        String tarDateStr = "2000-01-01T00:00:00+0800";
        SimpleDateFormat srcDateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        SimpleDateFormat tarDateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
//        if (beforeToday(srcDateStr)) {
//            continue;
//        }
        try {
            Date date = srcDateFormatter.parse(srcDateStr);
            tarDateStr = tarDateFormatter.format(date) + "T00:00:00+0800";
        } catch (ParseException e) {
            logger.warn("Parse date: " + srcDateStr + "error", e);
        }
        return tarDateStr;
    }

    private boolean beforeToday(String dateString) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        Date currentDate = null;
        Date compareDate = new Date();
        try {
            compareDate = df.parse(dateString);
            String tmp = df.format(new Date());
            currentDate = df.parse(tmp);
            //LOGGER.info(dateString + "," + tmp);
        } catch (ParseException e) {
            logger.info("Remove date before today");
        }
        if (compareDate.getTime() < currentDate.getTime()) {
            return true;
        }
        return false;
    }

    public ResponsePeriodPojo getResponsePeriodPojo(String weather, String temperature, String windPower, String windDirection) {
        ResponsePeriodPojo pojo = new ResponsePeriodPojo();
        pojo.setForecast(WeatherEnvironment.getWeatherMap().getProperty(
                "WEATHER_" + weather));
        ResponseTemperaturePojo tempPojo = getResponseTemperaturePojo(temperature);
        pojo.setTemperature(tempPojo);

        ResponseWindPojo windPojo = new ResponseWindPojo();
        windPojo.setWindPower(windPower);
        windPojo.setWindDirection(windDirection);
        pojo.setWind(windPojo);
        return pojo;
    }

    private String parseWeatherAqiColor(int aqi) {
        String color = "";
        if (aqi <= 50) {// 0~50
            color = "GREEN";
        } else if (aqi <= 100) {
            color = "YELLOW";
        } else if (aqi <= 150) {
            color = "ORANGE";
        } else if (aqi <= 200) {
            color = "RED";
        } else if (aqi <= 300) {
            color = "PURPLE";
        } else {
            color = "MAROON";
        }
        return color;
    }

    public static String getWeek(Date date) {
        String[] weeks = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (week_index < 0) {
            week_index = 0;
        }
        return weeks[week_index];
    }

    public static String getWindPower(String mo, String en) {
        if (!mo.equals(en)) {
            mo = mo + "-" + en + "级";
        } else {
            if ("0".equals(mo) && ("0".equals(en))) {
                mo = "微风";
            } else {
                mo = mo + "级";
            }
        }

        return mo;
    }
}
