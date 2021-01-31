package com.wz.backtracking;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * An integer has sequential digits if and only if each digit in the number is one more than the previous digit.
 * Return a sorted list of all the integers in the range [low, high] inclusive that have sequential digits.
 *
 * Example 1:
 * Input: low = 100, high = 300
 * Output: [123,234]
 *
 * Example 2:
 * Input: low = 1000, high = 13000
 * Output: [1234,2345,3456,4567,5678,6789,12345]
 *
 * Constraints:
 * 10 <= low <= high <= 10^9
 */
public class SequentialDigits {
    public static void main(String[] args) {
        System.out.println(sequentialDigits(100, 300));
    }

    /**
     * BFS
     */
    public static List<Integer> sequentialDigits(int low, int high) {
        List<Integer> result = new LinkedList<>();
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i < 9; i++) {
            queue.offer(i);
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            if (cur >= low && cur <= high) {
                result.add(cur);
            }

            // 下一个数字入队
            if (cur <= high && cur % 10 < 9) {
                queue.add(cur * 10 + cur % 10 + 1);
            }
        }

        return result;
    }
}
