package com.wz.string;

/**
 * Given a binary string S (a string consisting only of '0' and '1's) and a positive integer N,
 * return true if and only if for every integer X from 1 to N, the binary representation of X is a substring of S.
 *
 * Example 1:
 * Input: S = "0110", N = 3
 * Output: true
 *
 * Example 2:
 * Input: S = "0110", N = 4
 * Output: false
 *
 * Note:
 * 1 <= S.length <= 1000
 * 1 <= N <= 10^9
 */
public class BinaryStringWithSubstringsRepresenting1ToN {
    public static void main(String[] args) {
        System.out.println(queryString("0110", 3));
        System.out.println(queryString("0110", 4));
    }

    public static boolean queryString(String S, int N) {
        for (int i = N; i > N / 2; i--) {
            if (!S.contains(Integer.toBinaryString(i))) {
                return false;
            }
        }
        return true;
    }
}
