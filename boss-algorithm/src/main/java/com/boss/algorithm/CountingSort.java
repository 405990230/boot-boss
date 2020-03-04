package com.boss.algorithm;

import java.util.Arrays;

/**
 * 计数排序
 */
public class CountingSort {
    public static void main(String[] args) {
        int[] arr={2,3,8,7,1,2,2,2,7,3,9,8,2,1,4,2,4,6,13,2};
        CountingSort(arr);
        System.out.println("排序后的数组为："+ Arrays.toString(arr));
    }
    /**
     * 计数排序
     *
     * @param array
     * @return
     */
    public static int[] CountingSort(int[] array) {
        if (array.length == 0) return array;
        int bias, min = array[0], max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max)
                max = array[i];
            if (array[i] < min)
                min = array[i];
        }
        bias = 0 - min;
        int[] bucket = new int[max - min + 1];
        Arrays.fill(bucket, 0);
        for (int i = 0; i < array.length; i++) {
            bucket[array[i] + bias]++;
        }
        int index = 0, i = 0;
        while (index < array.length) {
            if (bucket[i] != 0) {
                array[index] = i - bias;
                bucket[i]--;
                index++;
            } else
                i++;
        }
        return array;
    }
}
