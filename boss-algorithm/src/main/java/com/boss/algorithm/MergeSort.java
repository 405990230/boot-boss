package com.boss.algorithm;

import java.util.Arrays;

/**
 * 归并排序(Merge Sort)
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] arr={3,44,38,5,47,15,36,26,27,2,46,4,19,50,48};
        mergeSort(arr, 0, arr.length-1);
        System.out.println("排序后的数组为："+ Arrays.toString(arr));
    }

    /**
     * 归并排序
     * 1.划分的过程 mid
     * 2.归并的过程 merge
     * 3.递归的过程 递归的实质
     */

    private static void mergeSort(int[] arr,int left,int right) {
        if(right <= left || arr.length<2) {
            return;
        }
        int mid=left+((right-left)>>1);//	int mid=left+(right-left)/2;
        mergeSort(arr,left,mid);
        mergeSort(arr,mid+1,right);
        merge(arr,left,mid,right);

    }

    /**
     * 合并
     */
    private static void merge(int[] arr, int left, int mid, int right) {
        int temp [] =new int [right-left+1];//中间数组
        int p1=left,p2=mid+1,index=0;

        while(p1<=mid && p2<=right){
            temp[index++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while(p2 <= right)  temp[index++]=arr[p2++];

        while(p1 <= mid)  temp[index++]=arr[p1++];

        for(int i=0;i<temp.length;i++) {
            arr[left++]=temp[i];
        }
    }
}