package com.wz.other;

/**
 * Given an integer num, return a string of its base 7 representation.
 *
 * Example 1:
 * Input: num = 100
 * Output: "202"
 *
 * Example 2:
 * Input: num = -7
 * Output: "-10"
 *
 * Constraints:
 * -10^7 <= num <= 10^7
 */
public class Base7 {
    public static void main(String[] args) {
        System.out.println(convertToBase7(100));
    }

    /**
     * 对 num 逐步取余
     */
    public static String convertToBase7(int num) {
        if (num == 0) {
            return "0";
        }

        boolean negative = num < 0;
        num = Math.abs(num);
        StringBuilder builder = new StringBuilder();
        while (num != 0) {
            builder.insert(0, num % 7);
            num /= 7;
        }
        return negative ? "-" + builder.toString() : builder.toString();
    }
}
