package com.wz.other;

/**
 * You are given a string s consisting of n characters which are either 'X' or 'O'.
 * A move is defined as selecting three consecutive characters of s and converting them to 'O'.
 * Note that if a move is applied to the character 'O', it will stay the same.
 * Return the minimum number of moves required so that all the characters of s are converted to 'O'.
 *
 * Example 1:
 * Input: s = "XXX"
 * Output: 1
 * Explanation: XXX -> OOO
 * We select all the 3 characters and convert them in one move.
 *
 * Example 2:
 * Input: s = "XXOX"
 * Output: 2
 * Explanation: XXOX -> OOOX -> OOOO
 * We select the first 3 characters in the first move, and convert them to 'O'.
 * Then we select the last 3 characters and convert them so that the final string contains all 'O's.
 *
 * Constraints:
 * 1. 3 <= s.length <= 1000
 * 2. s[i] is either 'X' or 'O'.
 */
public class MinimumMovesToConvertString {
    public static void main(String[] args) {
        System.out.println(minimumMoves("XXOX"));
    }

    /**
     * 每次可将相邻的三个字符转换为 0，直接遍历字符串，遇到 X 时说明需要转换，因此 count++
     * 同时不管后面两个字符是什么，都不用增加转换次数，因此直接跳过后面两个，继续向后判断
     */
    public static int minimumMoves(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'X') {
                count++;
                i += 2;
            }
        }
        return count;
    }
}
