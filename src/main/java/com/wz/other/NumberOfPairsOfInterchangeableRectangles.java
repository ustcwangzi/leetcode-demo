package com.wz.other;

import java.util.HashMap;
import java.util.Map;

/**
 * You are given n rectangles represented by a 0-indexed 2D integer array rectangles,
 * where rectangles[i] = [widthi, heighti] denotes the width and height of the ith rectangle.
 * Two rectangles i and j (i < j) are considered interchangeable if they have the same width-to-height ratio.
 * More formally, two rectangles are interchangeable if widthi/heighti == widthj/heightj (using decimal division, not integer division).
 * Return the number of pairs of interchangeable rectangles in rectangles.
 *
 * Example 1:
 * Input: rectangles = [[4,8],[3,6],[10,20],[15,30]]
 * Output: 6
 * Explanation: The following are the interchangeable pairs of rectangles by index (0-indexed):
 * - Rectangle 0 with rectangle 1: 4/8 == 3/6.
 * - Rectangle 0 with rectangle 2: 4/8 == 10/20.
 * - Rectangle 0 with rectangle 3: 4/8 == 15/30.
 * - Rectangle 1 with rectangle 2: 3/6 == 10/20.
 * - Rectangle 1 with rectangle 3: 3/6 == 15/30.
 * - Rectangle 2 with rectangle 3: 10/20 == 15/30.
 *
 * Example 2:
 * Input: rectangles = [[4,5],[7,8]]
 * Output: 0
 * Explanation: There are no interchangeable pairs of rectangles.
 *
 * Constraints:
 * 1. n == rectangles.length
 * 2. 1 <= n <= 10^5
 * 3. rectangles[i].length == 2
 * 4. 1 <= widthi, heighti <= 10^5
 */
public class NumberOfPairsOfInterchangeableRectangles {
    public static void main(String[] args) {
        System.out.println(interchangeableRectangles(new int[][]{{4, 8}, {3, 6}, {10, 20}, {15, 30}}));
    }

    /**
     * 使用 map 记录每个 width/height 的出现次数，出现次数为 n 时，interchangeable 结果数为 n*(n-1)/2
     */
    public static long interchangeableRectangles(int[][] rectangles) {
        Map<Double, Long> map = new HashMap<>();
        for (int[] rectangle : rectangles) {
            double value = (double) rectangle[0] / (double) rectangle[1];
            map.put(value, map.getOrDefault(value, 0L) + 1);
        }


        long count = 0;
        for (long value : map.values()) {
            count += (value * (value - 1)) / 2;
        }
        return count;
    }
}
