package com.wz.dfs;

import java.util.*;

/**
 * There are n cities numbered from 0 to n-1 and n-1 roads such that there is only one way to travel between two different cities
 * (this network form a tree). Last year, The ministry of transport decided to orient the roads in one direction because they are too narrow.
 * Roads are represented by connections where connections[i] = [a, b] represents a road from city a to b.
 * This year, there will be a big event in the capital (city 0), and many people want to travel to this city.
 * Your task consists of reorienting some roads such that each city can visit the city 0. Return the minimum number of edges changed.
 * It's guaranteed that each city can reach the city 0 after reorder.
 *
 * Example 1:
 * @link ../../../../resource/ReorderRoutesToMakeAllPathsLeadToTheCityZero1.jpg
 * Input: n = 6, connections = [[0,1],[1,3],[2,3],[4,0],[4,5]]
 * Output: 3
 * Explanation: Change the direction of edges show in red such that each node can reach the node 0 (capital).
 *
 * Example 2:
 * @link ../../../../resource/ReorderRoutesToMakeAllPathsLeadToTheCityZero2.jpg
 * Input: n = 5, connections = [[1,0],[1,2],[3,2],[3,4]]
 * Output: 2
 * Explanation: Change the direction of edges show in red such that each node can reach the node 0 (capital).
 */
public class ReorderRoutesToMakeAllPathsLeadToTheCityZero {
    public static void main(String[] args) {
        int[][] connections = new int[][]{{0, 1}, {1, 3}, {2, 3}, {4, 0}, {4, 5}};
        System.out.println(minReorder(6, connections));

        connections = new int[][]{{1, 0}, {1, 2}, {3, 2}, {3, 4}};
        System.out.println(minReorder(6, connections));
    }

    /**
     * BFS
     * 题目转换为从节点 0 出发到达所有顶点需要翻转多条边
     * 使用 BFS 进行遍历，如果一个节点的 neighbor 不是指向当前节点，则需要改变
     * 因此既要知道下一层的节点，又要知道指向关系，所以建立两个图 directGraph 和 unDirectGraph
     */
    public static int minReorder(int n, int[][] connections) {
        // 分别保存指向关系 和 下一层的节点
        Map<Integer, Set<Integer>> directGraph = new HashMap<>(n), unDirectGraph = new HashMap<>(n);
        buildGraph(connections, directGraph, unDirectGraph);

        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        visited[0] = true;

        int count = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int cur = queue.poll();
                for (int neighbor : unDirectGraph.get(cur)) {
                    if (visited[neighbor]) {
                        continue;
                    }
                    //  neighbor 没有指向 cur
                    if (!directGraph.get(neighbor).contains(cur)) {
                        count++;
                    }

                    queue.add(neighbor);
                    visited[neighbor] = true;
                }
            }
        }
        return count;
    }

    private static void buildGraph(int[][] connections,
                                   Map<Integer, Set<Integer>> directGraph,
                                   Map<Integer, Set<Integer>> unDirectGraph) {
        for (int[] connection : connections) {
            // a -> b
            int a = connection[0], b = connection[1];
            directGraph.putIfAbsent(a, new HashSet<>());
            // 层次遍历时，对每个节点都会进行 get 操作，为保持同一，这里也进行 put
            directGraph.putIfAbsent(b, new HashSet<>());
            directGraph.get(a).add(b);

            unDirectGraph.putIfAbsent(a, new HashSet<>());
            unDirectGraph.putIfAbsent(b, new HashSet<>());
            unDirectGraph.get(a).add(b);
            unDirectGraph.get(b).add(a);
        }
    }
}
