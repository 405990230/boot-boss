package com.boss.javabasic.replace;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class ReplaceTest {
    public static void main(String[] args) {
        String heading = "???? 399001 日K";
        heading = heading.replaceAll("\\?{1,8}", "深证成指");
        System.out.println(heading);

        HashMap<String, String> map = new HashMap<>();
        map.put("key", "value");
        ConcurrentHashMap<String, String> cMap = new ConcurrentHashMap<>();
        cMap.put("key", "value");
    }

}
