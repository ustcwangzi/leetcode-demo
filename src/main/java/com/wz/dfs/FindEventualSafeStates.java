package com.wz.dfs;

import java.util.LinkedList;
import java.util.List;

/**
 * We start at some node in a directed graph, and every turn, we walk along a directed edge of the graph. If we reach a terminal node (that is, it has no outgoing directed edges), we stop.
 *
 * We define a starting node to be safe if we must eventually walk to a terminal node. More specifically, there is a natural number k, so that we must have stopped at a terminal node in less than k steps for any choice of where to walk.
 *
 * Return an array containing all the safe nodes of the graph. The answer should be sorted in ascending order.
 *
 * The directed graph has n nodes with labels from 0 to n - 1, where n is the length of graph. The graph is given in the following form: graph[i] is a list of labels j such that (i, j) is a directed edge of the graph, going from node i to node j.
 *
 *
 *
 * Example 1:
 * @see ../../../../resource/FindEventualSafeStates.jpg
 * Illustration of graph
 * Input: graph = [[1,2],[2,3],[5],[0],[5],[],[]]
 * Output: [2,4,5,6]
 * Explanation: The given graph is shown above.
 */
public class FindEventualSafeStates {
    public static void main(String[] args) {
        int[][] graph = new int[][]{{1, 2}, {2, 3}, {5}, {0}, {5}, {}, {}};
        System.out.println(eventualSafeNodes(graph));
    }

    /**
     * 之所以某些结点不是安全状态，是因为有环的存在，而环经过的所有结点，一定不是安全状态节点，所以可以通过 DFS 遍历有向图来找出环即可
     * 在大多数的算法中，经典的 DFS 遍历法对于节点都有三种状态标记，white，gray 和 black，
     * 其中 white 表示节点还未遍历，gray 表示正在遍历该节点，black 表示已经结束该节点的遍历，采用 0、1、2 来表示这三种状态，
     * 对每个结点都调用 DFS，在 DFS 中，如果当前节点不是 0，表示已经访问过了，那么如果当前节点是 2，直接返回 true，
     * 否则返回 false，因为遇到遍历中的节点，表示一定有环存在。如果当前节点是 0，则先标记为 1，然后开始遍历所有邻接节点，
     * 遍历中如果某个邻接节点是 2，直接跳过，如果某个邻接节点正在遍历中、或者对该邻接节点调用 DFS 返回 false 了，直接返回 false
     * 遍历结束时，将当前结点标记为 2，并且返回 true
     */
    public static List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        List<Integer> result = new LinkedList<>();
        int[] color = new int[n];
        for (int i = 0; i < n; ++i) {
            if (dfs(graph, i, color)) {
                result.add(i);
            }
        }
        return result;
    }

    /**
     * 检测是否存在环
     */
    private static boolean dfs(int[][] graph, int cur, int[] color) {
        if (color[cur] > 0) {
            return color[cur] == 2;
        }

        // 标记为正在遍历
        color[cur] = 1;
        for (int i : graph[cur]) {
            if (color[i] == 2) {
                continue;
            }
            if (color[i] == 1 || !dfs(graph, i, color)) {
                return false;
            }
        }
        // 标记为遍历结束
        color[cur] = 2;
        return true;
    }
}
