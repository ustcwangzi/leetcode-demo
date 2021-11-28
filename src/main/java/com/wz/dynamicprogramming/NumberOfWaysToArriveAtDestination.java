package com.wz.dynamicprogramming;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * You are in a city that consists of n intersections numbered from 0 to n - 1 with bi-directional roads between some intersections.
 * The inputs are generated such that you can reach any intersection from any other intersection and that there is at most one road between any two intersections.
 * You are given an integer n and a 2D integer array roads where roads[i] = [ui, vi, timei] means that there is a road between intersections ui and vi
 * that takes timei minutes to travel. You want to know in how many ways you can travel from intersection 0 to intersection n - 1 in the shortest amount of time.
 * Return the number of ways you can arrive at your destination in the shortest amount of time. Since the answer may be large, return it modulo 10^9 + 7.
 *
 * Example 1:
 * @link ../../../../resource/NumberOfWaysToArriveAtDestination.jpg
 * Input: n = 7, roads = [[0,6,7],[0,1,2],[1,2,3],[1,3,3],[6,3,3],[3,5,1],[6,5,1],[2,5,1],[0,4,5],[4,6,2]]
 * Output: 4
 * Explanation: The shortest amount of time it takes to go from intersection 0 to intersection 6 is 7 minutes.
 * The four ways to get there in 7 minutes are:
 * - 0 ➝ 6
 * - 0 ➝ 4 ➝ 6
 * - 0 ➝ 1 ➝ 2 ➝ 5 ➝ 6
 * - 0 ➝ 1 ➝ 3 ➝ 5 ➝ 6
 *
 * Constraints:
 * 1. 1 <= n <= 200
 * 2. n - 1 <= roads.length <= n * (n - 1) / 2
 * 3. roads[i].length == 3
 * 4. 0 <= ui, vi <= n - 1
 * 5. 1 <= timei <= 10^9
 * 6. ui != vi
 * 7. There is at most one road connecting any two intersections.
 * 8. You can reach any intersection from any other intersection.
 */
public class NumberOfWaysToArriveAtDestination {
    public static void main(String[] args) {
        System.out.println(countPaths(7, new int[][]{
                {0, 6, 7}, {0, 1, 2}, {1, 2, 3}, {1, 3, 3}, {6, 3, 3}, {3, 5, 1}, {6, 5, 1}, {2, 5, 1}, {0, 4, 5}, {4, 6, 2}}));
    }

    /**
     * Dijkstra + DP
     */
    public static int countPaths(int n, int[][] roads) {
        int[] count = dijkstra(n, buildGraph(n, roads));
        return count[n - 1];
    }

    private static int[] dijkstra(int n, int[][] graph) {
        int[] dist = new int[n], count = new int[n];
        Queue<int[]> queue = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        queue.offer(new int[]{0, 0});
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;
        count[0] = 1;
        while (!queue.isEmpty()) {
            int[] edge = queue.poll();
            if (dist[edge[0]] < edge[1]) {
                continue;
            }
            if (edge[0] == n - 1) {
                break;
            }

            for (int i = 0; i < n; i++) {
                if (graph[edge[0]][i] == 0) {
                    continue;
                }
                int cost = edge[1] + graph[edge[0]][i];
                if (cost <= dist[i]) {
                    if (cost < dist[i]) {
                        count[i] = 0;
                        dist[i] = cost;
                        queue.offer(new int[]{i, cost});
                    }
                    count[i] = (count[i] + count[edge[0]]) % 1_000_000_007;
                }
            }
        }
        return count;
    }

    private static int[][] buildGraph(int n, int[][] roads) {
        int[][] graph = new int[n][n];
        for (int[] road : roads) {
            graph[road[0]][road[1]] = road[2];
            graph[road[1]][road[0]] = road[2];
        }
        return graph;
    }
}
