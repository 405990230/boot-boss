package com.boss.common.news.vo.hu;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ImagesData implements Serializable {

    private static final long serialVersionUID = 233399766672497437L;

    private PreviewData preview;
    private ThumbData thumb;
    private FullData full;

}
