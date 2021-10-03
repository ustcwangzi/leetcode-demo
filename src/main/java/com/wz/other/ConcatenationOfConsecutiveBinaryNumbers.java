package com.wz.other;

/**
 * Given an integer n, return the decimal value of the binary string formed by concatenating the binary representations of 1 to n in order, modulo 10^9 + 7.
 *
 * Example 1:
 * Input: n = 1
 * Output: 1
 * Explanation: "1" in binary corresponds to the decimal value 1.
 *
 * Example 2:
 * Input: n = 3
 * Output: 27
 * Explanation: In binary, 1, 2, and 3 corresponds to "1", "10", and "11".
 * After concatenating them, we have "11011", which corresponds to the decimal value 27.
 *
 * Example 3:
 * Input: n = 12
 * Output: 505379714
 * Explanation: The concatenation results in "1101110010111011110001001101010111100".
 * The decimal value of that is 118505380540.
 * After modulo 10^9 + 7, the result is 505379714.
 *
 * Constraints:
 * 1 <= n <= 10^5
 */
public class ConcatenationOfConsecutiveBinaryNumbers {
    public static void main(String[] args) {
        System.out.println(concatenatedBinary(3));
        System.out.println(concatenatedBinary(12));
    }

    /**
     * i = 1, binary = 1, sum = 1
     * i = 2, binary = 10 (2 digits), concatenated = "1"+"10" = "110", sum = 1 * 2^2 + 2 = 6
     * i = 3, binary = 11 (2 digits), concatenated = "110"+"11" = "11011", sum = 6 * 2^2 + 3 = 27
     * i = 4, binary = 100 (3 digits), concatenated = "11011"+"100" = "11011100", sum = 27 * 2^3 + 4 = 220
     */
    public static int concatenatedBinary(int n) {
        long sum = 0, MOD = 1_000_000_007;
        int base = 1;
        for (int i = 1; i <= n; i++) {
            if (i == base * 2) {
                base = i;
            }
            sum = (sum * base * 2 + i) % MOD;
        }
        return (int) sum;
    }
}