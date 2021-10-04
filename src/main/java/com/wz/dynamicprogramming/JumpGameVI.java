package com.wz.dynamicprogramming;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * You are given a 0-indexed integer array nums and an integer k.
 * You are initially standing at index 0. In one move, you can jump at most k steps forward without going outside the boundaries of the array.
 * That is, you can jump from index i to any index in the range [i + 1, min(n - 1, i + k)] inclusive.
 * You want to reach the last index of the array (index n - 1). Your score is the sum of all nums[j] for each index j you visited in the array.
 * Return the maximum score you can get.
 *
 * Example 1:
 * Input: nums = [1,-1,-2,4,-7,3], k = 2
 * Output: 7
 * Explanation: You can choose your jumps forming the subsequence [1,-1,4,3]. The sum is 7.
 *
 * Example 2:
 * Input: nums = [10,-5,-2,4,0,3], k = 3
 * Output: 17
 * Explanation: You can choose your jumps forming the subsequence [10,4,3]. The sum is 17.
 *
 * Constraints:
 * 1. 1 <= nums.length, k <= 10^5
 * 2. -10^4 <= nums[i] <= 10^4
 */
public class JumpGameVI {
    public static void main(String[] args) {
        System.out.println(maxResult(new int[]{1, -1, -2, 4, -7, 3}, 2));
        System.out.println(maxResult(new int[]{10, -5, -2, 4, 0, 3}, 3));
    }

    /**
     * 动态规划 + 优先级队列
     * dp[i] 表示到达位置 i 的最大得分，则 dp[i] = nums[i] + max{dp[i-k ... i-1]}
     * 为快速计算前 k 个位置的最大得分，这里使用优先级队列对得分进行存储，队头就是最大得分
     */
    public static int maxResult(int[] nums, int k) {
        int n = nums.length;
        int[] dp = new int[n];
        Queue<Integer> queue = new PriorityQueue<>((o1, o2) -> Integer.compare(dp[o2], dp[o1]));
        for (int i = 0; i < n; i++) {
            // 当前队头与 i 距离超过 k，弹出
            while (!queue.isEmpty() && i > queue.peek() + k) {
                queue.poll();
            }
            dp[i] = nums[i] + (queue.isEmpty() ? 0 : dp[queue.peek()]);
            queue.offer(i);
        }
        return dp[n - 1];
    }
}
