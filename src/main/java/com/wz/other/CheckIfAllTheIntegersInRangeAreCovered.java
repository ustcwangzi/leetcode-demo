package com.wz.other;

import java.util.HashSet;
import java.util.Set;

/**
 * You are given a 2D integer array ranges and two integers left and right.
 * Each ranges[i] = [starti, endi] represents an inclusive interval between starti and endi.
 * Return true if each integer in the inclusive range [left, right] is covered by at least one interval in ranges. Return false otherwise.
 * An integer x is covered by an interval ranges[i] = [starti, endi] if starti <= x <= endi.
 *
 * Example 1:
 * Input: ranges = [[1,2],[3,4],[5,6]], left = 2, right = 5
 * Output: true
 * Explanation: Every integer between 2 and 5 is covered:
 * - 2 is covered by the first range.
 * - 3 and 4 are covered by the second range.
 * - 5 is covered by the third range.
 *
 * Example 2:
 * Input: ranges = [[1,10],[10,20]], left = 21, right = 21
 * Output: false
 * Explanation: 21 is not covered by any range.
 *
 * Constraints:
 * 1. 1 <= ranges.length <= 50
 * 2. 1 <= starti <= endi <= 50
 * 3. 1 <= left <= right <= 50
 */
public class CheckIfAllTheIntegersInRangeAreCovered {
    public static void main(String[] args) {
        System.out.println(isCovered(new int[][]{{1, 2}, {3, 4}, {5, 6}}, 2, 5));
        System.out.println(isCovered(new int[][]{{1, 10}, {10, 20}}, 21, 21));
    }

    /**
     * 遍历 ranges，将 [range[0], range[1]] 存入 set 中，然后判断 [left, right] 中的元素是否全部在 set 中
     */
    public static boolean isCovered(int[][] ranges, int left, int right) {
        Set<Integer> set = new HashSet<>();
        for (int[] range : ranges) {
            for (int i = range[0]; i <= range[1]; i++) {
                set.add(i);
            }
        }
        for (int i = left; i <= right; i++) {
            if (!set.contains(i)) {
                return false;
            }
        }
        return true;
    }
}
