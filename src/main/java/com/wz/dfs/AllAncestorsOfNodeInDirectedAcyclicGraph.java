package com.wz.dfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * You are given a positive integer n representing the number of nodes of a Directed Acyclic Graph (DAG). The nodes are numbered from 0 to n - 1 (inclusive).
 * You are also given a 2D integer array edges, where edges[i] = [fromi, toi] denotes that there is a unidirectional edge from fromi to toi in the graph.
 * Return a list answer, where answer[i] is the list of ancestors of the ith node, sorted in ascending order.
 * A node u is an ancestor of another node v if u can reach v via a set of edges.
 *
 * Example 1:
 * @link ../../../../resource/AllAncestorsOfNodeInDirectedAcyclicGraph1.jpg
 * Input: n = 8, edgeList = [[0,3],[0,4],[1,3],[2,4],[2,7],[3,5],[3,6],[3,7],[4,6]]
 * Output: [[],[],[],[0,1],[0,2],[0,1,3],[0,1,2,3,4],[0,1,2,3]]
 * Explanation:
 * The above diagram represents the input graph.
 * - Nodes 0, 1, and 2 do not have any ancestors.
 * - Node 3 has two ancestors 0 and 1.
 * - Node 4 has two ancestors 0 and 2.
 * - Node 5 has three ancestors 0, 1, and 3.
 * - Node 6 has five ancestors 0, 1, 2, 3, and 4.
 * - Node 7 has four ancestors 0, 1, 2, and 3.
 *
 * Example 2:
 * @link ../../../../resource/AllAncestorsOfNodeInDirectedAcyclicGraph2.jpg
 * Input: n = 5, edgeList = [[0,1],[0,2],[0,3],[0,4],[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]
 * Output: [[],[0],[0,1],[0,1,2],[0,1,2,3]]
 * Explanation:
 * The above diagram represents the input graph.
 * - Node 0 does not have any ancestor.
 * - Node 1 has one ancestor 0.
 * - Node 2 has two ancestors 0 and 1.
 * - Node 3 has three ancestors 0, 1, and 2.
 * - Node 4 has four ancestors 0, 1, 2, and 3.
 *
 * Constraints:
 * 1. 1 <= n <= 1000
 * 2. 0 <= edges.length <= min(2000, n * (n - 1) / 2)
 * 3. edges[i].length == 2
 * 4. 0 <= fromi, toi <= n - 1
 * 5. fromi != toi
 * 6. There are no duplicate edges.
 * 7. The graph is directed and acyclic.
 */
public class AllAncestorsOfNodeInDirectedAcyclicGraph {
    public static void main(String[] args) {
        System.out.println(getAncestors(8, new int[][]{
                {0, 3}, {0, 4}, {1, 3}, {2, 4}, {2, 7}, {3, 5}, {3, 6}, {3, 7}, {4, 6}
        }));
    }

    /**
     * DFS
     * 先根据 edges 构造图，然后依次从 0～n 节点出发 DFS 遍历图，则起始节点是所有遍历过程中经历节点的祖先
     */
    public static List<List<Integer>> getAncestors(int n, int[][] edges) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge : edges) {
            graph.putIfAbsent(edge[0], new ArrayList<>());
            graph.get(edge[0]).add(edge[1]);
        }
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            result.add(new ArrayList<>());
        }

        for (int i = 0; i < n; i++) {
            boolean[] visited = new boolean[n];
            dfs(i, i, visited, graph, result);
        }
        return result;
    }

    private static void dfs(int start, int cur, boolean[] visited, Map<Integer, List<Integer>> graph, List<List<Integer>> result) {
        visited[cur] = true;
        for (int neighbor : graph.getOrDefault(cur, new ArrayList<>())) {
            if (!visited[neighbor]) {
                result.get(neighbor).add(start);
                dfs(start, neighbor, visited, graph, result);
            }
        }
    }
}
