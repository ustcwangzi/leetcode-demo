package com.wz.array;

import java.util.Arrays;

/**
 * There are n flights, and they are labeled from 1 to n.
 * We have a list of flight bookings.  The i-th booking bookings[i] = [i, j, k] means that we booked k seats from flights labeled i to j inclusive.
 * Return an array answer of length n, representing the number of seats booked on each flight in order of their label.
 *
 * Example 1:
 * Input: bookings = [[1,2,10],[2,3,20],[2,5,25]], n = 5
 * Output: [10,55,45,25,25]
 */
public class CorporateFlightBookings {
    public static void main(String[] args) {
        int[][] bookings = new int[][]{
                {1, 2, 10},
                {2, 3, 20},
                {2, 5, 25}
        };
        System.out.println(Arrays.toString(corpFlightBookings(bookings, 5)));
    }

    /**
     * running sum，也叫扫描线，重点在于 interval 有一个值的情况下，只看变化的 index 和变化值，最后叠加就是最终答案
     * 对于一个 [i, j, k]，看作在 i 位置的增加 k, 在 j+1 位置减少了k
     * 构建 booking 所有位置的差分关系，表明了 i 位置的高度比 i-1 位置的高度高多少
     * 以 [[1,2,10],[2,3,20],[2,5,25]], n = 5 为例说明这一过程
     *      索引: 1   2   3   4   5
     * 第一次遍历: 10     -10
     * 第二次遍历:     20     -20
     * 第三次遍历:     45
     *   遍历结果: 10  45 -10 -20  0
     *   最终结果: 10  55  45  25  25
     */
    public static int[] corpFlightBookings(int[][] bookings, int n) {
        int[] result = new int[n];
        for (int[] book : bookings) {
            result[book[0] - 1] += book[2];
            if (book[1] < n) {
                result[book[1]] -= book[2];
            }
        }
        for (int i = 1; i < n; i++) {
            result[i] += result[i - 1];
        }

        return result;
    }
}
