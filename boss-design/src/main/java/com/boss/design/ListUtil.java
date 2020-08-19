package com.boss.design;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ListUtil {

    public static List<String> list = Arrays.asList("1","2","2","3","3","4","4","5","6","7","8");
    public static List<City> cities = null;
    static {
        cities = new ArrayList<City>(){
            {
                add(new City("上海",11));
                add(new City("武汉",22));
                add(new City("武汉",55));
                add(new City("上海",33));
                add(new City("北京",33));
                add(new City("深圳",43));
            }
        };

    }
    public static void main(String[] args) {
        System.out.println(ListUtil.distinctElements(list));
        System.out.println(ListUtil.getNoDuplicateElements(list));
        System.out.println(ListUtil.getDuplicateElements(list));
        System.out.println(ListUtil.getDuplicateElementsForObject(cities));
        System.out.println(ListUtil.getNoDuplicateElementsForObject(cities));
        System.out.println(ListUtil.getElementsAfterDuplicate(cities));
        System.out.println(ListUtil.getDuplicateObject(cities));
        System.out.println(ListUtil.getNoDuplicateObject(cities));
        System.out.println(ListUtil.distinctObject(cities));
    }



    //去重后的集合 [1, 2, 3, 4, 5, 6, 7, 8]
    public static <T> List<T> distinctElements(List<T> list) {
        return list.stream().distinct().collect(Collectors.toList());
    }

    //lambda表达式 去除集合重复的值  [1, 5, 6, 7, 8]
    public static <T> List<T> getNoDuplicateElements(List<T> list) {
        // 获得元素出现频率的 Map，键为元素，值为元素出现的次数
        Map<T, Long> map = list.stream().collect(Collectors.groupingBy(p -> p,Collectors.counting()));
        System.out.println("getDuplicateElements2: "+map);
        return map.entrySet().stream() // Set<Entry>转换为Stream<Entry>
                .filter(entry -> entry.getValue() == 1) // 过滤出元素出现次数等于 1 的 entry
                .map(entry -> entry.getKey()) // 获得 entry 的键（重复元素）对应的 Stream
                .collect(Collectors.toList()); // 转化为 List
    }

    //lambda表达式 查找出重复的集合 [2, 3, 4]
    public static <T> List<T> getDuplicateElements(List<T> list) {
        return list.stream().collect(Collectors.collectingAndThen(Collectors
                            .groupingBy(p -> p, Collectors.counting()), map->{
                                map.values().removeIf(size -> size ==1); // >1 查找不重复的集合；== 1 查找重复的集合
                                List<T> tempList = new ArrayList<>(map.keySet());
                                return tempList;
                            }));
    }

    //利用set集合
    public static <T> Set<T> getDuplicateElements2(List<T> list) {
        Set<T> set = new HashSet<>();
        Set<T> exist = new HashSet<>();
        for (T s : list) {
            if (set.contains(s)) {
                exist.add(s);
            } else {
                set.add(s);
            }
        }
        return exist;
    }

    /**-----------对象List做处理--------------*/

    //查找对象中某个原属重复的  属性集合   [上海, 武汉]
    public static List<String> getDuplicateElementsForObject(List<City> list) {
        return list.stream().collect(Collectors.groupingBy(p -> p.getCity(),Collectors.counting())).entrySet().stream()
                .filter(entry -> entry.getValue() > 1) // >1 查找重复的集合；== 查找不重复的集合
                .map(entry -> entry.getKey())
                .collect(Collectors.toList());
    }

    //查找对象中某个原属未重复的  属性集合   [深圳, 北京]
    public static List<String> getNoDuplicateElementsForObject(List<City> list){
        Map<String,List<City>> map = list.stream().collect(Collectors.groupingBy(City::getCity));
        return map.entrySet().stream().filter(entry -> entry.getValue().size() == 1)
                .map(entry -> entry.getKey()) // 获得 entry 的键（重复元素）对应的 Stream
                .collect(Collectors.toList()); // 转化为 List

    }

    //查找对象中某个原属去重后的集合 [上海, 武汉, 北京, 深圳]
    public static List<String> getElementsAfterDuplicate(List<City> list) {
        return list.stream().map(o->o.getCity()).distinct().collect(Collectors.toList());
    }

    //对象中某个原属重复的 对象集合
    // [[City(city=上海, total=11), City(city=上海, total=33)], [City(city=武汉, total=22), City(city=武汉, total=55)]]
    public static List<List<City>> getDuplicateObject(List<City> list) {
        return list.stream().collect(Collectors.groupingBy(City::getCity)).entrySet().stream()
                .filter(entry -> entry.getValue().size() > 1) // >1 查找重复的集合；== 查找不重复的集合
                .map(entry -> entry.getValue())
                .collect(Collectors.toList());
    }

    //对象中某个原属未重复 对象集合
    //[[City(city=深圳, total=43)], [City(city=北京, total=33)]]
    public static List<City> getNoDuplicateObject(List<City> list) {
        List<City> cities = new ArrayList<>();
        list.stream().collect(Collectors.groupingBy(City::getCity)).entrySet().stream()
                    .filter(entry -> entry.getValue().size() ==1) //>1 查找重复的集合；== 查找不重复的集合;
                    .map(entry -> entry.getValue())
                    .forEach(p -> cities.addAll(p));
        return cities;
    }


    //根据对象的某个原属去重后的 对象集合
    //[City(city=上海, total=11), City(city=武汉, total=22), City(city=北京, total=33), City(city=深圳, total=43)]
    public static List<City> distinctObject(List<City> list) {
        return list.stream().filter(distinctByKey(City::getCity)).collect(Collectors.toList());
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return object -> seen.putIfAbsent(keyExtractor.apply(object), Boolean.TRUE) == null;
    }

}
