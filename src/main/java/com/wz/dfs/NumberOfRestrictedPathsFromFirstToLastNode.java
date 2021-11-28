package com.wz.dfs;

import java.util.*;

/**
 * There is an undirected weighted connected graph. You are given a positive integer n which denotes that the graph has n nodes labeled from 1 to n,
 * and an array edges where each edges[i] = [ui, vi, weighti] denotes that there is an edge between nodes ui and vi with weight equal to weighti.
 * A path from node start to node end is a sequence of nodes [z0, z1, z2, ..., zk] such that z0 = start and zk = end and there is an edge between zi and zi+1 where 0 <= i <= k-1.
 * The distance of a path is the sum of the weights on the edges of the path.
 * Let distanceToLastNode(x) denote the shortest distance of a path between node n and node x.
 * A restricted path is a path that also satisfies that distanceToLastNode(zi) > distanceToLastNode(zi+1) where 0 <= i <= k-1.
 * Return the number of restricted paths from node 1 to node n. Since that number may be too large, return it modulo 109 + 7.
 *
 * Example 1:
 * @link ../../../../resource/NumberOfRestrictedPathsFromFirstToLastNode1.jpg
 * Input: n = 5, edges = [[1,2,3],[1,3,3],[2,3,1],[1,4,2],[5,2,2],[3,5,1],[5,4,10]]
 * Output: 3
 * Explanation: Each circle contains the node number in black and its distanceToLastNode value in blue. The three restricted paths are:
 * 1) 1 --> 2 --> 5
 * 2) 1 --> 2 --> 3 --> 5
 * 3) 1 --> 3 --> 5
 *
 * Example 2:
 * @link ../../../../resource/NumberOfRestrictedPathsFromFirstToLastNode2.jpg
 * Input: n = 7, edges = [[1,3,1],[4,1,2],[7,3,4],[2,5,3],[5,6,1],[6,7,2],[7,5,3],[2,6,4]]
 * Output: 1
 * Explanation: Each circle contains the node number in black and its distanceToLastNode value in blue. The only restricted path is 1 --> 3 --> 7.
 *
 * Constraints:
 * 1. 1 <= n <= 2 * 104
 * 2. n - 1 <= edges.length <= 4 * 10^4
 * 3. edges[i].length == 3
 * 4. 1 <= ui, vi <= n
 * 5. ui != vi
 * 6. 1 <= weighti <= 10^5
 * 7. There is at most one edge between any two nodes.
 * 8. There is at least one path between any two nodes.
 */
public class NumberOfRestrictedPathsFromFirstToLastNode {
    public static void main(String[] args) {
        System.out.println(countRestrictedPaths(5, new int[][]{
                {1, 2, 3}, {1, 3, 3}, {2, 3, 1}, {1, 4, 2}, {5, 2, 2}, {3, 5, 1}, {5, 4, 10}
        }));
    }

    /**
     * Dijkstra + DFS
     * 使用 Dijkstra 算法求出各个节点到节点 n 的最短距离，然后使用 DFS 计算受限路径数
     */
    public static int countRestrictedPaths(int n, int[][] edges) {
        Map<Integer, Map<Integer, Integer>> graph = buildGraph(n, edges);
        int[] dist = new int[n + 1], dp = new int[n + 1];
        dijkstra(graph, dist, n);
        Arrays.fill(dp, -1);
        return dfs(1, n, graph, dist, dp);
    }

    private static Map<Integer, Map<Integer, Integer>> buildGraph(int n, int[][] edges) {
        Map<Integer, Map<Integer, Integer>> graph = new HashMap<>(2 * edges.length);
        for (int[] edge : edges) {
            graph.putIfAbsent(edge[0], new HashMap<>());
            graph.get(edge[0]).put(edge[1], edge[2]);

            graph.putIfAbsent(edge[1], new HashMap<>());
            graph.get(edge[1]).put(edge[0], edge[2]);
        }
        return graph;
    }

    private static void dijkstra(Map<Integer, Map<Integer, Integer>> graph, int[] dist, int start) {
        Queue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        queue.offer(new int[]{start, 0});
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        while (!queue.isEmpty()) {
            int[] edge = queue.poll();
            Map<Integer, Integer> neighbors = graph.get(edge[0]);
            if (dist[edge[0]] < edge[1] || neighbors == null) {
                continue;
            }

            for (Map.Entry<Integer, Integer> entry : neighbors.entrySet()) {
                if (dist[entry.getKey()] > dist[edge[0]] + entry.getValue()) {
                    dist[entry.getKey()] = dist[edge[0]] + entry.getValue();
                    queue.offer(new int[]{entry.getKey(), dist[entry.getKey()]});
                }
            }
        }
    }

    private static int dfs(int start, int end, Map<Integer, Map<Integer, Integer>> graph, int[] dist, int[] dp) {
        if (start == end) {
            return 1;
        }

        if (dp[start] != -1) {
            return dp[start];
        }

        long result = 0;
        if (graph.get(start) != null) {
            for (int cur : graph.get(start).keySet()) {
                if (dist[start] > dist[cur]) {
                    result += (dfs(cur, end, graph, dist, dp) % 1_000_000_007);
                }
            }
        }

        return dp[start] = (int) (result % 1_000_000_007);
    }
}
