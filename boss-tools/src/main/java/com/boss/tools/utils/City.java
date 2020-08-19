package com.boss.tools.utils;

import lombok.Data;

@Data
public class City {
    private String city;
    private Integer total;
    private Integer num;

    public City(String city, Integer total){
        this.city =city;
        this.total =total;
    }

    public City(String city, Integer total,Integer num){
        this.city =city;
        this.total =total;
        this.num = num;
        this.num = num;
    }
    public City(){}
}
