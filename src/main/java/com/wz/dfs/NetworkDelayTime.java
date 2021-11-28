package com.wz.dfs;

import java.util.*;

/**
 * There are N network nodes, labelled 1 to N.
 * Given times, a list of travel times as directed edges times[i] = (u, v, w), where u is the source node, v is the target node,
 * and w is the time it takes for a signal to travel from source to target.
 * Now, we send a signal from a certain node K. How long will it take for all nodes to receive the signal? If it is impossible, return -1.
 *
 * Example 1:
 * @link ../../../../resource/NetworkDelayTime.jpg
 * Input: times = [[2,1,1],[2,3,1],[3,4,1]], N = 4, K = 2
 * Output: 2
 *
 * Note:
 * 1. N will be in the range [1, 100].
 * 2. K will be in the range [1, N].
 * 3. The length of times will be in the range [1, 6000].
 * 4. All edges times[i] = (u, v, w) will have 1 <= u, v <= N and 0 <= w <= 100.
 */
public class NetworkDelayTime {
    public static void main(String[] args) {
        int[][] times = new int[][]{
                {2, 1, 1},
                {2, 3, 1},
                {3, 4, 1},
        };
        System.out.println(networkDelayTime(times, 4, 2));
    }

    /**
     * Dijkstra
     */
    public static int networkDelayTime(int[][] times, int N, int K) {
        int[] dist = new int[N + 1];
        boolean[] visited = new boolean[N + 1];
        for (int i = 1; i < N + 1; i++) {
            dist[i] = Integer.MAX_VALUE;
            visited[i] = false;
        }
        dist[K] = 0;

        Map<Integer, List<int[]>> graph = buildGraph(times);
        Queue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        int[] start = new int[]{K, 0};
        queue.offer(start);

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            visited[current[0]] = true;
            if (graph.containsKey(current[0])) {
                List<int[]> neighbors = graph.get(current[0]);
                for (int[] neighbor : neighbors) {
                    int cost = neighbor[1] + current[1];
                    if (!visited[neighbor[0]] && dist[neighbor[0]] > cost) {
                        dist[neighbor[0]] = cost;
                        queue.offer(new int[]{neighbor[0], cost});
                    }
                }
            }
        }

        int maxDist = 0;
        for (int i = 1; i < N + 1; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                return -1;
            }
            maxDist = Math.max(maxDist, dist[i]);
        }
        return maxDist;
    }

    private static Map<Integer, List<int[]>> buildGraph(int[][] times) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] time : times) {
            int u = time[0], v = time[1], w = time[2];
            int[] t = new int[]{v, w};
            List<int[]> list = new ArrayList<>();
            if (graph.containsKey(u)) {
                list = graph.get(u);
            }
            list.add(t);
            graph.put(u, list);
        }
        return graph;
    }
}
