package com.wz.heap;

import java.util.*;

/**
 * You are given n tasks labeled from 0 to n - 1 represented by a 2D integer array tasks,
 * where tasks[i] = [enqueueTimei, processingTimei] means that the ith task will be available to process at enqueueTimei and will take processingTimei to finish processing.
 * You have a single-threaded CPU that can process at most one task at a time and will act in the following way:
 * 1. If the CPU is idle and there are no available tasks to process, the CPU remains idle.
 * 2. If the CPU is idle and there are available tasks, the CPU will choose the one with the shortest processing time.
 *    If multiple tasks have the same shortest processing time, it will choose the task with the smallest index.
 * 3. Once a task is started, the CPU will process the entire task without stopping.
 * 4. The CPU can finish a task then start a new one instantly.
 * Return the order in which the CPU will process the tasks.
 *
 * Example 1:
 * Input: tasks = [[1,2],[2,4],[3,2],[4,1]]
 * Output: [0,2,3,1]
 * Explanation: The events go as follows:
 * - At time = 1, task 0 is available to process. Available tasks = {0}.
 * - Also at time = 1, the idle CPU starts processing task 0. Available tasks = {}.
 * - At time = 2, task 1 is available to process. Available tasks = {1}.
 * - At time = 3, task 2 is available to process. Available tasks = {1, 2}.
 * - Also at time = 3, the CPU finishes task 0 and starts processing task 2 as it is the shortest. Available tasks = {1}.
 * - At time = 4, task 3 is available to process. Available tasks = {1, 3}.
 * - At time = 5, the CPU finishes task 2 and starts processing task 3 as it is the shortest. Available tasks = {1}.
 * - At time = 6, the CPU finishes task 3 and starts processing task 1. Available tasks = {}.
 * - At time = 10, the CPU finishes task 1 and becomes idle.
 *
 * Example 2:
 * Input: tasks = [[7,10],[7,12],[7,5],[7,4],[7,2]]
 * Output: [4,3,2,0,1]
 * Explanation: The events go as follows:
 * - At time = 7, all the tasks become available. Available tasks = {0,1,2,3,4}.
 * - Also at time = 7, the idle CPU starts processing task 4. Available tasks = {0,1,2,3}.
 * - At time = 9, the CPU finishes task 4 and starts processing task 3. Available tasks = {0,1,2}.
 * - At time = 13, the CPU finishes task 3 and starts processing task 2. Available tasks = {0,1}.
 * - At time = 18, the CPU finishes task 2 and starts processing task 0. Available tasks = {1}.
 * - At time = 28, the CPU finishes task 0 and starts processing task 1. Available tasks = {}.
 * - At time = 40, the CPU finishes task 1 and becomes idle.
 *
 * Constraints:
 * 1. tasks.length == n
 * 2. 1 <= n <= 10^5
 * 3. 1 <= enqueueTimei, processingTimei <= 10^9
 */
public class SingleThreadedCPU {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(getOrder(new int[][]{{1, 2}, {2, 4}, {3, 2}, {4, 1}})));
        System.out.println(Arrays.toString(getOrder(new int[][]{{7, 10}, {7, 12}, {7, 5}, {7, 4}, {7, 2}})));
    }

    /**
     * 小根堆
     * CPU空闲则执行 enqueueTime 最小的任务，否则将任务放入队列，待上一个任务处理完毕，取出队列中的任务执行
     * 优先取 processingTime 较小的，processingTime 相等则取 index 较小的
     * 因为队列需要用到任务的原始 index，因此将任务包装成 Task，保存 index、enqueueTime、processingTime
     * 先对数组进行排序，按照 enqueueTime 升序排列
     * 使用 index、taskIndex、endTime 分别记录结果索引、任务索引、当前任务结束时间，初始 endTime 等于第一个任务的 enqueueTime
     * 如果当前任务的 enqueueTime <= endTime 则需要入队，否则取出队列中的任务执行，同时更新 endTime
     */
    public static int[] getOrder(int[][] tasks) {
        int n = tasks.length;
        List<Task> taskList = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            taskList.add(new Task(i, tasks[i][0], tasks[i][1]));
        }
        taskList.sort(Comparator.comparingInt(o -> o.enqueueTime));

        PriorityQueue<Task> queue = new PriorityQueue<>((o1, o2) -> {
            if (o1.processingTime == o2.processingTime) {
                return Integer.compare(o1.index, o2.index);
            }
            return Integer.compare(o1.processingTime, o2.processingTime);
        });

        // 结果索引、任务索引、当前任务结束时间
        int index = 0, taskIndex = 0, endTime = taskList.get(0).enqueueTime;
        int[] result = new int[n];
        while (index < n) {
            while (taskIndex < n && taskList.get(taskIndex).enqueueTime <= endTime) {
                queue.offer(taskList.get(taskIndex++));
            }

            if (queue.isEmpty()) {
                // 没有排队的任务，重新设置结束时间
                endTime = taskList.get(taskIndex).enqueueTime;
            } else {
                // 执行堆顶任务
                Task cur = queue.poll();
                endTime += cur.processingTime;
                result[index++] = cur.index;
            }
        }
        return result;
    }

    private static class Task {
        public int index;
        public int enqueueTime;
        public int processingTime;

        public Task(int index, int enqueueTime, int processingTime) {
            this.index = index;
            this.enqueueTime = enqueueTime;
            this.processingTime = processingTime;
        }
    }
}
