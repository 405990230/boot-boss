package com.boss.server.entity;

import lombok.Data;

@Data
public class WebsitesEntity {
    private Integer id;

    private String name;

    private String url;

    private Integer alexa;

    private String country;

}