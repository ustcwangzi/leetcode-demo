package com.wz.binarysearch;

import java.util.Arrays;
import java.util.TreeMap;

/**
 * You are given an array of intervals, where intervals[i] = [starti, endi] and each starti is unique.
 * The right interval for an interval i is an interval j such that startj >= endi and startj is minimized.
 * Return an array of right interval indices for each interval i. If no right interval exists for interval i, then put -1 at index i.
 *
 * Example 1:
 * Input: intervals = [[1,2]]
 * Output: [-1]
 * Explanation: There is only one interval in the collection, so it outputs -1.
 *
 * Example 2:
 * Input: intervals = [[3,4],[2,3],[1,2]]
 * Output: [-1,0,1]
 * Explanation: There is no right interval for [3,4].
 * The right interval for [2,3] is [3,4] since start0 = 3 is the smallest start that is >= end1 = 3.
 * The right interval for [1,2] is [2,3] since start1 = 2 is the smallest start that is >= end2 = 2.
 *
 * Example 3:
 * Input: intervals = [[1,4],[2,3],[3,4]]
 * Output: [-1,2,-1]
 * Explanation: There is no right interval for [1,4] and [3,4].
 * The right interval for [2,3] is [3,4] since start2 = 3 is the smallest start that is >= end1 = 3.
 *
 * Constraints:
 * 1. 1 <= intervals.length <= 2 * 104
 * 2. intervals[i].length == 2
 * 3. -106 <= starti <= endi <= 106
 * 4. The start point of each interval is unique.
 */
public class FindRightInterval {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(findRightInterval(new int[][]{{3, 4}, {2, 3}, {1, 2}})));
        System.out.println(Arrays.toString(findRightInterval(new int[][]{{1, 4}, {2, 3}, {3, 4}})));
    }

    /**
     * TreeMap
     * 需要寻找大于 end 的最小 start，因此首先想到 TreeMap 的 ceilingKey
     * 将全部 start 存入 TreeMap 中，然后遍历 intervals，在 TreeMap 寻找大于 end 的最小 start
     */
    public static int[] findRightInterval(int[][] intervals) {
        int n = intervals.length;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            map.put(intervals[i][0], i);
        }

        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            // 大于 intervals[i][1] 的最小 key
            Integer ceilingKey = map.ceilingKey(intervals[i][1]);
            result[i] = ceilingKey == null ? -1 : map.get(ceilingKey);
        }
        return result;
    }
}
