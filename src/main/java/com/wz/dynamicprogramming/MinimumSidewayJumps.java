package com.wz.dynamicprogramming;

/**
 * There is a 3 lane road of length n that consists of n + 1 points labeled from 0 to n.
 * A frog starts at point 0 in the second lane and wants to jump to point n. However, there could be obstacles along the way.
 * You are given an array obstacles of length n + 1 where each obstacles[i] (ranging from 0 to 3) describes an obstacle on the lane obstacles[i] at point i.
 * If obstacles[i] == 0, there are no obstacles at point i. There will be at most one obstacle in the 3 lanes at each point.
 * For example, if obstacles[2] == 1, then there is an obstacle on lane 1 at point 2.
 * The frog can only travel from point i to point i + 1 on the same lane if there is not an obstacle on the lane at point i + 1.
 * To avoid obstacles, the frog can also perform a side jump to jump to another lane (even if they are not adjacent) at the same point if there is no obstacle on the new lane.
 * For example, the frog can jump from lane 3 at point 3 to lane 1 at point 3.
 * Return the minimum number of side jumps the frog needs to reach any lane at point n starting from lane 2 at point 0.
 * Note: There will be no obstacles on points 0 and n.
 *
 * Example 1:
 * @link ../../../../resource/MinimumSidewayJumps1.jpg
 * Input: obstacles = [0,1,2,3,0]
 * Output: 2
 * Explanation: The optimal solution is shown by the arrows above. There are 2 side jumps (red arrows).
 * Note that the frog can jump over obstacles only when making side jumps (as shown at point 2).
 *
 * Example 2:
 * @link ../../../../resource/MinimumSidewayJumps2.jpg
 * Input: obstacles = [0,1,1,3,3,0]
 * Output: 0
 * Explanation: There are no obstacles on lane 2. No side jumps are required.
 *
 * Example 3:
 * @link ../../../../resource/MinimumSidewayJumps3.jpg
 * Input: obstacles = [0,2,1,0,3,0]
 * Output: 2
 * Explanation: The optimal solution is shown by the arrows above. There are 2 side jumps.
 *
 * Constraints:
 * 1. obstacles.length == n + 1
 * 2. 1 <= n <= 5 * 10^5
 * 3. 0 <= obstacles[i] <= 3
 * 4. obstacles[0] == obstacles[n] == 0
 */
public class MinimumSidewayJumps {
    public static void main(String[] args) {
        System.out.println(minSideJumps(new int[]{0, 1, 2, 3, 0}));
        System.out.println(minSideJumps(new int[]{0, 1, 1, 3, 3, 0}));
        System.out.println(minSideJumps(new int[]{0, 2, 1, 0, 3, 0}));
    }

    /**
     * 动态规划
     * dp[i][j] 表示到达第 i 个位置的第 j 个跑道，需要跳跃的最小次数
     * 状态转移:
     * dp[i][j] = min{dp[i-1][j_0], dp[i-1][j_2] + 1, dp[i-1][j_3 + 1]}，因为只有三个跑道，j 的范围为 0～2
     * 可以发现 dp[i][j] 仅仅和 dp[i-1][j] 有关，因此可以只用一维数组
     */
    public static int minSideJumps(int[] obstacles) {
        int n = obstacles.length;
        int[] dp = new int[]{1, 0, 1};
        for (int i = 1; i < n; i++) {
            if (obstacles[i] == 0) {
                // 没有障碍，三条跑道都可以
                dp[0] = Math.min(dp[0], Math.min(dp[1] + 1, dp[2] + 1));
                dp[1] = Math.min(dp[0] + 1, Math.min(dp[1], dp[2] + 1));
                dp[2] = Math.min(dp[0] + 1, Math.min(dp[1] + 1, dp[2]));
            } else if (obstacles[i] == 1) {
                // 第一条跑道有障碍，只能选择二、三跑道
                dp[0] = n;
                dp[1] = Math.min(dp[1], dp[2] + 1);
                dp[2] = Math.min(dp[1] + 1, dp[2]);
            } else if (obstacles[i] == 2) {
                // 第二条跑道有障碍，只能选择一、三跑道
                dp[0] = Math.min(dp[0], dp[2] + 1);
                dp[1] = n;
                dp[2] = Math.min(dp[0] + 1, dp[2]);
            } else {
                // 第三条跑道有障碍，只能选择一、二跑道
                dp[0] = Math.min(dp[0], dp[1] + 1);
                dp[1] = Math.min(dp[0] + 1, dp[1]);
                dp[2] = n;
            }
        }

        return Math.min(dp[0], Math.min(dp[1], dp[2]));
    }
}
