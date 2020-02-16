package com.boss.common.api;

import lombok.Data;

@Data
public class JsonResult<T> {
    private String code;
    private String status;
    private String msg;
    private T data;

}
