package com.wz.dynamicprogramming;

/**
 * Your car starts at position 0 and speed +1 on an infinite number line.  (Your car can go into negative positions.)
 * Your car drives automatically according to a sequence of instructions A (accelerate) and R (reverse).
 * When you get an instruction "A", your car does the following: position += speed, speed *= 2.
 * When you get an instruction "R", your car does the following:
 * if your speed is positive then speed = -1 , otherwise speed = 1.  (Your position stays the same.)
 * For example, after commands "AAR", your car goes to positions 0->1->3->3, and your speed goes to 1->2->4->-1.
 * Now for some target position, say the length of the shortest sequence of instructions to get there.
 *
 * Example 1:
 * Input:
 * target = 3
 * Output: 2
 * Explanation:
 * The shortest instruction sequence is "AA".
 * Your position goes from 0->1->3.
 * Example 2:
 * Input:
 * target = 6
 * Output: 5
 * Explanation:
 * The shortest instruction sequence is "AAARA".
 * Your position goes from 0->1->3->7->7->6.
 *
 * Note:
 * 1 <= target <= 10000.
 */
public class RaceCar {
    public static void main(String[] args) {
        System.out.println(racecar(3));
    }

    /**
     * dp[i] 表示在位置 i 的最短步数
     * 有一个特殊情况，就是当前的 pos 正好等于 2^n-1，此时所需步数可以直接运算出来，即 d[i]=n  if i == 2^n-1
     * 如果当前的pos不是最佳情况，那么就有两种策略，一是先经过pos，在往回倒，二是在到达之前进行倒车再前进。
     */
    public static int racecar(int target) {
        int[] dp = new int[target + 1];
        return helper(target, dp);
    }

    private static int helper(int target, int[] dp) {
        if (dp[target] != 0) {
            return dp[target];
        }
        int n = (int) Math.ceil(Math.log(target + 1) / Math.log(2));
        if (1 << n == target + 1) {
            return dp[target] = n;
        }
        dp[target] = n + 1 + helper((1 << n) - 1 - target, dp);
        for (int m = 0; m < n - 1; m++) {
            int pos = (1 << (n - 1)) - (1 << m);
            dp[target] = Math.min(dp[target], n + m + 1 + helper(target - pos, dp));
        }
        return dp[target];
    }
}
