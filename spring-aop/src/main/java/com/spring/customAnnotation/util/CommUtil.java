package com.spring.customAnnotation.util;

import com.spring.customAnnotation.anno.Entity;


public class CommUtil {
    /**
     * 通过一个对象构建一条查询的sql
     */
    public static String buildQuerySqlForEnntity(Object object){
        Class clazz = object.getClass();
        //set up 1 判断是否加了这个注解
        if(clazz.isAnnotationPresent(Entity.class)){
            //set up 2 得到注解
            Entity entity = (Entity)clazz.getAnnotation(Entity.class);
            //set up 3 调用方法
            String entityName = entity.value();
            System.out.println(entityName);
        }
        System.out.println();
        return "";
    }
}
