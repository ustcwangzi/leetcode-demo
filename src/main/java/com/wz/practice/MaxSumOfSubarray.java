package com.wz.practice;

/**
 * 给定一个数组arr，返回子数组的最大累加和
 * 例如，arr = [1, -2, 3, 5, -2, 6, -1]，所有子数组中，[3, 5, -2, 6]可以累加出最大的和12，所以返回12.
 * 题目保证没有全为负数的数据
 * [要求]
 * 时间复杂度为O(n)O(n)，空间复杂度为O(1)O(1)
 */
public class MaxSumOfSubarray {
    public static void main(String[] args) {
        System.out.println(maxSumOfSubarray(new int[]{1, -2, 3, 5, -2, 6, -1}));
    }

    /**
     * 使用 curSum 记录到当前位置的累加和，result 记录全局的结果
     * 每遍历一个元素更新 curSum、result，同时如果当前 curSum<0，那么累加这一部分只会让结果更新，因此直接将 curSum 设置为 0
     */
    public static int maxSumOfSubarray(int[] arr) {
        int curSum = 0, result = Integer.MIN_VALUE;
        for (int num : arr) {
            curSum += num;
            result = Math.max(result, curSum);
            curSum = Math.max(curSum, 0);
        }
        return result;
    }
}
