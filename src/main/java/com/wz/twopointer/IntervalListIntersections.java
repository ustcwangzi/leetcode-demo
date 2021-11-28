package com.wz.twopointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * You are given two lists of closed intervals, firstList and secondList,
 * where firstList[i] = [starti, endi] and secondList[j] = [startj, endj]. Each list of intervals is pairwise disjoint and in sorted order.
 * Return the intersection of these two interval lists.
 * A closed interval [a, b] (with a < b) denotes the set of real numbers x with a <= x <= b.
 * The intersection of two closed intervals is a set of real numbers that are either empty or represented as a closed interval.
 * For example, the intersection of [1, 3] and [2, 4] is [2, 3].
 *
 * Example 1:
 * @link ../../../../resource/IntervalListIntersections.jpg
 * Input: firstList = [[0,2],[5,10],[13,23],[24,25]], secondList = [[1,5],[8,12],[15,24],[25,26]]
 * Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
 *
 * Constraints:
 * 1. 0 <= firstList.length, secondList.length <= 1000
 * 2. firstList.length + secondList.length >= 1
 * 3. 0 <= starti < endi <= 10^9
 * 4. endi < starti+1
 * 5. 0 <= startj < endj <= 10^9
 * 6. endj < startj+1
 */
public class IntervalListIntersections {
    public static void main(String[] args) {
        int[][] firstList = new int[][]{{0, 2}, {5, 10}, {13, 23}, {24, 25}};
        int[][] secondList = new int[][]{{1, 5}, {8, 12}, {15, 24}, {25, 26}};
        int[][] result = intervalIntersection(firstList, secondList);
        Arrays.stream(result).forEach(array -> System.out.println(Arrays.toString(array)));

        firstList = new int[][]{{3, 5}, {9, 20}};
        secondList = new int[][]{{4, 5}, {7, 10}, {11, 12}, {14, 15}, {16, 20}};
        result = intervalIntersection(firstList, secondList);
        Arrays.stream(result).forEach(array -> System.out.println(Arrays.toString(array)));
    }

    /**
     * 双指针
     * 使用 i、j 分别指向两个数组开始位置，可以通过判断 first 的结束位置是否位于 second 开始和结束之间
     * 或者 second 的结束位置是否位于 first 的开始和结束之间来判断 first 和 second 是否相交
     * 如果当前两个位置有相交，则将相交部分加入结果中
     * 然后，通过将 first、second 中结束位置较小的向右移动，因为结束位置较小，不可能再和后面的位置相交
     */
    public static int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        int m = firstList.length, n = secondList.length, i = 0, j = 0;
        List<int[]> result = new ArrayList<>();
        while (i < m && j < n) {
            int[] first = firstList[i], second = secondList[j];
            boolean intersection = (first[1] >= second[0] && first[1] <= second[1])
                    || (first[0] >= second[0] && first[0] <= second[1])
                    || (second[1] >= first[0] && second[1] <= first[1])
                    || (second[0] >= first[0] && second[0] <= first[1]);
            if (intersection) {
                result.add(new int[]{Math.max(first[0], second[0]), Math.min(first[1], second[1])});
            }
            // 结束早的向右移动
            if (first[1] > second[1]) {
                j++;
            } else if (first[1] < second[1]) {
                i++;
            } else {
                i++;
                j++;
            }
        }
        return result.toArray(new int[][]{});
    }
}
