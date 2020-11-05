package com.wz.string;

import java.util.Arrays;

/**
 * Given an array of integers cost and an integer target. Return the maximum integer you can paint under the following rules:
 * 1. The cost of painting a digit (i+1) is given by cost[i] (0 indexed).
 * 2. The total cost used must be equal to target.
 * 3. Integer does not have digits 0.
 * Since the answer may be too large, return it as string.
 * If there is no way to paint any integer given the condition, return "0".
 *
 * Example 1:
 * Input: cost = [4,3,2,5,6,7,2,5,5], target = 9
 * Output: "7772"
 * Explanation:  The cost to paint the digit '7' is 2, and the digit '2' is 3. Then cost("7772") = 2*3+ 3*1 = 9.
 *               You could also paint "977", but "7772" is the largest number.
 * Digit    cost
 *   1  ->   4
 *   2  ->   3
 *   3  ->   2
 *   4  ->   5
 *   5  ->   6
 *   6  ->   7
 *   7  ->   2
 *   8  ->   5
 *   9  ->   5
 *
 * Example 2:
 * Input: cost = [7,6,5,5,5,6,8,7,8], target = 12
 * Output: "85"
 * Explanation: The cost to paint the digit '8' is 7, and the digit '5' is 5. Then cost("85") = 7 + 5 = 12.
 *
 * Constraints:
 * 1. cost.length == 9
 * 2. 1 <= cost[i] <= 5000
 * 3. 1 <= target <= 5000
 */
public class FormLargestIntegerWithDigitsThatAddUpToTarget {
    public static void main(String[] args) {
        System.out.println(largestNumber(new int[]{4, 3, 2, 5, 6, 7, 2, 5, 5}, 9));
    }

    /**
     * 总成本是 target 的条件下，生成最大的结果
     * 动态规划
     * dp[i] 表示成本为 i 的情况下，生成的最大结果
     * i 从 1 到 target，然后从 1 到 9 选择 digit，逐个试探，生成最优解
     */
    public static String largestNumber(int[] cost, int target) {
        String[] dp = new String[target + 1];
        Arrays.fill(dp, "0");
        dp[0] = "";
        for (int i = 1; i <= target; i++) {
            for (int digit = 1; digit <= 9; digit++) {
                int s = i - cost[digit - 1];
                if (s < 0 || dp[s].equals("0") || dp[s].length() + 1 < dp[i].length()) {
                    continue;
                }
                // 满足条件，将本次的 digit 拼接在结果中
                dp[i] = digit + dp[s];
            }
        }
        return dp[target];
    }
}
