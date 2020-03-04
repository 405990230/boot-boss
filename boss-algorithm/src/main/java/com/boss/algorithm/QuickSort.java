package com.boss.algorithm;

import java.util.Arrays;

/**
 * 快速排序(Quick Sort)
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] arr={3,7,2,9,1,4,6,8,10,5};
        QuickSort(arr,0,arr.length-1);
        System.out.println("交换后："+ Arrays.toString(arr));
    }
    /**
     * 快速排序方法
     * @param arr
     * @param start
     * @param end
     * @return
     */
    public static int[] QuickSort(int[] arr, int start, int end) {
        if(end <= start ) return arr;
        // 最终这个smallIndex的值，是基准线的值
        int smallIndex = partition(arr, start, end);
        if (smallIndex > start)
            QuickSort(arr, start, smallIndex - 1);
        if (smallIndex < end)
            QuickSort(arr, smallIndex + 1, end);
        return arr;
    }
    /**
     * 快速排序算法——partition
     * @param arr
     * @param start
     * @param end
     * @return
     */
    // 分区操作函数
    public static int partition(int[] arr, int start, int end) {
        // 基准值关键词
        int pivot = (int) (start + Math.random() * (end - start + 1));
        // 将基准值移到数组的最后
        swap(arr, pivot, end);
        // 比基准数大的索引角标，用于换位置
        int smallindex = start - 1;
        for (int i = start; i <= end; i++) {
            if (arr[i] <= arr[end]) { //将第i个元素与基准值进行了一个对比
                smallindex++;
                if (i > smallindex) {
                    // 交换位置的util
                    swap(arr, i, smallindex);
                }
            }
        }
        return smallindex;
    }


    /**
     * 交换数组内两个元素
     * @param array
     * @param i
     * @param j
     */
    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}

