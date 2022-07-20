package com.wz.dfs;

/**
 * There are several stones arranged in a row, and each stone has an associated value which is an integer given in the array stoneValue.
 * In each round of the game, Alice divides the row into two non-empty rows (i.e. left row and right row),
 * then Bob calculates the value of each row which is the sum of the values of all the stones in this row.
 * Bob throws away the row which has the maximum value, and Alice's score increases by the value of the remaining row.
 * If the value of the two rows are equal, Bob lets Alice decide which row will be thrown away. The next round starts with the remaining row.
 * The game ends when there is only one stone remaining. Alice's is initially zero.
 * Return the maximum score that Alice can obtain.
 *
 * Example 1:
 * Input: stoneValue = [6,2,3,4,5,5]
 * Output: 18
 * Explanation: In the first round, Alice divides the row to [6,2,3], [4,5,5]. The left row has the value 11 and the right row has value 14. Bob throws away the right row and Alice's score is now 11.
 * In the second round Alice divides the row to [6], [2,3]. This time Bob throws away the left row and Alice's score becomes 16 (11 + 5).
 * The last round Alice has only one choice to divide the row which is [2], [3]. Bob throws away the right row and Alice's score is now 18 (16 + 2). The game ends because only one stone is remaining in the row.
 *
 * Example 2:
 * Input: stoneValue = [7,7,7,7,7,7,7]
 * Output: 28
 *
 * Example 3:
 * Input: stoneValue = [4]
 * Output: 0
 *
 * Constraints:
 * 1. 1 <= stoneValue.length <= 500
 * 2. 1 <= stoneValue[i] <= 10^6
 */
public class StoneGameV {
    public static void main(String[] args) {
        System.out.println(stoneGameV(new int[]{6, 2, 3, 4, 5, 5}));
        System.out.println(stoneGameV(new int[]{7, 7, 7, 7, 7, 7, 7}));
    }

    /**
     * DFS + 动态规划
     * 为了方便快速求区间和，先计算出前缀和 preSum，然后使用 DFS 计算 [start-1, end-1] Alice 能获得的最大分数
     * 为了避免重复计算，使用 dp[][] 记录已经得到的结果
     * 然后遍历 start～end 依次作为分割点，求出对应的左右分数 left、right
     * - 若 left > right，Bob 会丢弃 left，Alice 得分为 right，剩余部分为 [i+1, end]
     * - 若 left < right，Bob 会丢弃 right，Alice 得分为 left，剩余部分为 [start, i]
     * - 若 left == right，Alice 从上面两种情况中选择最佳方案
     */
    public static int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;
        int[][] dp = new int[n + 1][n + 1];
        int[] preSum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            preSum[i + 1] = preSum[i] + stoneValue[i];
        }
        return dfs(dp, preSum, 1, n);
    }

    private static int dfs(int[][] dp, int[] preSum, int start, int end) {
        if (start == end) {
            return 0;
        }
        if (dp[start][end] != 0) {
            return dp[start][end];
        }

        int max = 0;
        for (int i = start; i < end; i++) {
            int left = preSum[i] - preSum[start - 1], right = preSum[end] - preSum[i];
            if (left > right) {
                max = Math.max(max, right + dfs(dp, preSum, i + 1, end));
            } else if (left < right) {
                max = Math.max(max, left + dfs(dp, preSum, start, i));
            } else {
                max = Math.max(max, left + dfs(dp, preSum, start, i));
                max = Math.max(max, right + dfs(dp, preSum, i + 1, end));
            }
        }
        return dp[start][end] = max;
    }
}
