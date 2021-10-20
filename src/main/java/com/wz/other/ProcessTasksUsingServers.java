package com.wz.other;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * You are given two 0-indexed integer arrays servers and tasks of lengths n and m respectively.
 * servers[i] is the weight of the ith server, and tasks[j] is the time needed to process the jth task in seconds.
 * Tasks are assigned to the servers using a task queue. Initially, all servers are free, and the queue is empty.
 * At second j, the jth task is inserted into the queue (starting with the 0th task being inserted at second 0).
 * As long as there are free servers and the queue is not empty, the task in the front of the queue will be assigned to
 * a free server with the smallest weight, and in case of a tie, it is assigned to a free server with the smallest index.
 * If there are no free servers and the queue is not empty, we wait until a server becomes free and immediately assign the next task.
 * If multiple servers become free at the same time, then multiple tasks from the queue will be assigned in order of insertion following the weight and index priorities above.
 * A server that is assigned task j at second t will be free again at second t + tasks[j].
 * Build an array ans of length m, where ans[j] is the index of the server the jth task will be assigned to.
 * Return the array ans.
 *
 * Example 1:
 * Input: servers = [3,3,2], tasks = [1,2,3,2,1,2]
 * Output: [2,2,0,2,1,2]
 * Explanation: Events in chronological order go as follows:
 * - At second 0, task 0 is added and processed using server 2 until second 1.
 * - At second 1, server 2 becomes free. Task 1 is added and processed using server 2 until second 3.
 * - At second 2, task 2 is added and processed using server 0 until second 5.
 * - At second 3, server 2 becomes free. Task 3 is added and processed using server 2 until second 5.
 * - At second 4, task 4 is added and processed using server 1 until second 5.
 * - At second 5, all servers become free. Task 5 is added and processed using server 2 until second 7.
 *
 * Example 2:
 * Input: servers = [5,1,4,3,2], tasks = [2,1,2,4,5,2,1]
 * Output: [1,4,1,4,1,3,2]
 * Explanation: Events in chronological order go as follows: 
 * - At second 0, task 0 is added and processed using server 1 until second 2.
 * - At second 1, task 1 is added and processed using server 4 until second 2.
 * - At second 2, servers 1 and 4 become free. Task 2 is added and processed using server 1 until second 4. 
 * - At second 3, task 3 is added and processed using server 4 until second 7.
 * - At second 4, server 1 becomes free. Task 4 is added and processed using server 1 until second 9. 
 * - At second 5, task 5 is added and processed using server 3 until second 7.
 * - At second 6, task 6 is added and processed using server 2 until second 7.
 *
 * Constraints:
 * 1. servers.length == n
 * 2. tasks.length == m
 * 3. 1 <= n, m <= 2 * 10^5
 * 4. 1 <= servers[i], tasks[j] <= 2 * 10^5
 */
public class ProcessTasksUsingServers {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(assignTasks(new int[]{3, 3, 2}, new int[]{1, 2, 3, 2, 1, 2})));
    }

    /**
     * 使用两个优先级队列分别代表空闲和工作服务器，其中空闲服务器按照 ID、weight 进行排序，工作服务器按照结束时间进行排序
     * 然后遍历 tasks 数组，模拟每个任务被执行的过程
     */
    public static int[] assignTasks(int[] servers, int[] tasks) {
        Queue<ServerTask> freeQueue = new PriorityQueue<>((o1, o2) -> {
            if (o1.serverWeight == o2.serverWeight) {
                return Integer.compare(o1.serverId, o2.serverId);
            }
            return Integer.compare(o1.serverWeight, o2.serverWeight);
        });
        Queue<ServerTask> workQueue = new PriorityQueue<>(Comparator.comparingLong(o -> o.endTime));
        // 初始时，所有服务器都处于空闲状态
        for (int i = 0; i < servers.length; i++) {
            freeQueue.offer(new ServerTask(i, servers[i], 0));
        }

        int[] result = new int[tasks.length];
        long curTime = 0;
        for (int i = 0; i < tasks.length; i++) {
            // 无空闲服务器，直接跳到最早结束时间
            if (freeQueue.isEmpty()) {
                curTime = workQueue.peek().endTime;
            }
            // 任务都是从 i 开始
            curTime = Math.max(i, curTime);
            // 将已经完成任务的服务器加入空闲队列
            while (!workQueue.isEmpty() && workQueue.peek().endTime <= curTime) {
                freeQueue.offer(workQueue.poll());
            }
            // 从空闲队列取出优先级最高的来执行当前任务
            ServerTask server = freeQueue.poll();
            result[i] = server.serverId;
            // 将当前任务加入工作队列
            workQueue.offer(new ServerTask(server.serverId, server.serverWeight, curTime + tasks[i]));
        }
        return result;
    }

    private static class ServerTask {
        int serverId;
        int serverWeight;
        long endTime;

        public ServerTask(int serverId, int serverWeight, long endTime) {
            this.serverId = serverId;
            this.serverWeight = serverWeight;
            this.endTime = endTime;
        }
    }

}
