package com.wz.dynamicprogramming;

/**
 * We partition a row of numbers A into at most K adjacent (non-empty) groups,
 * then our score is the sum of the average of each group. What is the largest score we can achieve?
 * Note that our partition must use every number in A, and that scores are not necessarily integers.
 *
 * Example:
 * Input:
 * A = [9,1,2,3,9]
 * K = 3
 * Output: 20
 * Explanation:
 * The best choice is to partition A into [9], [1, 2, 3], [9]. The answer is 9 + (1 + 2 + 3) / 3 + 9 = 20.
 * We could have also partitioned A into [9, 1], [2], [3, 9], for example.
 * That partition would lead to a score of 5 + 2 + 6 = 13, which is worse.
 *
 * Note:
 * 1. 1 <= A.length <= 100.
 * 2. 1 <= A[i] <= 10000.
 * 3. 1 <= K <= A.length.
 * 4. Answers within 10^-6 of the correct answer will be accepted as correct.
 */
public class LargestSumOfAverages {
    public static void main(String[] args) {
        System.out.println(largestSumOfAverages(new int[]{1, 2, 3, 4, 5, 6, 7}, 4));
    }

    /**
     * 动态规划
     * dp[i][k] 表示 [0,i] 中的元素最多划分为 k 份所能得到的最大均值和
     * 在 0～i 范围内寻找切分点 j 使结果最大，将 [0...j] 划分为 k-1 份，其最大平均值是 dp[j][k-1]
     * 然后剩余的元素划分为 1 份，其最大平均值是 sum[j+1...i]/(i-j)，可以使用累加和数组 preSum 来加快计算
     */
    public static double largestSumOfAverages(int[] A, int K) {
        int n = A.length;
        // [0...i] 的元素累加和
        int[] preSum = new int[n];
        double[][] dp = new double[n][K + 1];
        for (int i = 0; i < n; i++) {
            preSum[i] = A[i] + (i == 0 ? 0 : preSum[i - 1]);
            // 只分为一份
            dp[i][1] = preSum[i] * 1.0 / (i + 1);
        }

        for (int k = 2; k <= K; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < i; j++) {
                    dp[i][k] = Math.max(dp[i][k], (preSum[i] - preSum[j]) * 1.0 / (i - j) + dp[j][k - 1]);
                }
            }
        }
        return dp[n - 1][K];
    }
}
