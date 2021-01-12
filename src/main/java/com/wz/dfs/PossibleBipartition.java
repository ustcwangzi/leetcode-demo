package com.wz.dfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a set of N people (numbered 1, 2, ..., N), we would like to split everyone into two groups of any size.
 * Each person may dislike some other people, and they should not go into the same group.
 * Formally, if dislikes[i] = [a, b], it means it is not allowed to put the people numbered a and b into the same group.
 * Return true if and only if it is possible to split everyone into two groups in this way.
 *
 * Example 1:
 * Input: N = 4, dislikes = [[1,2],[1,3],[2,4]]
 * Output: true
 * Explanation: group1 [1,4], group2 [2,3]
 *
 * Example 2:
 * Input: N = 3, dislikes = [[1,2],[1,3],[2,3]]
 * Output: false
 *
 * Constraints:
 * 1. 1 <= N <= 2000
 * 2. 0 <= dislikes.length <= 10000
 * 3. dislikes[i].length == 2
 * 4. 1 <= dislikes[i][j] <= N
 * 5. dislikes[i][0] < dislikes[i][1]
 * 6. There does not exist i != j for which dislikes[i] == dislikes[j].
 */
public class PossibleBipartition {
    public static void main(String[] args) {
        int[][] dislikes = new int[][]{{1, 2}, {1, 3}, {2, 4}};
        System.out.println(possibleBipartition(4, dislikes));
        dislikes = new int[][]{{1, 2}, {1, 3}, {2, 3}};
        System.out.println(possibleBipartition(4, dislikes));
    }

    /**
     * BFS
     * 与 {@link IsGraphBipartite} 类似
     * 本题没有给出邻接表，所以需要先建立邻接表，剩余步骤和 {@link IsGraphBipartite} 一样
     */
    public static boolean possibleBipartition(int N, int[][] dislikes) {
        List<List<Integer>> graph = new ArrayList<>(N + 1);
        for (int i = 0; i <= N; i++) {
            graph.add(new LinkedList<>());
        }
        for (int[] dislike : dislikes) {
            graph.get(dislike[0]).add(dislike[1]);
            graph.get(dislike[1]).add(dislike[0]);
        }

        int[] colors = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            if (colors[i] != 0) {
                continue;
            }
            // 染色
            colors[i] = 1;
            Queue<Integer> queue = new LinkedList<>();
            queue.add(i);
            while (!queue.isEmpty()) {
                int cur = queue.poll();
                for (int neighbor : graph.get(cur)) {
                    // 相连的两个节点已经被染成相同的颜色，直接返回false
                    if (colors[neighbor] == colors[cur]) {
                        return false;
                    }
                    // 相连的两个节点染成不同的颜色
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
