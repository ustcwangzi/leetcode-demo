package com.wz.bfs;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * You are given an undirected graph (the "original graph") with n nodes labeled from 0 to n - 1.
 * You decide to subdivide each edge in the graph into a chain of nodes, with the number of new nodes varying between each edge.
 * The graph is given as a 2D array of edges where edges[i] = [ui, vi, cnti] indicates that there is an edge between nodes ui and vi in the original graph,
 * and cnti is the total number of new nodes that you will subdivide the edge into. Note that cnti == 0 means you will not subdivide the edge.
 * To subdivide the edge [ui, vi], replace it with (cnti + 1) new edges and cnti new nodes. The new nodes are x1, x2, ..., xcnti,
 * and the new edges are [ui, x1], [x1, x2], [x2, x3], ..., [xcnti-1, xcnti], [xcnti, vi].
 * In this new graph, you want to know how many nodes are reachable from the node 0, where a node is reachable if the distance is maxMoves or less.
 * Given the original graph and maxMoves, return the number of nodes that are reachable from node 0 in the new graph.
 *
 * Example 1:
 * @link ../../../../resource/ReachableNodesInSubdividedGraph.jpg
 * Input: edges = [[0,1,10],[0,2,1],[1,2,2]], maxMoves = 6, n = 3
 * Output: 13
 * Explanation: The edge subdivisions are shown in the image above.
 * The nodes that are reachable are highlighted in yellow.
 *
 * Example 2:
 * Input: edges = [[0,1,4],[1,2,6],[0,2,8],[1,3,1]], maxMoves = 10, n = 4
 * Output: 23
 *
 * Example 3:
 * Input: edges = [[1,2,4],[1,4,5],[1,3,1],[2,3,4],[3,4,5]], maxMoves = 17, n = 5
 * Output: 1
 * Explanation: Node 0 is disconnected from the rest of the graph, so only node 0 is reachable.
 *
 * Constraints:
 * 1. 0 <= edges.length <= min(n * (n - 1) / 2, 10^4)
 * 2. edges[i].length == 3
 * 3. 0 <= ui < vi < n
 * 4. There are no multiple edges in the graph.
 * 5. 0 <= cnti <= 10^4
 * 6. 0 <= maxMoves <= 10^9
 * 7. 1 <= n <= 3000
 */
public class ReachableNodesInSubdividedGraph {
    public static void main(String[] args) {
        System.out.println(reachableNodes(new int[][]{{0, 1, 10}, {0, 2, 1}, {1, 2, 2}}, 6, 3));
        System.out.println(reachableNodes(new int[][]{{0, 1, 4}, {1, 2, 6}, {0, 2, 8}, {1, 3, 1}}, 10, 4));
    }

    /**
     * BFS，找到每一个原始节点能够走的最大步数，然后按照这个最大步数访问它周围的子节点
     */
    public static int reachableNodes(int[][] edges, int maxMoves, int n) {
        Map<Integer, Map<Integer, Integer>> graph = buildGraph(edges, n);
        Queue<int[]> queue = new PriorityQueue<>((o1, o2) -> Integer.compare(o2[1], o1[1]));
        queue.offer(new int[]{0, maxMoves});
        boolean[] visited = new boolean[n];
        int result = 0;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (visited[cur[0]]) {
                continue;
            }

            visited[cur[0]] = true;
            result++;

            for (int neighbor : graph.get(cur[0]).keySet()) {
                int nodeCount = graph.get(cur[0]).get(neighbor);
                // 说明 neighbor 是可访问的
                if (!visited[neighbor] && cur[1] - nodeCount - 1 >= 0) {
                    queue.offer(new int[]{neighbor, cur[1] - nodeCount - 1});
                }

                // 能够达到的节点个数
                int reachableCount = Math.min(cur[1], nodeCount);
                graph.get(cur[0]).put(neighbor, nodeCount - reachableCount);
                graph.get(neighbor).put(cur[0], nodeCount - reachableCount);

                result += reachableCount;
            }
        }
        return result;
    }

    private static Map<Integer, Map<Integer, Integer>> buildGraph(int[][] edges, int n) {
        Map<Integer, Map<Integer, Integer>> graph = new HashMap<>(n);
        for (int i = 0; i < n; i++) {
            graph.put(i, new HashMap<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).put(edge[1], edge[2]);
            graph.get(edge[1]).put(edge[0], edge[2]);
        }
        return graph;
    }
}
