package com.wz.greedy;

/**
 * You are driving a vehicle that has capacity empty seats initially available for passengers.
 * The vehicle only drives east (ie. it cannot turn around and drive west.)
 * Given a list of trips, trip[i] = [num_passengers, start_location, end_location] contains information about the i-th trip:
 * the number of passengers that must be picked up, and the locations to pick them up and drop them off.
 * The locations are given as the number of kilometers due east from your vehicle's initial location.
 * Return true if and only if it is possible to pick up and drop off all passengers for all the given trips.
 *
 * Example 1:
 * Input: trips = [[2,1,5],[3,3,7]], capacity = 4
 * Output: false
 *
 * Example 2:
 * Input: trips = [[2,1,5],[3,5,7]], capacity = 3
 * Output: true
 *
 * Constraints:
 * 1. trips.length <= 1000
 * 2. trips[i].length == 3
 * 3. 1 <= trips[i][0] <= 100
 * 4. 0 <= trips[i][1] < trips[i][2] <= 1000
 * 6. 1 <= capacity <= 100000
 */
public class CarPooling {
    public static void main(String[] args) {
        int[][] trips = new int[][]{{2, 1, 5}, {3, 3, 7}};
        System.out.println(carPooling(trips, 4));
        trips = new int[][]{{2, 1, 5}, {3, 5, 7}};
        System.out.println(carPooling(trips, 3));
    }

    /**
     * 与 {@link com.wz.array.CorporateFlightBookings} 类似
     * 用数组 counter 记录每个位置相比于前一个位置上的差值，然后遍历 counter 进行累加
     * 如果累加的过程中，有任何一个位置上的乘客数量大于 capacity，则返回 false
     */
    public static boolean carPooling(int[][] trips, int capacity) {
        int[] counter = new int[1001];
        for (int[] trip : trips) {
            counter[trip[1]] += trip[0];
            counter[trip[2]] -= trip[0];
        }

        int cap = 0;
        for (int i = 0; i < 1001; i++) {
            cap += counter[i];
            if (cap > capacity) {
                return false;
            }
        }
        return true;
    }
}
