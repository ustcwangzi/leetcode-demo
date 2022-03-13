package com.wz.binarysearch;

/**
 * You are given an array time where time[i] denotes the time taken by the ith bus to complete one trip.
 * Each bus can make multiple trips successively; that is, the next trip can start immediately after completing the current trip.
 * Also, each bus operates independently; that is, the trips of one bus do not influence the trips of any other bus.
 * You are also given an integer totalTrips, which denotes the number of trips all buses should make in total.
 * Return the minimum time required for all buses to complete at least totalTrips trips.
 *
 * Example 1:
 * Input: time = [1,2,3], totalTrips = 5
 * Output: 3
 * Explanation:
 * - At time t = 1, the number of trips completed by each bus are [1,0,0].
 *   The total number of trips completed is 1 + 0 + 0 = 1.
 * - At time t = 2, the number of trips completed by each bus are [2,1,0].
 *   The total number of trips completed is 2 + 1 + 0 = 3.
 * - At time t = 3, the number of trips completed by each bus are [3,1,1].
 *   The total number of trips completed is 3 + 1 + 1 = 5.
 * So the minimum time needed for all buses to complete at least 5 trips is 3.
 *
 * Example 2:
 * Input: time = [2], totalTrips = 1
 * Output: 2
 * Explanation:
 * There is only one bus, and it will complete its first trip at t = 2.
 * So the minimum time needed to complete 1 trip is 2.
 *
 * Constraints:
 * 1. 1 <= time.length <= 10^5
 * 2. 1 <= time[i], totalTrips <= 10^7
 */
public class MinimumTimeToCompleteTrips {
    public static void main(String[] args) {
        System.out.println(minimumTime(new int[]{1, 2, 3}, 5));
    }

    /**
     * 二分查找，与 {@link KokoEatingBananas} 类似
     * 最小耗时是 1，最大耗时是 10^7 * 10^7，为什么最大耗时是这个呢，假设只有一个bus，
     * 若 time=[1], totalTrips=1，则耗时为 1
     * 若 time=[10^7], totalTrips=10^7，则耗时为 10^7 * 10^7
     * 因此范围为 [1...10^14]
     * 使用二分查找来求解，对于每个 mid，求出 tripCount 与 totalTrips 比较，若小于，则说明 mid 太小，将 left 赋值为 mid+1
     * 否则说明符合要求，将 right 赋值为 mid-1，缩小范围，同时记录当前结果
     */
    public static long minimumTime(int[] time, int totalTrips) {
        long left = 1, right = 100_000_000_000_000L, result = 0;
        while (left <= right) {
            long mid = left + (right - left) / 2;
            long tripCount = 0;
            for (int t : time) {
                tripCount += mid / t;
            }
            if (tripCount >= totalTrips) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return result;
    }
}
