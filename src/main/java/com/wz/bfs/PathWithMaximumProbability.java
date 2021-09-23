package com.wz.bfs;

import java.util.*;

/**
 * You are given an undirected weighted graph of n nodes (0-indexed), represented by an edge list where edges[i] = [a, b]
 * is an undirected edge connecting the nodes a and b with a probability of success of traversing that edge succProb[i].
 * Given two nodes start and end, find the path with the maximum probability of success to go from start to end and return its success probability.
 * If there is no path from start to end, return 0. Your answer will be accepted if it differs from the correct answer by at most 1e-5.
 *
 * Example 1:
 * @see ../../../../resource/PathWithMaximumProbability1.jpg
 * Input: n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.2], start = 0, end = 2
 * Output: 0.25000
 * Explanation: There are two paths from start to end, one having a probability of success = 0.2 and the other has 0.5 * 0.5 = 0.25.
 *
 * Example 2:
 * @see ../../../../resource/PathWithMaximumProbability2.jpg
 * Input: n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.3], start = 0, end = 2
 * Output: 0.30000
 *
 * Constraints:
 * 1. 2 <= n <= 10^4
 * 2. 0 <= start, end < n
 * 3. start != end
 * 4. 0 <= a, b < n
 * 5. a != b
 * 6. 0 <= succProb.length == edges.length <= 2*10^4
 * 7. 0 <= succProb[i] <= 1
 * 8. There is at most one edge between every two nodes.
 */
public class PathWithMaximumProbability {
    public static void main(String[] args) {
        double[] succProb = new double[]{0.5, 0.5, 0.2};
        System.out.println(maxProbability(3, new int[][]{{0, 1}, {1, 2}, {0, 2}}, succProb, 0, 2));
        succProb = new double[]{0.5, 0.5, 0.3};
        System.out.println(maxProbability(3, new int[][]{{0, 1}, {1, 2}, {0, 2}}, succProb, 0, 2));
    }

    /**
     * BFS + 优先级队列
     * 使用 map 保存每个节点的邻居节点，优先级队列维护 probability 最大的节点，初试时将 start 放入队列
     * 然后通过 BFS 遍历邻居节点，同时使用 set 保存遍历过的节点，遇到 end 就结束
     */
    public static double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        Map<Integer, List<Node>> graph = buildGraph(n, edges, succProb);
        Queue<Node> queue = new PriorityQueue<>((o1, o2) -> Double.compare(o2.prob, o1.prob));
        queue.offer(new Node(start, 1));
        Set<Integer> visited = new HashSet<>();

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            if (visited.contains(cur.id)) {
                continue;
            }
            if (cur.id == end) {
                return cur.prob;
            }

            visited.add(cur.id);
            for (Node neighbor : graph.get(cur.id)) {
                if (visited.contains(neighbor.id)) {
                    continue;
                }
                queue.offer(new Node(neighbor.id, cur.prob * neighbor.prob));
            }
        }
        return 0;
    }

    private static Map<Integer, List<Node>> buildGraph(int n, int[][] edges, double[] succProb) {
        Map<Integer, List<Node>> graph = new HashMap<>(n);
        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (int i = 0; i < edges.length; i++) {
            graph.get(edges[i][0]).add(new Node(edges[i][1], succProb[i]));
            graph.get(edges[i][1]).add(new Node(edges[i][0], succProb[i]));
        }
        return graph;
    }

    private static class Node {
        int id;
        double prob;

        public Node(int id, double prob) {
            this.id = id;
            this.prob = prob;
        }
    }
}
