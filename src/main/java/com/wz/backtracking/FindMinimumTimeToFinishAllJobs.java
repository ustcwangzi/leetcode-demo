package com.wz.backtracking;

import java.util.Arrays;

/**
 * You are given an integer array jobs, where jobs[i] is the amount of time it takes to complete the ith job.
 * There are k workers that you can assign jobs to. Each job should be assigned to exactly one worker.
 * The working time of a worker is the sum of the time it takes to complete all jobs assigned to them.
 * Your goal is to devise an optimal assignment such that the maximum working time of any worker is minimized.
 * Return the minimum possible maximum working time of any assignment.
 *
 * Example 1:
 * Input: jobs = [3,2,3], k = 3
 * Output: 3
 * Explanation: By assigning each person one job, the maximum time is 3.
 *
 * Example 2:
 * Input: jobs = [1,2,4,7,8], k = 2
 * Output: 11
 * Explanation: Assign the jobs the following way:
 * Worker 1: 1, 2, 8 (working time = 1 + 2 + 8 = 11)
 * Worker 2: 4, 7 (working time = 4 + 7 = 11)
 * The maximum working time is 11.
 *
 * Constraints:
 * 1. 1 <= k <= jobs.length <= 12
 * 2. 1 <= jobs[i] <= 107
 */
public class FindMinimumTimeToFinishAllJobs {
    public static void main(String[] args) {
        System.out.println(minimumTimeRequired(new int[]{3, 2, 3}, 3));
    }

    public static int minimumTimeRequired(int[] jobs, int k) {
        int[] result = {Integer.MAX_VALUE};
        // 从耗时较多的工作开始分配
        Arrays.parallelSort(jobs);
        dfs(jobs, jobs.length - 1, new int[k], result);
        return result[0];
    }

    private static void dfs(int[] jobs, int cur, int[] workerTime, int[] result) {
        if (cur < 0) {
            result[0] = Math.min(result[0], Arrays.stream(workerTime).max().getAsInt());
            return;
        }
        // 耗时较多，提前结束
        if (Arrays.stream(workerTime).max().getAsInt() >= result[0]) {
            return;
        }

        for (int i = 0; i < workerTime.length; i++) {
            // 耗时相同，不用重复计算
            if (i > 0 && workerTime[i] == workerTime[i - 1]) {
                continue;
            }
            // 当前 job 分给 i
            workerTime[i] += jobs[cur];
            dfs(jobs, cur - 1, workerTime, result);
            // 当前 job 不分给 i
            workerTime[i] -= jobs[cur];
        }
    }
}
