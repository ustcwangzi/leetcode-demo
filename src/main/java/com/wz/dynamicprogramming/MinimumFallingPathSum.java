package com.wz.dynamicprogramming;

/**
 * Given a square array of integers A, we want the minimum sum of a falling path through A.
 * A falling path starts at any element in the first row, and chooses one element from each row.
 * The next row's choice must be in a column that is different from the previous row's column by at most one.
 *
 * Example 1:
 * Input: [[1,2,3],[4,5,6],[7,8,9]]
 * Output: 12
 * Explanation:
 * The possible falling paths are:
 * [1,4,7], [1,4,8], [1,5,7], [1,5,8], [1,5,9]
 * [2,4,7], [2,4,8], [2,5,7], [2,5,8], [2,5,9], [2,6,8], [2,6,9]
 * [3,5,7], [3,5,8], [3,5,9], [3,6,8], [3,6,9]
 * The falling path with the smallest sum is [1,4,7], so the answer is 12.
 *
 * Constraints:
 * 1. 1 <= A.length == A[0].length <= 100
 * 2. -100 <= A[i][j] <= 100
 */
public class MinimumFallingPathSum {
    public static void main(String[] args) {
        int[][] A = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println(minFallingPathSum(A));
    }

    /**
     * dp[i][j] 表示在 (i,j) 的最小的下降路径
     * 对于第一行 dp[0][j]，因为没有上一行，直接就是 A[0][j]
     * 从第二行开始遍历，取上一行中相邻的三个元素中的最小值：dp[i-1][j-1]、dp[i-1][j]，dp[i-1][j+1]，将最小值加到 A[i][j] 上即可
     */
    public static int minFallingPathSum(int[][] A) {
        int n = A.length, result = Integer.MAX_VALUE;
        int[][] dp = new int[n][n];
        // 第一行
        System.arraycopy(A[0], 0, dp[0], 0, n);

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int pre;
                if (j == 0) {
                    // 第一列
                    pre = Math.min(dp[i - 1][j], dp[i - 1][j + 1]);
                } else if (j == n - 1) {
                    // 最后一列
                    pre = Math.min(dp[i - 1][j - 1], dp[i - 1][j]);
                } else {
                    // 其他位置
                    pre = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i - 1][j + 1]));
                }
                dp[i][j] = pre + A[i][j];
            }
        }

        // 根据最后一行的 dp 值，获取结果
        for (int j = 0; j < n; j++) {
            result = Math.min(result, dp[n - 1][j]);
        }
        return result;
    }
}
