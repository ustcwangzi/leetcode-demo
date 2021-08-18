package com.wz.other;

import java.util.HashMap;
import java.util.Map;

/**
 * You are given n points in the plane that are all distinct, where points[i] = [xi, yi].
 * A boomerang is a tuple of points (i, j, k) such that the distance between i and j equals the distance between i and k (the order of the tuple matters).
 * Return the number of boomerangs.
 *
 * Example 1:
 * Input: points = [[0,0],[1,0],[2,0]]
 * Output: 2
 * Explanation: The two boomerangs are [[1,0],[0,0],[2,0]] and [[1,0],[2,0],[0,0]].
 *
 * Example 2:
 * Input: points = [[1,1],[2,2],[3,3]]
 * Output: 2
 *
 * Example 3:
 * Input: points = [[1,1]]
 * Output: 0
 *
 * Constraints:
 * 1. n == points.length
 * 2. 1 <= n <= 500
 * 3. points[i].length == 2
 * 4. -10^4 <= xi, yi <= 10^4
 * 5. All the points are unique.
 */
public class NumberOfBoomerangs {
    public static void main(String[] args) {
        System.out.println(numberOfBoomerangs(new int[][]{{0, 0}, {1, 0}, {2, 0}}));
    }

    /**
     *  要求第一个点和第二个点之间的距离跟第一个点和第三个点之间的距离相等
     *  有一个点 a，还有两个点 b 和 c，如果 ab 和 ac 之间的距离相等，那么就有两种排列 abc 和 acb；
     *  如果有三个点 b，c，d 都分别和 a 之间的距离相等，那么有六种排列方法，abc, acb, acd, adc, abd, adb；
     *  如果有 n 个点和 a 距离相等，那么排列方式为 n*(n-1)。
     *  那么问题就变成了遍历所有点，分别让每个点当成 a，然后遍历其他点，统计和 a 距离相等的点有多少个，然后 n*(n-1) 累加到结果中
     */
    public static int numberOfBoomerangs(int[][] points) {
        int result = 0;
        for (int i = 0; i < points.length; i++) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int j = 0; j < points.length; j++) {
                if (i == j) {
                    continue;
                }
                int distance = distance(points[i], points[j]);
                map.put(distance, map.getOrDefault(distance, 0) + 1);
            }
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                result += entry.getValue() * (entry.getValue() - 1);
            }
        }
        return result;
    }

    private static int distance(int[] point1, int[] point2) {
        int x = point1[0] - point2[0], y = point1[1] - point2[1];
        return x * x + y * y;
    }
}
