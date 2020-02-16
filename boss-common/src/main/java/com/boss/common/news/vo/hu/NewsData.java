package com.boss.common.news.vo.hu;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NewsData implements Serializable {

    private static final long serialVersionUID = 923617461492108380L;

    private String oid;
    private List<ItemsData> items;


}
