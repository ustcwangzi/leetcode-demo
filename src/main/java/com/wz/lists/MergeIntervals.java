package com.wz.lists;

import java.util.Arrays;

/**
 * Given a collection of intervals, merge all overlapping intervals.
 * <p>
 * Example 1:
 * Input: [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
 * <p>
 * Example 2:
 * Input: [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 */
public class MergeIntervals {
    public static void main(String[] args) {
        int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        intervals = merge(intervals);
        Arrays.stream(intervals).forEach(array -> System.out.print(array[0] + "," + array[1] + " "));

        System.out.println();

        intervals = new int[][]{{1, 4}, {0, 2}, {3, 5}, {4, 9}};
        intervals = merge(intervals);
        Arrays.stream(intervals).forEach(array -> System.out.print(array[0] + "," + array[1] + " "));

        System.out.println();

        intervals = new int[][]{{2, 3}, {5, 5}, {2, 2}, {3, 4}, {3, 4}};
        intervals = merge(intervals);
        Arrays.stream(intervals).forEach(array -> System.out.print(array[0] + "," + array[1] + " "));
    }

    /**
     * 排序后，检查相邻的元素是否可以合并，若可以合并将元素值更新为合并后的结果
     * 最后，收集不相同的元素，就是最终结果
     */
    public static int[][] merge(int[][] intervals) {
        if (intervals.length <= 1) {
            return intervals;
        }
        Arrays.sort(intervals, ((o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o1[1] - o2[1];
            } else {
                return o1[0] - o2[0];
            }
        }));

        int size = intervals.length;
        for (int i = 1; i < intervals.length; i++) {
            int[] pre = intervals[i - 1];
            int[] cur = intervals[i];
            if (pre[1] >= cur[0]) {
                int[] mergeResult = {pre[0], Math.max(pre[1], cur[1])};
                intervals[i] = mergeResult;
                int index = i - 1;
                // 前面可能存在多个相同的元素，都需要进行替换
                while (index >= 0 && Arrays.equals(pre, intervals[index])) {
                    intervals[index--] = mergeResult;
                }
                size--;
            }
        }

        int[][] result = new int[size][2];
        result[0] = intervals[0];
        if (size == 1) {
            return result;
        }

        size = 1;
        for (int i = 1; i < intervals.length; i++) {
            if (!Arrays.equals(result[size - 1], intervals[i])) {
                result[size++] = intervals[i];
            }
        }
        return result;
    }
}
