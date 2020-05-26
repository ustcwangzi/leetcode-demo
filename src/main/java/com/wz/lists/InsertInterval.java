package com.wz.lists;

import java.util.Arrays;

/**
 * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
 * You may assume that the intervals were initially sorted according to their start times.
 *
 * Example 1:
 * Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
 * Output: [[1,5],[6,9]]
 *
 * Example 2:
 * Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * Output: [[1,2],[3,10],[12,16]]
 * Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
 */
public class InsertInterval {
    public static void main(String[] args) {
        int[][] intervals = {{1, 3}, {6, 9}};
        intervals = insert(intervals, new int[]{2, 5});
        Arrays.stream(intervals).forEach(array -> System.out.print(array[0] + "," + array[1] + " "));

        System.out.println();

        intervals = new int[][]{{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}};
        intervals = insert(intervals, new int[]{4, 8});
        Arrays.stream(intervals).forEach(array -> System.out.print(array[0] + "," + array[1] + " "));

        System.out.println();

        intervals = new int[][]{{1, 5}};
        intervals = insert(intervals, new int[]{6, 8});
        Arrays.stream(intervals).forEach(array -> System.out.print(array[0] + "," + array[1] + " "));
    }

    /**
     * 思想与{@link MergeIntervals}类似，将newInterval加入到intervals数组中之后进行合并操作
     */
    public static int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals.length == 0) {
            return new int[][]{newInterval};
        }

        int[][] tmp = new int[intervals.length + 1][2];
        System.arraycopy(intervals, 0, tmp, 0, intervals.length);
        tmp[intervals.length] = newInterval;
        Arrays.sort(tmp, ((o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o1[1] - o2[1];
            } else {
                return o1[0] - o2[0];
            }
        }));

        int size = tmp.length;
        for (int i = 1; i < tmp.length; i++) {
            int[] pre = tmp[i - 1];
            int[] cur = tmp[i];
            if (pre[1] >= cur[0]) {
                int[] mergeResult = {pre[0], Math.max(pre[1], cur[1])};
                tmp[i] = mergeResult;
                int index = i - 1;
                // 前面可能存在多个相同的元素，都需要进行替换
                while (index >= 0 && Arrays.equals(pre, tmp[index])) {
                    tmp[index--] = mergeResult;
                }
                size--;
            }
        }

        int[][] result = new int[size][2];
        result[0] = tmp[0];
        if (size == 1) {
            return result;
        }

        size = 1;
        for (int i = 1; i < tmp.length; i++) {
            if (!Arrays.equals(result[size - 1], tmp[i])) {
                result[size++] = tmp[i];
            }
        }
        return result;
    }
}
