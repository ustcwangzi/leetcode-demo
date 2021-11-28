package com.wz.dynamicprogramming;

/**
 * We have two types of tiles: a 2x1 domino shape, and an "L" tromino shape. These shapes may be rotated.
 * XX  <- domino
 * XX  <- "L" tromino
 * X
 * Given N, how many ways are there to tile a 2 x N board? Return your answer modulo 10^9 + 7.
 * (In a tiling, every square must be covered by a tile. Two tilings are different if and only if there are two
 * 4-directionally adjacent cells on the board such that exactly one of the tilings has both squares occupied by a tile.)
 *
 * Example:
 * Input: 3
 * Output: 5
 * Explanation:
 * The five different ways are listed below, different letters indicates different tiles:
 * XYZ XXZ XYY XXY XYY
 * XYZ YYZ XZZ XYY XXY
 *
 * Note:
 * N  will be in range [1, 1000].
 */
public class DominoAndTrominoTiling {
    public static void main(String[] args) {
        System.out.println(numTilings(3));
    }

    /**
     * dp[i] 表示填满前 i 列的不同填法总数
     * 当N=1时，那么就是一个2x1大小的棋盘，只能放一个多米诺骨牌，只有一种情况。
     * 当N=2时，那么就是一个2x2大小的棋盘，如下图所示，有两种放置方法，可以将两个多米诺骨牌竖着并排放，或者是将其横着并排放。
     * 当N=3时，那么就是一个3x2大小的棋盘，共用五种放置方法，如下图所示。仔细观察这五种情况，发现其时时跟上面的情况有联系的。
     *  前两种情况其实是N=2的两种情况后面加上了一个竖着的多米诺骨牌，第三种情况其实是N=1的那种情况后面加上了两个平行的横向的多米诺骨牌，
     *  后两种情况是N=0（空集）再加上两种三格骨牌对角摆开的情况。
     * 当N=4时，那么就是一个4x2大小的棋盘，共用十一种放置方法，太多了就不一一画出来了，但是其也是由之前的情况组合而成的。
     *  首先是N=3的所有情况后面加上一个竖着多米诺骨牌，然后是N=2的所有情况加上两个平行的横向的多米诺骨牌，然后N=1再加上两种三格骨牌对角摆开的情况，
     *  然后N=0（空集）再加上两种三格骨牌和一个横向多米诺骨牌组成的情况。
     * @link ../../../../resource/DominoAndTrominoTiling.jpg
     * 总结一个很重要的规律，就是 dp[n] 是由之前的 dp 值组成的，其中 dp[n-1] 和 dp[n-2] 各自能贡献一种组成方式，而dp[n-3]，一直到dp[0]，
     * 都能各自贡献两种组成方式，所以状态转移方程：
     * dp[n] = dp[n-1] + dp[n-2] + 2 * (dp[n-3] + ... + dp[0])
     *       = dp[n-1] + dp[n-3] + dp[n-2] + dp[n-3] + 2 * (dp[n-4] + ... dp[0])
     *       = dp[n-1] + dp[n-3] + dp[n-1]
     *       = 2 * dp[n-1] + dp[n-3]
     */
    public static int numTilings(int N) {
        int mod = 1000000007;
        if (N <= 1) {
            return 1;
        }
        int[] dp = new int[N + 1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= N; ++i) {
            dp[i] = ((dp[i - 1] * 2) % mod + dp[i - 3]) % mod;
        }
        return dp[N];
    }
}
