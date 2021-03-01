package com.wz.greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Given an array of events where events[i] = [startDayi, endDayi]. Every event i starts at startDayi and ends at endDayi.
 * You can attend an event i at any day d where startTimei <= d <= endTimei. Notice that you can only attend one event at any time d.
 * Return the maximum number of events you can attend.
 *
 * Example 1:
 * @see ../../../../resource/MaximumNumberOfEventsThatCanBeAttended.jpg
 * Input: events = [[1,2],[2,3],[3,4]]
 * Output: 3
 * Explanation: You can attend all the three events.
 * One way to attend them all is as shown.
 * Attend the first event on day 1.
 * Attend the second event on day 2.
 * Attend the third event on day 3.
 *
 * Example 2:
 * Input: events= [[1,2],[2,3],[3,4],[1,2]]
 * Output: 4
 *
 * Example 3:
 * Input: events = [[1,1],[1,2],[1,3],[1,4],[1,5],[1,6],[1,7]]
 * Output: 7
 *
 * Constraints:
 * 1. 1 <= events.length <= 10^5
 * 2. events[i].length == 2
 * 3. 1 <= startDayi <= endDayi <= 10^5
 */
public class MaximumNumberOfEventsThatCanBeAttended {
    public static void main(String[] args) {
        System.out.println(maxEvents(new int[][]{{1, 2}, {2, 3}, {3, 4}, {1, 2}}));
        System.out.println(maxEvents(new int[][]{{1, 1}, {1, 2}, {1, 3}, {1, 4}, {1, 5}, {1, 6}, {1, 7}}));
    }

    /**
     * 对每一天，在当天所有 events 中选择结束时间最早的进行安排
     * 为快速选择结束时间最早的任务，使用优先级队列存储任务
     */
    public static int maxEvents(int[][] events) {
        Arrays.parallelSort(events, Comparator.comparingInt(o -> o[0]));
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> {
            if (o1[1] == o2[1]) {
                return Integer.compare(o1[0], o2[0]);
            }
            return Integer.compare(o1[1], o2[1]);
        });

        int result = 0, index = 0;
        for (int day = 1; day <= 100000; day++) {
            // 将当天的任务全部加入优先级队列
            while (index < events.length && events[index][0] == day) {
                queue.offer(events[index++]);
            }
            // 移除超过时间限制的任务
            while (!queue.isEmpty() && queue.peek()[1] < day) {
                queue.poll();
            }
            // 选择最早结束的任务
            if (!queue.isEmpty()) {
                queue.poll();
                result++;
            }
        }
        return result;
    }
}
