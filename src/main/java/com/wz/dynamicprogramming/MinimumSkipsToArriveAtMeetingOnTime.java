package com.wz.dynamicprogramming;

/**
 * You are given an integer hoursBefore, the number of hours you have to travel to your meeting. To arrive at your meeting, you have to travel through n roads.
 * The road lengths are given as an integer array dist of length n, where dist[i] describes the length of the ith road in kilometers.
 * In addition, you are given an integer speed, which is the speed (in km/h) you will travel at.
 * After you travel road i, you must rest and wait for the next integer hour before you can begin traveling on the next road.
 * Note that you do not have to rest after traveling the last road because you are already at the meeting.
 * For example, if traveling a road takes 1.4 hours, you must wait until the 2 hour mark before traveling the next road.
 * If traveling a road takes exactly 2 hours, you do not need to wait.
 * However, you are allowed to skip some rests to be able to arrive on time, meaning you do not need to wait for the next integer hour.
 * Note that this means you may finish traveling future roads at different hour marks.
 * For example, suppose traveling the first road takes 1.4 hours and traveling the second road takes 0.6 hours.
 * Skipping the rest after the first road will mean you finish traveling the second road right at the 2 hour mark, letting you start traveling the third road immediately.
 * Return the minimum number of skips required to arrive at the meeting on time, or -1 if it is impossible.
 *
 * Example 1:
 * Input: dist = [1,3,2], speed = 4, hoursBefore = 2
 * Output: 1
 * Explanation:
 * Without skipping any rests, you will arrive in (1/4 + 3/4) + (3/4 + 1/4) + (2/4) = 2.5 hours.
 * You can skip the first rest to arrive in ((1/4 + 0) + (3/4 + 0)) + (2/4) = 1.5 hours.
 * Note that the second rest is shortened because you finish traveling the second road at an integer hour due to skipping the first rest.
 *
 * Example 2:
 * Input: dist = [7,3,5,5], speed = 2, hoursBefore = 10
 * Output: 2
 * Explanation:
 * Without skipping any rests, you will arrive in (7/2 + 1/2) + (3/2 + 1/2) + (5/2 + 1/2) + (5/2) = 11.5 hours.
 * You can skip the first and third rest to arrive in ((7/2 + 0) + (3/2 + 0)) + ((5/2 + 0) + (5/2)) = 10 hours.
 *
 * Example 3:
 * Input: dist = [7,3,5,5], speed = 1, hoursBefore = 10
 * Output: -1
 * Explanation: It is impossible to arrive at the meeting on time even if you skip all the rests.
 *
 * Constraints:
 * 1. n == dist.length
 * 2. 1 <= n <= 1000
 * 3. 1 <= dist[i] <= 10^5
 * 4. 1 <= speed <= 10^6
 * 5. 1 <= hoursBefore <= 10^7
 */
public class MinimumSkipsToArriveAtMeetingOnTime {
    public static void main(String[] args) {
        System.out.println(minSkips(new int[]{1, 3, 2}, 4, 2));
        System.out.println(minSkips(new int[]{7, 3, 5, 5}, 2, 10));
        System.out.println(minSkips(new int[]{7, 3, 5, 5}, 1, 10));
    }

    /**
     * 选择休息可以转化为增加相应路程，如 speed=4, dist=1 时，选择休息相当于总路程增加 3，同时当总路程为速度整数倍时，选择休息不增加路程
     * 记 dp[i][j] 为第 i 段时跳过 j 次休息后最小增加的路程
     * 对于一直休息有
     * if ((sum + dp[i-1][0]) % speed != 0){
     *     dp[i][0] = dp[i-1][0] +  speed - ((sum + dp[i-1][0])%speed);
     * } else {
     *     dp[i][0] = dp[i-1][0];
     * }
     * 其他状态有转换方程
     * if((sum + dp[i-1][j]) % speed != 0){
     *     dp[i][j] = Math.min(dp[i-1][j-1], dp[i-1][j] + speed - ((sum + dp[i-1][j])%speed));
     * } else {
     *     dp[i][j] = Math.min(dp[i-1][j-1], dp[i-1][j]);
     * }
     * 可以使用j从大到小遍历的方式复用空间，注意到dp转换过程中一直有 dp[i-1][j] >= dp[i-1][j-1]，复用后后半操作可忽略
     * 最后找到最小的休息次数满足 总路程小于等于 可以到达的最远距离即可
     */
    public static int minSkips(int[] dist, int speed, int hoursBefore) {
        int n = dist.length, sum = 0;
        int[] dp = new int[n + 1];
        for (int i = 0; i < n; i++) {
            for (int j = i; j > 0; j--) {
                if ((sum + dp[j]) % speed != 0) {
                    dp[j] = Math.min(dp[j - 1], dp[j] + speed - ((sum + dp[j]) % speed));
                }
            }
            if ((sum + dp[0]) % speed != 0) {
                dp[0] = dp[0] + speed - ((sum + dp[0]) % speed);
            }
            sum += dist[i];
        }

        for (int i = 0; i <= n; i++) {
            if (sum + dp[i] <= (long) hoursBefore * speed) {
                return i;
            }
        }
        return -1;
    }
}
