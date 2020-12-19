package com.wz.dynamicprogramming;

/**
 * You want to schedule a list of jobs in d days. Jobs are dependent (i.e To work on the i-th job,
 * you have to finish all the jobs j where 0 <= j < i).
 * You have to finish at least one task every day. The difficulty of a job schedule is the sum of difficulties of
 * each day of the d days. The difficulty of a day is the maximum difficulty of a job done in that day.
 * Given an array of integers jobDifficulty and an integer d. The difficulty of the i-th job is jobDifficulty[i].
 * Return the minimum difficulty of a job schedule. If you cannot find a schedule for the jobs return -1.
 *
 * Example 1:
 * Input: jobDifficulty = [6,5,4,3,2,1], d = 2
 * Output: 7
 * Explanation: First day you can finish the first 5 jobs, total difficulty = 6.
 * Second day you can finish the last job, total difficulty = 1.
 * The difficulty of the schedule = 6 + 1 = 7
 *
 * Example 2:
 * Input: jobDifficulty = [9,9,9], d = 4
 * Output: -1
 * Explanation: If you finish a job per day you will still have a free day. you cannot find a schedule for the given jobs.
 *
 * Example 3:
 * Input: jobDifficulty = [1,1,1], d = 3
 * Output: 3
 * Explanation: The schedule is one job per day. total difficulty will be 3.
 *
 * Constraints:
 * 1. 1 <= jobDifficulty.length <= 300
 * 2. 0 <= jobDifficulty[i] <= 1000
 * 3. 1 <= d <= 10
 */
public class MinimumDifficultyOfJobSchedule {
    public static void main(String[] args) {
        System.out.println(minDifficulty(new int[]{6, 5, 4, 3, 2, 1}, 2));
    }

    /**
     * dp[i][j] 表示为前 i 天安排 j 个工作的最小工作量，等于 min{dp[i][j], dp[i-1][k-1] + max(job[k,j]}
     * k 范围 [i, j]，因为前面 i 天每天都要有工作，i 之前的不能分配到第 i 天
     */
    public static int minDifficulty(int[] jobDifficulty, int d) {
        int n = jobDifficulty.length;
        if (n < d) {
            return -1;
        }
        int[][] dp = new int[d][n];
        dp[0][0] = jobDifficulty[0];
        for (int j = 1; j < n; ++j) {
            dp[0][j] = Math.max(jobDifficulty[j], dp[0][j - 1]);
        }
        for (int i = 1; i < d; ++i) {
            for (int j = i; j < n; ++j) {
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = j, localMax = 0; k >= i; k--) {
                    localMax = Math.max(localMax, jobDifficulty[k]);
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][k - 1] + localMax);
                }
            }
        }
        return dp[d - 1][n - 1];
    }
}
