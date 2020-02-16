package com.boss.info.bmw.pojo.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;
@Data
@JsonInclude(value= JsonInclude.Include.NON_NULL)
public class WeatherApiPojo {
    private String code;
    private List<DataApiPojo> data;
}
