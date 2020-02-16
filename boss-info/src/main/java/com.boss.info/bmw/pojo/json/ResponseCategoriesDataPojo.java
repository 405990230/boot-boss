package com.boss.info.bmw.pojo.json;

import lombok.Data;

import java.util.List;

@Data
public class ResponseCategoriesDataPojo {

    private String language;
    private List<String> names;
}
