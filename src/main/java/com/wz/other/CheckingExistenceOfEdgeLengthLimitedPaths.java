package com.wz.other;

import java.util.Arrays;
import java.util.Comparator;

/**
 * An undirected graph of n nodes is defined by edgeList, where edgeList[i] = [ui, vi, disi] denotes an edge
 * between nodes ui and vi with distance disi. Note that there may be multiple edges between two nodes.
 * Given an array queries, where queries[j] = [pj, qj, limitj], your task is to determine for each queries[j]
 * whether there is a path between pj and qj such that each edge on the path has a distance strictly less than limitj .
 * Return a boolean array answer, where answer.length == queries.length and the jth value of answer is true
 * if there is a path for queries[j] is true, and false otherwise.
 *
 * Example 1:
 * @link ../../../../resource/CheckingExistenceOfEdgeLengthLimitedPaths1.jpg
 * Input: n = 3, edgeList = [[0,1,2],[1,2,4],[2,0,8],[1,0,16]], queries = [[0,1,2],[0,2,5]]
 * Output: [false,true]
 * Explanation: The above figure shows the given graph. Note that there are two overlapping edges between 0 and 1 with distances 2 and 16.
 * For the first query, between 0 and 1 there is no path where each distance is less than 2, thus we return false for this query.
 * For the second query, there is a path (0 -> 1 -> 2) of two edges with distances less than 5, thus we return true for this query.
 *
 * Example 2:
 * @link ../../../../resource/CheckingExistenceOfEdgeLengthLimitedPaths2.jpg
 * Input: n = 5, edgeList = [[0,1,10],[1,2,5],[2,3,9],[3,4,13]], queries = [[0,4,14],[1,4,13]]
 * Output: [true,false]
 * Exaplanation: The above figure shows the given graph.
 *
 * Constraints:
 * 1. 2 <= n <= 105
 * 2. 1 <= edgeList.length, queries.length <= 10^5
 * 3. edgeList[i].length == 3
 * 4. queries[j].length == 3
 * 5. 0 <= ui, vi, pj, qj <= n - 1
 * 6. ui != vi
 * 7. pj != qj
 * 8. 1 <= disi, limitj <= 10^9
 * 9. There may be multiple edges between two nodes.
 */
public class CheckingExistenceOfEdgeLengthLimitedPaths {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(distanceLimitedPathsExist(4, new int[][]{{0, 1, 2}, {1, 2, 4}, {2, 0, 8}, {1, 0, 16}}, new int[][]{{0, 1, 2}, {0, 2, 5}})));
        System.out.println(Arrays.toString(distanceLimitedPathsExist(5, new int[][]{{0, 1, 10}, {1, 2, 5}, {2, 3, 9}, {3, 4, 13}}, new int[][]{{0, 4, 14}, {1, 4, 13}})));
    }

    /**
     * 并查集 Union-Find
     * 先对 edgeList、queries 按照 distance 进行排序，然后遍历排序后的 queries
     * 对于每个 query，若 distance < limit，则将 u、v 进行 union，root 相等则说明是可达的
     */
    public static boolean[] distanceLimitedPathsExist(int n, int[][] edgeList, int[][] queries) {
        Arrays.parallelSort(edgeList, Comparator.comparingInt(o -> o[2]));

        int m = queries.length;
        int[][] sortedQueries = new int[m][4];
        for (int i = 0; i < m; i++) {
            sortedQueries[i] = new int[]{queries[i][0], queries[i][1], queries[i][2], i};
        }
        Arrays.parallelSort(sortedQueries, Comparator.comparingInt(o -> o[2]));

        UnionFind uf = new UnionFind(n);
        int index = 0;
        boolean[] result = new boolean[m];
        for (int i = 0; i < m; i++) {
            int[] query = sortedQueries[i];
            while (index < edgeList.length && edgeList[index][2] < query[2]) {
                uf.union(edgeList[index][0], edgeList[index][1]);
                index++;
            }
            result[query[3]] = uf.find(query[0]) == uf.find(query[1]);
        }
        return result;
    }

    private static class UnionFind {
        int[] root;

        public UnionFind(int n) {
            root = new int[n];
            for (int i = 0; i < n; i++) {
                root[i] = i;
            }
        }

        public void union(int x, int y) {
            int rootX = find(x), rootY = find(y);
            if (rootX != rootY) {
                root[rootX] = rootY;
            }
        }

        public int find(int x) {
            if (root[x] != x) {
                return root[x] = find(root[x]);
            }
            return x;
        }
    }
}
