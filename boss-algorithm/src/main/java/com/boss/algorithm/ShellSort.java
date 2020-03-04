package com.boss.algorithm;

import java.util.Arrays;

/**
 * 希尔排序(Shell Sort)
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] arr={8,9,1,7,2,3,5,4,6,0};
        shellSort(arr);
    }


    /**
     * 希尔排序函数
     * @param array
     * @return
     */
    public static int[] shellSort(int[] array) {
        // 定义希尔增量。
        int temp, gap = array.length / 2;
        // gap缩小到0的时候就退出循环。
        while (gap > 0) {
            // 每组进行直接插入排序。
            for (int i = gap; i < array.length; i++) { // i 代表待插入元素的索引。
                temp = array[i];
                int preIndex = i - gap;// preIndex 代表i的上一个元素，相差一个增量gap。

                // preIndex < 0 时退出循环，说明 preIndex 是最小的元素的索引值。
                // 或者 arr[preIndex] <= value 时退出循环，说明 preIndex 是比value小的元素的索引值。
                while (preIndex >= 0 && array[preIndex] > temp) {
                    array[preIndex + gap] = array[preIndex];
                    preIndex -= gap;
                }
                array[preIndex + gap] = temp;
            }
            // 缩小增量。
            gap /= 2;
            System.out.println("交换后："+ Arrays.toString(array));

        }
        return array;
    }
}
