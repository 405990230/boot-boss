package com.boss.design;

import org.apache.poi.ss.formula.functions.T;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class TestZj {
    public static void main(String[] args) {
        List<ZjDepartmentEquityVO> list = new ArrayList<>();
        list.add(new ZjDepartmentEquityVO("2020-04-18","00000",new BigDecimal(14000)));
        list.add(new ZjDepartmentEquityVO("2020-04-14","11111",new BigDecimal(14011)));
        list.add(new ZjDepartmentEquityVO("2020-04-15","22222",new BigDecimal(14022)));
        list.add(new ZjDepartmentEquityVO("2020-04-14","33333",new BigDecimal(14033)));
        list.add(new ZjDepartmentEquityVO("2020-04-15","11111",new BigDecimal(15011)));
        list.add(new ZjDepartmentEquityVO("2020-04-20","22222",new BigDecimal(15022)));
        list.add(new ZjDepartmentEquityVO("2020-04-15","33333",new BigDecimal(15033)));
        list.add(new ZjDepartmentEquityVO("2020-04-16","00000",new BigDecimal(16000)));
        list.add(new ZjDepartmentEquityVO("2020-04-16","22222",new BigDecimal(16022)));
        list.add(new ZjDepartmentEquityVO("2020-04-16","33333",new BigDecimal(16033)));
        list.add(new ZjDepartmentEquityVO("2020-04-17","00000",new BigDecimal(17000)));
        list.add(new ZjDepartmentEquityVO("2020-04-17","11111",new BigDecimal(17011)));
        list.add(new ZjDepartmentEquityVO("2020-04-17","33333",new BigDecimal(17033)));




        //Map<String,List<BigDecimal>> dateGroupMap = list.stream().collect(Collectors.groupingBy(ZjDepartmentEquityVO::getGroupName, Collectors.mapping(ZjDepartmentEquityVO::getTodayBalance, Collectors.toList())));
        List<String> dateList = list.stream().map(ZjDepartmentEquityVO::getDate).distinct().sorted().collect(Collectors.toList());
        //Map<String,List<String>> dateGroupMap = list.stream().collect(Collectors.groupingBy(ZjDepartmentEquityVO::getDate, Collectors.mapping(ZjDepartmentEquityVO::getGroupName, Collectors.toList())));
        List<String> groupNameName = list.stream().map(ZjDepartmentEquityVO::getGroupName).distinct().collect(Collectors.toList());
        Map<String,List<BigDecimal>> groupMap = new LinkedHashMap<>();
        int index = groupNameName.size()<=10?groupNameName.size():10;
        for(int i=0;i<index;i++){
            List<BigDecimal> todayBalanceList = new ArrayList<>(Collections.nCopies(dateList.size(),new BigDecimal(0)));
            for(ZjDepartmentEquityVO vo : list){
                if(groupNameName.get(i).equals(vo.getGroupName())) {
                    todayBalanceList.set(dateList.indexOf(vo.getDate()),vo.getTodayBalance());
                }
            }
            groupMap.put(groupNameName.get(i),todayBalanceList);
        }
        System.out.println(groupMap);
    }
}
