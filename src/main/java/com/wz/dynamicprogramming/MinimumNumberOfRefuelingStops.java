package com.wz.dynamicprogramming;

import java.util.Arrays;

/**
 * A car travels from a starting position to a destination which is target miles east of the starting position.
 * Along the way, there are gas stations.  Each station[i] represents a gas station that is station[i][0] miles
 * east of the starting position, and has station[i][1] liters of gas.
 * The car starts with an infinite tank of gas, which initially has startFuel liters of fuel in it.
 * It uses 1 liter of gas per 1 mile that it drives.
 * When the car reaches a gas station, it may stop and refuel, transferring all the gas from the station into the car.
 * What is the least number of refueling stops the car must make in order to reach its destination?  If it cannot reach the destination, return -1.
 * Note that if the car reaches a gas station with 0 fuel left, the car can still refuel there.
 * If the car reaches the destination with 0 fuel left, it is still considered to have arrived.
 *
 * Example 1:
 * Input: target = 100, startFuel = 1, stations = [[10,100]]
 * Output: -1
 * Explanation: We can't reach the target (or even the first gas station).
 *
 * Example 2:
 * Input: target = 100, startFuel = 10, stations = [[10,60],[20,30],[30,30],[60,40]]
 * Output: 2
 * Explanation:
 * We start with 10 liters of fuel.
 * We drive to position 10, expending 10 liters of fuel.  We refuel from 0 liters to 60 liters of gas.
 * Then, we drive from position 10 to position 60 (expending 50 liters of fuel),
 * and refuel from 10 liters to 50 liters of gas.  We then drive to and reach the target.
 * We made 2 refueling stops along the way, so we return 2.
 *
 * Note:
 * 1. 1 <= target, startFuel, stations[i][1] <= 10^9
 * 2. 0 <= stations.length <= 500
 * 3. 0 < stations[0][0] < stations[1][0] < ... < stations[stations.length-1][0] < target
 */
public class MinimumNumberOfRefuelingStops {
    public static void main(String[] args) {
        System.out.println(minRefuelStops(100, 10, new int[][]{{10, 60}, {20, 30}, {60, 40}}));
    }

    /**
     * dp[i] 表示加 i 次油能到达的最远距离，那么最后只要找第一个i值使得 dp[i] 大于等于 target 即可
     * dp 数组的大小初始化为加油站的个数加1，值均初始化为 startFuel 即可，因为初始的油量能到达的距离是确定的。
     * 遍历每一个加油站，对于每个遍历到的加油站 k，需要再次遍历其之前的所有的加油站 i，
     * 能到达当前加油站 k 的条件是当前的 dp[i] 值大于等于加油站k距起点的距离，
     * 若大于等于的话，可以更新 dp[i+1] 为 dp[i]+stations[k][1]，这样就可以得到最远能到达的距离
     * 当 dp 数组更新完成后，需要再遍历一遍，找到第一个大于等于 target 的 dp[i] 值，并返回 i 即可
     */
    public static int minRefuelStops(int target, int startFuel, int[][] stations) {
        int n = stations.length;
        long[] dp = new long[n + 1];
        Arrays.fill(dp, startFuel);
        for (int k = 0; k < n; ++k) {
            for (int i = k; i >= 0 && dp[i] >= stations[k][0]; --i) {
                dp[i + 1] = Math.max(dp[i + 1], dp[i] + stations[k][1]);
            }
        }
        for (int i = 0; i <= n; ++i) {
            if (dp[i] >= target) return i;
        }
        return -1;
    }
}
