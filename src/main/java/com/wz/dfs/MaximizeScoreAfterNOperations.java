package com.wz.dfs;

/**
 * You are given nums, an array of positive integers of size 2 * n. You must perform n operations on this array.
 * In the ith operation (1-indexed), you will:
 * - Choose two elements, x and y.
 * - Receive a score of i * gcd(x, y).
 * - Remove x and y from nums.
 * Return the maximum score you can receive after performing n operations.
 * The function gcd(x, y) is the greatest common divisor of x and y.
 *
 * Example 1:
 * Input: nums = [1,2]
 * Output: 1
 * Explanation: The optimal choice of operations is:
 * (1 * gcd(1, 2)) = 1
 *
 * Example 2:
 * Input: nums = [3,4,6,8]
 * Output: 11
 * Explanation: The optimal choice of operations is:
 * (1 * gcd(3, 6)) + (2 * gcd(4, 8)) = 3 + 8 = 11
 *
 * Example 3:
 * Input: nums = [1,2,3,4,5,6]
 * Output: 14
 * Explanation: The optimal choice of operations is:
 * (1 * gcd(1, 5)) + (2 * gcd(2, 4)) + (3 * gcd(3, 6)) = 1 + 4 + 9 = 14
 *
 * Constraints:
 * 1. 1 <= n <= 7
 * 2. nums.length == 2 * n
 * 3. 1 <= nums[i] <= 10^6
 */
public class MaximizeScoreAfterNOperations {
    public static void main(String[] args) {
        System.out.println(maxScore(new int[]{1, 2}));
        System.out.println(maxScore(new int[]{3, 4, 6, 8}));
    }

    /**
     * 与 {@link MaximumCompatibilityScoreSum} 类似，遍历所有组合形式
     */
    public static int maxScore(int[] nums) {
        int[] memo = new int[1 << nums.length];
        return dfs(nums, memo, 1, 0);
    }

    private static int dfs(int[] nums, int[] score, int index, int mask) {
        int n = nums.length;
        if (index > (n / 2)) {
            return 0;
        }
        if (score[mask] != 0) {
            return score[mask];
        }

        int maxScore = 0;
        for (int i = 0; i < n; i++) {
            // 不在当前 mask 范围内
            if ((mask & (1 << i)) != 0) {
                continue;
            }
            for (int j = i + 1; j < n; j++) {
                if ((mask & (1 << j)) != 0) {
                    continue;
                }
                int newMask = mask | (1 << i) | (1 << j);
                int cur = index * gcd(nums[i], nums[j]);
                maxScore = Math.max(maxScore, cur + dfs(nums, score, index + 1, newMask));
            }
        }

        return score[mask] = maxScore;
    }

    private static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}
