package com.wz.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Given a collection of intervals, find the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.
 *
 * Example 1:
 * Input: [[1,2],[2,3],[3,4],[1,3]]
 * Output: 1
 * Explanation: [1,3] can be removed and the rest of intervals are non-overlapping.
 *
 * Example 2:
 * Input: [[1,2],[1,2],[1,2]]
 * Output: 2
 * Explanation: You need to remove two [1,2] to make the rest of intervals non-overlapping.
 *
 * Example 3:
 * Input: [[1,2],[2,3]]
 * Output: 0
 * Explanation: You don't need to remove any of the intervals since they're already non-overlapping.
 *
 * Note:
 * 1. You may assume the interval's end point is always bigger than its start point.
 * 2. Intervals like [1,2] and [2,3] have borders "touching" but they don't overlap each other.
 */
public class NonOverlappingIntervals {
    public static void main(String[] args) {
        System.out.println(eraseOverlapIntervals(new int[][]{{1, 2}, {2, 3}, {3, 4}, {1, 3}}));
    }

    /**
     * 对数组进行排序，以第二个元素从小到大排序，然后记录结尾元素 end
     * 遍历排序后的数组，若当前的第一个元素小于 end，则说明发生了重叠，结果加一，否则更新 end
     */
    public static int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length <= 1) {
            return 0;
        }
        Arrays.parallelSort(intervals, Comparator.comparingInt(o -> o[1]));

        int result = 0, end = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < end) {
                result++;
            } else {
                end = intervals[i][1];
            }
        }
        return result;
    }
}
