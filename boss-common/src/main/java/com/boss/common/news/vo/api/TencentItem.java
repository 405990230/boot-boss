package com.boss.common.news.vo.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TencentItem implements Serializable {

    private static final long serialVersionUID = 5718582642797319948L;

    private String newsId;
    private String title;
    private String abstracts;
    private String pubDate;
    private String source;
    private String picCount;
    private String smallpicurl;
    private String newsType;
    private String author;
    private String content;
    private List<PicturesData> pictures;

}
