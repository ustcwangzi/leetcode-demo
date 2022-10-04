package com.wz.dynamicprogramming;

/**
 * Alice and Bob take turns playing a game, with Alice starting first.
 * There are n stones arranged in a row. On each player's turn, while the number of stones is more than one, they will do the following:
 * - Choose an integer x > 1, and remove the leftmost x stones from the row.
 * - Add the sum of the removed stones' values to the player's score.
 * - Place a new stone, whose value is equal to that sum, on the left side of the row.
 * The game stops when only one stone is left in the row.
 * The score difference between Alice and Bob is (Alice's score - Bob's score). Alice's goal is to maximize the score difference, and Bob's goal is the minimize the score difference.
 * Given an integer array stones of length n where stones[i] represents the value of the ith stone from the left,
 * return the score difference between Alice and Bob if they both play optimally.
 *
 * Example 1:
 * Input: stones = [-1,2,-3,4,-5]
 * Output: 5
 * Explanation:
 * - Alice removes the first 4 stones, adds (-1) + 2 + (-3) + 4 = 2 to her score, and places a stone of
 *   value 2 on the left. stones = [2,-5].
 * - Bob removes the first 2 stones, adds 2 + (-5) = -3 to his score, and places a stone of value -3 on
 *   the left. stones = [-3].
 * The difference between their scores is 2 - (-3) = 5.
 *
 * Example 2:
 * Input: stones = [7,-6,5,10,5,-2,-6]
 * Output: 13
 * Explanation:
 * - Alice removes all stones, adds 7 + (-6) + 5 + 10 + 5 + (-2) + (-6) = 13 to her score, and places a
 *   stone of value 13 on the left. stones = [13].
 * The difference between their scores is 13 - 0 = 13.
 *
 * Example 3:
 * Input: stones = [-10,-12]
 * Output: -22
 * Explanation:
 * - Alice can only make one move, which is to remove both stones. She adds (-10) + (-12) = -22 to her
 *   score and places a stone of value -22 on the left. stones = [-22].
 * The difference between their scores is (-22) - 0 = -22.
 *
 * Constraints:
 * 1. n == stones.length
 * 2. 2 <= n <= 10^5
 * 3. -10^4 <= stones[i] <= 10^4
 */
public class StoneGameVIII {
    public static void main(String[] args) {
        System.out.println(stoneGameVIII(new int[]{-1, 2, -3, 4, -5}));
        System.out.println(stoneGameVIII(new int[]{7, -6, 5, 10, 5, -2, -6}));
        System.out.println(stoneGameVIII(new int[]{-10, -12}));
    }

    /**
     * 玩家的操作等价于：一名玩家在前缀和数组中选取第 i 个值，然后另一玩家在 [i+1, n-1] 区间内选取下一前缀和数值
     * 因为在同一条件下 Alice 和 Bob 的操作完全相同，设计一个动态规划数组表示玩家的最优策略即可
     * dp[i] 表示区间 [i, n-1] 范围内能够获得的最大分数差，则对位置 i，有两种选择：
     * - 选取第 i 个前缀和，当前收益是 preSum[i]，后手从 i+1 开始拿，最终有 dp[i] = preSum[i] - dp[i+1]
     * - 不选取第 i 个前缀和，等价于从 i+1 开始拿，此时 dp[i] = dp[i+1]
     * 综上，根据是否选取第 i 个前缀和可得到状态转移方程 dp[i] = max{dp[i+1], preSum[i] - dp[i+1]}
     * 对于边界情况，先手可以一次性全部拿掉，因此有 dp[n-1] = preSum[n - 1]，然后从后向前进行动态规划
     * 又因为第一次选取操作时只能从第 2 个石子开始，有效起始点为 dp[1]
     */
    public static int stoneGameVIII(int[] stones) {
        int n = stones.length;
        int[] preSum = new int[n], dp = new int[n];
        preSum[0] = stones[0];
        for (int i = 1; i < n; i++) {
            preSum[i] = preSum[i - 1] + stones[i];
        }

        dp[n - 1] = preSum[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            dp[i] = Math.max(dp[i + 1], preSum[i] - dp[i + 1]);
        }
        return dp[1];
    }
}
