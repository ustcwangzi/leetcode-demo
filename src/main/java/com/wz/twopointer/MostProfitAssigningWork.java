package com.wz.twopointer;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * We have jobs: difficulty[i] is the difficulty of the ith job, and profit[i] is the profit of the ith job.
 * Now we have some workers. worker[i] is the ability of the ith worker, which means that this worker can only complete a job with difficulty at most worker[i].
 * Every worker can be assigned at most one job, but one job can be completed multiple times.
 * For example, if 3 people attempt the same job that pays $1, then the total profit will be $3.  If a worker cannot complete any job, his profit is $0.
 * What is the most profit we can make?
 *
 * Example 1:
 * Input: difficulty = [2,4,6,8,10], profit = [10,20,30,40,50], worker = [4,5,6,7]
 * Output: 100
 * Explanation: Workers are assigned jobs of difficulty [4,4,6,6] and they get profit of [20,20,30,30] seperately.
 *
 * Notes:
 * 1. 1 <= difficulty.length = profit.length <= 10000
 * 2. 1 <= worker.length <= 10000
 * 3. difficulty[i], profit[i], worker[i]  are in range [1, 10^5]
 */
public class MostProfitAssigningWork {
    public static void main(String[] args) {
        System.out.println(maxProfitAssignment(new int[]{2, 4, 6, 8, 10}, new int[]{10, 20, 30, 40, 50}, new int[]{4, 5, 6, 7}));
    }

    /**
     * 大根堆，优先选择收益最高的工作
     * 将 profit 和 difficulty 按照 profit 放到大根堆中，然后将 worker 排序
     * 逆序遍历 worker，如果堆顶难度大于当前 worker，则弹出堆顶，因为剩余的 worker 也处理不了，否则就将堆顶的收益加入到结果中
     */
    public static int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> o2[0] - o1[0]);
        for (int i = 0; i < difficulty.length; i++) {
            queue.offer(new int[]{profit[i], difficulty[i]});
        }

        Arrays.parallelSort(worker);
        int result = 0;
        for (int i = worker.length - 1; i >= 0; i--) {
            // 将无法处理的工作弹出
            while (!queue.isEmpty() && queue.peek()[1] > worker[i]) {
                queue.poll();
            }
            if (queue.isEmpty()) {
                break;
            }
            result += queue.peek()[0];
        }
        return result;
    }
}
