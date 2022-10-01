package com.wz.other;

import java.util.*;

/**
 * There is a directed graph of n colored nodes and m edges. The nodes are numbered from 0 to n - 1.
 * You are given a string colors where colors[i] is a lowercase English letter representing the color of the ith node in this graph (0-indexed).
 * You are also given a 2D array edges where edges[j] = [aj, bj] indicates that there is a directed edge from node aj to node bj.
 * A valid path in the graph is a sequence of nodes x1 -> x2 -> x3 -> ... -> xk such that there is a directed edge from xi to xi+1
 * for every 1 <= i < k. The color value of the path is the number of nodes that are colored the most frequently occurring color along that path.
 * Return the largest color value of any valid path in the given graph, or -1 if the graph contains a cycle.
 *
 * Example 1:
 * @link ../../../../resource/LargestColorValueInDirectedGraph1.jpg
 * Input: colors = "abaca", edges = [[0,1],[0,2],[2,3],[3,4]]
 * Output: 3
 * Explanation: The path 0 -> 2 -> 3 -> 4 contains 3 nodes that are colored "a" (red in the above image).
 *
 * Example 2:
 * @link ../../../../resource/LargestColorValueInDirectedGraph2.jpg
 * Input: colors = "a", edges = [[0,0]]
 * Output: -1
 * Explanation: There is a cycle from 0 to 0.
 *
 * Constraints:
 * 1. n == colors.length
 * 2. m == edges.length
 * 3. 1 <= n <= 10^5
 * 4. 0 <= m <= 10^5
 * 5. colors consists of lowercase English letters.
 * 6. 0 <= aj, bj < n
 */
public class LargestColorValueInDirectedGraph {
    public static void main(String[] args) {
        System.out.println(largestPathValue("abaca", new int[][]{{0, 1}, {0, 2}, {2, 3}, {3, 4}}));
        System.out.println(largestPathValue("a", new int[][]{{0, 0}}));
    }

    /**
     * 对各节点进行拓扑排序，然后遍历拓扑序列，结合动态规划逐步更新颜色最大值，并在拓扑遍历中判断是否存在环
     */
    public static int largestPathValue(String colors, int[][] edges) {
        int n = colors.length();
        int[] inDegree = new int[n];
        Map<Integer, Set<Integer>> graph = buildGraph(n, edges, inDegree);

        // dp[i][j] 是截止到 i 节点时，第 j 中颜色的最大值
        int[][] dp = new int[n][26];
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
                dp[i][colors.charAt(i) - 'a'] = 1;
            }
        }

        int result = 0, nodeNum = 0;
        // 拓扑遍历
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            nodeNum++;

            int max = Arrays.stream(dp[cur]).max().orElse(0);
            result = Math.max(result, max);

            // 遍历所有的邻居节点来更新颜色最大值
            for (int neighbor : graph.get(cur)) {
                for (int i = 0; i < 26; i++) {
                    dp[neighbor][i] = Math.max(dp[neighbor][i], dp[cur][i] + (colors.charAt(neighbor) - 'a' == i ? 1 : 0));
                }
                inDegree[neighbor]--;

                if (inDegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }
        // nodeNum != n 说明存在环
        return nodeNum == n ? result : -1;
    }

    private static Map<Integer, Set<Integer>> buildGraph(int n, int[][] edges, int[] inDegree) {
        Map<Integer, Set<Integer>> graph = new HashMap<>(n);
        for (int i = 0; i < n; i++) {
            graph.put(i, new HashSet<>());
        }
        for (int[] edge : edges) {
            inDegree[edge[1]]++;
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        return graph;
    }

}
