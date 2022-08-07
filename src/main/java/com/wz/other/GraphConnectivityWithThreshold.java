package com.wz.other;

import java.util.ArrayList;
import java.util.List;

/**
 * We have n cities labeled from 1 to n. Two different cities with labels x and y are directly connected by a bidirectional road
 * if and only if x and y share a common divisor strictly greater than some threshold. More formally,
 * cities with labels x and y have a road between them if there exists an integer z such that all of the following are true:
 * - x % z == 0,
 * - y % z == 0, and
 * - z > threshold.
 * Given the two integers, n and threshold, and an array of queries, you must determine for each queries[i] = [ai, bi]
 * if cities ai and bi are connected directly or indirectly. (i.e. there is some path between them).
 * Return an array answer, where answer.length == queries.length and answer[i] is true if for the ith query,
 * there is a path between ai and bi, or answer[i] is false if there is no path.
 *
 * Example 1:
 * @link ../../../../resource/GraphConnectivityWithThreshold1.jpg
 * Input: n = 6, threshold = 2, queries = [[1,4],[2,5],[3,6]]
 * Output: [false,false,true]
 * Explanation: The divisors for each number:
 * 1:   1
 * 2:   1, 2
 * 3:   1, 3
 * 4:   1, 2, 4
 * 5:   1, 5
 * 6:   1, 2, 3, 6
 * Using the underlined divisors above the threshold, only cities 3 and 6 share a common divisor, so they are the
 * only ones directly connected. The result of each query:
 * [1,4]   1 is not connected to 4
 * [2,5]   2 is not connected to 5
 * [3,6]   3 is connected to 6 through path 3--6
 *
 * Example 2:
 * @link ../../../../resource/GraphConnectivityWithThreshold2.jpg
 * Input: n = 6, threshold = 0, queries = [[4,5],[3,4],[3,2],[2,6],[1,3]]
 * Output: [true,true,true,true,true]
 * Explanation: The divisors for each number are the same as the previous example. However, since the threshold is 0,
 * all divisors can be used. Since all numbers share 1 as a divisor, all cities are connected.
 *
 * Example 3:
 * @link ../../../../resource/GraphConnectivityWithThreshold3.jpg
 * Input: n = 5, threshold = 1, queries = [[4,5],[4,5],[3,2],[2,3],[3,4]]
 * Output: [false,false,false,false,false]
 * Explanation: Only cities 2 and 4 share a common divisor 2 which is strictly greater than the threshold 1, so they are the only ones directly connected.
 * Please notice that there can be multiple queries for the same pair of nodes [x, y], and that the query [x, y] is equivalent to the query [y, x].
 *
 * Constraints:
 * 1. 2 <= n <= 10^4
 * 2. 0 <= threshold <= n
 * 3. 1 <= queries.length <= 10^5
 * 4. queries[i].length == 2
 * 5. 1 <= ai, bi <= cities
 * 6. ai != bi
 */
public class GraphConnectivityWithThreshold {
    public static void main(String[] args) {
        System.out.println(areConnected(6, 2, new int[][]{{1, 4}, {2, 5}, {3, 6}}));
        System.out.println(areConnected(6, 0, new int[][]{{4, 5}, {3, 4}, {3, 2}, {2, 6}, {1, 3}}));
    }

    /**
     * 并查集 Union-Find
     * 遍历所有可能的 z，即 [threshold+1, n]，和 j 乘积小于等于 n 时，将其加入到 UF 中
     * 遍历结束后，再遍历 query 数组，若 query[0] 和 query[1] 的 root 节点相同时，满足要求
     */
    public static List<Boolean> areConnected(int n, int threshold, int[][] queries) {
        List<Boolean> result = new ArrayList<>(queries.length);
        UnionFind uf = new UnionFind(n + 1);
        for (int i = threshold + 1; i <= n; i++) {
            int j = 1;
            while (i * j <= n) {
                uf.union(i, i * j);
                j++;
            }
        }
        for (int[] query : queries) {
            result.add(uf.find(query[0]) == uf.find(query[1]));
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
