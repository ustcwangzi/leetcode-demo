package com.wz.queue;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * We have a list of points on the plane.  Find the K closest points to the origin (0, 0).
 * (Here, the distance between two points on a plane is the Euclidean distance.)
 * You may return the answer in any order.  The answer is guaranteed to be unique (except for the order that it is in.)
 *
 * Example 1:
 * Input: points = [[1,3],[-2,2]], K = 1
 * Output: [[-2,2]]
 * Explanation:
 * The distance between (1, 3) and the origin is sqrt(10).
 * The distance between (-2, 2) and the origin is sqrt(8).
 * Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
 * We only want the closest K = 1 points from the origin, so the answer is just [[-2,2]].
 *
 * Example 2:
 * Input: points = [[3,3],[5,-1],[-2,4]], K = 2
 * Output: [[3,3],[-2,4]]
 * (The answer [[-2,4],[3,3]] would also be accepted.)
 *
 * Note:
 * 1. 1 <= K <= points.length <= 10000
 * 2. -10000 < points[i][0] < 10000
 * 3. -10000 < points[i][1] < 10000
 */
public class KClosestPointsToOrigin {
    public static void main(String[] args) {
        int[][] points = new int[][]{{1, 3}, {2, -2}};
        int[][] result = kClosest(points, 1);
        for (int[] array : result) {
            System.out.println(Arrays.toString(array));
        }
    }

    /**
     * 大根堆
     */
    public static int[][] kClosest(int[][] points, int K) {
        PriorityQueue<int[]> queue = new PriorityQueue<>(K, (o1, o2) -> Integer.compare(o2[0] * o2[0] + o2[1] * o2[1], o1[0] * o1[0] + o1[1] * o1[1]));
        for (int[] point : points) {
            queue.offer(point);
            if (queue.size() > K) {
                queue.poll();
            }
        }
        int[][] result = new int[K][2];
        int index = 0;
        while (!queue.isEmpty()) {
            result[index++] = queue.poll();
        }
        return result;
    }
}
