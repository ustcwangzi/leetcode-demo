package com.wz.dynamicprogramming;

import java.util.Arrays;

/**
 * We are playing the Guessing Game. The game will work as follows:
 * 1. I pick a number between 1 and n.
 * 2. You guess a number.
 * 3. If you guess the right number, you win the game.
 * 4. If you guess the wrong number, then I will tell you whether the number I picked is higher or lower, and you will continue guessing.
 * 5. Every time you guess a wrong number x, you will pay x dollars. If you run out of money, you lose the game.
 * Given a particular n, return the minimum amount of money you need to guarantee a win regardless of what number I pick.
 *
 * Example 1:
 * Input: n = 10
 * Output: 16
 * Explanation: The winning strategy is as follows:
 * - The range is [1,10]. Guess 7.
 *     - If this is my number, your total is $0. Otherwise, you pay $7.
 *     - If my number is higher, the range is [8,10]. Guess 9.
 *         - If this is my number, your total is $7. Otherwise, you pay $9.
 *         - If my number is higher, it must be 10. Guess 10. Your total is $7 + $9 = $16.
 *         - If my number is lower, it must be 8. Guess 8. Your total is $7 + $9 = $16.
 *     - If my number is lower, the range is [1,6]. Guess 3.
 *         - If this is my number, your total is $7. Otherwise, you pay $3.
 *         - If my number is higher, the range is [4,6]. Guess 5.
 *             - If this is my number, your total is $7 + $3 = $10. Otherwise, you pay $5.
 *             - If my number is higher, it must be 6. Guess 6. Your total is $7 + $3 + $5 = $15.
 *             - If my number is lower, it must be 4. Guess 4. Your total is $7 + $3 + $5 = $15.
 *         - If my number is lower, the range is [1,2]. Guess 1.
 *             - If this is my number, your total is $7 + $3 = $10. Otherwise, you pay $1.
 *             - If my number is higher, it must be 2. Guess 2. Your total is $7 + $3 + $1 = $11.
 * The worst case in all these scenarios is that you pay $16. Hence, you only need $16 to guarantee a win.
 *
 * Example 2:
 * Input: n = 2
 * Output: 1
 * Explanation: There are two possible numbers, 1 and 2.
 * - Guess 1.
 *     - If this is my number, your total is $0. Otherwise, you pay $1.
 *     - If my number is higher, it must be 2. Guess 2. Your total is $1.
 * The worst case is that you pay $1.
 *
 * Constraints:
 * 1 <= n <= 200
 */
public class GuessNumberHigherOrLowerII {
    public static void main(String[] args) {
        System.out.println(getMoneyAmount(10));
    }

    /**
     * 动态规划 + DFS
     * dp[i][j] 代表从数字 i 到 j 之间猜中任意一个数字最少需要花费的钱数
     * 当 n=3 时，要找到 1，2，3 中任何一个数字，要花费 2 元，即从 2 开始查询，若命中则花费 0 元，若未命中也知道目标值比 2 大还是比 2 小，
     * 下次一定命中，由此可见，要知道 dp[1][n]，只要找到花费最小的中间点 k，即 dp[i][j] = k + max{dp[i,k-1], dp[k+1][j]}
     */
    public static int getMoneyAmount(int n) {
        int[][] dp = new int[n + 1][n + 1];
        for (int[] array : dp) {
            Arrays.fill(array, Integer.MAX_VALUE);
        }
        return dfs(1, n, dp);
    }

    private static int dfs(int left, int right, int[][] dp) {
        if (left >= right) {
            return 0;
        }
        if (dp[left][right] != Integer.MAX_VALUE) {
            return dp[left][right];
        }
        // 找到最小的中间点
        for (int i = left; i <= right; i++) {
            dp[left][right] = Math.min(dp[left][right], i + Math.max(dfs(left, i - 1, dp), dfs(i + 1, right, dp)));
        }
        return dp[left][right];
    }
}
