package com.boss.design;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test1 {
    public static void main(String[] args) {
        List list = new ArrayList(Arrays.asList("a","b","c"));
        System.out.println(list.indexOf("b"));

    }
}
//    public static void main(String[] args) throws Exception{
////        String str = "湖北省武汉市";
////        System.out.println(str.indexOf("1"));
////        System.out.println(str.split("省")[0]);
////
////        String s = "111";
////        System.out.println(s.split(" ")[0]);
//
//        int second = 110;
//        int count = 23;
//        int num = count;
//        System.out.println();
//        for(int i = 0;i<num;i++){
//            if(count==0){
//                System.out.println("发送结束");
//                return;
//            }
//            long sleepTime = (long)((double) second/num*1000);
//            System.out.println(sleepTime);
//            Thread.sleep(sleepTime);
//            if(i!=num-1){
//                count-=1;
//                System.out.println("发送"+1);
//            } else {
//                System.out.println("发送"+count);
//                count-=count;
//            }
//            System.out.println("发送次数："+i);
//        }
//        System.out.println(count);
//    }
//}

//    public static void main(String[] args) {
//        try {
//            String str = "河北省、山西省、辽宁省、吉林省、黑龙江省、江苏省、浙江省、安徽省、福建省、江西省、山东省、河南省、湖北省、湖南省、广东省、海南省、四川省、贵州省、云南省、陕西省、甘肃省、青海省、台湾省";
//            str+= "、"+"内蒙古自治区、广西壮族自治区、西藏自治区、宁夏回族自治区、新疆维吾尔自治区";
//            str+= "、"+"北京市、天津市、上海市、重庆市";
//            str+= "、"+"香港特别行政区、澳门特别行政区";
//            List<String> list = new ArrayList<>(Arrays.asList(str.split("、")));
//            System.out.println(list.toString());
//            //System.out.println(IPSeeker.INSTANCE.getCountry("106.14.160.27"));
//        } catch (Exception e) {
//            log.error("BehaviorBeanUtils getCity error", e);
//        }
//    }