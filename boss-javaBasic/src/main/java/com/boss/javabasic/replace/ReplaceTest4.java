package com.boss.javabasic.replace;


public class ReplaceTest4 {
    public static void main(String[] args) {
        String str = "https://omcaliimpcedst.chinacloudsites.cn/100x100,q70/https://img.meituan.net/msmerchant/9858a136800708a696e8d4e34cc45f27682154.jpg";
        str = str.substring(str.lastIndexOf("http"));
        System.out.println(str);
    }

}
