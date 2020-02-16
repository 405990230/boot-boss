package com.boss.redis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class R implements Serializable{
    private Integer code;
    private Object data;
    private String msg;
}
