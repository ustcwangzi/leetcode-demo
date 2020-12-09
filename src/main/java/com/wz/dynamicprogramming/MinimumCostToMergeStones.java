package com.wz.dynamicprogramming;

/**
 * There are N piles of stones arranged in a row.  The i-th pile has stones[i] stones.
 * A move consists of merging exactly K consecutive piles into one pile,
 * and the cost of this move is equal to the total number of stones in these K piles.
 * Find the minimum cost to merge all piles of stones into one pile.  If it is impossible, return -1.
 *
 * Example 1:
 * Input: stones = [3,2,4,1], K = 2
 * Output: 20
 * Explanation:
 * We start with [3, 2, 4, 1].
 * We merge [3, 2] for a cost of 5, and we are left with [5, 4, 1].
 * We merge [4, 1] for a cost of 5, and we are left with [5, 5].
 * We merge [5, 5] for a cost of 10, and we are left with [10].
 * The total cost was 20, and this is the minimum possible.
 *
 * Example 2:
 * Input: stones = [3,2,4,1], K = 3
 * Output: -1
 * Explanation: After any merge operation, there are 2 piles left, and we can't merge anymore. So the task is impossible.
 *
 * Example 3:
 * Input: stones = [3,5,1,2,6], K = 3
 * Output: 25
 * Explanation:
 * We start with [3, 5, 1, 2, 6].
 * We merge [5, 1, 2] for a cost of 8, and we are left with [3, 8, 6].
 * We merge [3, 8, 6] for a cost of 17, and we are left with [17].
 * The total cost was 25, and this is the minimum possible.
 *
 * Note:
 * 1. 1 <= stones.length <= 30
 * 2. 2 <= K <= 30
 * 3. 1 <= stones[i] <= 100
 */
public class MinimumCostToMergeStones {
    public static void main(String[] args) {
        System.out.println(mergeStones(new int[]{3, 5, 1, 2, 6}, 3));
    }

    /**
     * dp[i][j] 表示合并范围 [i,j] 内的石头堆的最小花费，最终 dp[0][n-1] 就是所要求的值
     * 从小区间开始更新的，结社其中的小区间的 dp 值都已经更新好了，就可以将大区间拆成两个小区间来更新了。一般来讲，
     * 将一个数组拆成两个非空子数组的时候，会遍历其所有情况，比如 [1, 2, 3, 4]，会拆成 [1] 和 [2,3,4]，[1,2] 和 [3,4], [1,2,3] 和 [4]。
     * 但是这道题由于其特殊性，并不需要遍历所有的拆分情况，因为某些区间是无法通过合并石子堆得到的，就拿上面的例子来说，若 K=3，
     * 那么就不需要用 [1,2] 和 [3,4] 来更新整个区间，都不到3个，无法合并，所以遍历的时候每次跳过 K-1 个位置即可，用 t 来分别区间 [i,j]，
     * 然后每次 t += K-1 即可，用两个小区间的 dp 值来更新整个区间。这还没有完，当某个子区间正好可以合并为一堆石子的时候，
     * 其 dp 值要加上该区间所有的石子数。举个例子，比如 [1,2,3]，K=3，那么分割的话，只能用 dp[0][0] + dp[1][2] 来更新 dp[0][2]，
     * 但是 dp[0][0] 和 dp[1][2] 均为0，因为区间长度均小于3，那么 dp[0][2] 值就无法更新成正确的值了，这三个数字是可以合并的，
     * 所以要加上区间内所有的数字之和，而为了快速的求得任意区间和，采用提前建立累加和数组 sums 的方式，来提高计算效率，所以整个状态转移方程为：
     * dp[i][j] = min(dp[i][j], dp[i][t] + dp[t + 1][j]); -> (i <= t < j)
     * dp[i][j] += sums[j + 1] - sums[i]; -> if ((j - i) % (K - 1) == 0)
     */
    public static int mergeStones(int[] stones, int K) {
        int n = stones.length;
        if ((n - 1) % (K - 1) != 0) {
            return -1;
        }
        int[] sums = new int[n + 1];
        int[][] dp = new int[n][n];
        for (int i = 1; i < n + 1; ++i) {
            sums[i] = sums[i - 1] + stones[i - 1];
        }
        for (int len = K; len <= n; ++len) {
            for (int i = 0; i + len <= n; ++i) {
                int j = i + len - 1;
                dp[i][j] = Integer.MAX_VALUE;
                for (int t = i; t < j; t += K - 1) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][t] + dp[t + 1][j]);
                }
                if ((j - i) % (K - 1) == 0) {
                    dp[i][j] += sums[j + 1] - sums[i];
                }
            }
        }
        return dp[0][n - 1];
    }
}
