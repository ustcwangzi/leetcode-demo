package com.wz.tree;

import java.util.*;

/**
 * There is an undirected connected tree with n nodes labeled from 0 to n - 1 and n - 1 edges.
 * You are given the integer n and the array edges where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the tree.
 * Return an array answer of length n where answer[i] is the sum of the distances between the ith node in the tree and all other nodes.
 *
 * Example 1:
 *     0
 *  /     \
 * 1       2
 *       / | \
 *      3  4  5
 * Input: n = 6, edges = [[0,1],[0,2],[2,3],[2,4],[2,5]]
 * Output: [8,12,6,10,10,10]
 * Explanation: The tree is shown above.
 * We can see that dist(0,1) + dist(0,2) + dist(0,3) + dist(0,4) + dist(0,5)
 * equals 1 + 1 + 2 + 2 + 2 = 8.
 * Hence, answer[0] = 8, and so on.
 *
 * Example 2:
 *  0
 * Input: n = 1, edges = []
 * Output: [0]
 *
 * Example 3:
 *   0
 *  /
 * 1
 * Input: n = 2, edges = [[1,0]]
 * Output: [1,1]
 *
 * Constraints:
 * 1. 1 <= n <= 3 * 10^4
 * 2. edges.length == n - 1
 * 3. edges[i].length == 2
 * 4. 0 <= ai, bi < n
 * 5. ai != bi
 * 6. The given input represents a valid tree.
 */
public class SumOfDistancesInTree {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(sumOfDistancesInTree(6, new int[][]{{0, 1}, {0, 2}, {2, 3}, {2, 4}, {2, 5}})));
        System.out.println(Arrays.toString(sumOfDistancesInTree(2, new int[][]{{1, 0}})));
    }

    /**
     *     0
     *    / \
     *   1   2
     *  / \
     * 3   4
     * 全部节点到节点 0 的距离之和为 6，对于左子树，左子树中到节点 1 的距离之和为 2，而左子树总共有 3 个节点，加起来是 5
     * 右子树只有一个节点 2，在右子树中的距离之和为 0，右子树总共有 1 个节点，5 加上 1，正好是6
     * 这就是计算每一个子树中的节点到子树根节点的距离之和的方法，即全部子节点的距离之和加上以子节点为根的子树节点个数
     * 两个数组 counts[] 和 distance[]，其中 counts[i] 表示以节点 i 为根节点的子树中节点的个数
     * distance[i] 表示以节点 i 为根节点的子树中其余节点到节点 i 的距离之和。根据上面的规律，能够总结出：
     * counts[root] = sum(counts[i]) + 1
     * distance[root] = sum(distance[i]) + sum(counts[i])
     * root 表示全部子树的根节点，i 表示 root 的相连子节点，distance[root] 表示以 root 为根节点的子树中全部的节点到 root 的距离之和，其余非子树中节点的距离之和尚未统计
     * 能够发现这两个式子中当前节点的值都是由其子节点决定的，这种由下而上的特色自然适合用后序遍历来解决
     *
     * 更新完了全部的 counts[root] 和 distance[root] 以后，就要来更新全部的 distance[i] 了
     * distance[root] 表示的是以 root 为根节点的子树中全部的节点到 root 的距离，那么子树以外的节点到 root 的距离也得加上，才是最终的 distance[i]
     * 虽然如今尚未更新全部的 distance[i]，可是有一个节点的 distance 值是正确的，就是整个树的根节点，这个真正的 distance[root] 值是正确的
     * 要计算 root 节点的一个子节点 i 的 distance 值，即要计算全部节点到节点 i 的距离之和，此时知道以节点 i 为根节点的子树的总节点个数为 counts[i]
     * 而这 counts[i] 个节点以前在算 distance[root] 时是到根节点 root 的距离，现在只要计算到节点 i 的距离，因此这 counts[i] 个节点的距离都少了 1
     * 而其余全部的节点，共 N - count[i] 个，离节点 i 的距离比离 root 节点的距离都增长了 1，因此 distance[i] 的更新方法如下：
     * distance[i] = distance[root] - counts[i] + N - counts[i]
     * 这里是从上而下的更新，适合用先序遍历来解决
     * 由于是无向图遍历，为了不死循环，通常要记录访问过的节点，这里因为是树的结构，不会存在环，可以简化，只要当前节点 cur 和上一个节点 pre 不一样即可
     */
    public static int[] sumOfDistancesInTree(int N, int[][] edges) {
        Map<Integer, List<Integer>> graph = new HashMap<>(N);
        for (int i = 0; i < N; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        int[] counts = new int[N], distance = new int[N];
        Arrays.fill(counts, 1);
        postOrder(graph, 0, -1, counts, distance);
        preOrder(graph, 0, -1, counts, distance);
        return distance;
    }

    private static void postOrder(Map<Integer, List<Integer>> graph, int cur, int pre, int[] counts, int[] distance) {
        for (int i : graph.get(cur)) {
            if (i == pre) {
                continue;
            }
            postOrder(graph, i, cur, counts, distance);
            counts[cur] += counts[i];
            distance[cur] += distance[i] + counts[i];
        }

    }

    private static void preOrder(Map<Integer, List<Integer>> graph, int cur, int pre, int[] counts, int[] distance) {
        for (int i : graph.get(cur)) {
            if (i == pre) {
                continue;
            }
            distance[i] = distance[cur] - counts[i] + counts.length - counts[i];
            preOrder(graph, i, cur, counts, distance);
        }
    }
}
