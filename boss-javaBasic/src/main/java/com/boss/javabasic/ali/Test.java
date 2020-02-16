package com.boss.javabasic.ali;

import com.alibaba.druid.sql.visitor.functions.If;

import javax.swing.text.html.parser.Entity;
import java.util.HashMap;
import java.util.Map;

/**
 * 1. 统计一篇文章中每个单词出现的次数。
 * 例如输入：
 * If not now, when? if not me, who?
 * 输出
 * if 2
 * not 2
 * now 1
 * when 1
 * me 1
 * who 1
 */
public class Test {

    public static void main(String[] args) {
        String str = "If not now, when? if not me, who?";
        praseString(str);
        int i = 1/0;
    }


    public static Map<String,Integer> praseString(String str){
        String[] strs = str.split("[,?' ']");
        Map<String,Integer> map = new HashMap<>();

        for(String s : strs){
            if("".equals(s)) continue;

            if(!map.containsKey(s)){
                map.put(s,1);
            }else{
                map.put(s,map.get(s)+1);
            }
        }
        for(Map.Entry<String,Integer> a:map.entrySet()){
            System.out.println(a.getKey()+" : "+a.getValue());
        }

        return map;
    }
}
