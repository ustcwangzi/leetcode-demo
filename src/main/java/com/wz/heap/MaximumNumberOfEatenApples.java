package com.wz.heap;

import java.util.AbstractMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * There is a special kind of apple tree that grows apples every day for n days. On the ith day, the tree grows apples[i] apples
 * that will rot after days[i] days, that is on day i + days[i] the apples will be rotten and cannot be eaten.
 * On some days, the apple tree does not grow any apples, which are denoted by apples[i] == 0 and days[i] == 0.
 * You decided to eat at most one apple a day (to keep the doctors away). Note that you can keep eating after the first n days.
 * Given two integer arrays days and apples of length n, return the maximum number of apples you can eat.
 *
 * Example 1:
 * Input: apples = [1,2,3,5,2], days = [3,2,1,4,2]
 * Output: 7
 * Explanation: You can eat 7 apples:
 * - On the first day, you eat an apple that grew on the first day.
 * - On the second day, you eat an apple that grew on the second day.
 * - On the third day, you eat an apple that grew on the second day. After this day, the apples that grew on the third day rot.
 * - On the fourth to the seventh days, you eat apples that grew on the fourth day.
 *
 * Example 2:
 * Input: apples = [3,0,0,0,0,2], days = [3,0,0,0,0,2]
 * Output: 5
 * Explanation: You can eat 5 apples:
 * - On the first to the third day you eat apples that grew on the first day.
 * - Do nothing on the fouth and fifth days.
 * - On the sixth and seventh days you eat apples that grew on the sixth day.
 *
 * Constraints:
 * 1. apples.length == n
 * 2. days.length == n
 * 3. 1 <= n <= 2 * 104
 * 4. 0 <= apples[i], days[i] <= 2 * 10^4
 * 5. days[i] = 0 if and only if apples[i] = 0.
 */
public class MaximumNumberOfEatenApples {
    public static void main(String[] args) {
        System.out.println(eatenApples(new int[]{1, 2, 3, 5, 2}, new int[]{3, 2, 1, 4, 2}));
        System.out.println(eatenApples(new int[]{3, 0, 0, 0, 0, 2}, new int[]{3, 0, 0, 0, 0, 2}));
    }

    /**
     * 小根堆
     * 使用小根堆存储每个苹果和该苹果腐烂的时间，腐烂时间最近的排在堆顶，对于当前时间 index
     * 将堆中腐烂时间小于等于 index 的全部移除，然后吃掉堆顶的一个苹果，若还剩下苹果继续加入堆中
     */
    public static int eatenApples(int[] apples, int[] days) {
        int n = apples.length, result = 0;
        PriorityQueue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>(n, Map.Entry.comparingByValue());

        int index = -1;
        while (index++ < n || !queue.isEmpty()) {
            if (index < n && apples[index] > 0) {
                queue.offer(new AbstractMap.SimpleEntry<>(apples[index], index + days[index]));
            }
            // 移除所有腐烂的苹果
            while (!queue.isEmpty() && queue.peek().getValue() <= index) {
                queue.poll();
            }
            if (queue.isEmpty()) {
                continue;
            }

            result++;
            Map.Entry<Integer, Integer> top = queue.poll();
            // 吃掉一个苹果
            if (top.getKey() > 1) {
                queue.offer(new AbstractMap.SimpleEntry<>(top.getKey() - 1, top.getValue()));
            }
        }

        return result;
    }
}
