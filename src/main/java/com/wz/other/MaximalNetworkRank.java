package com.wz.other;

/**
 * There is an infrastructure of n cities with some number of roads connecting these cities.
 * Each roads[i] = [ai, bi] indicates that there is a bidirectional road between cities ai and bi.
 * The network rank of two different cities is defined as the total number of directly connected roads to either city.
 * If a road is directly connected to both cities, it is only counted once.
 * The maximal network rank of the infrastructure is the maximum network rank of all pairs of different cities.
 * Given the integer n and the array roads, return the maximal network rank of the entire infrastructure.
 *
 * Example 1:
 * @see ../../../../resource/MaximalNetworkRank1.jpg
 * Input: n = 4, roads = [[0,1],[0,3],[1,2],[1,3]]
 * Output: 4
 * Explanation: The network rank of cities 0 and 1 is 4 as there are 4 roads that are connected to either 0 or 1. The road between 0 and 1 is only counted once.
 *
 * Example 2:
 * @see ../../../../resource/MaximalNetworkRank2.jpg
 * Input: n = 5, roads = [[0,1],[0,3],[1,2],[1,3],[2,3],[2,4]]
 * Output: 5
 * Explanation: There are 5 roads that are connected to cities 1 or 2.
 *
 * Constraints:
 * 1. 2 <= n <= 100
 * 2. 0 <= roads.length <= n * (n - 1) / 2
 * 3. roads[i].length == 2
 * 4. 0 <= ai, bi <= n-1
 * 5. ai != bi
 * 6. Each pair of cities has at most one road connecting them.
 */
public class MaximalNetworkRank {
    public static void main(String[] args) {
        System.out.println(maximalNetworkRank(4, new int[][]{{0, 1}, {0, 3}, {1, 2}, {1, 3}}));
        System.out.println(maximalNetworkRank(5, new int[][]{{0, 1}, {0, 3}, {1, 2}, {1, 3}, {2, 3}, {2, 4}}));
    }

    /**
     * 使用 edges 记录每个节点相邻的节点个数，使用 adjacent[i][j] 记录节点 i 和节点 j 是否相邻
     * 然后双层遍历所有节点，累加每个节点的 rank，同时若两点是能直接相连的，需要进行减一
     */
    public static int maximalNetworkRank(int n, int[][] roads) {
        int[] edges = new int[n + 1];
        boolean[][] adjacent = new boolean[n + 1][n + 1];
        for (int[] road : roads) {
            edges[road[0]]++;
            edges[road[1]]++;
            adjacent[road[0]][road[1]] = true;
            adjacent[road[1]][road[0]] = true;
        }

        int result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int rank = edges[i] + edges[j] - (adjacent[i][j] ? 1 : 0);
                result = Math.max(result, rank);
            }
        }
        return result;
    }
}
