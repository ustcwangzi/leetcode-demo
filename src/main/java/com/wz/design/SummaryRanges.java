package com.wz.design;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

/**
 * Given a data stream input of non-negative integers a1, a2, ..., an, summarize the numbers seen so far as a list of disjoint intervals.
 * Implement the SummaryRanges class:
 * - SummaryRanges() Initializes the object with an empty stream.
 * - void addNum(int val) Adds the integer val to the stream.
 * - int[][] getIntervals() Returns a summary of the integers in the stream currently as a list of disjoint intervals [starti, endi].
 *
 * Example 1:
 * Input
 * ["SummaryRanges", "addNum", "getIntervals", "addNum", "getIntervals", "addNum", "getIntervals", "addNum", "getIntervals", "addNum", "getIntervals"]
 * [[], [1], [], [3], [], [7], [], [2], [], [6], []]
 * Output
 * [null, null, [[1, 1]], null, [[1, 1], [3, 3]], null, [[1, 1], [3, 3], [7, 7]], null, [[1, 3], [7, 7]], null, [[1, 3], [6, 7]]]
 *
 * Explanation
 * SummaryRanges summaryRanges = new SummaryRanges();
 * summaryRanges.addNum(1);      // arr = [1]
 * summaryRanges.getIntervals(); // return [[1, 1]]
 * summaryRanges.addNum(3);      // arr = [1, 3]
 * summaryRanges.getIntervals(); // return [[1, 1], [3, 3]]
 * summaryRanges.addNum(7);      // arr = [1, 3, 7]
 * summaryRanges.getIntervals(); // return [[1, 1], [3, 3], [7, 7]]
 * summaryRanges.addNum(2);      // arr = [1, 2, 3, 7]
 * summaryRanges.getIntervals(); // return [[1, 3], [7, 7]]
 * summaryRanges.addNum(6);      // arr = [1, 2, 3, 6, 7]
 * summaryRanges.getIntervals(); // return [[1, 3], [6, 7]]
 *
 * Constraints:
 * 1. 0 <= val <= 10^4
 * 2. At most 3 * 10^4 calls will be made to addNum and getIntervals.
 */
public class SummaryRanges {
    public static void main(String[] args) {
        SummaryRanges ranges = new SummaryRanges();
        ranges.addNum(1);
        ranges.addNum(3);
        ranges.addNum(7);
        ranges.addNum(2);
        int[][] result = ranges.getIntervals();
        for (int[] array : result) {
            System.out.print(Arrays.toString(array));
        }

        System.out.println();
        ranges.addNum(6);
        result = ranges.getIntervals();
        for (int[] array : result) {
            System.out.print(Arrays.toString(array));
        }
        System.out.println();

        ranges = new SummaryRanges();
        ranges.addNum(6);
        ranges.addNum(7);
        ranges.addNum(6);
        result = ranges.getIntervals();
        for (int[] array : result) {
            System.out.print(Arrays.toString(array));
        }
    }

    TreeMap<Integer, Integer> map;

    public SummaryRanges() {
        map = new TreeMap<>();
    }

    /**
     * 假设加入元素为 b，有两种情况可以进行合并：
     * 1、已有 [b+1, c]，可以合并为 [b, c]
     * 2、已有 [a, b-1]，可以合并为 [a, b]
     * 假设已有 [1,3]，则 map 中为 [1,1],[3,3]，加入 2 时，left=1，right=3
     * 先和 [3,3] 合并为 [2,3]，同时从 map 中移除 [3,3]
     * 再和 [1,1] 合并，注意此时合并结果是 [1,3] 而不是 [1,2]，同时从 map 中移除 [2,3]
     */
    public void addNum(int val) {
        // 防止覆盖已有 interval
        if (map.containsKey(val)) {
            return;
        }
        map.put(val, val);
        Integer left = map.lowerKey(val), right = map.higherKey(val);
        if (right != null && right == val + 1) {
            map.put(val, map.get(right));
            map.remove(right);
        }
        if (left != null && map.get(left) >= val - 1) {
            // 因为上面 right 可能是满足条件的，val 已合并
            map.put(left, Math.max(map.get(left), map.get(val)));
            map.remove(val);
        }
    }

    public int[][] getIntervals() {
        int[][] result = new int[map.size()][2];
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            result[index++] = new int[]{entry.getKey(), entry.getValue()};
        }
        return result;
    }
}
