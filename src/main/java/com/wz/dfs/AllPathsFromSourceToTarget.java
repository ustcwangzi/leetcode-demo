package com.wz.dfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a directed acyclic graph (DAG) of n nodes labeled from 0 to n - 1, find all possible paths
 * from node 0 to node n - 1, and return them in any order.
 * The graph is given as follows: graph[i] is a list of all nodes you can visit from node i
 * (i.e., there is a directed edge from node i to node graph[i][j]).
 *
 * Example 1:
 * @see ../../../../resource/AllPathsFromSourceToTarget.jpg
 * Input: graph = [[1,2],[3],[3],[]]
 * Output: [[0,1,3],[0,2,3]]
 * Explanation: There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.
 */
public class AllPathsFromSourceToTarget {
    public static void main(String[] args) {
        int[][] graph = new int[][]{{1, 2}, {3}, {3}, {0}};
        System.out.println(allPathsSourceTarget(graph));
    }

    /**
     * DFS
     * 思路与 {@link PathSumII} 类似
     * 使用 path 收集所有的路径，存在 result 中
     */
    public static List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> result = new LinkedList<>();
        dfs(graph, 0, result, new ArrayList<>());
        return result;
    }

    private static void dfs(int[][] graph, int cur, List<List<Integer>> result, List<Integer> path) {
        path.add(cur);
        if (cur == graph.length - 1) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int neighbor : graph[cur]) {
            dfs(graph, neighbor, result, path);
            path.remove(path.size() - 1);
        }
    }
}
