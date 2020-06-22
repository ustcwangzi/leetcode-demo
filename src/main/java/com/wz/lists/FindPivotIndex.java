package com.wz.lists;

/**
 * Given an array of integers nums, write a method that returns the "pivot" index of this array.
 * We define the pivot index as the index where the sum of all the numbers to the left of the index
 * is equal to the sum of all the numbers to the right of the index.
 * If no such index exists, we should return -1.
 * If there are multiple pivot indexes, you should return the left-most pivot index.
 *
 * Example 1:
 * Input: nums = [1,7,3,6,5,6]
 * Output: 3
 * Explanation:
 * The sum of the numbers to the left of index 3 (nums[3] = 6) is equal to the sum of numbers to the right of index 3.
 * Also, 3 is the first index where this occurs.
 *
 * Example 2:
 * Input: nums = [1,2,3]
 * Output: -1
 * Explanation:
 * There is no index that satisfies the conditions in the problem statement.
 */
public class FindPivotIndex {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 7, 3, 6, 5, 6};
        System.out.println(pivotIndex(nums));

        nums = new int[]{1, 2, 3};
        System.out.println(pivotIndex(nums));

        nums = new int[]{1, -1, -1, 0, 1, 1};
        System.out.println(pivotIndex(nums));
    }

    /**
     * 以sum[i]保存0～i之间的元素累加和
     * 则对于位置i，sum[i-1]代表i之前的元素之和，sum[n-1] - sum[i]代表i之后的元素之和
     * 如果两者相等，表示i就是需要找的位置
     */
    public static int pivotIndex(int[] nums) {
        if (nums.length <= 2) {
            return -1;
        }

        int[] sum = new int[nums.length];
        sum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum[i] += sum[i - 1] + nums[i];
        }

        for (int i = 0; i < nums.length; i++) {
            if (i == 0 && sum[nums.length - 1] - sum[0] == 0) {
                return i;
            }
            if (i > 0 && sum[i - 1] == sum[nums.length - 1] - sum[i]) {
                return i;
            }
        }

        return -1;
    }
}
