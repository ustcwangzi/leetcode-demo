package com.wz.array;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a non-negative integer numRows, generate the first numRows of Pascal's triangle.
 *
 * @link ../../../../resource/PascalTriangle.jpg
 * In Pascal's triangle, each number is the sum of the two numbers directly above it.
 *
 * Example:
 * Input: 5
 * Output:
 * [
 *      [1],
 *     [1,1],
 *    [1,2,1],
 *   [1,3,3,1],
 *  [1,4,6,4,1]
 * ]
 */
public class PascalTriangle {
    public static void main(String[] args) {
        List<List<Integer>> result = generate(5);
        System.out.println(result);
    }

    /**
     * pre代表上一行，则 cur[i] = pre[i-1] + pre[i]
     */
    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>(numRows);
        if (numRows <= 0) {
            return result;
        }

        // 按行进行元素填充
        for (int i = 0; i < numRows; i++) {
            List<Integer> pre = i == 0 ? null : result.get(i - 1);
            List<Integer> group = new ArrayList<>(i + 1);
            fillElement(pre, group, i + 1);
            result.add(group);
        }

        return result;
    }

    private static void fillElement(List<Integer> pre, List<Integer> cur, int size) {
        cur.add(0, 1);
        for (int i = 1; i < size - 1; i++) {
            cur.add(i, pre.get(i - 1) + pre.get(i));
        }
        if (size > 1) {
            cur.add(size - 1, 1);
        }
    }
}
