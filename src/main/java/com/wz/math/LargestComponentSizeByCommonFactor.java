package com.wz.math;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a non-empty array of unique positive integers A, consider the following graph:
 * There are A.length nodes, labelled A[0] to A[A.length - 1];
 * There is an edge between A[i] and A[j] if and only if A[i] and A[j] share a common factor greater than 1.
 * Return the size of the largest connected component in the graph.
 *
 * Example 1:
 * Input: [4,6,15,35]
 * Output: 4
 *
 * Example 2:
 * Input: [20,50,9,63]
 * Output: 2
 */
public class LargestComponentSizeByCommonFactor {
    public static void main(String[] args) {
        System.out.println(largestComponentSize(new int[]{4, 6, 15, 35}));
        System.out.println(largestComponentSize(new int[]{20, 50, 9, 63}));
    }

    /**
     * 并查集
     * 对于每个数字进行因数分解，因数分解的结果通过并查集连接在一起，然后统计哪个结果集最大
     */
    public static int largestComponentSize(int[] A) {
        int max = 0;
        for (int num : A) {
            max = Math.max(max, num);
        }

        UnionFind uf = new UnionFind(max);
        for (int num : A) {
            for (int i = 2; i <= Math.sqrt(num); i++) {
                if (num % i == 0) {
                    uf.union(num, i);
                    uf.union(num, num / i);
                }
            }
        }

        Map<Integer, Integer> map = new HashMap<>();
        int result = 0;
        for (int num : A) {
            int root = uf.find(num);
            map.put(root, map.getOrDefault(root, 0) + 1);
            result = Math.max(result, map.get(root));
        }

        return result;
    }

    private static class UnionFind {
        private int[] father;

        public UnionFind(int n) {
            this.father = new int[n + 1];
            for (int i = 0; i <= n; i++) {
                father[i] = i;
            }
        }

        public int find(int x) {
            int j = x;
            while (father[j] != j) {
                j = father[j];
            }

            while (x != j) {
                int fx = father[x];
                father[x] = j;
                x = fx;
            }
            return j;
        }

        public void union(int a, int b) {
            int root_a = find(a);
            int root_b = find(b);
            if (root_a != root_b) {
                father[root_a] = root_b;
            }
        }
    }
}
