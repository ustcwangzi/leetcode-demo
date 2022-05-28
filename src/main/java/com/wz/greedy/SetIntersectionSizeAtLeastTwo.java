package com.wz.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * An integer interval [a, b] (for integers a < b) is a set of all consecutive integers from a to b, including a and b.
 * Find the minimum size of a set S such that for every integer interval A in intervals, the intersection of S with A has a size of at least two.
 *
 * Example 1:
 * Input: intervals = [[1,3],[1,4],[2,5],[3,5]]
 * Output: 3
 * Explanation: Consider the set S = {2, 3, 4}.  For each interval, there are at least 2 elements from S in the interval.
 * Also, there isn't a smaller size set that fulfills the above condition.
 * Thus, we output the size of this set, which is 3.
 *
 * Example 2:
 * Input: intervals = [[1,2],[2,3],[2,4],[4,5]]
 * Output: 5
 * Explanation: An example of a minimum sized set is {1, 2, 3, 4, 5}.
 *
 * Constraints:
 * 1. 1 <= intervals.length <= 3000
 * 2. intervals[i].length == 2
 * 3. 0 <= ai < bi <= 10^8
 */
public class SetIntersectionSizeAtLeastTwo {
    public static void main(String[] args) {
        System.out.println(intersectionSizeTwo(new int[][]{{1, 3}, {1, 4}, {2, 5}, {3, 5}}));
        System.out.println(intersectionSizeTwo(new int[][]{{1, 3}, {3, 7}, {5, 7}, {7, 8}}));
    }

    /**
     * 对数组按照 end 正序排序，若 end 相同则按照 start 逆序排序，排序后加入 last-1 和 last，因为结果最少需要两个元素
     * 然后遍历数组，对于每个 [start,end]，求出和 [preLast, last] 相交元素个数
     * - 若相交元素至少为两个，则直接遍历下一个元素
     * - 若相交元素为一个，则将 end 加入结果中
     * - 若没有相交元素，则将 end-1、end 加入结果中
     * ----------------------------------------------------------------------------------------
     * 注意，end 相同时需要按照 start 逆序排序，以 [[1,3],[3,7],[5,7],[7,8]] 说明该过程
     * ----------------------------------------------------------------------------------------
     * 若按照 start 正序排序，则数组本身就是排序后的结果，开始时 list=[2,3]，last=3，preLast=2
     * [3,7]，last>=start，加入 7，list=[2,3,7]，last=7，preLast=3
     * [5,7]，last>=start，加入 7，list=[2,3,7,7]，last=7，preLast=7
     * [7,8]，last>=start && preLast>=start，list不变
     * 可以看到最终结果为 [2,3,7,7]，很明显不满足条件，是错误的结果
     * ----------------------------------------------------------------------------------------
     * 若按照 start 逆序排序，排序后为[[1,3],[5,7],[3,7],[7,8]]，开始时 list=[2,3]，last=3，preLast=2
     * [5,7]，last>=start，加入 6,7，list=[2,3,6,7]，last=7，preLast=6
     * [3,7]，last>=start && preLast>=start，list不变
     * [7,8]，last>=start，加入 8，list=[2,3,6,7,8]
     */
    public static int intersectionSizeTwo(int[][] intervals) {
        Arrays.parallelSort(intervals, (o1, o2) -> {
            if (o1[1] == o2[1]) {
                return Integer.compare(o2[0], o1[0]);
            }
            return Integer.compare(o1[1], o2[1]);
        });
        List<Integer> list = new ArrayList<>();
        list.add(intervals[0][1] - 1);
        list.add(intervals[0][1]);
        for (int i = 1; i < intervals.length; i++) {
            int start = intervals[i][0], end = intervals[i][1];
            int last = list.get(list.size() - 1), preLast = list.get(list.size() - 2);
            // 至少两个相交
            if (last >= start && preLast >= start) {
                continue;
            }
            // 一个相交
            if (last >= start) {
                list.add(end);
            } else {
                // 没有相交
                list.add(end - 1);
                list.add(end);
            }
        }
        return list.size();
    }
}
