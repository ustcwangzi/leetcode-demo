package com.wz.other;

import java.util.Arrays;

/**
 * There are n cities numbered from 1 to n. You are given an array edges of size n-1,
 * where edges[i] = [ui, vi] represents a bidirectional edge between cities ui and vi.
 * There exists a unique path between each pair of cities. In other words, the cities form a tree.
 * A subtree is a subset of cities where every city is reachable from every other city in the subset,
 * where the path between each pair passes through only the cities from the subset.
 * Two subtrees are different if there is a city in one subtree that is not present in the other.
 * For each d from 1 to n-1, find the number of subtrees in which the maximum distance between any two cities in the subtree is equal to d.
 * Return an array of size n-1 where the dth element (1-indexed) is the number of subtrees in which the maximum distance between any two cities is equal to d.
 * Notice that the distance between the two cities is the number of edges in the path between them.
 *
 * Example 1:
 * @link ../../../../resource/CountSubtreesWithMaxDistanceBetweenCities.jpg
 * Input: n = 4, edges = [[1,2],[2,3],[2,4]]
 * Output: [3,4,0]
 * Explanation:
 * The subtrees with subsets {1,2}, {2,3} and {2,4} have a max distance of 1.
 * The subtrees with subsets {1,2,3}, {1,2,4}, {2,3,4} and {1,2,3,4} have a max distance of 2.
 * No subtree has two nodes where the max distance between them is 3.
 *
 * Example 2:
 * Input: n = 2, edges = [[1,2]]
 * Output: [1]
 *
 * Example 3:
 * Input: n = 3, edges = [[1,2],[2,3]]
 * Output: [2,1]
 *
 * Constraints:
 * 1. 2 <= n <= 15
 * 2. edges.length == n-1
 * 3. edges[i].length == 2
 * 4. 1 <= ui, vi <= n
 * 5. All pairs (ui, vi) are distinct.
 */
public class CountSubtreesWithMaxDistanceBetweenCities {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(countSubgraphsForEachDiameter(4, new int[][]{{1, 2}, {2, 3}, {2, 4}})));
        System.out.println(Arrays.toString(countSubgraphsForEachDiameter(2, new int[][]{{1, 2}})));
        System.out.println(Arrays.toString(countSubgraphsForEachDiameter(3, new int[][]{{1, 2}, {2, 3}})));
    }

    /**
     * 先计算任意两个节点直接的距离
     * - 若节点直接相连，则 distance[i][j] == distance[j][i]
     * - 若节点间接相连，则 distance[i][j] = distance[i][k] + distance[k][j]
     * 使用 bitmask 记录组合形式，然后遍历每一种组合，找到合法的子树 边个数 == 节点个数-1，求最大距离
     */
    public static int[] countSubgraphsForEachDiameter(int n, int[][] edges) {
        int[][] distance = calDistance(n, edges);
        int[] result = new int[n - 1];
        for (int state = 0; state < (1 << n); state++) {
            int maxDist = 0, edgeCount = 0, cityCount = 0;
            for (int i = 0; i < n; i++) {
                // 节点 i 不存在
                if ((state & (1 << i)) == 0) {
                    continue;
                }

                cityCount++;
                for (int j = i + 1; j < n; j++) {
                    if ((state & (1 << j)) == 0) {
                        continue;
                    }

                    maxDist = Math.max(maxDist, distance[i][j]);
                    // 距离为 1，说明是边
                    if (distance[i][j] == 1) {
                        edgeCount++;
                    }
                }
            }

            // 边个数 == 节点个数-1
            if (edgeCount == cityCount - 1 && maxDist > 0) {
                result[maxDist - 1]++;
            }
        }
        return result;
    }

    private static int[][] calDistance(int n, int[][] edges) {
        int[][] distance = new int[n][n];
        Arrays.stream(distance).forEach(array -> Arrays.fill(array, n));
        for (int[] edge : edges) {
            distance[edge[0] - 1][edge[1] - 1] = 1;
            distance[edge[1] - 1][edge[0] - 1] = 1;
        }
        // k 是过渡节点
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    distance[i][j] = Math.min(distance[i][j], distance[i][k] + distance[k][j]);
                }
            }
        }
        return distance;
    }
}
