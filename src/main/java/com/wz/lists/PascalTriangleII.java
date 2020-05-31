package com.wz.lists;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a non-negative index k where k ≤ 33, return the kth index row of the Pascal's triangle.
 * Note that the row index starts from 0.
 *
 * @see ../../../../resource/PascalTriangle.jpg
 * In Pascal's triangle, each number is the sum of the two numbers directly above it.
 *
 * Example:
 * Input: 3
 * Output: [1,3,3,1]
 */
public class PascalTriangleII {
    public static void main(String[] args) {
        List<Integer> result = getRow(3);
        System.out.println(result);
    }

    /**
     * 思路与{@link PascalTriangle}类似
     * 需要记录上一层的结果即可，然后利用 cur[i] = pre[i-1] + pre[i]
     */
    public static List<Integer> getRow(int rowIndex) {
        List<Integer> result = new ArrayList<>();
        if (rowIndex < 0) {
            return result;
        }

        result.add(1);
        for (int i = 1; i <= rowIndex; i++) {
            // 上一层的结果
            List<Integer> lastRow = result;
            // 本次的结果
            result = new ArrayList<>(i + 1);

            // 计算第i行第j个数
            for (int j = 0; j < i + 1; j++) {
                if (j == 0 || j == i) {
                    result.add(j, 1);
                } else {
                    result.add(j, lastRow.get(j - 1) + lastRow.get(j));
                }
            }
        }
        return result;
    }
}
