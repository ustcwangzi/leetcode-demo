package com.wz.dfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given an undirected graph, return true if and only if it is bipartite.
 * Recall that a graph is bipartite if we can split its set of nodes into two independent subsets A and B,
 * such that every edge in the graph has one node in A and another node in B.
 * The graph is given in the following form: graph[i] is a list of indexes j for which the edge between nodes i and j exists.
 * Each node is an integer between 0 and graph.length - 1.  There are no self edges or parallel edges:
 * graph[i] does not contain i, and it doesn't contain any element twice.
 *
 * Example 1:
 * @see ../../../../resource/IsGraphBipartite1.jpg
 * Input: graph = [[1,3],[0,2],[1,3],[0,2]]
 * Output: true
 * Explanation: We can divide the vertices into two groups: {0, 2} and {1, 3}.
 *
 * Example 2:
 * @see ../../../../resource/IsGraphBipartite2.jpg
 * Input: graph = [[1,2,3],[0,2],[0,1,3],[0,2]]
 * Output: false
 * Explanation: We cannot find a way to divide the set of nodes into two independent subsets.
 *
 *
 * Constraints:
 * 1. 1 <= graph.length <= 100
 * 2. 0 <= graph[i].length < 100
 * 3. 0 <= graph[i][j] <= graph.length - 1
 * 4. graph[i][j] != i
 * 5. All the values of graph[i] are unique.
 * 6. The graph is guaranteed to be undirected.
 */
public class IsGraphBipartite {
    public static void main(String[] args) {
        int[][] graph = new int[][]{{1, 3}, {0, 2}, {1, 3}, {0, 2}};
        System.out.println(isBipartite(graph));
    }

    /**
     * graph[i]，表示顶点i所有相邻的顶点，比如对于例子1来说，顶点0和顶点1、3相连，顶点1和顶点0、2相连....
     * 二分图，就是可以将图中的所有顶点分成两个不相交的集合，使得同一个集合的顶点不相连
     * 染色法，将相连的两个顶点染成不同的颜色，一旦在染的过程中发现有相连的两个顶点已经被染成相同的颜色，说明不是二分图
     * 使用两种颜色，分别用 1 和 -1 来表示，初始时每个顶点用 0 表示未染色
     */
    public static boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] colors = new int[n];
        for (int i = 0; i < n; i++) {
            if (colors[i] != 0) {
                continue;
            }

            // 染色
            colors[i] = 1;
            Queue<Integer> queue = new LinkedList<>();
            queue.add(i);
            while (!queue.isEmpty()) {
                int cur = queue.poll();
                for (int neighbor : graph[cur]) {
                    // 相连的两个顶点已经被染成相同的颜色
                    if (colors[neighbor] == colors[cur]) {
                        return false;
                    }
                    // 相连的两个顶点染成不同的颜色
                    if (colors[neighbor] == 0) {
                        colors[neighbor] = -1 * colors[cur];
                        queue.add(neighbor);
                    }
                }
            }
        }
        return true;
    }
}
