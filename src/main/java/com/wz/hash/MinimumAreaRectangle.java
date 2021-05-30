package com.wz.hash;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Given a set of points in the xy-plane, determine the minimum area of a rectangle formed from these points, with sides parallel to the x and y axes.
 * If there isn't any rectangle, return 0.
 *
 * Example 1:
 * Input: [[1,1],[1,3],[3,1],[3,3],[2,2]]
 * Output: 4
 *
 * Example 2:
 * Input: [[1,1],[1,3],[3,1],[3,3],[4,1],[4,3]]
 * Output: 2
 *
 * Note:
 * 1. 1 <= points.length <= 500
 * 2. 0 <= points[i][0] <= 40000
 * 3. 0 <= points[i][1] <= 40000
 * 4. All points are distinct.
 */
public class MinimumAreaRectangle {
    public static void main(String[] args) {
        System.out.println(minAreaRect(new int[][]{{1, 1}, {1, 3}, {3, 1}, {3, 3}, {2, 2}}));
    }

    /**
     * 双层遍历，假设其中两个点分别是左上角 (x1, y1) 和右下角 (x2, y2)，看能否找到 (x1, y2), (x2, y1) 组成矩形
     * 为快速查找，使用 map 将 x 坐标对应的 y 全放在 set 中
     */
    public static int minAreaRect(int[][] points) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int[] point : points) {
            map.putIfAbsent(point[0], new HashSet<>());
            map.get(point[0]).add(point[1]);
        }

        int result = Integer.MAX_VALUE;
        // 对 (x1, y1) 和 (x2, y2) 寻找 (x1, y2), (x2, y1)
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                int x1 = points[i][0], y1 = points[i][1];
                int x2 = points[j][0], y2 = points[j][1];
                if (x1 == x2 || y1 == y2) {
                    continue;
                }
                if (map.get(x1).contains(y2) && map.get(x2).contains(y1)) {
                    result = Math.min(result, Math.abs(x1 - x2) * Math.abs(y1 - y2));
                }
            }
        }
        return result == Integer.MAX_VALUE ? 0 : result;
    }
}
