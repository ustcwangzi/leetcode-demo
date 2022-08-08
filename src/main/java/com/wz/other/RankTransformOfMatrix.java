package com.wz.other;

import java.util.*;

/**
 * Given an m x n matrix, return a new matrix answer where answer[row][col] is the rank of matrix[row][col].
 * The rank is an integer that represents how large an element is compared to other elements. It is calculated using the following rules:
 * - The rank is an integer starting from 1.
 * - If two elements p and q are in the same row or column, then:
 *     If p < q then rank(p) < rank(q)
 *     If p == q then rank(p) == rank(q)
 *     If p > q then rank(p) > rank(q)
 * - The rank should be as small as possible.
 * The test cases are generated so that answer is unique under the given rules.
 *
 * Example 1:
 * @link ../../../../resource/RankTransformOfMatrix1.jpg
 * Input: matrix = [[1,2],[3,4]]
 * Output: [[1,2],[2,3]]
 * Explanation:
 * The rank of matrix[0][0] is 1 because it is the smallest integer in its row and column.
 * The rank of matrix[0][1] is 2 because matrix[0][1] > matrix[0][0] and matrix[0][0] is rank 1.
 * The rank of matrix[1][0] is 2 because matrix[1][0] > matrix[0][0] and matrix[0][0] is rank 1.
 * The rank of matrix[1][1] is 3 because matrix[1][1] > matrix[0][1], matrix[1][1] > matrix[1][0], and both matrix[0][1] and matrix[1][0] are rank 2.
 *
 * Example 2:
 * @link ../../../../resource/RankTransformOfMatrix2.jpg
 * Input: matrix = [[7,7],[7,7]]
 * Output: [[1,1],[1,1]]
 *
 * Example 3:
 * @link ../../../../resource/RankTransformOfMatrix3.jpg
 * Input: matrix = [[20,-21,14],[-19,4,19],[22,-47,24],[-19,4,19]]
 * Output: [[4,2,3],[1,3,4],[5,1,6],[1,3,4]]
 *
 * Constraints:
 * 1. m == matrix.length
 * 2. n == matrix[i].length
 * 3. 1 <= m, n <= 500
 * 4. -10^9 <= matrix[row][col] <= 10^9
 */
public class RankTransformOfMatrix {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(matrixRankTransform(new int[][]{{1, 2}, {3, 4}})));
        System.out.println(Arrays.toString(matrixRankTransform(new int[][]{{20, -21, 14}, {-19, 4, 19}, {22, -47, 24}, {-19, 4, 19}})));
    }

    /**
     * Greedy + 并查集 Union-Find
     */
    public static int[][] matrixRankTransform(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        Map<Integer, List<int[]>> map = new TreeMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                map.putIfAbsent(matrix[i][j], new ArrayList<>());
                map.get(matrix[i][j]).add(new int[]{i, j});
            }
        }

        int[] rank = new int[m + n];
        for (int value : map.keySet()) {
            UnionFind uf = new UnionFind(m + n);
            for (int[] array : map.get(value)) {
                uf.union(array[0], array[1] + m);
            }

            Map<Integer, List<Integer>> groups = uf.getGroups();
            for (int col : groups.keySet()) {
                int maxRank = 0;
                for (int i : groups.get(col)) {
                    maxRank = Math.max(maxRank, rank[i]);
                }
                for (int i : groups.get(col)) {
                    rank[i] = maxRank + 1;
                }
            }
            for (int[] array : map.get(value)) {
                matrix[array[0]][array[1]] = rank[array[0]];
            }
        }
        return matrix;
    }

    private static class UnionFind {
        int[] root;

        public UnionFind(int n) {
            root = new int[n];
        }

        public void union(int x, int y) {
            if (root[x] == 0) {
                root[x] = x;
            }
            if (root[y] == 0) {
                root[y] = y;
            }
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

        public Map<Integer, List<Integer>> getGroups() {
            Map<Integer, List<Integer>> groups = new HashMap<>();
            for (int i = 0; i < root.length; i++) {
                if (root[i] != 0) {
                    int rootI = find(root[i]);
                    groups.putIfAbsent(rootI, new ArrayList<>());
                    groups.get(rootI).add(i);
                }
            }
            return groups;
        }
    }
}
