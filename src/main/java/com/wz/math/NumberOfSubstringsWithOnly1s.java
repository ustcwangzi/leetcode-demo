package com.wz.math;

/**
 * Given a binary string s (a string consisting only of '0' and '1's).
 * Return the number of substrings with all characters 1's.
 * Since the answer may be too large, return it modulo 10^9 + 7.
 *
 * Example 1:
 * Input: s = "0110111"
 * Output: 9
 * Explanation: There are 9 substring in total with only 1's characters.
 * "1" -> 5 times.
 * "11" -> 3 times.
 * "111" -> 1 time.
 *
 * Example 2:
 * Input: s = "101"
 * Output: 2
 * Explanation: Substring "1" is shown 2 times in s.
 */
public class NumberOfSubstringsWithOnly1s {
    public static void main(String[] args) {
        System.out.println(numSub("0110111"));
        System.out.println(numSub("101"));
    }

    /**
     * 看连续1的数量，比如111，result就是1+2+3，遇到0就重置0
     */
    public static int numSub(String s) {
        int mod = 1000000007;
        int result = 0, count = 0;

        char[] array = s.toCharArray();
        for (char cur : array) {
            if (cur == '1') {
                count++;
                result = (result + count) % mod;
            } else {
                count = 0;
            }
        }

        return result;
    }
}
