package com.wz.greedy;

import java.util.HashMap;
import java.util.Map;

/**
 * You are given a 0-indexed integer array tasks, where tasks[i] represents the difficulty level of a task.
 * In each round, you can complete either 2 or 3 tasks of the same difficulty level.
 * Return the minimum rounds required to complete all the tasks, or -1 if it is not possible to complete all the tasks.
 *
 * Example 1:
 * Input: tasks = [2,2,3,3,2,4,4,4,4,4]
 * Output: 4
 * Explanation: To complete all the tasks, a possible plan is:
 * - In the first round, you complete 3 tasks of difficulty level 2.
 * - In the second round, you complete 2 tasks of difficulty level 3.
 * - In the third round, you complete 3 tasks of difficulty level 4.
 * - In the fourth round, you complete 2 tasks of difficulty level 4.
 * It can be shown that all the tasks cannot be completed in fewer than 4 rounds, so the answer is 4.
 *
 * Example 2:
 * Input: tasks = [2,3,3]
 * Output: -1
 * Explanation: There is only 1 task of difficulty level 2, but in each round, you can only complete either 2 or 3 tasks
 * of the same difficulty level. Hence, you cannot complete all the tasks, and the answer is -1.
 *
 * Constraints:
 * 1. 1 <= tasks.length <= 10^5
 * 2. 1 <= tasks[i] <= 10^9
 */
public class MinimumRoundsToCompleteAllTasks {
    public static void main(String[] args) {
        System.out.println(minimumRounds(new int[]{2, 2, 3, 3, 2, 4, 4, 4, 4, 4}));
        System.out.println(minimumRounds(new int[]{2, 3, 3}));
        System.out.println(minimumRounds(new int[]{5, 5, 5, 5}));
        System.out.println(minimumRounds(new int[]{5, 5, 5, 5, 5, 5}));
    }

    /**
     * 使用 map 统计每个元素出现的频次，若 freq == 1， 直接返回 -1，否则
     * freq = 3 * tasks + 3 * tasks + .... + 2 * tasks
     * 尽可能多的 3-tasks，再加上一个或两个 2-tasks
     * If freq = 1, return -1
     * If freq = 2, needs one 2-tasks
     * If freq = 3, needs one 3-tasks
     * If freq = 3k, freq = 3 * k, needs k rounds
     * If freq = 3k + 1, freq = 3 * (k - 1) + 2 + 2, needs k + 1 rounds
     * If freq = 3k + 2, freq = 3 * k + 2, needs k + 1 rounds
     */
    public static int minimumRounds(int[] tasks) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int task : tasks) {
            map.put(task, map.getOrDefault(task, 0) + 1);
        }

        int result = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int count = entry.getValue();
            if (count == 1) {
                return -1;
            }
            if (count % 3 == 0) {
                result += count / 3;
            } else {
                result += count / 3 + 1;
            }
        }
        return result;
    }
}
