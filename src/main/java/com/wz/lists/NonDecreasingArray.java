package com.wz.lists;

/**
 * Given an array nums with n integers, your task is to check if it could become non-decreasing by modifying at most 1 element.
 * We define an array is non-decreasing if nums[i] <= nums[i + 1] holds for every i (0-based) such that (0 <= i <= n - 2).
 *
 * Example 1:
 * Input: nums = [4,2,3]
 * Output: true
 * Explanation: You could modify the first 4 to 1 to get a non-decreasing array.
 *
 * Example 2:
 * Input: nums = [4,2,1]
 * Output: false
 * Explanation: You can't get a non-decreasing array by modify at most one element.
 */
public class NonDecreasingArray {
    public static void main(String[] args) {
        int[] nums = new int[]{4, 2, 3};
        System.out.println(checkPossibility(nums));

        nums = new int[]{4, 2, 1};
        System.out.println(checkPossibility(nums));

        nums = new int[]{3, 4, 2, 3};
        System.out.println(checkPossibility(nums));
    }

    /**
     * [4]， 2， 3
     * -1， [4]，2， 3
     * 2，   3， 3，[2]，4
     * 分析上面三个例子可以发现，当后面的数字小于前面的数字产生后，有时候需要修改前面较大的数字(比如前两个例子需要修改4)，
     * 有时候却要修改后面较小的那个数字(比如前第三个例子需要修改2)
     * 判断修改那个数字其实跟再前面一个数的大小有关系
     * 如果再前面的数不存在，比如例1，4前面没有数字，直接修改前面的数字为当前的数字2即可
     * 而当再前面的数字存在，并且小于当前数时，比如例2，-1小于2，还是需要修改前面的数字4为当前数字2
     * 如果再前面的数大于当前数，比如例3，3大于2，需要修改当前数2为前面的数3
     * 用变量count记录修改次数，如果count>=2则返回false，否则返回true
     */
    public static boolean checkPossibility(int[] nums) {
        if (nums.length <= 2) {
            return true;
        }

        int count = 0;
        for (int i = 1; i < nums.length; i++) {
            if (count == 2) {
                return false;
            }
            if (nums[i] >= nums[i - 1]) {
                continue;
            }
            count++;
            if (i == 1 || nums[i] > nums[i - 2]) {
                nums[i - 1] = nums[i];
            } else {
                nums[i] = nums[i - 1];
            }
        }
        return count < 2;
    }
}
