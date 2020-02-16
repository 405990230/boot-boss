package com.boss.algorithm.maopao;

import java.util.Arrays;

/**
 * Created by qxr4383 on 2019/5/8.
 * 冒泡算法
 */
public class MaoPao {
    public static void main(String[] args) {
        int[] a = {49, 38, 65, 97, 27, 78, 34, 12, 64, 1};
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = 0; j < a.length - i - 1; j++) {
                if (a[j] > a[j + 1]) {
                    int tem = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = tem;
                }
            }
        }
        System.out.println(Arrays.toString(a));
    }
}
