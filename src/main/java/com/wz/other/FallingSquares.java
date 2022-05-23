package com.wz.other;

import java.util.ArrayList;
import java.util.List;

/**
 * There are several squares being dropped onto the X-axis of a 2D plane.
 * You are given a 2D integer array positions where positions[i] = [lefti, sideLengthi] represents the ith square
 * with a side length of sideLengthi that is dropped with its left edge aligned with X-coordinate lefti.
 * Each square is dropped one at a time from a height above any landed squares. It then falls downward (negative Y direction)
 * until it either lands on the top side of another square or on the X-axis. A square brushing the left/right side of
 * another square does not count as landing on it. Once it lands, it freezes in place and cannot be moved.
 * After each square is dropped, you must record the height of the current tallest stack of squares.
 * Return an integer array ans where ans[i] represents the height described above after dropping the ith square.
 *
 * Example 1:
 * @link ../../../../resource/FallingSquares.jpg
 * Input: positions = [[1,2],[2,3],[6,1]]
 * Output: [2,5,5]
 * Explanation:
 * After the first drop, the tallest stack is square 1 with a height of 2.
 * After the second drop, the tallest stack is squares 1 and 2 with a height of 5.
 * After the third drop, the tallest stack is still squares 1 and 2 with a height of 5.
 * Thus, we return an answer of [2, 5, 5].
 *
 * Example 2:
 * Input: positions = [[100,100],[200,100]]
 * Output: [100,100]
 * Explanation:
 * After the first drop, the tallest stack is square 1 with a height of 100.
 * After the second drop, the tallest stack is either square 1 or square 2, both with heights of 100.
 * Thus, we return an answer of [100, 100].
 * Note that square 2 only brushes the right side of square 1, which does not count as landing on it.
 *
 * Constraints:
 * 1. 1 <= positions.length <= 1000
 * 2. 1 <= lefti <= 10^8
 * 3. 1 <= sideLengthi <= 10^6
 */
public class FallingSquares {
    public static void main(String[] args) {
        System.out.println(fallingSquares(new int[][]{{1, 2}, {2, 3}, {6, 1}}));
        System.out.println(fallingSquares(new int[][]{{100, 100}, {200, 100}}));
    }

    /**
     * 直接模拟
     * 每次掉落一个方块，更新它后面的所有方块的高度，因为前面掉落的不会影响到后面的方块
     */
    public static List<Integer> fallingSquares(int[][] positions) {
        int n = positions.length;
        List<Integer> result = new ArrayList<>(n);
        int[] heights = new int[n];
        for (int i = 0; i < n; i++) {
            int left1 = positions[i][0], right1 = positions[i][0] + positions[i][1];
            heights[i] += positions[i][1];
            for (int j = i + 1; j < n; j++) {
                int left2 = positions[j][0], right2 = positions[j][0] + positions[j][1];
                if (left2 < right1 && left1 < right2) {
                    heights[j] = Math.max(heights[i], heights[j]);
                }
            }
        }
        for (int height : heights) {
            result.add(result.isEmpty() ? height : Math.max(result.get(result.size() - 1), height));
        }
        return result;
    }
}
