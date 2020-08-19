package com.boss.tools.utils;


import java.util.*;
import java.util.stream.Collectors;

public class ListUtil_sort {

    public static List<Integer> list = Arrays.asList(10,1,6,4,8,7,9,3,2,5);
    public static List<City> cities = null;
    public static List<City> cities2 = null;
    static {
        cities = new ArrayList<City>(){
            {
                add(new City("上海",11));
                add(new City("武汉",55));
                add(new City("武汉",33));
                add(new City("深圳",33));
            }
        };

        cities2 = new ArrayList<City>(){
            {
                add(new City("上海",11,11));
                add(new City("武汉",55,22));
                add(new City("南京",33,55));
                add(new City("深圳",33,44));
            }
        };

    }
    public static void main(String[] args) {
        System.out.println(sort(list));
        System.out.println(reversed(list));
        System.out.println(sortForObject(cities));
        System.out.println(reversedForObject(cities));
        System.out.println(sortForObject2(cities2));
    }

    //list排序 正序
    public static <T> List<T> sort(List<T> list){
        return list.stream().sorted().collect(Collectors.toList());
    }

    //list排序 倒序
    public static List<Integer> reversed(List<Integer> list){
        return list.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
    }

    //根据对象某个属性排序  正序
    public static List<City> sortForObject(List<City> list){
        return list.stream().sorted(Comparator.comparing(City::getTotal)).collect(Collectors.toList());
    }

    //根据对象某个属性排序  倒序
    public static List<City> reversedForObject(List<City> list){
        return list.stream().sorted(Comparator.comparing(City::getTotal).reversed()).collect(Collectors.toList());
    }

    //根据对象两个属性排序  正序
    public static List<City> sortForObject2(List<City> list){
        return list.stream().sorted(Comparator.comparing(City::getTotal).thenComparing(City::getNum)).collect(Collectors.toList());
    }

}
