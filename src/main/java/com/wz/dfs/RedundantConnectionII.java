package com.wz.dfs;

import java.util.Arrays;

/**
 * In this problem, a rooted tree is a directed graph such that, there is exactly one node (the root) for which all other nodes are descendants of this node, plus every node has exactly one parent, except for the root node which has no parents.
 * The given input is a directed graph that started as a rooted tree with n nodes (with distinct values from 1 to n), with one additional directed edge added. The added edge has two different vertices chosen from 1 to n, and was not an edge that already existed.
 * The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [ui, vi] that represents a directed edge connecting nodes ui and vi, where ui is a parent of child vi.
 * Return an edge that can be removed so that the resulting graph is a rooted tree of n nodes. If there are multiple answers, return the answer that occurs last in the given 2D-array.
 *
 * Example 1:
 * @link ../../../../resource/RedundantConnectionII1.jpg
 * Input: edges = [[1,2],[1,3],[2,3]]
 * Output: [2,3]
 *
 * Example 2:
 * @link ../../../../resource/RedundantConnectionII2.jpg
 * Input: edges = [[1,2],[2,3],[3,4],[4,1],[1,5]]
 * Output: [4,1]
 *
 * Constraints:
 * 1. n == edges.length
 * 2. 3 <= n <= 1000
 * 3. edges[i].length == 2
 * 4. 1 <= ui, vi <= n
 * 5. ui != vi
 */
public class RedundantConnectionII {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(findRedundantDirectedConnection(new int[][]{{1, 2}, {1, 3}, {2, 3}})));
        System.out.println(Arrays.toString(findRedundantDirectedConnection(new int[][]{{1, 2}, {2, 3}, {3, 4}, {4, 1}, {1, 5}})));
        System.out.println(Arrays.toString(findRedundantDirectedConnection(new int[][]{{2, 1}, {3, 1}, {4, 2}, {1, 4}})));
    }

    /**
     * 并查集 Union-Find，是对 {@link RedundantConnection} 的扩展，那题是无向图，本题是有向图
     * 使用 parents[] 记录每个节点的根节点，inDegree[] 记录每个节点的入度
     * 分为两种情况：
     * 情况一：图中有环，即两个节点的根节点相同
     * 情况二：图中无环，但某个节点存在两个父节点，即入度等于二
     * 针对两种情况，分别采用与 {@link RedundantConnection} 类似的解决方案即可
     */
    public static int[] findRedundantDirectedConnection(int[][] edges) {
        int n = edges.length;
        int[] parents = new int[n + 1], inDegree = new int[n + 1];
        for (int[] edge : edges) {
            inDegree[edge[1]]++;
        }
        for (int i = 0; i < edges.length; i++) {
            parents[i] = i;
        }

        // 有环
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            if (inDegree[v] == 1) {
                int rootU = findRoot(parents, u), rootV = findRoot(parents, v);
                if (rootU == rootV) {
                    return edge;
                }
                parents[rootU] = rootV;
            }
        }
        // 无环，两个父节点
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            if (inDegree[v] > 1) {
                int rootU = findRoot(parents, u), rootV = findRoot(parents, v);
                if (rootU == rootV) {
                    return edge;
                }
                parents[rootU] = rootV;
            }
        }
        return new int[2];
    }

    private static int findRoot(int[] parents, int num) {
        if (parents[num] == num) {
            return num;
        }
        return parents[num] = findRoot(parents, parents[num]);
    }
}
