package com.wz.heap;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * There is a party where n friends numbered from 0 to n - 1 are attending. There is an infinite number of chairs in this party that are numbered from 0 to infinity.
 * When a friend arrives at the party, they sit on the unoccupied chair with the smallest number.
 * For example, if chairs 0, 1, and 5 are occupied when a friend comes, they will sit on chair number 2.
 * When a friend leaves the party, their chair becomes unoccupied at the moment they leave.
 * If another friend arrives at that same moment, they can sit in that chair.
 * You are given a 0-indexed 2D integer array times where times[i] = [arrivali, leavingi],
 * indicating the arrival and leaving times of the ith friend respectively, and an integer targetFriend. All arrival times are distinct.
 * Return the chair number that the friend numbered targetFriend will sit on.
 *
 * Example 1:
 * Input: times = [[1,4],[2,3],[4,6]], targetFriend = 1
 * Output: 1
 * Explanation:
 * - Friend 0 arrives at time 1 and sits on chair 0.
 * - Friend 1 arrives at time 2 and sits on chair 1.
 * - Friend 1 leaves at time 3 and chair 1 becomes empty.
 * - Friend 0 leaves at time 4 and chair 0 becomes empty.
 * - Friend 2 arrives at time 4 and sits on chair 0.
 * Since friend 1 sat on chair 1, we return 1.
 *
 * Example 2:
 * Input: times = [[3,10],[1,5],[2,6]], targetFriend = 0
 * Output: 2
 * Explanation:
 * - Friend 1 arrives at time 1 and sits on chair 0.
 * - Friend 2 arrives at time 2 and sits on chair 1.
 * - Friend 0 arrives at time 3 and sits on chair 2.
 * - Friend 1 leaves at time 5 and chair 0 becomes empty.
 * - Friend 2 leaves at time 6 and chair 1 becomes empty.
 * - Friend 0 leaves at time 10 and chair 2 becomes empty.
 * Since friend 0 sat on chair 2, we return 2.
 *
 * Constraints:
 * 1. n == times.length
 * 2. 2 <= n <= 10^4
 * 3. times[i].length == 2
 * 4. 1 <= arrivali < leavingi <= 10^5
 * 5. 0 <= targetFriend <= n - 1
 * 6. Each arrivali time is distinct.
 */
public class TheNumberOfTheSmallestUnoccupiedChair {
    public static void main(String[] args) {
        System.out.println(smallestChair(new int[][]{{1, 4}, {2, 3}, {4, 6}}, 1));
    }

    /**
     * 用三个小根堆，分别存放到达、离开的时间及对应的人员编号、和可使用的椅子编号，然后模拟朋友们的来访过程，直至目标朋友到达
     */
    public static int smallestChair(int[][] times, int targetFriend) {
        int n = times.length, targetTime = times[targetFriend][0];
        Queue<int[]> arriveTimeQueue = new PriorityQueue<>((Comparator.comparingInt(o -> o[0])));
        Queue<int[]> leaveTimeQueue = new PriorityQueue<>((Comparator.comparingInt(o -> o[0])));
        Queue<Integer> chairQueue = new PriorityQueue<>(n);
        for (int i = 0; i < n; i++) {
            chairQueue.offer(i);
            if (times[i][0] <= targetTime) {
                arriveTimeQueue.offer(new int[]{times[i][0], i});
            }
            if (times[i][1] <= targetTime) {
                leaveTimeQueue.offer(new int[]{times[i][1], i});
            }
        }

        int[] unoccupied = new int[n];
        // 当前时刻
        int now = arriveTimeQueue.peek()[0];
        while (now < targetTime) {
            // 当前到达的朋友使用椅子
            unoccupied[arriveTimeQueue.poll()[1]] = chairQueue.poll();
            // 时间跳到下一个朋友到达
            now = arriveTimeQueue.peek()[0];
            // 之前的朋友归还椅子
            while (!leaveTimeQueue.isEmpty() && leaveTimeQueue.peek()[0] <= now) {
                chairQueue.offer(unoccupied[leaveTimeQueue.poll()[1]]);
            }
        }
        // 目标朋友到达
        return chairQueue.peek();
    }
}
