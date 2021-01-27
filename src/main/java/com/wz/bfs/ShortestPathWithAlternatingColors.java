package com.wz.bfs;

import java.util.*;

/**
 * Consider a directed graph, with nodes labelled 0, 1, ..., n-1.  In this graph, each edge is either red or blue, and there could be self-edges or parallel edges.
 * Each [i, j] in red_edges denotes a red directed edge from node i to node j.  Similarly, each [i, j] in blue_edges denotes a blue directed edge from node i to node j.
 * Return an array answer of length n, where each answer[X] is the length of the shortest path from node 0 to node X
 * such that the edge colors alternate along the path (or -1 if such a path doesn't exist).
 *
 * Example 1:
 * Input: n = 3, red_edges = [[0,1],[1,2]], blue_edges = []
 * Output: [0,1,-1]
 *
 * Example 2:
 * Input: n = 3, red_edges = [[0,1]], blue_edges = [[2,1]]
 * Output: [0,1,-1]
 *
 * Constraints:
 * 1. 1 <= n <= 100
 * 2. red_edges.length <= 400
 * 3. blue_edges.length <= 400
 * 4. red_edges[i].length == blue_edges[i].length == 2
 * 5. 0 <= red_edges[i][j], blue_edges[i][j] < n
 */
public class ShortestPathWithAlternatingColors {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(shortestAlternatingPaths(3, new int[][]{{0, 1}, {1, 2}}, new int[][]{})));
    }

    /**
     * 与 {@link ShortestPathInBinaryMatrix} 类似
     * 需要两种颜色的路径交替出现，因此构建 graph 时，使用第一位表示位置、第二位表示颜色(0 为红色，1 为绿色)
     */
    public static int[] shortestAlternatingPaths(int n, int[][] red_edges, int[][] blue_edges) {
        Map<Integer, List<int[]>> graph = new HashMap<>(n);
        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (int[] edge : red_edges) {
            graph.get(edge[0]).add(new int[]{edge[1], 0});
        }
        for (int[] edge : blue_edges) {
            graph.get(edge[0]).add(new int[]{edge[1], 1});
        }

        int[] result = new int[n];
        Arrays.fill(result, -1);

        Queue<int[]> queue = new LinkedList<>();
        // 位置、颜色、路径长度，使用 -1 是因为不知道红色和绿色哪种开始结果最小
        queue.add(new int[]{0, -1, 0});
        boolean[][] visited = new boolean[n][2];
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                if (result[cur[0]] == -1) {
                    result[cur[0]] = cur[2];
                }
                for (int[] neighbor : graph.get(cur[0])) {
                    if (visited[neighbor[0]][neighbor[1]]) {
                        continue;
                    }
                    // 下一步颜色不能和当前步相同
                    if (neighbor[1] != cur[1]) {
                        queue.add(new int[]{neighbor[0], neighbor[1], cur[2] + 1});
                        visited[neighbor[0]][neighbor[1]] = true;
                    }
                }
            }
        }
        return result;
    }

}
