package com.boss.design;

import lombok.Data;

@Data
public class City {
    private String city;
    private Integer total;

    public City(String city,Integer total){
        this.city =city;
        this.total =total;
    }
    public City(){}
}
