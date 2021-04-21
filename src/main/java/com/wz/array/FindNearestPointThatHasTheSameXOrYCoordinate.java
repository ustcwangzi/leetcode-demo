package com.wz.array;

/**
 * You are given two integers, x and y, which represent your current location on a Cartesian grid: (x, y).
 * You are also given an array points where each points[i] = [ai, bi] represents that a point exists at (ai, bi).
 * A point is valid if it shares the same x-coordinate or the same y-coordinate as your location.
 * Return the index (0-indexed) of the valid point with the smallest Manhattan distance from your current location.
 * If there are multiple, return the valid point with the smallest index. If there are no valid points, return -1.
 * The Manhattan distance between two points (x1, y1) and (x2, y2) is abs(x1 - x2) + abs(y1 - y2).
 *
 * Example 1:
 * Input: x = 3, y = 4, points = [[1,2],[3,1],[2,4],[2,3],[4,4]]
 * Output: 2
 * Explanation: Of all the points, only [3,1], [2,4] and [4,4] are valid. Of the valid points, [2,4] and [4,4]
 * have the smallest Manhattan distance from your current location, with a distance of 1. [2,4] has the smallest index, so return 2.
 *
 * Constraints:
 * 1. 1 <= points.length <= 10^4
 * 2. points[i].length == 2
 * 3. 1 <= x, y, ai, bi <= 10^4
 */
public class FindNearestPointThatHasTheSameXOrYCoordinate {
    public static void main(String[] args) {
        System.out.println(nearestValidPoint(3, 4, new int[][]{{1, 2}, {3, 1}, {2, 4}, {2, 3}, {4, 4}}));
    }

    /**
     * 直接遍历 points，如果当前 point 与 (x,y) 相同横坐标或纵坐标，就进行计算曼哈顿距离
     */
    public static int nearestValidPoint(int x, int y, int[][] points) {
        int minDistance = Integer.MAX_VALUE, result = -1;
        for (int i = 0; i < points.length; i++) {
            int[] point = points[i];
            if (point[0] == x || point[1] == y) {
                int distance = Math.abs(point[0] - x) + Math.abs(point[1] - y);
                if (distance < minDistance) {
                    minDistance = distance;
                    result = i;
                }
            }
        }
        return result;
    }
}
