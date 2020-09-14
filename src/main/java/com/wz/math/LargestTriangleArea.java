package com.wz.math;

/**
 * You have a list of points in the plane. Return the area of the largest triangle that can be formed by any 3 of the points.
 *
 * Example:
 * Input: points = [[0,0],[0,1],[1,0],[0,2],[2,0]]
 * Output: 2
 *
 * Notes:
 * 3 <= points.length <= 50.
 * No points will be duplicated.
 *  -50 <= points[i][j] <= 50.
 * Answers within 10^-6 of the true value will be accepted as correct.
 */
public class LargestTriangleArea {
    public static void main(String[] args) {
        int[][] points = new int[][]{
                {0, 0}, {0, 1}, {1, 0}, {0, 2}, {2, 0}
        };
        System.out.println(largestTriangleArea(points));
    }

    /**
     * 遍历任意三个点，通过三个顶点的坐标求出三角形面积
     */
    public static double largestTriangleArea(int[][] points) {
        double result = 0;
        for (int[] i : points) {
            for (int[] j : points) {
                for (int[] k : points) {
                    result = Math.max(result, 0.5 * Math.abs(i[0] * j[1] + j[0] * k[1] + k[0] * i[1] - j[0] * i[1] - k[0] * j[1] - i[0] * k[1]));
                }
            }
        }
        return result;
    }
}
