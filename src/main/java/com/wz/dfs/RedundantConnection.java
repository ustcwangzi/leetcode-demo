package com.wz.dfs;

import java.util.Arrays;

/**
 * In this problem, a tree is an undirected graph that is connected and has no cycles.
 * The given input is a graph that started as a tree with N nodes (with distinct values 1, 2, ..., N),
 * with one additional edge added. The added edge has two different vertices chosen from 1 to N, and was not an edge that already existed.
 * The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [u, v] with u < v,
 * that represents an undirected edge connecting nodes u and v.
 * Return an edge that can be removed so that the resulting graph is a tree of N nodes. If there are multiple answers,
 * return the answer that occurs last in the given 2D-array. The answer edge [u, v] should be in the same format, with u < v.
 *
 * Example 1:
 * Input: [[1,2], [1,3], [2,3]]
 * Output: [2,3]
 * Explanation: The given undirected graph will be like this:
 *   1
 *  / \
 * 2 - 3
 *
 * Example 2:
 * Input: [[1,2], [2,3], [3,4], [1,4], [1,5]]
 * Output: [1,4]
 * Explanation: The given undirected graph will be like this:
 * 5 - 1 - 2
 *     |   |
 *     4 - 3
 *
 * Note:
 * 1. The size of the input 2D-array will be between 3 and 1000.
 * 2. Every integer represented in the 2D-array will be between 1 and N, where N is the size of the input array.
 */
public class RedundantConnection {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(findRedundantConnection(new int[][]{{1, 2}, {1, 3}, {2, 3}})));
    }

    /**
     * 并查集 Union-Find
     * 找到冗余边，删掉之后将有环图变为无环图，只需要找到其中一条边两个节点的顶点（即根节点）相同即可
     * 遍历二维数组，每次都把边的两个顶点找出并记录下来，假设 nums[a] = b，那么就有 a 的顶点是 b
     * 如果这两个节点的顶点相同，就返回这条边，如果顶点不相同，那么将第一个节点的根结点置为第二个节点
     */
    public static int[] findRedundantConnection(int[][] edges) {
        int[] parents = new int[edges.length + 1];

        for (int[] edge : edges) {
            int root1 = findRoot(parents, edge[0]), root2 = findRoot(parents, edge[1]);
            if (root1 == root2) {
                return edge;
            }
            parents[root1] = root2;
        }

        return new int[2];
    }

    private static int findRoot(int[] parents, int num) {
        if (parents[num] != 0) {
            return findRoot(parents, parents[num]);
        }
        // 根节点不存在时，这个节点自身就是根节点
        return num;
    }
}
