package com.wz.bfs;

import java.util.*;

/**
 * Given an array of integers arr, you are initially positioned at the first index of the array.
 * In one step you can jump from index i to index:
 * - i + 1 where: i + 1 < arr.length.
 * - i - 1 where: i - 1 >= 0.
 * - j where: arr[i] == arr[j] and i != j.
 * Return the minimum number of steps to reach the last index of the array.
 * Notice that you can not jump outside of the array at any time.
 *
 * Example 1:
 * Input: arr = [100,-23,-23,404,100,23,23,23,3,404]
 * Output: 3
 * Explanation: You need three jumps from index 0 --> 4 --> 3 --> 9. Note that index 9 is the last index of the array.
 *
 * Example 2:
 * Input: arr = [7]
 * Output: 0
 * Explanation: Start index is the last index. You do not need to jump.
 *
 * Example 3:
 * Input: arr = [7,6,9,6,9,6,9,7]
 * Output: 1
 * Explanation: You can jump directly from index 0 to index 7 which is last index of the array.
 *
 * Constraints:
 * 1. 1 <= arr.length <= 5 * 10^4
 * 2. -10^8 <= arr[i] <= 10^8
 */
public class JumpGameIV {
    public static void main(String[] args) {
        System.out.println(minJumps(new int[]{100, -23, -23, 404, 100, 23, 23, 23, 3, 404}));
        System.out.println(minJumps(new int[]{7, 6, 9, 6, 9, 6, 9, 7}));
    }

    /**
     * BFS，根据要求对三种情况进行处理
     */
    public static int minJumps(int[] arr) {
        int n = arr.length, step = 0;
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];
        queue.offer(0);
        visited[0] = true;

        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.putIfAbsent(arr[i], new HashSet<>());
            map.get(arr[i]).add(i);
        }

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int cur = queue.poll();
                if (cur == n - 1) {
                    return step;
                }
                if (cur + 1 < n && !visited[cur + 1]) {
                    queue.offer(cur + 1);
                    visited[cur + 1] = true;
                }
                if (cur - 1 >= 0 && !visited[cur - 1]) {
                    queue.offer(cur - 1);
                    visited[cur - 1] = true;
                }
                for (int neighbor : map.getOrDefault(arr[cur], Collections.emptySet())) {
                    if (!visited[neighbor] && neighbor != cur) {
                        queue.offer(neighbor);
                        visited[neighbor] = true;
                    }
                }
                map.remove(arr[cur]);
            }
            step++;
        }
        return -1;
    }
}
