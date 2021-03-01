package com.wz.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Given a list of intervals, remove all intervals that are covered by another interval in the list.
 *
 * Interval [a,b) is covered by interval [c,d) if and only if c <= a and b <= d.
 *
 * After doing so, return the number of remaining intervals.
 *
 *
 *
 * Example 1:
 *
 * Input: intervals = [[1,4],[3,6],[2,8]]
 * Output: 2
 * Explanation: Interval [3,6] is covered by [2,8], therefore it is removed.
 *
 * Example 2:
 * Input: intervals = [[0,10],[5,12]]
 * Output: 2
 *
 * Constraints:
 * 1. 1 <= intervals.length <= 1000
 * 2. intervals[i].length == 2
 * 3. 0 <= intervals[i][0] < intervals[i][1] <= 10^5
 * 4. All the intervals are unique.
 */
public class RemoveCoveredIntervals {
    public static void main(String[] args) {
        System.out.println(removeCoveredIntervals(new int[][]{{1, 4}, {3, 6}, {2, 8}}));
        System.out.println(removeCoveredIntervals(new int[][]{{0, 10}, {5, 12}}));
    }

    /**
     * 按照 intervals[0][j] 对数组进行排序，使用 left、right 记录当前最大区间
     * 遍历数组，当前区间完全在当前最大区间右侧时，结果加一，同时更新最大区间
     * 否则说明区间发生重叠，left 不变、right 更新
     */
    public static int removeCoveredIntervals(int[][] intervals) {
        Arrays.parallelSort(intervals, Comparator.comparingInt(o -> o[0]));

        int result = 0, left = -1, right = -1;
        for (int[] interval : intervals) {
            if (interval[0] > left && interval[1] > right) {
                result++;
                left = interval[0];
            }
            right = Math.max(right, interval[1]);
        }
        return result;
    }
}
