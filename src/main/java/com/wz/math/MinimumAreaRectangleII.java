package com.wz.math;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a set of points in the xy-plane, determine the minimum area of any rectangle formed from these points,
 * with sides not necessarily parallel to the x and y axes.
 * If there isn't any rectangle, return 0.
 *
 * Example 1:
 * Input: [[1,2],[2,1],[1,0],[0,1]]
 * Output: 2.00000
 * Explanation: The minimum area rectangle occurs at [1,2],[2,1],[1,0],[0,1], with an area of 2.
 *
 * Example 2:
 * Input: [[0,1],[2,1],[1,1],[1,0],[2,0]]
 * Output: 1.00000
 * Explanation: The minimum area rectangle occurs at [1,0],[1,1],[2,1],[2,0], with an area of 1.
 */
public class MinimumAreaRectangleII {
    public static void main(String[] args) {
        System.out.println(minAreaFreeRect(new int[][]{{1, 2}, {2, 1}, {1, 0}, {0, 1}}));
        System.out.println(minAreaFreeRect(new int[][]{{0, 1}, {2, 1}, {1, 1}, {1, 0}, {2, 0}}));
    }

    /**
     * 找到各个矩形的几何方法
     * 原理：
     * （1）平行的定义：两个点(x1,y1)，(x2,y2)的向量为 (x1-x2,y1-y2)，两条平行线的向量相等
     * （2）垂直的定义：两个向量的点积为0，两个向量(x1,y1)，(x2,y2)，点积的为 x1*x2+y1*y2
     * （3）距离的公式为：两个点(x1,y1)，(x2,y2)，距离的平方为 (x1-x2)*(x1-x2)+(y1-y2)*(y1-y2)
     * （4）面积的公式为：长的距离*宽的距离
     */
    public static double minAreaFreeRect(int[][] points) {
        int len = points.length;
        if (len < 4) {
            return 0.0;
        }
        double result = Double.MAX_VALUE;
        Map<String, List<int[]>> map = new HashMap<>();
        for (int i = 0; i < len; ++i) {
            for (int j = i + 1; j < len; ++j) {
                long diagonal = (points[i][0] - points[j][0]) * (points[i][0] - points[j][0]) +
                        (points[i][1] - points[j][1]) * (points[i][1] - points[j][1]);
                double centerX = (double) (points[i][0] + points[j][0]) / 2;
                double centerY = (double) (points[i][1] + points[j][1]) / 2;
                String key = "" + diagonal + "+" + centerX + "+" + centerY;
                map.computeIfAbsent(key, k -> new ArrayList<>());
                map.get(key).add(new int[]{i, j});
            }
        }

        for (String key : map.keySet()) {
            List<int[]> list = map.get(key);
            if (list.size() < 2) continue;
            for (int i = 0; i < list.size(); ++i) {
                for (int j = i + 1; j < list.size(); ++j) {
                    int p1 = list.get(i)[0];
                    int p2 = list.get(j)[0];
                    int p3 = list.get(j)[1];
                    double x = Math.sqrt((points[p1][0] - points[p2][0]) * (points[p1][0] - points[p2][0])
                            + (points[p1][1] - points[p2][1]) * (points[p1][1] - points[p2][1]));
                    double y = Math.sqrt((points[p1][0] - points[p3][0]) * (points[p1][0] - points[p3][0])
                            + (points[p1][1] - points[p3][1]) * (points[p1][1] - points[p3][1]));
                    double area = x * y;
                    result = Math.min(result, area);
                }
            }
        }

        return result == Double.MAX_VALUE ? 0.0 : result;
    }
}
