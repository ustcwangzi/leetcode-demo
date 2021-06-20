package com.wz.twopointer;

/**
 * Given a binary string s, return true if the longest contiguous segment of 1s is strictly longer than the longest contiguous segment of 0s in s. Return false otherwise.
 * For example, in s = "110100010" the longest contiguous segment of 1s has length 2, and the longest contiguous segment of 0s has length 3.
 * Note that if there are no 0s, then the longest contiguous segment of 0s is considered to have length 0. The same applies if there are no 1s.
 *
 * Example 1:
 * Input: s = "1101"
 * Output: true
 * Explanation:
 * The longest contiguous segment of 1s has length 2: "1101"
 * The longest contiguous segment of 0s has length 1: "1101"
 * The segment of 1s is longer, so return true.
 *
 * Example 2:
 * Input: s = "111000"
 * Output: false
 * Explanation:
 * The longest contiguous segment of 1s has length 3: "111000"
 * The longest contiguous segment of 0s has length 3: "111000"
 * The segment of 1s is not longer, so return false.
 *
 * Example 3:
 * Input: s = "110100010"
 * Output: false
 * Explanation:
 * The longest contiguous segment of 1s has length 2: "110100010"
 * The longest contiguous segment of 0s has length 3: "110100010"
 * The segment of 1s is not longer, so return false.
 *
 * Constraints:
 * 1. 1 <= s.length <= 100
 * 2. s[i] is either '0' or '1'.
 */
public class LongerContiguousSegmentsOfOnesThanZeros {
    public static void main(String[] args) {
        System.out.println(checkZeroOnes("1101"));
        System.out.println(checkZeroOnes("111000"));
    }

    /**
     * 遍历 s，记录当前当前连续 0、连续 1 的个数，并更新最长连续 0、最长连续 1 的个数
     */
    public static boolean checkZeroOnes(String s) {
        int curOne = 0, curZero = 0, maxOne = 0, maxZero = 0;
        if (s.charAt(0) == '1') {
            curOne++;
            maxOne = curOne;
        } else {
            curZero++;
            maxZero = curZero;
        }

        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                if (s.charAt(i - 1) == '1') {
                    curOne++;
                } else {
                    curOne = 1;
                }
            } else {
                if (s.charAt(i - 1) == '0') {
                    curZero++;
                } else {
                    curZero = 1;
                }
            }
            maxOne = Math.max(maxOne, curOne);
            maxZero = Math.max(maxZero, curZero);
        }
        return maxOne > maxZero;
    }
}
