package com.wz.dfs;

import java.util.*;

/**
 * Given an undirected tree consisting of n vertices numbered from 0 to n-1, which has some apples in their vertices.
 * You spend 1 second to walk over one edge of the tree. Return the minimum time in seconds you have to
 * spend to collect all apples in the tree, starting at vertex 0 and coming back to this vertex.
 * The edges of the undirected tree are given in the array edges, where edges[i] = [ai, bi] means that
 * exists an edge connecting the vertices ai and bi. Additionally, there is a boolean array hasApple,
 * where hasApple[i] = true means that vertex i has an apple; otherwise, it does not have any apple.
 *
 * Example 1:
 * @link ../../../../resource/MinimumTimeToCollectAllApplesInTree.jpg
 * Input: n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], hasApple = [false,false,true,false,true,true,false]
 * Output: 8
 * Explanation: The figure above represents the given tree where red vertices have an apple. One optimal path to collect all apples is shown by the green arrows.
 */
public class MinimumTimeToCollectAllApplesInTree {
    public static void main(String[] args) {
        int[][] edges = new int[][]{{0, 1}, {0, 2}, {1, 4}, {1, 5}, {2, 3}, {2, 6}};
        System.out.println(minTime(7, edges, Arrays.asList(false, false, true, false, true, true, false)));
    }

    /**
     * DFS
     * 首先根据 edges 建立 graph，然后遍历每个 node 的 neighbor，看每一个孩子节点是否有苹果，result 代表需要走几步，
     * 所以如果某个节点的孩子节点传上来的 result > 0，表示下面有苹果，或者这个节点本身是有苹果的，那么就意味着一定需要走过去拿苹果，
     * 所以 result += 2，加 2 是因为拿一个苹果需要走两步
     */
    public static int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        Map<Integer, List<Integer>> graph = buildGraph(edges);
        return dfs(0, graph, hasApple, new boolean[n]);
    }

    private static int dfs(int node, Map<Integer, List<Integer>> graph, List<Boolean> hasApple, boolean[] visited) {
        if (visited[node]) {
            return 0;
        }
        visited[node] = true;

        int result = 0;
        for (int neighbor : graph.getOrDefault(node, new ArrayList<>())) {
            result += dfs(neighbor, graph, hasApple, visited);
        }
        // 当前节点下面有苹果，或当前节点本身有苹果
        if (node > 0 && (result > 0 || hasApple.get(node))) {
            result += 2;
        }
        return result;
    }


    private static Map<Integer, List<Integer>> buildGraph(int[][] edges) {
        Map<Integer, List<Integer>> graph = new HashMap<>(edges.length * 2);
        for (int[] edge : edges) {
            graph.putIfAbsent(edge[0], new LinkedList<>());
            graph.putIfAbsent(edge[1], new LinkedList<>());
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        return graph;
    }
}
