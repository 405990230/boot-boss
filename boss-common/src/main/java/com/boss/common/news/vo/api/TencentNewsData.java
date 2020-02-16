package com.boss.common.news.vo.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TencentNewsData implements Serializable {

    private static final long serialVersionUID = 1529816911764213764L;

    private Integer status;
    private String msg;
    private Integer newsSum;
    private String currentPage;
    private String itemSize;
    private String itemClass;
    private List<TencentItem> item;

}
