package com.boss.algorithm;

import java.util.Arrays;

/**
 * 插入排序(Insertion Sort)
 */
public class InsertionSort {
    public static void main(String[] args) {
        int[] arr={3,44,38,5,47,15,36,26,27,2,46,4,19,50,48};
        System.out.println("交换之前：" + Arrays.toString(arr));
        insertionSort(arr);
        System.out.println("交换后："+ Arrays.toString(arr));
    }

    /**
     * 插入排序
     * @param array
     * @return
     */
    public static int[] insertionSort(int[] array) {
        // 基本情况下的数组可以直接返回
        if(array == null || array.length <= 1) {
            return array;
        }
        int current;
        for (int i = 0; i < array.length - 1; i++) {
            // 第一个数默认已排序，从第二个数开始
            current = array[i + 1];
            // 前一个数的下标
            int preIdx = i;
            // 拿当前的数与之前已排序序列逐一往前比较，如果比较的数据比当前的大，就把该数往后挪一步
            while (preIdx >= 0 && current < array[preIdx]) {
                array[preIdx + 1] = array[preIdx];
                preIdx--;
            }
            // while循环跳出说明找到了位置
            array[preIdx + 1] = current;
        }
        return array;
    }
}
