package com.boss.tools.utils;


import java.util.*;
import java.util.stream.Collectors;

public class ListUtil_sum {

    public static List<Integer> list = Arrays.asList(10,1,6,4,8,7,9,3,2,5);
    public static List<String> strList = Arrays.asList("10","1","6","4");
    public static List<City> cities = null;
    public static Map<String,Integer> cityMap = null;
    static {
        cities = new ArrayList<City>(){
            {
                add(new City("上海",11));
                add(new City("武汉",55));
                add(new City("武汉",45));
                add(new City("深圳",33));
            }
        };
        cityMap = new HashMap<>();
        cityMap.put("武汉",55);
        cityMap.put("上海",11);
    }
    public static void main(String[] args) {
        System.out.println(calculation(list));
        calculation2(cities);
        listToMap(cities);
        mapToList(cityMap);
        stringToList("上海、武汉");
        joinStringValueByList(cities);
        joinStringValueByList2(strList);
        System.out.println(filter(cities));
    }


    //根据对象某个属性求各自值
    ///IntSummaryStatistics{count=4, sum=132, min=11, average=33.000000, max=55}
    public static IntSummaryStatistics calculation(List<Integer> list){
        IntSummaryStatistics stat = list.stream().collect(Collectors.summarizingInt(p -> p));
        System.out.println("max:"+stat.getMax());
        System.out.println("min:"+stat.getMin());
        System.out.println("sum:"+stat.getSum());
        System.out.println("count:"+stat.getCount());
        System.out.println("average:"+stat.getAverage());
        return stat;
    }

    //根据对象某个属性求各自值
    public static void calculation2(List<City> list){
        System.out.println("sum="+ list.stream().mapToInt(City::getTotal).sum());
        System.out.println("max="+ list.stream().mapToInt(City::getTotal).max().getAsInt());
        System.out.println("min="+ list.stream().mapToInt(City::getTotal).min().getAsInt());
        System.out.println("ave="+ list.stream().mapToInt(City::getTotal).average().getAsDouble());
    }

    //功能描述 List转map
    public static void listToMap(List<City> list){
        //用 (k1,k2)->k1 来设置，如果有重复的key,则保留key1,舍弃key2
        Map<String,City> map = list.stream().collect(Collectors.toMap(City::getCity,city -> city, (k1, k2) -> k1));
        map.forEach((k,v) -> System.out.println("k=" + k + ",v=" + v));
    }


    //对象某个属性 等于特定值的累加
    public static void calculation11(List<City> list){
        Map<String, IntSummaryStatistics> intSummaryStatistics = list.stream().
                collect(Collectors.groupingBy(i -> i.getCity(), Collectors.summarizingInt(City::getTotal)));
        System.out.println("-4-->" + intSummaryStatistics);
        System.out.println("-5-->" + intSummaryStatistics.get("武汉").getSum());
    }

    //功能描述 map转list
    public static void mapToList(Map<String,Integer> map){
        List<City> list = map.entrySet().stream().map(key -> new City(key.getKey(),key.getValue())).collect(Collectors.toList());
        System.out.println(list);
        list.forEach(bean -> System.out.println(bean.getCity() + "," + bean.getTotal()));
    }

    //功能描述 字符串转list
    public static void stringToList(String str){
        //不需要处理
        //<String> list = Arrays.asList(str.split(","));
        //需要处理
        List<String> list = Arrays.asList(str.split(",")).stream().map(string -> String.valueOf(string)).collect(Collectors.toList());
        list.forEach(string -> System.out.println(string));
    }

    //功能描述 姓名以逗号拼接
    public static void joinStringValueByList(List<City> list){
        System.out.println(list.stream().map(City::getCity).collect(Collectors.joining(",")));
    }

    //功能描述 姓名以逗号拼接
    public static void joinStringValueByList2(List<String> list){
        //方式一
        System.out.println(String.join(",", list));
        //方式二
        System.out.println(list.stream().collect(Collectors.joining(",")));
    }

    //功能描述 过滤
    public static List<City> filter(List<City> list){
        return list.stream().filter(a -> a.getTotal()>44).collect(Collectors.toList());
    }

}
