package com.boss.common.news.vo.hu;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PreviewData implements Serializable {

    private static final long serialVersionUID = 5691294265858085869L;

    private String link;
    private int x;
    private int y;

}
