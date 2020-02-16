package com.spring.customAnnotation.test;

import com.spring.customAnnotation.entity.CityEntity;
import com.spring.customAnnotation.util.CommUtil;

public class Test {
    /**
     * xml是用来描述数据
     * @param args
     */
    public static void main(String[] args) {
        CityEntity cityEntity = new CityEntity();
        cityEntity.setId("1");
        cityEntity.setName("test");
        String sql = CommUtil.buildQuerySqlForEnntity(cityEntity);
        //select * from city where id = "1" and name ="test"
        System.out.println(sql);
    }
}
