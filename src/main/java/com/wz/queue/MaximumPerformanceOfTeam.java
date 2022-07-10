package com.wz.queue;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * You are given two integers n and k and two integer arrays speed and efficiency both of length n.
 * There are n engineers numbered from 1 to n. speed[i] and efficiency[i] represent the speed and efficiency of the ith engineer respectively.
 * Choose at most k different engineers out of the n engineers to form a team with the maximum performance.
 * The performance of a team is the sum of their engineers' speeds multiplied by the minimum efficiency among their engineers.
 * Return the maximum performance of this team. Since the answer can be a huge number, return it modulo 109 + 7.
 *
 * Example 1:
 * Input: n = 6, speed = [2,10,3,1,5,8], efficiency = [5,4,3,9,7,2], k = 2
 * Output: 60
 * Explanation:
 * We have the maximum performance of the team by selecting engineer 2 (with speed=10 and efficiency=4) and
 * engineer 5 (with speed=5 and efficiency=7). That is, performance = (10 + 5) * min(4, 7) = 60.
 *
 * Example 2:
 * Input: n = 6, speed = [2,10,3,1,5,8], efficiency = [5,4,3,9,7,2], k = 3
 * Output: 68
 * Explanation:
 * This is the same example as the first but k = 3. We can select engineer 1, engineer 2 and engineer 5
 * to get the maximum performance of the team. That is, performance = (2 + 10 + 5) * min(5, 4, 7) = 68.
 *
 * Example 3:
 * Input: n = 6, speed = [2,10,3,1,5,8], efficiency = [5,4,3,9,7,2], k = 4
 * Output: 72
 *
 * Constraints:
 * 1. 1 <= k <= n <= 10^5
 * 2. speed.length == n
 * 3. efficiency.length == n
 * 4. 1 <= speed[i] <= 10^5
 * 5. 1 <= efficiency[i] <= 10^8
 */
public class MaximumPerformanceOfTeam {
    public static void main(String[] args) {
        System.out.println(maxPerformance(6, new int[]{2, 10, 3, 1, 5, 8}, new int[]{5, 4, 3, 9, 7, 2}, 2));
        System.out.println(maxPerformance(6, new int[]{2, 10, 3, 1, 5, 8}, new int[]{5, 4, 3, 9, 7, 2}, 3));
        System.out.println(maxPerformance(6, new int[]{2, 10, 3, 1, 5, 8}, new int[]{5, 4, 3, 9, 7, 2}, 4));
    }

    /**
     * 将 speed、efficiency 组合生成 engineers，然后对 engineers 按照 efficiency 从大到小排序
     * 遍历排序后的 engineers，维护一个长度为 k 的小根堆存储 speed，因为 efficiency 已逆序排序，当前 efficiency 一定是 k 个人中最小的
     * 直接使用 speed 之和 * efficiency 即可得到 performance，当堆的长度大于 k 时，直接弹出堆顶元素
     */
    public static int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
        int[][] engineers = new int[n][2];
        for (int i = 0; i < n; i++) {
            engineers[i] = new int[]{speed[i], efficiency[i]};
        }
        Arrays.parallelSort(engineers, (o1, o2) -> Integer.compare(o2[1], o1[1]));

        Queue<Integer> queue = new PriorityQueue<>(k);
        long result = 0, sum = 0;
        int mod = 1000000007;
        for (int[] engineer : engineers) {
            queue.offer(engineer[0]);
            sum += engineer[0];
            if (queue.size() > k) {
                sum -= queue.poll();
            }
            result = Math.max(result, sum * engineer[1]);
        }
        return (int) (result % mod);
    }
}
