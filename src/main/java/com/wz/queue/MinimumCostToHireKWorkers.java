package com.wz.queue;

import java.util.*;

/**
 * There are n workers. You are given two integer arrays quality and wage where quality[i] is the quality of the ith worker and wage[i] is the minimum wage expectation for the ith worker.
 * We want to hire exactly k workers to form a paid group. To hire a group of k workers, we must pay them according to the following rules:
 * 1. Every worker in the paid group should be paid in the ratio of their quality compared to other workers in the paid group.
 * 2. Every worker in the paid group must be paid at least their minimum wage expectation.
 * Given the integer k, return the least amount of money needed to form a paid group satisfying the above conditions. Answers within 10-5 of the actual answer will be accepted.
 *
 * Example 1:
 * Input: quality = [10,20,5], wage = [70,50,30], k = 2
 * Output: 105.00000
 * Explanation: We pay 70 to 0th worker and 35 to 2nd worker.
 *
 * Example 2:
 * Input: quality = [3,1,10,10,1], wage = [4,8,2,2,7], k = 3
 * Output: 30.66667
 * Explanation: We pay 4 to 0th worker, 13.33333 to 2nd and 3rd workers separately.
 *
 * Constraints:
 * 1. n == quality.length == wage.length
 * 2. 1 <= k <= n <= 10^4
 * 3. 1 <= quality[i], wage[i] <= 10^4
 */
public class MinimumCostToHireKWorkers {
    public static void main(String[] args) {
        System.out.println(mincostToHireWorkers(new int[]{10, 20, 5}, new int[]{70, 50, 30}, 2));
        System.out.println(mincostToHireWorkers(new int[]{3, 1, 10, 10, 1}, new int[]{4, 8, 2, 2, 7}, 3));
    }

    /**
     * 有 n 个员工，每个员工有个能力值，还有个薪水期望值，从中雇佣 k 个员工，需要满足两个条件：
     * 1. 每个员工的薪水要和其能力值成恒定比例，2. 每个员工的薪水不低于其期望值。求雇佣 k 个员工的最小花费是多少
     * 要求薪水和能力成比例，而这个比例一定是恒定值，只要能求出这个最低的薪水能力比例值，再乘以 k 个员工的总能力值，就可以得到最少的花费
     * 可以先按照 wage/quality 从小到大对员工进行排序，排在前面的薪水能力比例值较小，然后再用一个大根堆来保存员工的 quality，
     * 遍历排序后的员工列表，将其加入堆中，同时累加 quality，若人数大于 k 了，则移除堆顶，因为堆顶 quality 最大，意味着总付出最多
     * 人数刚好等于 k 时，则用当前总的能力值 totalQuality 乘以 当前员工的薪水能力比例值 得到一个总花费，来更新结果
     * 因为当前员工的薪水能力比例值是大于堆中其他所有员工的，那么乘以恒定的总能力值，得出的总薪水数一定大于等于使用其他员工的薪水能力比例值，
     * 则每个员工可得到的薪水一定是大于等于其期望值的，这样就同时满足了两个条件
     */
    public static double mincostToHireWorkers(int[] quality, int[] wage, int k) {
        List<Worker> workers = new ArrayList<>(quality.length);
        for (int i = 0; i < quality.length; i++) {
            workers.add(new Worker(quality[i], wage[i]));
        }
        // 按 薪水能力比例值 排序
        workers.sort(Comparator.comparingDouble(o -> o.ratio));

        Queue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());
        int totalQuality = 0;
        double result = Integer.MAX_VALUE;
        for (Worker worker : workers) {
            queue.offer(worker.quality);
            totalQuality += worker.quality;
            // 移除能力值最大的
            if (queue.size() > k) {
                totalQuality -= queue.poll();
            }
            if (queue.size() == k) {
                // 总能力值 * 薪水能力比例值
                result = Math.min(result, totalQuality * worker.ratio);
            }
        }
        return result;
    }

    private static class Worker {
        private final int quality;
        private final double ratio;

        public Worker(int quality, int wage) {
            this.quality = quality;
            this.ratio = (double) wage / this.quality;
        }
    }
}
