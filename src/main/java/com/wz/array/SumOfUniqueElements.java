package com.wz.array;

/**
 * You are given an integer array nums. The unique elements of an array are the elements that appear exactly once in the array.
 * Return the sum of all the unique elements of nums.
 *
 * Example 1:
 * Input: nums = [1,2,3,2]
 * Output: 4
 * Explanation: The unique elements are [1,3], and the sum is 4.
 *
 * Example 2:
 * Input: nums = [1,2,3,4,5]
 * Output: 15
 * Explanation: The unique elements are [1,2,3,4,5], and the sum is 15.
 *
 * Constraints:
 * 1. 1 <= nums.length <= 100
 * 2. 1 <= nums[i] <= 100
 */
public class SumOfUniqueElements {
    public static void main(String[] args) {
        System.out.println(sumOfUnique(new int[]{1, 2, 3, 2}));
        System.out.println(sumOfUnique(new int[]{1, 2, 3, 4, 5}));
    }

    /**
     * 遍历 nums，使用数组记录每个元素出现次数，然后再遍历数组，累加只出现一次的元素
     */
    public static int sumOfUnique(int[] nums) {
        int[] array = new int[101];
        for (int num : nums) {
            array[num]++;
        }

        int sum = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[i] == 1) {
                sum += i;
            }
        }
        return sum;
    }
}
