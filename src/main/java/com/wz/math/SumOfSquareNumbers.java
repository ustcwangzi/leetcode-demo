package com.wz.math;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a non-negative integer c, your task is to decide whether there're two integers a and b such that a2 + b2 = c.
 *
 * Example 1:
 * Input: 5
 * Output: True
 * Explanation: 1 * 1 + 2 * 2 = 5
 *
 * Example 2:
 * Input: 3
 * Output: False
 */
public class SumOfSquareNumbers {
    public static void main(String[] args) {
        System.out.println(judgeSquareSum(5));
        System.out.println(judgeSquareSum(3));
    }

    /**
     * 从0遍历到c的平方根，对于每个i*i，都加入 Set 中，然后计算 c - i*i，如果这个差值也在 Set 中，返回 true，遍历结束返回 false
     */
    public static boolean judgeSquareSum(int c) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i <= Math.sqrt(c); ++i) {
            set.add(i * i);
            if (set.contains(c - i * i)) {
                return true;
            }
        }
        return false;
    }
}
