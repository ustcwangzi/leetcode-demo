package com.wz.dynamicprogramming;

import java.util.Arrays;
import java.util.Comparator;

/**
 * We have n jobs, where every job is scheduled to be done from startTime[i] to endTime[i], obtaining a profit of profit[i].
 * You're given the startTime , endTime and profit arrays, you need to output the maximum profit you can take such that
 * there are no 2 jobs in the subset with overlapping time range.
 * If you choose a job that ends at time X you will be able to start another job that starts at time X.
 *
 * Example 1:
 * @link ../../../../resource/MaximumProfitInJobScheduling1.jpg
 * Input: startTime = [1,2,3,3], endTime = [3,4,5,6], profit = [50,10,40,70]
 * Output: 120
 * Explanation: The subset chosen is the first and fourth job.
 * Time range [1-3]+[3-6] , we get profit of 120 = 50 + 70.
 * Example 2:
 * @link ../../../../resource/MaximumProfitInJobScheduling2.jpg
 * Input: startTime = [1,2,3,4,6], endTime = [3,5,10,6,9], profit = [20,20,100,70,60]
 * Output: 150
 * Explanation: The subset chosen is the first, fourth and fifth job.
 * Profit obtained 150 = 20 + 70 + 60.
 *
 * Constraints:
 * 1. 1 <= startTime.length == endTime.length == profit.length <= 5 * 10^4
 * 2. 1 <= startTime[i] < endTime[i] <= 10^9
 * 3. 1 <= profit[i] <= 10^4
 */
public class MaximumProfitInJobScheduling {
    public static void main(String[] args) {
        int[] startTime = new int[]{1, 2, 3, 4, 6}, endTime = new int[]{3, 5, 10, 6, 9}, profit = new int[]{20, 20, 100, 70, 60};
        System.out.println(jobScheduling1(startTime, endTime, profit));
        System.out.println(jobScheduling2(startTime, endTime, profit));
    }

    /**
     * 方案一
     * jobs[i].profit 表示考虑了前 i 份工作，能获得的最大利润
     * 对于第 i 份工作，查找最大的 j < i，使得 jobs[j].endTime <= jobs[i].startTime，
     * jobs[i].profit = max{jobs(i).profit, jobs[j].profit + jobs[i].profit}
     * 最终返回 jobs[n−1].profit
     */
    public static int jobScheduling1(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        Job[] jobs = new Job[n];
        for (int i = 0; i < n; i++) {
            jobs[i] = new Job(startTime[i], endTime[i], profit[i]);
        }
        Arrays.parallelSort(jobs, Comparator.comparingInt(a -> a.endTime));

        int[] dp = new int[n];
        int result = Integer.MIN_VALUE;
        for (int i = 0; i < dp.length; i++) {
            dp[i] = jobs[i].profit;
            // find out maximum profit before jobs[i].startTime
            for (int j = 0; j < i; j++) {
                if (jobs[i].startTime >= jobs[j].endTime) {
                    dp[i] = Math.max(dp[i], dp[j] + jobs[i].profit);
                }
            }
            result = Math.max(result, dp[i]);
        }
        return result;
    }

    /**
     * 方案二，思路与方案一类似，只是查找过程采用二分查找
     */
    public static int jobScheduling2(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        Job[] jobs = new Job[n];
        for (int i = 0; i < n; i++) {
            jobs[i] = new Job(startTime[i], endTime[i], profit[i]);
        }
        Arrays.parallelSort(jobs, Comparator.comparingInt(a -> a.endTime));

        for (int i = 1; i < n; i++) {
            int lastEndTime = jobs[i].startTime;
            // find out maximum profit before jobs[i].startTime
            int pre = binarySearch(jobs, i - 1, lastEndTime);
            jobs[i].profit = Math.max(jobs[i - 1].profit, jobs[i].profit + pre);
        }
        return jobs[n - 1].profit;
    }

    private static int binarySearch(Job[] jobs, int end, int lastEndTime) {
        int left = 0, right = end;
        while (left < right) {
            int mid = left + (right - left) / 2 + 1;
            if (jobs[mid].endTime == lastEndTime) {
                return jobs[mid].profit;
            }
            if (jobs[mid].endTime > lastEndTime) {
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        return jobs[left].endTime <= lastEndTime ? jobs[left].profit : 0;
    }

    private static class Job {
        int startTime;
        int endTime;
        int profit;

        public Job(int startTime, int endTime, int profit) {
            this.startTime = startTime;
            this.endTime = endTime;
            this.profit = profit;
        }
    }
}
