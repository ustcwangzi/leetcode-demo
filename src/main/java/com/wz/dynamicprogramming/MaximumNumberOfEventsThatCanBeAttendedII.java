package com.wz.dynamicprogramming;

import java.util.Arrays;
import java.util.Comparator;

/**
 * You are given an array of events where events[i] = [startDayi, endDayi, valuei]. The ith event starts at startDayi and ends at endDayi,
 * and if you attend this event, you will receive a value of valuei. You are also given an integer k which represents the maximum number of events you can attend.
 * You can only attend one event at a time. If you choose to attend an event, you must attend the entire event.
 * Note that the end day is inclusive: that is, you cannot attend two events where one of them starts and the other ends on the same day.
 * Return the maximum sum of values that you can receive by attending events.
 *
 * Example 1:
 * @link ../../../../resource/MaximumNumberOfEventsThatCanBeAttendedII1.jpg
 * Input: events = [[1,2,4],[3,4,3],[2,3,1]], k = 2
 * Output: 7
 * Explanation: Choose the green events, 0 and 1 (0-indexed) for a total value of 4 + 3 = 7.
 *
 * Example 2:
 * @link ../../../../resource/MaximumNumberOfEventsThatCanBeAttendedII2.jpg
 * Input: events = [[1,2,4],[3,4,3],[2,3,10]], k = 2
 * Output: 10
 * Explanation: Choose event 2 for a total value of 10.
 * Notice that you cannot attend any other event as they overlap, and that you do not have to attend k events.
 *
 * Example 3:
 * @link ../../../../resource/MaximumNumberOfEventsThatCanBeAttendedII3.jpg
 * Input: events = [[1,1,1],[2,2,2],[3,3,3],[4,4,4]], k = 3
 * Output: 9
 * Explanation: Although the events do not overlap, you can only attend 3 events. Pick the highest valued three.
 *
 *
 * Constraints:
 * 1. 1 <= k <= events.length
 * 2. 1 <= k * events.length <= 10^6
 * 3. 1 <= startDayi <= endDayi <= 10^9
 * 4. 1 <= valuei <= 10^6
 */
public class MaximumNumberOfEventsThatCanBeAttendedII {
    public static void main(String[] args) {
        System.out.println(maxValue(new int[][]{{1, 2, 4}, {3, 4, 3}, {2, 3, 1}}, 2));
        System.out.println(maxValue(new int[][]{{1, 2, 4}, {3, 4, 3}, {2, 3, 10}}, 2));
        System.out.println(maxValue(new int[][]{{1, 1, 1}, {2, 2, 2}, {3, 3, 3}, {4, 4, 4}}, 3));
    }

    /**
     * 动态规划 + DFS
     * 首先将会议按开始时间排序，dp[i][j] 表示在前 i 个会议中参加 j 个会议得到的最大价值
     * 则 dp[i][j] = max{dp[i - 1][j], dp[最后一个时间不冲突的会议][j - 1] + events[排序后的第 i 个会议]}。
     * 因为会议已按开始时间排序，可用二分法查找最后一个时间不冲突的会议
     */
    public static int maxValue(int[][] events, int k) {
        Arrays.parallelSort(events, Comparator.comparingInt(o -> o[0]));
        int n = events.length;
        int[][] dp = new int[n][k];
        Arrays.stream(dp).forEach(array -> Arrays.fill(array, -1));
        return dfs(events, dp, 0, k);
    }

    private static int dfs(int[][] events, int[][] dp, int i, int k) {
        if (i == events.length || k == 0) {
            return 0;
        }
        if (dp[i][k - 1] != -1) {
            return dp[i][k - 1];
        }

        int next = binarySearch(events, i);
        // 选择下一个会议、不选择下一个会议，两者取最大值
        return dp[i][k - 1] = Math.max(events[i][2] + dfs(events, dp, next, k - 1), dfs(events, dp, i + 1, k));
    }

    private static int binarySearch(int[][] events, int i) {
        int left = i + 1, right = events.length - 1, result = events.length;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (events[mid][0] > events[i][1]) {
                result = Math.min(result, mid);
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return result;
    }
}
