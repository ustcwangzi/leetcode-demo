package com.wz.math;

import java.util.HashMap;
import java.util.Map;

/**
 * Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
 *
 * Example 1:
 * Input: [[1,1],[2,2],[3,3]]
 * Output: 3
 * Explanation:
 * ^
 * |
 * |        o
 * |     o
 * |  o
 * +------------->
 * 0  1  2  3  4
 *
 * Example 2:
 * Input: [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
 * Output: 4
 * Explanation:
 * ^
 * |
 * |  o
 * |     o        o
 * |        o
 * |  o        o
 * +------------------->
 * 0  1  2  3  4  5  6
 */
public class MaxPointsOnLine {
    public static void main(String[] args) {
        int[][] points = new int[][]{
                {1, 1}, {2, 2}, {3, 3}
        };
        System.out.println(maxPoints(points));

        points = new int[][]{
                {1, 1}, {3, 2}, {5, 3}, {4, 1}, {2, 3}, {1, 4}
        };
        System.out.println(maxPoints(points));
    }

    /**
     * 给定点两两之间都可以算一个斜率，每个斜率代表一条直线，对每一条直线，带入所有的点看是否共线并计算个数
     * 有两点特殊情况需要考虑，一是当两个点重合时，无法确定一条直线，但这也是共线的情况，需要特殊处理
     * 二是斜率不存在的情况，由于两个点 (x1, y1) 和 (x2, y2) 的斜率k表示为 (y2 - y1) / (x2 - x1)，那么当 x1 = x2 时斜率不存在
     * 用 HashMap 来记录斜率和共线点个数之间的映射，用 duplicate 来记录重合点的个数，最后只需和 HashMap 中的值相加即为共线点的总数
     * 但这种方法通过斜率来判断共线需要用到除法，为了更加精确无误的计算共线，应当避免除法
     * 这里把除数和被除数都保存下来，不做除法，但是要让这两数分别除以它们的最大公约数
     * 这样例如8和4，4和2，2和1，这三组商相同的数就都会存到一个映射里面，同样也能实现目标
     */
    public static int maxPoints(int[][] points) {
        int result = 0;
        for (int i = 0; i < points.length; i++) {
            // 为避免除法，这里的 key 用 map 来记录除数和被除数
            Map<Map<Integer, Integer>, Integer> map = new HashMap<>();
            int duplicate = 1;
            for (int j = i + 1; j < points.length; j++) {
                if (points[i][0] == points[j][0] && points[i][1] == points[j][1]) {
                    duplicate++;
                    continue;
                }

                int dx = points[j][0] - points[i][0];
                int dy = points[j][1] - points[i][1];
                int d = gcd(dx, dy);
                Map<Integer, Integer> point = new HashMap<>();
                point.put(dx / d, dy / d);
                map.put(point, map.getOrDefault(point, 0) + 1);
            }

            result = Math.max(result, duplicate);
            for (Map.Entry<Map<Integer, Integer>, Integer> entry : map.entrySet()) {
                result = Math.max(result, entry.getValue() + duplicate);
            }
        }

        return result;
    }

    /**
     * 计算最大公约数
     */
    private static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
