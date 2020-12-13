package com.wz.dynamicprogramming;

/**
 * Alice and Bob continue their games with piles of stones.  There are a number of piles arranged in a row,
 * and each pile has a positive integer number of stones piles[i].  The objective of the game is to end with the most stones.
 * Alice and Bob take turns, with Alice starting first.  Initially, M = 1.
 * On each player's turn, that player can take all the stones in the first X remaining piles, where 1 <= X <= 2M.  Then, we set M = max(M, X).
 * The game continues until all the stones have been taken.
 * Assuming Alice and Bob play optimally, return the maximum number of stones Alice can get.
 *
 * Example 1:
 * Input: piles = [2,7,9,4,4]
 * Output: 10
 * Explanation:  If Alice takes one pile at the beginning, Bob takes two piles, then Alice takes 2 piles again.
 * Alice can get 2 + 4 + 4 = 10 piles in total. If Alice takes two piles at the beginning,
 * then Bob can take all three piles left. In this case, Alice get 2 + 7 = 9 piles in total. So we return 10 since it's larger.
 *
 * Example 2:
 * Input: piles = [1,2,3,4,5,100]
 * Output: 104
 *
 * Constraints:
 * 1. 1 <= piles.length <= 100
 * 2. 1 <= piles[i] <= 104
 */
public class StoneGameII {
    public static void main(String[] args) {
        System.out.println(stoneGameII(new int[]{2, 7, 9, 4, 4}));
    }

    /**
     * dp[i][M] 表示在 piles[i...n-1] 中以 [1,2*M] 为取值范围可以取的最多石子数
     * dp[i][M] = sum(piles[i...-1]) − min{dp[i+x][max{M,x}]}  (1 ≤ x ≤2∗M )
     * 也就是说，希望 Alice 尽可能多拿的话，那么就希望 Bob 少拿，Alice 最多拿 sum(piles[i...n-1])个石子，
     * 而 Bob 最少拿 min{f[i+x][max{M, x}]} 个石子，最后 Alice 实际上可以最多拿二者之差个石子
     */
    public static int stoneGameII(int[] piles) {
        int n = piles.length;
        if (n == 1) {
            return piles[0];
        }
        if (n == 2) {
            return piles[0] + piles[1];
        }

        // sum(piles[i...-1])
        int[] sum = new int[n];
        sum[n - 1] = piles[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            sum[i] = sum[i + 1] + piles[i];
        }

        // piles[1,2*M] 能取的最多石子数
        int[][] dp = new int[n][n];
        return helper(piles, sum, 0, dp, 1);
    }

    private static int helper(int[] piles, int[] sum, int i, int[][] dp, int M) {
        if (i == piles.length) {
            return 0;
        }
        // 可以获取剩余所有石子
        if (2 * M >= piles.length - i) {
            return sum[i];
        }
        if (dp[i][M] != 0) {
            return dp[i][M];
        }

        // 希望 Bob 拿最少的石子
        int min = Integer.MAX_VALUE;
        for (int x = 1; x <= 2 * M; x++) {
            min = Math.min(min, helper(piles, sum, i + x, dp, Math.max(M, x)));
        }

        return dp[i][M] = sum[i] - min;
    }
}
