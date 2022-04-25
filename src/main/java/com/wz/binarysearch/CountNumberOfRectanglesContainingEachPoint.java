package com.wz.binarysearch;

import java.util.*;

/**
 * You are given a 2D integer array rectangles where rectangles[i] = [li, hi] indicates that ith rectangle has a length of li and a height of hi.
 * You are also given a 2D integer array points where points[j] = [xj, yj] is a point with coordinates (xj, yj).
 * The ith rectangle has its bottom-left corner point at the coordinates (0, 0) and its top-right corner point at (li, hi).
 * Return an integer array count of length points.length where count[j] is the number of rectangles that contain the jth point.
 * The ith rectangle contains the jth point if 0 <= xj <= li and 0 <= yj <= hi.
 * Note that points that lie on the edges of a rectangle are also considered to be contained by that rectangle.
 *
 * Example 1:
 * @link ../../../../resource/CountNumberOfRectanglesContainingEachPoint1.jpg
 * Input: rectangles = [[1,2],[2,3],[2,5]], points = [[2,1],[1,4]]
 * Output: [2,1]
 * Explanation:
 * The first rectangle contains no points.
 * The second rectangle contains only the point (2, 1).
 * The third rectangle contains the points (2, 1) and (1, 4).
 * The number of rectangles that contain the point (2, 1) is 2.
 * The number of rectangles that contain the point (1, 4) is 1.
 * Therefore, we return [2, 1].
 *
 * Example 2:
 * @link ../../../../resource/CountNumberOfRectanglesContainingEachPoint2.jpg
 * Input: rectangles = [[1,1],[2,2],[3,3]], points = [[1,3],[1,1]]
 * Output: [1,3]
 * Explanation:
 * The first rectangle contains only the point (1, 1).
 * The second rectangle contains only the point (1, 1).
 * The third rectangle contains the points (1, 3) and (1, 1).
 * The number of rectangles that contain the point (1, 3) is 1.
 * The number of rectangles that contain the point (1, 1) is 3.
 * Therefore, we return [1, 3].
 *
 * Constraints:
 * 1. 1 <= rectangles.length, points.length <= 5 * 10^4
 * 2. rectangles[i].length == points[j].length == 2
 * 3. 1 <= li, xj <= 10^9
 * 4. 1 <= hi, yj <= 100
 * 5. All the rectangles are unique.
 * 6. All the points are unique.
 */
public class CountNumberOfRectanglesContainingEachPoint {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(countRectangles1(new int[][]{{1, 2}, {2, 3}, {2, 5}}, new int[][]{{2, 1}, {1, 4}})));
        System.out.println(Arrays.toString(countRectangles2(new int[][]{{1, 2}, {2, 3}, {2, 5}}, new int[][]{{2, 1}, {1, 4}})));
    }

    /**
     * 方案一，直接双层遍历，判断每一个 point 是否在 rectangle 内，TLE
     */
    public static int[] countRectangles1(int[][] rectangles, int[][] points) {
        int[] result = new int[points.length];
        for (int i = 0; i < points.length; i++) {
            int count = 0;
            for (int[] rectangle : rectangles) {
                if (points[i][0] <= rectangle[0] && points[i][1] <= rectangle[1]) {
                    count++;
                }
            }
            result[i] = count;
        }
        return result;
    }

    /**
     * 方案二，x 的范围是 1～10^9，y 的范围是 1～100，将每一个 y 对应的 x 存在 map 中
     * 然后对于每个 point，先使用 subMap 获取到对应的 list，再在 list 中使用二分查找找到最后一个符合条件的 index
     * 那么 n-index 就是所有符合条件的 rectangle 个数
     */
    public static int[] countRectangles2(int[][] rectangles, int[][] points) {
        int max = Integer.MIN_VALUE;
        TreeMap<Integer, List<Integer>> map = new TreeMap<>();
        for (int[] rectangle : rectangles) {
            map.putIfAbsent(rectangle[1], new ArrayList<>());
            map.get(rectangle[1]).add(rectangle[0]);
            max = Math.max(max, rectangle[1]);
        }
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            Collections.sort(entry.getValue());
        }

        int[] result = new int[points.length];
        for (int i = 0; i < points.length; i++) {
            if (points[i][1] > max) {
                continue;
            }

            int count = 0;
            for (List<Integer> list : map.subMap(points[i][1], max + 1).values()) {
                int index = binarySearch(list, points[i][0]);
                if (index >= 0) {
                    count += list.size() - index;
                }
            }
            result[i] = count;
        }
        return result;
    }

    private static int binarySearch(List<Integer> list, int value) {
        int left = 0, right = list.size() - 1, result = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (list.get(mid) < value) {
                left = mid + 1;
            } else {
                result = mid;
                right = mid - 1;
            }
        }
        return result;
    }
}
