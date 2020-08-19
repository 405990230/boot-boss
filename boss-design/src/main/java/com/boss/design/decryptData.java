package com.boss.design;


import java.util.Objects;

public class decryptData {

    public static void main(String[] args) {
        System.out.println(AESUtil.decryptData("jOpXiFwqo/yvu6QDnts/TA=="));
        System.out.println(AESUtil.encryptData("13875550678"));
        System.out.println(AESEncryptUtils.decrypt("33fbd850f8fc51eecdfe06118c009f3a"));
        //System.out.println(new City("1",null).getTotal()==0);
        Integer a =1222;
        System.out.println("1".equals(a));
        System.out.println(Objects.equals(1222,a));
    }

//    public static String bulidLog(String... query){
//        String str = "";
//        for (String cs : query) {
//            str+=cs+"，";
//        }
//        return str.substring(0,str.lastIndexOf("，"));
//    }
//
//    public static void main(String[] args) {
//        System.out.println(bulidLog("table","a","b","c"));
//    }
}
