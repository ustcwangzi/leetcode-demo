package com.wz.bfs;

import java.util.*;

/**
 * Given an undirected tree consisting of n vertices numbered from 1 to n. A frog starts jumping from vertex 1.
 * In one second, the frog jumps from its current vertex to another unvisited vertex if they are directly connected.
 * The frog can not jump back to a visited vertex. In case the frog can jump to several vertices, it jumps randomly to one of them with the same probability.
 * Otherwise, when the frog can not jump to any unvisited vertex, it jumps forever on the same vertex.
 * The edges of the undirected tree are given in the array edges, where edges[i] = [ai, bi] means that exists an edge connecting the vertices ai and bi.
 * Return the probability that after t seconds the frog is on the vertex target. Answers within 10-5 of the actual answer will be accepted.
 *
 * Example 1:
 * @link ../../../../resource/FrogPositionAfterTSeconds1.jpg
 * Input: n = 7, edges = [[1,2],[1,3],[1,7],[2,4],[2,6],[3,5]], t = 2, target = 4
 * Output: 0.16666666666666666
 * Explanation: The figure above shows the given graph. The frog starts at vertex 1, jumping with 1/3 probability to the vertex 2 after second 1
 * and then jumping with 1/2 probability to vertex 4 after second 2. Thus the probability for the frog is on the vertex 4 after 2 seconds is 1/3 * 1/2 = 1/6 = 0.16666666666666666.
 *
 * Example 2:
 * @link ../../../../resource/FrogPositionAfterTSeconds2.jpg
 * Input: n = 7, edges = [[1,2],[1,3],[1,7],[2,4],[2,6],[3,5]], t = 1, target = 7
 * Output: 0.3333333333333333
 * Explanation: The figure above shows the given graph. The frog starts at vertex 1, jumping with 1/3 = 0.3333333333333333 probability to the vertex 7 after second 1.
 *
 * Constraints:
 * 1. 1 <= n <= 100
 * 2. edges.length == n - 1
 * 3. edges[i].length == 2
 * 4. 1 <= ai, bi <= n
 * 5. 1 <= t <= 50
 * 6. 1 <= target <= n
 */
public class FrogPositionAfterTSeconds {
    public static void main(String[] args) {
        int[][] edges = new int[][]{{1, 2}, {1, 3}, {1, 7}, {2, 4}, {2, 6}, {3, 5}};
        System.out.println(frogPosition(7, edges, 2, 4));
        System.out.println(frogPosition(7, edges, 1, 7));
    }

    /**
     * 在普通 BFS 的基础上增加当前节点的可能性，下一个节点可能性为 1/count * 当前节点可能性，其中 count 为当前节点可以到达的下一个节点个数
     */
    public static double frogPosition(int n, int[][] edges, int t, int target) {
        Map<Integer, Set<Integer>> graph = buildGraph(n, edges);
        Queue<Node> queue = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];
        queue.offer(new Node(1, 1));
        visited[1] = true;

        int step = 0;
        while (!queue.isEmpty() && step <= t) {
            int size = queue.size();
            while (size-- > 0) {
                Node cur = queue.poll();
                int count = 0;
                for (int neighbor : graph.get(cur.val)) {
                    if (!visited[neighbor]) {
                        count++;
                    }
                }
                if (cur.val == target && (count == 0 || step == t)) {
                    return cur.probability;
                }
                for (int neighbor : graph.get(cur.val)) {
                    if (!visited[neighbor]) {
                        visited[neighbor] = true;
                        queue.offer(new Node(neighbor, 1.0 / count * cur.probability));
                    }
                }
            }
            step++;
        }
        return 0.0;
    }

    private static Map<Integer, Set<Integer>> buildGraph(int n, int[][] edges) {
        Map<Integer, Set<Integer>> graph = new HashMap<>(n);
        for (int i = 1; i <= n; i++) {
            graph.put(i, new HashSet<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        return graph;
    }

    private static class Node {
        private final int val;
        private final double probability;

        public Node(int val, double probability) {
            this.val = val;
            this.probability = probability;
        }
    }
}
