package com.wz.math;

/**
 * Given a number N, return a string consisting of "0"s and "1"s that represents its value in base -2 (negative two).
 * The returned string must have no leading zeroes, unless the string is "0".
 *
 * Example 1:
 * Input: 2
 * Output: "110"
 * Explantion: (-2) ^ 2 + (-2) ^ 1 = 2
 *
 * Example 2:
 * Input: 3
 * Output: "111"
 * Explantion: (-2) ^ 2 + (-2) ^ 1 + (-2) ^ 0 = 3
 */
public class ConvertToBaseNeg2 {
    public static void main(String[] args) {
        System.out.println(baseNeg2(2));
        System.out.println(baseNeg2(3));
    }

    public static String baseNeg2(int N) {
        StringBuilder builder = new StringBuilder();
        int base = -2;
        while (N != 0) {
            int remainder = N % base;
            N = N / base;
            if (remainder < 0) {
                // 余数为负，说明商乘以base后的值比被除数大，此时应该将商加1，使乘积小于被除数，如此余数大于0
                remainder += Math.abs(base);
                ++N;
            }
            builder.insert(0, remainder);
        }
        return builder.toString().equals("") ? "0" : builder.toString();
    }
}
