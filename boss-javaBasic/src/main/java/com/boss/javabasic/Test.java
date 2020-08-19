package com.boss.javabasic;

public class Test {
    public int[] searchRange(int[] nums, int target) {
        int left = 0;
        int  right = nums.length -1;
        int leftIndex = -1;
        int rightIndex = -1;
        boolean isLeft = true;
        boolean isRight = true;
        for (int i = 0; i < nums.length; i++) {
            if (nums[left] == target && isLeft){
                leftIndex = left;
                isLeft = false;
            }
            if (nums[right] == target && isRight){
                rightIndex = right;
                isRight = false;
            }
            if (leftIndex > 0 && rightIndex > 0){
                break;
            }
            left++;
            right--;
        }
        return new int[] {leftIndex,rightIndex};
    }

    public static void main(String[] args) {
        String answer = "1_A,2_B,3_A,4_R,5_D,6_A,7_B,8_A,9_AC,10_B,11_B,12_A,13_C,14_A,15_A,16_A,17_D,18_A,19_A,20_C";
        System.out.println(answer.substring(answer.indexOf("4")+2,answer.indexOf("4")+3));
    }
}
