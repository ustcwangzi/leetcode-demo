package com.wz.dfs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * There is a bi-directional graph with n vertices, where each vertex is labeled from 0 to n - 1 (inclusive).
 * The edges in the graph are represented as a 2D integer array edges, where each edges[i] = [ui, vi] denotes a bi-directional edge
 * between vertex ui and vertex vi. Every vertex pair is connected by at most one edge, and no vertex has an edge to itself.
 * You want to determine if there is a valid path that exists from vertex start to vertex end.
 * Given edges and the integers n, start, and end, return true if there is a valid path from start to end, or false otherwise.
 *
 * Example 1:
 * @see ../../../../resource/FindIfPathExistsInGraph1.jpg
 * Input: n = 3, edges = [[0,1],[1,2],[2,0]], start = 0, end = 2
 * Output: true
 * Explanation: There are two paths from vertex 0 to vertex 2:
 * - 0 → 1 → 2
 * - 0 → 2
 *
 * Example 2:
 * @see ../../../../resource/FindIfPathExistsInGraph2.jpg
 * Input: n = 6, edges = [[0,1],[0,2],[3,5],[5,4],[4,3]], start = 0, end = 5
 * Output: false
 * Explanation: There is no path from vertex 0 to vertex 5.
 *
 * Constraints:
 * 1. 1 <= n <= 2 * 10^5
 * 2. 0 <= edges.length <= 2 * 10^5
 * 3. edges[i].length == 2
 * 4. 0 <= ui, vi <= n - 1
 * 5. ui != vi
 * 6. 0 <= start, end <= n - 1
 * 7. There are no duplicate edges.
 * 8. There are no self edges.
 */
public class FindIfPathExistsInGraph {
    public static void main(String[] args) {
        System.out.println(validPath(3, new int[][]{{0, 1}, {1, 2}, {2, 0}}, 0, 2));
        System.out.println(validPath2(3, new int[][]{{0, 1}, {1, 2}, {2, 0}}, 0, 2));
    }

    /**
     * 方案一：DFS
     */
    public static boolean validPath(int n, int[][] edges, int start, int end) {
        return dfs(buildGraph(edges), new HashSet<>(), start, end);
    }

    /**
     * 方案二：并查集 Union-Find
     */
    public static boolean validPath2(int n, int[][] edges, int start, int end) {
        UnionFind unionFind = new UnionFind(n);
        for (int[] edge : edges) {
            unionFind.union(edge[0], edge[1]);
        }
        return unionFind.find(start) == unionFind.find(end);
    }

    private static boolean dfs(Map<Integer, Set<Integer>> graph, Set<Integer> visited, int cur, int end) {
        if (cur == end) {
            return true;
        }
        if (visited.contains(cur)) {
            return false;
        }

        visited.add(cur);
        for (int neighbor : graph.get(cur)) {
            if (dfs(graph, visited, neighbor, end)) {
                return true;
            }
        }
        return false;
    }

    private static Map<Integer, Set<Integer>> buildGraph(int[][] edges) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int[] edge : edges) {
            graph.putIfAbsent(edge[0], new HashSet<>());
            graph.get(edge[0]).add(edge[1]);
            graph.putIfAbsent(edge[1], new HashSet<>());
            graph.get(edge[1]).add(edge[0]);
        }
        return graph;
    }

    private static class UnionFind {
        int[] root;

        public UnionFind(int n) {
            root = new int[n];
            for (int i = 0; i < n; i++) {
                root[i] = i;
            }
        }

        public void union(int x, int y) {
            int rootX = find(x), rootY = find(y);
            if (rootX != rootY) {
                root[rootX] = rootY;
            }
        }

        public int find(int x) {
            if (root[x] != x) {
                return root[x] = find(root[x]);
            }
            return x;
        }
    }
}
