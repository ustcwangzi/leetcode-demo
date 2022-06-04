package com.wz.bfs;

import java.util.*;

/**
 * You are given an array routes representing bus routes where routes[i] is a bus route that the ith bus repeats forever.
 * For example, if routes[0] = [1, 5, 7], this means that the 0th bus travels in the sequence 1 -> 5 -> 7 -> 1 -> 5 -> 7 -> 1 -> ... forever.
 * You will start at the bus stop source (You are not on any bus initially), and you want to go to the bus stop target. You can travel between bus stops by buses only.
 * Return the least number of buses you must take to travel from source to target. Return -1 if it is not possible.
 *
 * Example 1:
 * Input: routes = [[1,2,7],[3,6,7]], source = 1, target = 6
 * Output: 2
 * Explanation: The best strategy is take the first bus to the bus stop 7, then take the second bus to the bus stop 6.
 *
 * Example 2:
 * Input: routes = [[7,12],[4,5,15],[6],[15,19],[9,12,13]], source = 15, target = 12
 * Output: -1
 *
 * Constraints:
 * 1. 1 <= routes.length <= 500.
 * 2. 1 <= routes[i].length <= 10^5
 * 3. All the values of routes[i] are unique.
 * 4. sum(routes[i].length) <= 10^5
 * 5. 0 <= routes[i][j] < 10^6
 * 6. 0 <= source, target < 10^6
 */
public class BusRoutes {
    public static void main(String[] args) {
        System.out.println(numBusesToDestination(new int[][]{{1, 2, 7}, {3, 6, 7}}, 1, 6));
        System.out.println(numBusesToDestination(new int[][]{{7, 12}, {4, 5, 15}, {6}, {15, 19}, {9, 12, 13}}, 15, 12));
    }

    /**
     * 使用 map 保存车站与公交的映射，然后从 source 开始进行 BFS，每遍历一层，level++
     * 遇到 target 则直接返回 level，遍历结束时返回 -1
     */
    public static int numBusesToDestination(int[][] routes, int source, int target) {
        // station -> bus
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < routes.length; i++) {
            for (int j = 0; j < routes[i].length; j++) {
                map.putIfAbsent(routes[i][j], new LinkedList<>());
                map.get(routes[i][j]).add(i);
            }
        }
        return bfs(routes, map, source, target);
    }

    private static int bfs(int[][] routes, Map<Integer, List<Integer>> map, int source, int target) {
        boolean[] visitedBus = new boolean[routes.length];
        Set<Integer> visitedStation = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(source);
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int cur = queue.remove();
                if (cur == target) {
                    return level;
                }

                for (int bus : map.get(cur)) {
                    if (visitedBus[bus]) {
                        continue;
                    }
                    visitedBus[bus] = true;
                    for (int station : routes[bus]) {
                        if (visitedStation.add(station)) {
                            queue.offer(station);
                        }
                    }
                }
            }
            level++;
        }
        return -1;
    }
}
