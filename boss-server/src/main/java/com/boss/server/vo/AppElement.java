package com.boss.server.vo;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.List;

@Slf4j
@Data
public class AppElement implements Serializable {

    private static final long serialVersionUID = -4344890662043594897L;

    private String url;
    private String policyUrl;
    private String dueDate;
    private Integer size;
    private String hash;
    private String version;
    private List<AppAttributes> attributes;

}
