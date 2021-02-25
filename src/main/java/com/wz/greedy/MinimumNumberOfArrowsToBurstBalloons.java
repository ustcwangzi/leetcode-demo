package com.wz.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * There are some spherical balloons spread in two-dimensional space. For each balloon,
 * provided input is the start and end coordinates of the horizontal diameter.
 * Since it's horizontal, y-coordinates don't matter, and hence the x-coordinates of start and end of the diameter suffice.
 * The start is always smaller than the end.
 * An arrow can be shot up exactly vertically from different points along the x-axis.
 * A balloon with xstart and xend bursts by an arrow shot at x if xstart ≤ x ≤ xend.
 * There is no limit to the number of arrows that can be shot. An arrow once shot keeps traveling up infinitely.
 * Given an array points where points[i] = [xstart, xend], return the minimum number of arrows that must be shot to burst all balloons.
 *
 * Example 1:
 * Input: points = [[10,16],[2,8],[1,6],[7,12]]
 * Output: 2
 * Explanation: One way is to shoot one arrow for example at x = 6 (bursting the balloons [2,8] and [1,6])
 *              and another arrow at x = 11 (bursting the other two balloons).
 *
 * Example 2:
 * Input: points = [[1,2],[2,3],[3,4],[4,5]]
 * Output: 2
 *
 * Constraints:
 * 1. 0 <= points.length <= 10^4
 * 2. points[i].length == 2
 * 3. -2^31 <= xstart < xend <= 2^31 - 1
 */
public class MinimumNumberOfArrowsToBurstBalloons {
    public static void main(String[] args) {
        int[][] points = new int[][]{
                {10, 16}, {2, 8}, {1, 6}, {7, 12}
        };
        System.out.println(findMinArrowShots(points));

        points = new int[][]{
                {1, 2}, {2, 3}, {3, 4}, {4, 5}
        };
        System.out.println(findMinArrowShots(points));
    }

    /**
     * 与 {@link NonOverlappingIntervals} 类似
     * 按照第二个元素进行排序，然后记录当前最大射程 end，遍历数组如果在射程内则继续遍历下一个，否则增加一次射击，同时更新射程
     */
    public static int findMinArrowShots(int[][] points) {
        if (points.length <= 1) {
            return points.length;
        }

        Arrays.parallelSort(points, Comparator.comparingInt(o -> o[1]));
        int result = 1, end = points[0][1];
        for (int i = 1; i < points.length; i++) {
            if (points[i][0] <= end) {
                continue;
            }
            result++;
            end = points[i][1];
        }
        return result;
    }
}
