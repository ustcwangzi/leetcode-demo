package com.wz.sort;

import java.util.Arrays;

/**
 * Given an integer array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....
 * You may assume the input array always has a valid answer.
 *
 * Example 1:
 * Input: nums = [1,5,1,1,6,4]
 * Output: [1,6,1,5,1,4]
 * Explanation: [1,4,1,5,1,6] is also accepted.
 *
 * Example 2:
 * Input: nums = [1,3,2,2,3,1]
 * Output: [2,3,1,3,1,2]
 *
 * Constraints:
 * 1. 1 <= nums.length <= 5 * 10^4
 * 2. 0 <= nums[i] <= 5000
 * 3. It is guaranteed that there will be an answer for the given input nums.
 */
public class WiggleSortII {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 5, 1, 1, 6, 4};
        wiggleSort(nums);
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 偶数位置放较小的数，奇数位置放较大的数，可以分别放置
     * 对数组进行排序，然后在奇数位置放排好序的数组的后一半元素，偶数位置放前一半元素
     */
    public static void wiggleSort(int[] nums) {
        Arrays.parallelSort(nums);
        int[] result = new int[nums.length];

        int i = 1, j = nums.length - 1;
        // 奇数位置放排序后的后一半元素
        while (i < nums.length) {
            result[i] = nums[j--];
            i += 2;
        }

        i = 0;
        // 偶数位置放排序后的前一半元素
        while (i < nums.length) {
            result[i] = nums[j--];
            i += 2;
        }

        i = 0;
        // 复制结果
        while (i < nums.length) {
            nums[i] = result[i];
            i++;
        }
    }
}
