package com.wz.queue;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Suppose LeetCode will start its IPO soon. In order to sell a good price of its shares to Venture Capital,
 * LeetCode would like to work on some projects to increase its capital before the IPO. Since it has limited resources,
 * it can only finish at most k distinct projects before the IPO. Help LeetCode design the best way to maximize its total capital after finishing at most k distinct projects.
 * You are given n projects where the ith project has a pure profit profits[i] and a minimum capital of capital[i] is needed to start it.
 * Initially, you have w capital. When you finish a project, you will obtain its pure profit and the profit will be added to your total capital.
 * Pick a list of at most k distinct projects from given projects to maximize your final capital, and return the final maximized capital.
 * The answer is guaranteed to fit in a 32-bit signed integer.
 *
 * Example 1:
 * Input: k = 2, w = 0, profits = [1,2,3], capital = [0,1,1]
 * Output: 4
 * Explanation: Since your initial capital is 0, you can only start the project indexed 0.
 * After finishing it you will obtain profit 1 and your capital becomes 1.
 * With capital 1, you can either start the project indexed 1 or the project indexed 2.
 * Since you can choose at most 2 projects, you need to finish the project indexed 2 to get the maximum capital.
 * Therefore, output the final maximized capital, which is 0 + 1 + 3 = 4.
 *
 * Example 2:
 * Input: k = 3, w = 0, profits = [1,2,3], capital = [0,1,2]
 * Output: 6
 *
 * Constraints:
 * 1. 1 <= k <= 10^5
 * 2. 0 <= w <= 10^9
 * 3. n == profits.length
 * 4. n == capital.length
 * 5. 1 <= n <= 10^5
 * 6. 0 <= profits[i] <= 10^4
 * 7. 0 <= capital[i] <= 10^9
 */
public class IPO {
    public static void main(String[] args) {
        System.out.println(findMaximizedCapital(2, 0, new int[]{1, 2, 3}, new int[]{0, 1, 1}));
        System.out.println(findMaximizedCapital(3, 0, new int[]{1, 2, 3}, new int[]{0, 1, 2}));
    }

    /**
     * 贪心 + 优先级队列
     * 初始资金 w，完成第 i 个项目需要资金 capital[i]，可以获取收益 profits[i]，最多完成 k 个项目可以获取的最大资金为多少
     * 每次都选择当前资金下能获取的最大收益，那么最终结果就是最大的
     * 对于当前资金 total，从 capital[] 中选择 <= total 的所有项目，从这些项目中选择一个 profit 最大的，作为这一轮的选择，依次下去
     * 可以看到，有两次选择：第一次选择 capital 满足条件的、第二次选择 profit 最大的，因此可以使用两个优先级队列
     * 一个优先级队列为小根堆，按照 capital 排序，另一个优先级队列为大根堆，按照 profit 排序
     */
    public static int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int n = profits.length;
        Queue<Integer> minQueue = new PriorityQueue<>(n, Comparator.comparingInt(o -> capital[o]));
        Queue<Integer> maxQueue = new PriorityQueue<>(n, (o1, o2) -> Integer.compare(profits[o2], profits[o1]));

        // 初始时将所有项目都加入小根堆中
        for (int i = 0; i < n; i++) {
            minQueue.offer(i);
        }

        int total = w;
        for (int i = 0; i < k; i++) {
            // 将满足资金要求的项目全部加入大根堆中
            while (!minQueue.isEmpty() && capital[minQueue.peek()] <= total) {
                maxQueue.offer(minQueue.poll());
            }
            if (maxQueue.isEmpty()) {
                break;
            }
            // 每一轮选择一个收益最高的
            total += profits[maxQueue.poll()];
        }
        return total;
    }
}
