package com.wz.other;

import java.util.Arrays;

/**
 * You are given an array points where points[i] = [xi, yi] is the coordinates of the ith point on a 2D plane. Multiple points can have the same coordinates.
 * You are also given an array queries where queries[j] = [xj, yj, rj] describes a circle centered at (xj, yj) with a radius of rj.
 * For each query queries[j], compute the number of points inside the jth circle. Points on the border of the circle are considered inside.
 * Return an array answer, where answer[j] is the answer to the jth query.
 *
 * Example 1:
 * @see ../../../../resource/QueriesOnNumberOfPointsInsideCircle.jpg
 * Input: points = [[1,3],[3,3],[5,3],[2,2]], queries = [[2,3,1],[4,3,1],[1,1,2]]
 * Output: [3,2,2]
 * Explanation: The points and circles are shown above.
 * queries[0] is the green circle, queries[1] is the red circle, and queries[2] is the blue circle.
 *
 * Constraints:
 * 1. 1 <= points.length <= 500
 * 2. points[i].length == 2
 * 3. 0 <= xi, yi <= 500
 * 4. 1 <= queries.length <= 500
 * 5. queries[j].length == 3
 * 6. 0 <= xj, yj <= 500
 * 7. 1 <= rj <= 500
 * 8. All coordinates are integers.
 */
public class QueriesOnNumberOfPointsInsideCircle {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(countPoints(new int[][]{{1, 3}, {3, 3}, {5, 3}, {2, 2}}, new int[][]{{2, 3, 1}, {4, 3, 1}, {1, 1, 2}})));
    }

    /**
     * 点到圆心距离的平方 <= 半径的平方 时，点就在圆内
     */
    public static int[] countPoints(int[][] points, int[][] queries) {
        int[] result = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int x = queries[i][0], y = queries[i][1], r = queries[i][2];
            for (int[] point : points) {
                if (Math.pow(x - point[0], 2) + Math.pow(y - point[1], 2) <= r * r) {
                    result[i]++;
                }
            }
        }
        return result;
    }
}
