package com.boss.javabasic.replace;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class ReplaceTest3 {
    public static void main(String[] args) {
        String heading = "6G?? 399001 日K";
        //heading = heading.replaceAll("\\?{2,8}","深证成指");
        //int i = heading.indexOf("?");
        int j = heading.lastIndexOf("?");
        heading = "" + "6G概念" + heading.substring(j + 1);
        System.out.println(heading);
        heading = "??B? 399001 日K";
        heading = "6G概念" + heading.substring(j + 1);
        System.out.println(heading);

        heading = "??300 399001 日K";

        System.out.println(get(heading));
    }

    public static String get(String heading){
        String heading2[] = heading.split(" ");
        heading2[0] = "沪深300";
        String heading3 = "";
        for(String str : heading2){
            heading3 += str+" ";
        }
        return heading3;
    }
}
