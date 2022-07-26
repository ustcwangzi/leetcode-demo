package com.wz.queue;

import java.util.*;

/**
 * You have k servers numbered from 0 to k-1 that are being used to handle multiple requests simultaneously.
 * Each server has infinite computational capacity but cannot handle more than one request at a time.
 * The requests are assigned to servers according to a specific algorithm:
 * - The ith (0-indexed) request arrives.
 * - If all servers are busy, the request is dropped (not handled at all).
 * - If the (i % k)th server is available, assign the request to that server.
 * - Otherwise, assign the request to the next available server (wrapping around the list of servers and starting from 0 if necessary).
 *   For example, if the ith server is busy, try to assign the request to the (i+1)th server, then the (i+2)th server, and so on.
 * You are given a strictly increasing array arrival of positive integers, where arrival[i] represents the arrival time of the ith request,
 * and another array load, where load[i] represents the load of the ith request (the time it takes to complete).
 * Your goal is to find the busiest server(s). A server is considered busiest if it handled the most number of requests successfully among all the servers.
 * Return a list containing the IDs (0-indexed) of the busiest server(s). You may return the IDs in any order.
 *
 * Example 1:
 * @link ../../../../resource/FindServersThatHandledMostNumberOfRequests.jpg
 * Input: k = 3, arrival = [1,2,3,4,5], load = [5,2,3,3,3]
 * Output: [1]
 * Explanation:
 * All of the servers start out available.
 * The first 3 requests are handled by the first 3 servers in order.
 * Request 3 comes in. Server 0 is busy, so it's assigned to the next available server, which is 1.
 * Request 4 comes in. It cannot be handled since all servers are busy, so it is dropped.
 * Servers 0 and 2 handled one request each, while server 1 handled two requests. Hence server 1 is the busiest server.
 *
 * Example 2:
 * Input: k = 3, arrival = [1,2,3,4], load = [1,2,1,2]
 * Output: [0]
 * Explanation:
 * The first 3 requests are handled by first 3 servers.
 * Request 3 comes in. It is handled by server 0 since the server is available.
 * Server 0 handled two requests, while servers 1 and 2 handled one request each. Hence server 0 is the busiest server.
 *
 * Example 3:
 * Input: k = 3, arrival = [1,2,3], load = [10,12,11]
 * Output: [0,1,2]
 * Explanation: Each server handles a single request, so they are all considered the busiest.
 *
 * Constraints:
 * 1. 1 <= k <= 10^5
 * 2. 1 <= arrival.length, load.length <= 10^5
 * 3. arrival.length == load.length
 * 4. 1 <= arrival[i], load[i] <= 10^9
 * 5. arrival is strictly increasing.
 */
public class FindServersThatHandledMostNumberOfRequests {
    public static void main(String[] args) {
        System.out.println(busiestServers(3, new int[]{1, 2, 3, 4, 5}, new int[]{5, 2, 3, 3, 3}));
        System.out.println(busiestServers(3, new int[]{1, 2, 3, 4}, new int[]{1, 2, 1, 2}));
        System.out.println(busiestServers(3, new int[]{1, 2, 3}, new int[]{10, 12, 11}));
    }

    /**
     * 使用 TreeSet 维护空闲机器，使用小根堆记录处理中的 serverId、结束时间，按结束时间排序
     * 遍历 arrival[]，若结束时间小于当前任务开始时间，说明任务执行完了，从堆中移除，同时将 serverId 加入到 TreeSet 中
     * 然后使用 i % k 计算 index，从 TreeSet 中获取大于等于 index 的第一个空闲机器对任务进行处理
     */
    public static List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        TreeSet<Integer> freeServers = new TreeSet<>();
        for (int i = 0; i < k; i++) {
            freeServers.add(i);
        }
        int[] counts = new int[k];
        int max = 0;
        Queue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        for (int i = 0; i < arrival.length; i++) {
            int start = arrival[i];
            while (!queue.isEmpty() && queue.peek()[1] <= start) {
                freeServers.add(queue.poll()[0]);
            }
            if (freeServers.isEmpty()) {
                continue;
            }

            int index = i % k, serverId = freeServers.ceiling(index) == null ? freeServers.first() : freeServers.ceiling(index);
            freeServers.remove(serverId);
            counts[serverId]++;
            max = Math.max(max, counts[serverId]);
            queue.offer(new int[]{serverId, start + load[i]});
        }

        List<Integer> result = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            if (counts[i] == max) {
                result.add(i);
            }
        }
        return result;
    }
}
