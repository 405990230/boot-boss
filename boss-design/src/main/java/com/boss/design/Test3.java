package com.boss.design;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Integer.sum;
import static java.util.stream.Collectors.groupingBy;

public class Test3 {
    public static  List<City> cities = null;
    static {
        cities = new ArrayList<City>(){
            {
                add(new City("上海",11));
                add(new City("武汉",22));
                add(new City("上海",33));
                add(new City("北京",33));
                add(new City("上海",33));
                add(new City("武汉",33));
                add(new City("南京",33));
                add(new City("广州",33));
            }
        };

    }

    static City city = new City("全局变量",11);

    public static void main(String[] args) {
        List<String> list2 =cities.stream().
                collect(Collectors.groupingBy(city->city.getCity(),Collectors.counting()))
                .entrySet().stream()
                .filter(entry->entry.getValue()>1)
                .map(entry->entry.getKey())
                .collect(Collectors.toList());

        System.out.println(list2.toString());
        ArrayList<City> list = new ArrayList<>();
        HashMap<String,Integer> map = new HashMap();
        for(City city : Test3.cities){
            if(map.get(city.getCity())==null){
                map.put(city.getCity(),city.getTotal());
            } else {
                map.put(city.getCity(),map.get(city.getCity())+city.getTotal());
            }
        }


        System.out.println(map.toString());






        BigDecimal bigDecimal = new BigDecimal(2362133927.8900001049041748046875);
        BigDecimal bigDecimal1 = new BigDecimal(1682613415.65999996662139892578125);
        System.out.println(bigDecimal1.divide(bigDecimal,2, RoundingMode.HALF_UP));
        BigDecimal result = bigDecimal1.divide(bigDecimal,2, RoundingMode.HALF_UP);
        if(result.compareTo(new BigDecimal(0.75))>-1||result.compareTo(new BigDecimal(0.25))<1){
            System.out.println("发送");
        }


        ThreadLocal local = new ThreadLocal();
        local.set("1");
        local.remove();

        Integer i = 1;
        String str ="a";
        method(1,str);
        System.out.println(i+"--"+str);

        City city2 = new City("main全局变量",11);

        setCity(city);
        System.out.println("-------"+city.getCity());
        setCity(city2);
        System.out.println("-------"+city2.getCity());



        String[] arr1 = {"a", "b", "c", "d", "e", "f"};
        List<String> listA = new ArrayList<>(Arrays.asList(arr1));

        String[] arr2 = {"d", "e", "f", "g", "h"};
        List<String> listB = new ArrayList<>(Arrays.asList(arr2));

        Set<String> set2 = new HashSet<>(listA);
        set2.addAll(listB);
        //List<String> list = new ArrayList<>(set2);
        System.out.println(list);

        List<String> collect = Stream.of(listA, listB)
                .flatMap(Collection::stream)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(collect);

    }


    private static void method(Integer i ,String str){
        i=i+3;
        str =str+"b";
    }

    public static void setCity(City c){
        c.setCity("局部变量");
        System.out.println(c.getCity());
    }
}
