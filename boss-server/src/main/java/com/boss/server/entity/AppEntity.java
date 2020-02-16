package com.boss.server.entity;

import lombok.Data;

@Data
public class AppEntity {

    private String appid;

    private String url;

    private String policyurl;

    private String hash;

    private Integer size;

    private String version;

    private String attributes;

    private byte[] target;


}