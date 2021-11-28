package com.wz.dfs;

import java.util.*;

/**
 * Given a tree (i.e. a connected, undirected graph that has no cycles) consisting of n nodes numbered from 0 to n - 1
 * and exactly n - 1 edges. The root of the tree is the node 0, and each node of the tree has a label which is a
 * lower-case character given in the string labels (i.e. The node with the number i has the label labels[i]).
 * The edges array is given on the form edges[i] = [ai, bi], which means there is an edge between nodes ai and bi in the tree.
 * Return an array of size n where ans[i] is the number of nodes in the subtree of the ith node which have the same label as node i.
 * A subtree of a tree T is the tree consisting of a node in T and all of its descendant nodes.
 *
 * Example 1:
 * @link ../../../../resource/NumberOfNodesInSubTreeWithTheSameLabel1.jpg
 * Input: n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], labels = "abaedcd"
 * Output: [2,1,1,1,1,1,1]
 * Explanation: Node 0 has label 'a' and its sub-tree has node 2 with label 'a' as well, thus the answer is 2. Notice that any node is part of its sub-tree.
 * Node 1 has a label 'b'. The sub-tree of node 1 contains nodes 1,4 and 5, as nodes 4 and 5 have different labels than node 1, the answer is just 1 (the node itself).
 *
 * Example 2:
 * @link ../../../../resource/NumberOfNodesInSubTreeWithTheSameLabel2.jpg
 * Input: n = 4, edges = [[0,1],[1,2],[0,3]], labels = "bbbb"
 * Output: [4,2,1,1]
 * Explanation: The sub-tree of node 2 contains only node 2, so the answer is 1.
 * The sub-tree of node 3 contains only node 3, so the answer is 1.
 * The sub-tree of node 1 contains nodes 1 and 2, both have label 'b', thus the answer is 2.
 * The sub-tree of node 0 contains nodes 0, 1, 2 and 3, all with label 'b', thus the answer is 4.
 */
public class NumberOfNodesInSubTreeWithTheSameLabel {
    public static void main(String[] args) {
        int[][] edges = new int[][]{{0, 1}, {0, 2}, {1, 4}, {1, 5}, {2, 3}, {2, 6}};
        System.out.println(Arrays.toString(countSubTrees(7, edges, "abaedcd")));

        edges = new int[][]{{0, 1}, {1, 2}, {0, 3}};
        System.out.println(Arrays.toString(countSubTrees(4, edges, "bbbb")));
    }

    /**
     * DFS
     * 建立好无向图之后，用传递 father 参数来避免回去访问的问题，只要 neighbor != father，就继续往下走
     */
    public static int[] countSubTrees(int n, int[][] edges, String labels) {
        Map<Integer, List<Integer>> graph = new HashMap<>(n);
        for (int[] edge : edges) {
            graph.putIfAbsent(edge[0], new ArrayList<>());
            graph.putIfAbsent(edge[1], new ArrayList<>());
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        int[] result = new int[n];
        dfs(0, -1, graph, labels, result);
        return result;
    }

    private static int[] dfs(int cur, int father, Map<Integer, List<Integer>> graph, String labels, int[] result) {
        int[] count = new int[26];
        count[labels.charAt(cur) - 'a']++;
        for (Integer neighbor : graph.get(cur)) {
            if (neighbor != father) {
                int[] ncount = dfs(neighbor, cur, graph, labels, result);
                for (int i = 0; i < 26; i++) {
                    count[i] += ncount[i];
                }
            }
        }
        result[cur] = count[labels.charAt(cur) - 'a'];
        return count;
    }
}
