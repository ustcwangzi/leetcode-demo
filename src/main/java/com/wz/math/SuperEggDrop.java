package com.wz.math;

/**
 * You are given K eggs, and you have access to a building with N floors from 1 to N.
 * Each egg is identical in function, and if an egg breaks, you cannot drop it again.
 * You know that there exists a floor F with 0 <= F <= N such that any egg dropped at a floor higher than F will break,
 * and any egg dropped at or below floor F will not break.
 * Each move, you may take an egg (if you have an unbroken one) and drop it from any floor X (with 1 <= X <= N).
 * Your goal is to know with certainty what the value of F is.
 * What is the minimum number of moves that you need to know with certainty what F is, regardless of the initial value of F?
 *
 * Example 1:
 * Input: K = 1, N = 2
 * Output: 2
 * Explanation:
 * Drop the egg from floor 1.  If it breaks, we know with certainty that F = 0.
 * Otherwise, drop the egg from floor 2.  If it breaks, we know with certainty that F = 1.
 * If it didn't break, then we know with certainty F = 2.
 * Hence, we needed 2 moves in the worst case to know what F is with certainty.
 *
 * Example 2:
 * Input: K = 2, N = 6
 * Output: 3
 */
public class SuperEggDrop {
    public static void main(String[] args) {
        System.out.println(superEggDrop(1, 2));
        System.out.println(superEggDrop(2, 6));
    }

    /**
     * 动态规划
     * dp[i][j] 表示进行 i 次移动，有 j 个鸡蛋，最多可以检查的楼层高度是多少
     * 在第 i 次移动时测试第 dp[i-1][j-1]+1 层
     * 如果测试时鸡蛋碎掉了，则可以通过 i−1 次移动和 j−1 个鸡蛋来找到最高不会碎掉楼层，因为楼层不会超过 dp[i-1][j-1] 了；
     * 如果鸡蛋没有碎掉，则在此基础上，可以使用 i−1 次移动和 j 个鸡蛋，再继续向上检查 dp[i-1][j] 层
     * 因此可以通过 i 步和 j 个鸡蛋来找到 [0, dp[i-1][j-1] + dp[i-1][j] + 1] 范围内的楼层
     * 即 dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j] + 1
     * 因为当前的操作次数值的更新只跟上一次操作次数有关，可以使用一个一维数组，其中 dp[i] 表示当前次数下使用i个鸡蛋可以测出的最高楼层
     * dp[i] = dp[i] + dp[i - 1] + 1
     */
    public static int superEggDrop(int K, int N) {
        int[] dp = new int[K + 1];
        int result = 0;
        while (dp[K] < N) {
            result++;
            for (int i = K; i > 0; i--) {
                dp[i] = dp[i] + dp[i - 1] + 1;
            }
        }
        return result;
    }
}
