package com.boss.common.news.vo.hu;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ItemsData implements Serializable {

    private static final long serialVersionUID = -41628340351540347L;

    private String title;
    private String timestamp;
    private List<String> paragraphs;
    private List<ImagesData> images;

}
