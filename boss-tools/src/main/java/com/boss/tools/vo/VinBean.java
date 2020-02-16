package com.boss.tools.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class VinBean {
    private String datetime;
    private Integer Count;
}
