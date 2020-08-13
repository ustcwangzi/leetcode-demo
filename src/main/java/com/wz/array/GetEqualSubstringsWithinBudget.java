package com.wz.array;

/**
 * You are given two strings s and t of the same length. You want to change s to t. Changing the i-th character of s
 * to i-th character of t costs |s[i] - t[i]| that is, the absolute difference between the ASCII values of the characters.
 * You are also given an integer maxCost.
 * Return the maximum length of a substring of s that can be changed to be the same as the corresponding substring of
 * twith a cost less than or equal to maxCost.
 * If there is no substring from s that can be changed to its corresponding substring from t, return 0.
 *
 * Example 1:
 * Input: s = "abcd", t = "bcdf", maxCost = 3
 * Output: 3
 * Explanation: "abc" of s can change to "bcd". That costs 3, so the maximum length is 3.
 *
 * Example 2:
 * Input: s = "abcd", t = "cdef", maxCost = 3
 * Output: 1
 * Explanation: Each character in s costs 2 to change to charactor in t, so the maximum length is 1.
 *
 * Example 3:
 * Input: s = "abcd", t = "acde", maxCost = 0
 * Output: 1
 * Explanation: You can't make any change, so the maximum length is 1.
 */
public class GetEqualSubstringsWithinBudget {
    public static void main(String[] args) {
        System.out.println(equalSubstring("abcd", "bcdf", 3));
        System.out.println(equalSubstring("abcd", "cdef", 3));
        System.out.println(equalSubstring("abcd", "acde", 0));
    }

    /**
     * 滑动窗口
     * 将 s[i] 变更为 t[i] 的开销为 abs(s[i]-t[i])，求最大长度，使得开销之和 <= maxCost
     * i代表左边界，j代表右边界，每次先向右移动j，同时 maxCost 减小，满足条件的情况下，i 不变化
     * 当不满足条件，即 maxCost < 0 时，i 增大，缩小窗口，每一步更新结果 result
     */
    public static int equalSubstring(String s, String t, int maxCost) {
        int result = 0;
        int i = 0, j;
        for (j = 0; j < s.length(); j++) {
            maxCost -= Math.abs(s.charAt(j) - t.charAt(j));
            if (maxCost < 0) {
                maxCost += Math.abs(s.charAt(i) - t.charAt(i));
                i++;
            }
            result = Math.max(result, j - i + 1);
        }
        return result;
    }
}
