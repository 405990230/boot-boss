package com.boss.common.news.vo.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PicturesData implements Serializable {

    private static final long serialVersionUID = -3916984744451263702L;

    private String url;
    private String title;

}
