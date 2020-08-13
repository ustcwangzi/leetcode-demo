package com.wz.array;

import java.util.AbstractMap;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Given the array nums consisting of n positive integers. You computed the sum of all non-empty continous subarrays
 * from the array and then sort them in non-decreasing order, creating a new array of n * (n + 1) / 2 numbers.
 * Return the sum of the numbers from index left to index right (indexed from 1), inclusive, in the new array.
 * Since the answer can be a huge number return it modulo 10^9 + 7.
 *
 * Example 1:
 * Input: nums = [1,2,3,4], n = 4, left = 1, right = 5
 * Output: 13
 * Explanation: All subarray sums are 1, 3, 6, 10, 2, 5, 9, 3, 7, 4. After sorting them in non-decreasing order we have
 * the new array [1, 2, 3, 3, 4, 5, 6, 7, 9, 10]. The sum of the numbers from index le = 1 to ri = 5 is 1 + 2 + 3 + 3 + 4 = 13.
 *
 * Example 2:
 * Input: nums = [1,2,3,4], n = 4, left = 3, right = 4
 * Output: 6
 * Explanation: The given array is the same as example 1. We have the new array [1, 2, 3, 3, 4, 5, 6, 7, 9, 10].
 * The sum of the numbers from index le = 3 to ri = 4 is 3 + 3 = 6.
 */
public class RangeSumOfSortedSubarraySums {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4};
        System.out.println(rangeSum(nums, 4, 1, 5));
        System.out.println(rangeSum(nums, 4, 3, 4));
    }

    /**
     * 因为所有数都是正数，所以以某个数作为起点，子数组包含的数越多，子数组之和也越大
     * 利用这个性质来解题，找出最小的right个子数组的和即可，不用求出所有子数组的和
     * 分别以各个元素作为子数组的起点，找出前 right 个最小的子数组的和，用优先队列存储各个子数组的和以及子数组中最后一个元素在原数组中的下标
     * 刚开始是每个子数组只有一个元素，弹出和最小的子数组，将该子数组加上后面一个数，然后再弹出和最小的子数组，直到找到要求区间内所有子数组之和
     */
    public static int rangeSum(int[] nums, int n, int left, int right) {
        // 以递增顺序保存子数组之和及最后一个元素下标
        PriorityQueue<AbstractMap.SimpleEntry<Integer, Integer>> queue =
                new PriorityQueue<>(Comparator.comparingInt(AbstractMap.SimpleEntry::getKey));
        // 初始将所有元素单独加入队列中
        for (int i = 0; i < nums.length; i++) {
            queue.offer(new AbstractMap.SimpleEntry<>(nums[i], i));
        }

        final int mod = (int) (1e9 + 7);
        int result = 0;
        // 依次弹出较小的子数组之和，并加入更大的子数组之和，直到满足要求
        for (int k = 1; k <= right; k++) {
            AbstractMap.SimpleEntry<Integer, Integer> node = queue.poll();
            int sum = node.getKey(), index = node.getValue();
            if (k >= left) {
                result = (result + sum) % mod;
            }
            if (index + 1 < n) {
                queue.offer(new AbstractMap.SimpleEntry<>(sum + nums[index + 1], index + 1));
            }
        }
        return result;
    }
}
