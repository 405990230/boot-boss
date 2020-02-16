package com.boss.javabasic.date;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {



    /**判断是否超过hours小时
     *
     * @param date
     * @param hours
     * @return boolean
     * @throws Exception
     */
    public static boolean judgmentDate(String date,int hours) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date start = sdf.parse(date);
        Date end = new Date();
        long cha = end.getTime() - start.getTime();
        if(cha<0){
            return false;
        }
        double result = cha * 1.0 / (1000 * 60 * 60);
        if(result<=hours){
            return false;
        }else{
            return true;
        }
    }


    public static void main(String[] args) throws Exception{
        int hours = 1;
        if(judgmentDate("2019-12-10T16:40:24",hours)){
            System.out.println("数据超过"+hours+"小时未更新");
        }
    }
}
