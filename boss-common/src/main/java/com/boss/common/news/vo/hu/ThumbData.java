package com.boss.common.news.vo.hu;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ThumbData implements Serializable {

    private static final long serialVersionUID = 7671649241456197411L;

    private String link;
    private Integer x;
    private Integer y;

}
