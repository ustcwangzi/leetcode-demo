package com.wz.greedy;

/**
 * Given a binary string s without leading zeros, return true if s contains at most one contiguous segment of ones. Otherwise, return false.
 *
 * Example 1:
 * Input: s = "1001"
 * Output: false
 * Explanation: The ones do not form a contiguous segment.
 *
 * Example 2:
 * Input: s = "110"
 * Output: true
 *
 * Constraints:
 * 1. 1 <= s.length <= 100
 * 2. s[i] is either '0' or '1'.
 * 3. s[0] is '1'.
 */
public class CheckIfBinaryStringHasAtMostOneSegmentOfOnes {
    public static void main(String[] args) {
        System.out.println(checkOnesSegment("1001"));
        System.out.println(checkOnesSegment("110"));
    }

    public static boolean checkOnesSegment(String s) {
        return !s.contains("01");
    }
}
