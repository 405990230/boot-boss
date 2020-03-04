package com.boss.algorithm;

/**
 * 桶排序(Bucket Sort)
 */

import java.util.Arrays;

public class BuketSort {

    public static void main(String[] args) {
        int[] arr={3,44,38,5,47,15,36,26,27,2,46,4,19,50,48};
        bucketSort(arr,3);
        System.out.println("排序后的数组为："+ Arrays.toString(arr));
    }

    public static void bucketSort(int [] a,int bucketSize){
        if (a.length<2)return;

        int minIndex = a[0];
        int maxIndex = a[1];
        for (int i=0;i<a.length;i++){
            if (maxIndex<a[i]) maxIndex = a[i];
            else if (minIndex>a[i]) minIndex = a[i];
        }

        int bucketCount = (maxIndex-minIndex)/bucketSize+1;
        int [][] buckets = new int[bucketCount][bucketSize];
        int [] indexArr = new int[bucketCount];

        for (int i=0;i<a.length;i++){
            int bucketIndex = (a[i]-minIndex)/bucketCount;
            if (indexArr[bucketIndex]==buckets[bucketIndex].length){
                ensureCapacity(buckets,bucketIndex);
            }
            buckets[bucketIndex][indexArr[bucketIndex]++] = a[i];
        }

        int k = 0;
        for (int i=0;i<buckets.length;i++){
            if (indexArr[i]==0)continue;
            quickSort(buckets[i],0,indexArr[i]-1);
            for (int j = 0; j<indexArr[i];j++){
                a[k++] = buckets[i][j];
            }
        }

    }

    private static void quickSort(int[] a, int p, int r) {
        if (p>=r)return;
        int q = partition(a,p,r);
        quickSort(a,p,q-1);
        quickSort(a,q+1,r);
    }

    private static int partition(int[] a, int p, int r) {
        int pivot = a[r];
        int i = p;
        for (int j=p;j<r;j++){
            if (a[j]<=pivot){
                swap(a,i,j);
                i++;
            }
        }
        swap(a,i,r);
        return i;
    }

    private static void swap(int[] a, int i, int j) {
        if (i==j)return;
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    private static void ensureCapacity(int[][] buckets, int bucketIndex) {
        int [] tmpArr = buckets[bucketIndex];
        int [] newArr = new int[tmpArr.length*2];
        for (int j=0;j<tmpArr.length;j++){
            newArr[j] = tmpArr[j];
        }
        buckets[bucketIndex] = newArr;
    }
}
