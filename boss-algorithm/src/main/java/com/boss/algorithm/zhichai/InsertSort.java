package com.boss.algorithm.zhichai;

public class InsertSort {
    //核心算法
    //A是待排序数组 n是表长
    public void insertSort(int[] A,int n ){
        int i,j;
        for(i=2;i<=n;i++){
            //待插入的数据A[i]大于A[i-1],则直接插入，反之则往前进行比较，
            //选择合适的插入的位置
            if(A[i]<A[i-1]){
                //A[0]仅是哨兵
                A[0]=A[i];
                //边查找边移动位置
                for(j=i-1;A[i]<A[j];j--){
                    A[j+1]=A[j];
                }
                //找到插入位置
                A[j+1]=A[0];
            }
        }
    }

    //测试
    public static void main(String[] args) {
        int[] A={0,2,12,6,8,11,31,18,27};
        int n =A.length-1;
        InsertSort insertSort = new InsertSort();
        insertSort.insertSort(A, n);
        for(int i=0;i<=n;i++){
            System.out.println(A[i]);
        }
    }
}
