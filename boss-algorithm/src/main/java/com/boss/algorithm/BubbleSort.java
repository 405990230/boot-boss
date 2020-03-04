package com.boss.algorithm;

import java.util.Arrays;

/**
 * 冒泡排序
 *
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr={3,44,38,5,47,15,36,26,27,2,46,4,19,50,48};
        System.out.println("排序前数组为："+ Arrays.toString(arr));
        BubbleSort(arr);
        System.out.println("排序后的数组为："+ Arrays.toString(arr));
    }


    /**
     * @param arr
     * @return
     */
    public static int[] BubbleSort(int[] arr){
        for(int i=0;i<arr.length-1;i++){//外层循环控制排序趟数
            for(int j=0;j<arr.length-1-i;j++){//内层循环控制每一趟排序多少次
                if(arr[j]>arr[j+1]){
                    int temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }
            }
        }
        return arr;
    }
}