package com.boss.server.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class AppAttributes implements Serializable {

    private static final long serialVersionUID = -556278966138616055L;

    private String key;
    private String value;

}
