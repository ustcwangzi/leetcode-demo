package com.wz.math;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a set of distinct positive integers, find the largest subset such that every pair (Si, Sj) of elements in this subset satisfies:
 * Si % Sj = 0 or Sj % Si = 0.
 * If there are multiple solutions, return any subset is fine.
 *
 * Example 1:
 * Input: [1,2,3]
 * Output: [1,2] (of course, [1,3] will also be ok)
 *
 * Example 2:
 * Input: [1,2,4,8]
 * Output: [1,2,4,8]
 */
public class LargestDivisibleSubset {
    public static void main(String[] args) {
        System.out.println(largestDivisibleSubset(new int[]{1}));
        System.out.println(largestDivisibleSubset(new int[]{1, 2, 3}));
        System.out.println(largestDivisibleSubset(new int[]{1, 2, 4, 8}));
    }

    /**
     * 动态规划
     * 较小数对较大数取余一定不为0，那么问题就变成了看较大数能不能整除这个较小数
     * 首先给数组排序，这样每次就只要看后面的数字能否整除前面的数字
     * dp[i] 表示到 位置 i 最大可整除的子集合的长度，还需要一个数组 parent，来保存上一个能整除的数字的位置
     * maxLen 和 index 分别表示最大子集合的长度和起始位置
     * 从后往前遍历数组，对于某个数字再遍历到末尾，在这个过程中，如果 nums[j] 能整除 nums[i], 且 dp[i] < dp[j] + 1 的话，
     * 更新 dp[i] 和 parent[i]，同时检查是否需要更新 maxLen 和 index，
     * 最后循环结束后，根据 parent 数组来依次找到每一个数字
     */
    public static List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.parallelSort(nums);
        int[] dp = new int[nums.length];
        int[] parent = new int[nums.length];
        List<Integer> result = new LinkedList<>();
        int maxLen = 0, index = 0;

        for (int i = nums.length - 1; i >= 0; i--) {
            // 向后找能整除的元素
            for (int j = i; j < nums.length; j++) {
                if (nums[j] % nums[i] == 0 && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                    parent[i] = j;
                    if (maxLen < dp[i]) {
                        maxLen = dp[i];
                        index = i;
                    }
                }
            }
        }

        for (int i = 0; i < maxLen; i++) {
            result.add(nums[index]);
            index = parent[index];
        }

        return result;
    }
}
