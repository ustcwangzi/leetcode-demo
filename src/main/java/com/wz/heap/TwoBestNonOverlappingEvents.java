package com.wz.heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * You are given a 0-indexed 2D integer array of events where events[i] = [startTimei, endTimei, valuei].
 * The ith event starts at startTimei and ends at endTimei, and if you attend this event, you will receive a value of valuei.
 * You can choose at most two non-overlapping events to attend such that the sum of their values is maximized.
 * Return this maximum sum.
 * Note that the start time and end time is inclusive:
 * that is, you cannot attend two events where one of them starts and the other ends at the same time.
 * More specifically, if you attend an event with end time t, the next event must start at or after t + 1.
 *
 * Example 1:
 * @link ../../../../resource/TwoBestNonOverlappingEvents1.jpg
 * Input: events = [[1,3,2],[4,5,2],[2,4,3]]
 * Output: 4
 * Explanation: Choose the green events, 0 and 1 for a sum of 2 + 2 = 4.
 *
 * Example 2:
 * @link ../../../../resource/TwoBestNonOverlappingEvents2.jpg
 * Example 1 Diagram
 * Input: events = [[1,3,2],[4,5,2],[1,5,5]]
 * Output: 5
 * Explanation: Choose event 2 for a sum of 5.
 *
 * Example 3:
 * @link ../../../../resource/TwoBestNonOverlappingEvents3.jpg
 * Input: events = [[1,5,3],[1,5,1],[6,6,5]]
 * Output: 8
 * Explanation: Choose events 0 and 2 for a sum of 3 + 5 = 8.
 *
 * Constraints:
 * 1. 2 <= events.length <= 10^5
 * 2. events[i].length == 3
 * 3. 1 <= startTimei <= endTimei <= 10^9
 * 4. 1 <= valuei <= 10^6
 */
public class TwoBestNonOverlappingEvents {
    public static void main(String[] args) {
        System.out.println(maxTwoEvents(new int[][]{{1, 3, 2}, {4, 5, 2}, {2, 4, 3}}));
        System.out.println(maxTwoEvents(new int[][]{{1, 3, 2}, {4, 5, 2}, {1, 5, 5}}));
        System.out.println(maxTwoEvents(new int[][]{{1, 5, 3}, {1, 5, 1}, {6, 6, 5}}));
    }

    /**
     * 最多两次不重叠的 event，因此可以直接遍历每个 event，找到它之前的最大不重叠 event 相加即可
     * 为避免重复遍历和快速找到每个 event 之前的不重叠 event，使用 PriorityQueue 存储，并按照结束时间排序
     * 同时 events 按照开始时间排序，遍历 events，对于当前 event：
     * 若 PriorityQueue 堆顶结束时间小于当前 event 开始时间，说明不重叠，更新最大 value
     * 然后将当前 event 加入堆中，并根据求出的最大 value 更新最大结果
     */
    public static int maxTwoEvents(int[][] events) {
        Arrays.parallelSort(events, Comparator.comparingInt(o -> o[0]));
        Queue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        int result = 0, maxValue = 0;
        for (int[] event : events) {
            while (!queue.isEmpty() && event[0] > queue.peek()[1]) {
                maxValue = Math.max(maxValue, queue.poll()[2]);
            }
            queue.offer(event);
            result = Math.max(result, maxValue + event[2]);
        }
        return result;
    }
}
