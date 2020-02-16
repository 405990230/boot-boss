package com.boss.info.bmw.pojo.api;

import com.boss.info.bmw.pojo.json.ResponseTemperaturePojo;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(value= JsonInclude.Include.NON_NULL)
public class CurrentWeatherPojo implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String time;
    private String weather;
    private String eweather;
    private String temperature;
    private String windDirection;
    private String windPower;
    private String humidity;
    private String currentTime;
    private String sunRiseTime;
    private String sunSetTime;
    private ResponseTemperaturePojo realTimeTemperature;

}
