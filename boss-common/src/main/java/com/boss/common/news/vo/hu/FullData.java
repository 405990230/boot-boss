package com.boss.common.news.vo.hu;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FullData implements Serializable {

    private static final long serialVersionUID = 8733502171188649524L;

    private String link;
    private int x;
    private int y;

}
