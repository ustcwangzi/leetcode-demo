package com.wz.other;

/**
 * Given two strings s and goal, return true if and only if s can become goal after some number of shifts on s.
 * A shift on s consists of moving the leftmost character of s to the rightmost position.
 * For example, if s = "abcde", then it will be "bcdea" after one shift.
 *
 * Example 1:
 * Input: s = "abcde", goal = "cdeab"
 * Output: true
 *
 * Example 2:
 * Input: s = "abcde", goal = "abced"
 * Output: false
 *
 * Constraints:
 * 1. 1 <= s.length, goal.length <= 100
 * 2. s and goal consist of lowercase English letters.
 */
public class RotateString {
    public static void main(String[] args) {
        System.out.println(rotateString("abcde", "cdeab"));
        System.out.println(rotateString("abcde", "abced"));
    }

    /**
     * 在 s 之后再加上一个 s，如果新的字符串 (s+s) 中包含 goal，说明 s 一定能通过偏移得到 goal
     */
    public static boolean rotateString(String s, String goal) {
        return s.length() == goal.length() && (s + s).contains(goal);
    }
}
