package com.wz.lists;

/**
 * On a plane there are n points with integer coordinates points[i] = [xi, yi].
 * Your task is to find the minimum time in seconds to visit all points.
 * You can move according to the next rules:
 * In one second always you can either move vertically, horizontally by one unit or diagonally
 * (it means to move one unit vertically and one unit horizontally in one second).
 * You have to visit the points in the same order as they appear in the array.
 *
 * Example 1:
 * Input: points = [[1,1],[3,4],[-1,0]]
 * Output: 7
 * Explanation: One optimal path is [1,1] -> [2,2] -> [3,3] -> [3,4] -> [2,3] -> [1,2] -> [0,1] -> [-1,0]
 * Time from [1,1] to [3,4] = 3 seconds
 * Time from [3,4] to [-1,0] = 4 seconds
 * Total time = 7 seconds
 *
 * Example 2:
 * Input: points = [[3,2],[-2,2]]
 * Output: 5
 */
public class MinimumTimeVisitingAllPoints {
    public static void main(String[] args) {
        int[][] points = new int[][]{
                {1, 1}, {3, 4}, {-1, 0}
        };
        System.out.println(minTimeToVisitAllPoints(points));

        points = new int[][]{
                {3, 2}, {-2, 2}
        };
        System.out.println(minTimeToVisitAllPoints(points));
    }

    /**
     * A到B优先对角移动，使对角移动最大化，然后再水平或者垂直移动
     * (x1,y1) 移动到 (x2,y2) 从分为两种情况：
     * 1. |x2-x1| < |y2-y1|
     *    移动路径为：(x1,y1) -> (x2,x2) -> (x2,y2)， 耗时为：|x2-x1|+(|y2-y1|-|x2-x1|)
     * 2. |x2-x1| > |y2-y1|
     *    移动路径为：(x1,y1) -> (y2,y2) -> (x2,y2)， 耗时为：|y2-y1|+(|x2-x1|-|y2-y1|)
     * 综合两种情况，最终计算公式为：
     * min{|x2-x1|, |y2-y1|} + abs{|x2-x1| - |y2-y1|}
     */
    public static int minTimeToVisitAllPoints(int[][] points) {
        int result = 0;
        for (int i = 1; i < points.length; i++) {
            int dx = Math.abs(points[i][0] - points[i - 1][0]);
            int dy = Math.abs(points[i][1] - points[i - 1][1]);
            result += Math.min(dx, dy) + Math.abs(dx - dy);
        }

        return result;
    }
}
