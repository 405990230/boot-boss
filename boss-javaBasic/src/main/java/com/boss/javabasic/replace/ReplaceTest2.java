package com.boss.javabasic.replace;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class ReplaceTest2 {
    public static void main(String[] args) {
        String heading = "??B? 399001 日K";
        //heading = heading.replaceAll("\\?{2,8}","深证成指");
        int i = heading.indexOf("?");
        int j = heading.lastIndexOf("?");
        heading = heading.substring(0, i) + "深证成指" + heading.substring(j + 1);
        System.out.println(heading);

        HashMap<String, String> map = new HashMap<>();
        map.put("key", "value");
        ConcurrentHashMap<String, String> cMap = new ConcurrentHashMap<>();
        cMap.put("key", "value");
    }

}
