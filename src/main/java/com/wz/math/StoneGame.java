package com.wz.math;

/**
 * Alex and Lee play a game with piles of stones.  There are an even number of piles arranged in a row,
 * and each pile has a positive integer number of stones piles[i].
 * The objective of the game is to end with the most stones.  The total number of stones is odd, so there are no ties.
 * Alex and Lee take turns, with Alex starting first.  Each turn, a player takes the entire pile of stones from either
 * the beginning or the end of the row.  This continues until there are no more piles left, at which point the person with the most stones wins.
 * Assuming Alex and Lee play optimally, return True if and only if Alex wins the game.
 *
 * Example 1:
 * Input: piles = [5,3,4,5]
 * Output: true
 * Explanation:
 * Alex starts first, and can only take the first 5 or the last 5.
 * Say he takes the first 5, so that the row becomes [3, 4, 5].
 * If Lee takes 3, then the board is [4, 5], and Alex takes 5 to win with 10 points.
 * If Lee takes the last 5, then the board is [3, 4], and Alex takes 4 to win with 9 points.
 * This demonstrated that taking the first 5 was a winning move for Alex, so we return true.
 *
 * Constraints:
 * 2 <= piles.length <= 500
 * piles.length is even.
 * 1 <= piles[i] <= 500
 * sum(piles) is odd.
 */
public class StoneGame {
    public static void main(String[] args) {
        System.out.println(stoneGame(new int[]{5, 3, 4, 5}));
        System.out.println(stoneGame2(new int[]{5, 3, 4, 5}));
    }

    /**
     * dp[i][j] 表示在区间 [i, j] 内 Alex 比 Lee 多拿的石子数，若为正数，说明 Alex 拿得多，若为负数，则表示 Lee 拿得多。
     * 则最终只要看 dp[0][n-1] 的值，若为正数，则 Alex 能获胜。
     * 在区间 [i, j] 内要计算 Alex 比 Lee 多拿的石子数，在这个区间内，Alex 只能拿i或者j位置上的石子，那么当 Alex 拿了 piles[i] 的话，
     * 等于 Alex 多了 piles[i] 个石子，此时区间缩小成了 [i+1, j]，此时应该 Lee 拿了，
     * dp[i+1][j] 表示是在区间 [i+1, j] 内 Alex 多拿的石子数，但是若区间 [i+1, j] 内 Lee 先拿的话，
     * 其多拿的石子数也应该是 dp[i+1][j]，因为两个人都要最优化拿，那么 dp[i][j] 的值其实可以被 piles[i] - dp[i+1][j] 更新，
     * 因为 Alex 拿了 piles[i]，减去 Lee 多出的 dp[i+1][j]，就是区间 [i, j] 中 Alex 多拿的石子数。
     * 同理，假如 Alex 先拿 piles[j]，那么就用 piles[j] - dp[i][j-1] 来更新 dp[i][j]，则用二者的较大值来更新即可。
     * 注意开始的时候要把 dp[i][i] 都初始化为 piles[i]
     */
    public static boolean stoneGame(int[] piles) {
        int n = piles.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; ++i) {
            dp[i][i] = piles[i];
        }
        for (int len = 1; len < n; ++len) {
            for (int i = 0; i < n - len; ++i) {
                int j = i + len;
                dp[i][j] = Math.max(piles[i] - dp[i + 1][j], piles[j] - dp[i][j - 1]);
            }
        }
        return dp[0][n - 1] > 0;
    }

    /**
     * 双指针
     * Alex 先拿 piles[l] 还剩下 piles[l+1, r]，Alex 先拿 piles[r] 还剩下 piles[l, r-1]，比较两者的值来决定 Alex 怎么拿
     * 最后如果 Alex 拿到的结果大于总数的一半，说明 Alex 获胜
     */
    public static boolean stoneGame2(int[] piles) {
        int sum = 0, total = 0;
        for (int x : piles) {
            total = total + x;
        }

        int l = 0;
        int r = piles.length - 1;
        while (l <= r) {
            int curr;
            if (r - l > 2) {
                if (piles[l] + piles[r - 1] >= piles[r] + piles[l + 1]) {
                    curr = piles[l++];
                } else {
                    curr = piles[r--];
                }
            } else {
                curr = Math.max(piles[l++], piles[r--]);
            }
            sum = sum + curr;
        }
        return sum > total / 2;
    }
}
