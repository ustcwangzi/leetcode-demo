package com.wz.other;

/**
 * A square triple (a,b,c) is a triple where a, b, and c are integers and a2 + b2 = c2.
 * Given an integer n, return the number of square triples such that 1 <= a, b, c <= n.
 *
 * Example 1:
 * Input: n = 5
 * Output: 2
 * Explanation: The square triples are (3,4,5) and (4,3,5).
 *
 * Example 2:
 * Input: n = 10
 * Output: 4
 * Explanation: The square triples are (3,4,5), (4,3,5), (6,8,10), and (8,6,10).
 *
 * Constraints:
 * 1 <= n <= 250
 */
public class CountSquareSumTriples {
    public static void main(String[] args) {
        System.out.println(countTriples(5));
        System.out.println(countTriples(10));
    }

    /**
     * 枚举 a,c，求出 b 的平方，检查是否存在整数 b 即可
     */
    public static int countTriples(int n) {
        int count = 0;
        for (int c = 1; c <= n; c++) {
            for (int a = 1; a < c; a++) {
                int value = c * c - a * a;
                int b = (int) Math.sqrt(value);
                if (b * b == value) {
                    count++;
                }
            }
        }
        return count;
    }
}
