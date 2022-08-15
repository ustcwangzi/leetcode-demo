package com.wz.other;

import java.util.TreeSet;

/**
 * You are given an array nums of n positive integers.
 * You can perform two types of operations on any element of the array any number of times:
 * - If the element is even, divide it by 2.
 *   For example, if the array is [1,2,3,4], then you can do this operation on the last element, and the array will be [1,2,3,2].
 * - If the element is odd, multiply it by 2.
 *   For example, if the array is [1,2,3,4], then you can do this operation on the first element, and the array will be [2,2,3,4].
 * The deviation of the array is the maximum difference between any two elements in the array.
 * Return the minimum deviation the array can have after performing some number of operations.
 *
 * Example 1:
 * Input: nums = [1,2,3,4]
 * Output: 1
 * Explanation: You can transform the array to [1,2,3,2], then to [2,2,3,2], then the deviation will be 3 - 2 = 1.
 *
 * Example 2:
 * Input: nums = [4,1,5,20,3]
 * Output: 3
 * Explanation: You can transform the array after two operations to [4,2,5,5,3], then the deviation will be 5 - 2 = 3.
 *
 * Example 3:
 * Input: nums = [2,10,8]
 * Output: 3
 *
 * Constraints:
 * 1. n == nums.length
 * 2. 2 <= n <= 5 * 10^4
 * 3. 1 <= nums[i] <= 10^9
 */
public class MinimizeDeviationInArray {
    public static void main(String[] args) {
        System.out.println(minimumDeviation(new int[]{1, 2, 3, 4}));
        System.out.println(minimumDeviation(new int[]{2, 10, 8}));
    }

    /**
     * 为了保持后续操作是同一个方向，就是往下除，先对于所有奇数进行一个乘二的预处理
     * 因为找最大值与最小值的最小差值，只要能够保持一端，去寻找另一端即可，保持一端最大或者最小
     * 可以使用 TreeSet，保持最小值不变，最大值是偶数时，则除二操作，同时更新结果
     */
    public static int minimumDeviation(int[] nums) {
        TreeSet<Integer> treeSet = new TreeSet<>();
        for (int num : nums) {
            if ((num & 1) == 1) {
                treeSet.add(num * 2);
            } else {
                treeSet.add(num);
            }
        }

        int result = treeSet.last() - treeSet.first();
        while ((treeSet.last() & 1) == 0) {
            int max = treeSet.last();
            treeSet.remove(max);
            treeSet.add(max / 2);
            result = Math.min(result, treeSet.last() - treeSet.first());
        }
        return result;
    }
}
