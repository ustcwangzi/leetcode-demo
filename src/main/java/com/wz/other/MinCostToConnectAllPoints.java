package com.wz.other;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * You are given an array points representing integer coordinates of some points on a 2D-plane, where points[i] = [xi, yi].
 *
 * The cost of connecting two points [xi, yi] and [xj, yj] is the manhattan distance between them: |xi - xj| + |yi - yj|, where |val| denotes the absolute value of val.
 *
 * Return the minimum cost to make all points connected. All points are connected if there is exactly one simple path between any two points.
 *
 *
 *
 * Example 1:
 * @see ../../../../resource/MinCostToConnectAllPoints1.jpg
 * Input: points = [[0,0],[2,2],[3,10],[5,2],[7,0]]
 * Output: 20
 * Explanation:
 * @see ../../../../resource/MinCostToConnectAllPoints2.jpg
 * We can connect the points as shown above to get the minimum cost of 20.
 * Notice that there is a unique path between every pair of points.
 *
 * Example 2:
 * Input: points = [[3,12],[-2,5],[-4,1]]
 * Output: 18
 *
 * Constraints:
 * 1. 1 <= points.length <= 1000
 * 2. -10^6 <= xi, yi <= 10^6
 * 3. All pairs (xi, yi) are distinct.
 */
public class MinCostToConnectAllPoints {
    public static void main(String[] args) {
        System.out.println(minCostConnectPoints(new int[][]{{0, 0}, {2, 2}, {3, 10}, {5, 2}, {7, 0}}));
    }

    /**
     * Prim 算法
     */
    public static int minCostConnectPoints(int[][] points) {
        int n = points.length, result = 0;
        PriorityQueue<Edge> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.dist));
        boolean[] visited = new boolean[n];
        int connected = n, i = 0;
        while (connected > 1) {
            // 新加入一条边
            visited[i] = true;
            // 找到距离 i 最短的边
            for (int j = 0; j < n; j++) {
                if (!visited[j]) {
                    queue.offer(new Edge(i, j, dist(points[i], points[j])));
                }
            }
            while (visited[queue.peek().y]) {
                queue.poll();
            }
            Edge minEdge = queue.poll();
            result += minEdge.dist;
            i = minEdge.y;
            connected--;
        }
        return result;
    }

    private static int dist(int[] point1, int[] point2) {
        return Math.abs(point1[0] - point2[0]) + Math.abs(point1[1] - point2[1]);
    }

    private static class Edge {
        int x, y;
        int dist;

        public Edge(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
}
