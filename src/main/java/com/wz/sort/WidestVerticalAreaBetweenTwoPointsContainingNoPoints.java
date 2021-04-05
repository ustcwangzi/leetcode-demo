package com.wz.sort;

import java.util.Arrays;

/**
 * Given n points on a 2D plane where points[i] = [xi, yi], Return the widest vertical area between two points such that no points are inside the area.
 * A vertical area is an area of fixed-width extending infinitely along the y-axis (i.e., infinite height).
 * The widest vertical area is the one with the maximum width.
 * Note that points on the edge of a vertical area are not considered included in the area.
 *
 * Example 1:
 * @see ../../../../resource/WidestVerticalAreaBetweenTwoPointsContainingNoPoints.jpg
 * Input: points = [[8,7],[9,9],[7,4],[9,7]]
 * Output: 1
 * Explanation: Both the red and the blue area are optimal.
 *
 * Example 2:
 * Input: points = [[3,1],[9,0],[1,0],[1,4],[5,3],[8,8]]
 * Output: 3
 *
 * Constraints:
 * 1. n == points.length
 * 2. 2 <= n <= 10^5
 * 3. points[i].length == 2
 * 4. 0 <= xi, yi <= 10^9
 */
public class WidestVerticalAreaBetweenTwoPointsContainingNoPoints {
    public static void main(String[] args) {
        System.out.println(maxWidthOfVerticalArea(new int[][]{{8, 7}, {9, 9}, {7, 4}, {9, 7}}));
    }

    /**
     * 按照横坐标排序，依次计算相邻横坐标的差
     */
    public static int maxWidthOfVerticalArea(int[][] points) {
        int[] x = new int[points.length];
        for (int i = 0; i < points.length; i++) {
            x[i] = points[i][0];
        }
        Arrays.parallelSort(x);

        int result = 0;
        for (int i = 0; i < points.length - 1; i++) {
            result = Math.max(result, x[i + 1] - x[i]);
        }
        return result;
    }
}
