package com.wz.dfs;

import java.util.Arrays;

/**
 * You are given an array of distinct positive integers locations where locations[i] represents the position of city i.
 * You are also given integers start, finish and fuel representing the starting city, ending city, and the initial amount of fuel you have, respectively.
 * At each step, if you are at city i, you can pick any city j such that j != i and 0 <= j < locations.length and move to city j.
 * Moving from city i to city j reduces the amount of fuel you have by |locations[i] - locations[j]|. Please notice that |x| denotes the absolute value of x.
 * Notice that fuel cannot become negative at any point in time, and that you are allowed to visit any city more than once (including start and finish).
 * Return the count of all possible routes from start to finish. Since the answer may be too large, return it modulo 10^9 + 7.
 *
 * Example 1:
 * Input: locations = [2,3,6,8,4], start = 1, finish = 3, fuel = 5
 * Output: 4
 * Explanation: The following are all possible routes, each uses 5 units of fuel:
 * 1 -> 3
 * 1 -> 2 -> 3
 * 1 -> 4 -> 3
 * 1 -> 4 -> 2 -> 3
 *
 * Example 2:
 * Input: locations = [4,3,1], start = 1, finish = 0, fuel = 6
 * Output: 5
 * Explanation: The following are all possible routes:
 * 1 -> 0, used fuel = 1
 * 1 -> 2 -> 0, used fuel = 5
 * 1 -> 2 -> 1 -> 0, used fuel = 5
 * 1 -> 0 -> 1 -> 0, used fuel = 3
 * 1 -> 0 -> 1 -> 0 -> 1 -> 0, used fuel = 5
 *
 * Example 3:
 * Input: locations = [5,2,1], start = 0, finish = 2, fuel = 3
 * Output: 0
 * Explanation: It is impossible to get from 0 to 2 using only 3 units of fuel since the shortest route needs 4 units of fuel.
 *
 * Constraints:
 * 1. 2 <= locations.length <= 100
 * 2. 1 <= locations[i] <= 10^9
 * 3. All integers in locations are distinct.
 * 4. 0 <= start, finish < locations.length
 * 5. 1 <= fuel <= 200
 */
public class CountAllPossibleRoutes {
    public static void main(String[] args) {
        System.out.println(countRoutes(new int[]{2, 3, 6, 8, 4}, 1, 3, 5));
        System.out.println(countRoutes(new int[]{5, 2, 1}, 0, 2, 3));
    }

    private static final int MOD = 1000000007;

    /**
     * 对于每个起始位置，遍历 locations[]，进行 DFS，每达到一次 finish 增加一种路径，直到 fuel < 0
     */
    public static int countRoutes(int[] locations, int start, int finish, int fuel) {
        long[][] dp = new long[locations.length][fuel + 1];
        Arrays.stream(dp).forEach(array -> Arrays.fill(array, -1));
        return (int) dfs(locations, dp, start, finish, fuel);
    }

    private static long dfs(int[] locations, long[][] dp, int cur, int finish, int fuel) {
        if (fuel < 0) {
            return 0;
        }
        if (dp[cur][fuel] != -1) {
            return dp[cur][fuel];
        }

        long result = 0;
        if (cur == finish) {
            result = 1;
        }
        for (int i = 0; i < locations.length; i++) {
            if (i == cur) {
                continue;
            }
            int after = fuel - Math.abs(locations[i] - locations[cur]);
            result = (result + dfs(locations, dp, i, finish, after)) % MOD;
        }
        return dp[cur][fuel] = result;
    }
}
