package com.wz.string;

/**
 * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
 *
 * Example 1:
 * Input: s = "(()"
 * Output: 2
 * Explanation: The longest valid parentheses substring is "()".
 *
 * Example 2:
 * Input: s = ")()())"
 * Output: 4
 * Explanation: The longest valid parentheses substring is "()()".
 */
public class LongestValidParentheses {
    public static void main(String[] args) {
        System.out.println(longestValidParentheses("(()"));
        System.out.println(longestValidParentheses(")()())"));
    }

    /**
     * 动态规划
     * dp[i] 表示以为i为结尾的最长有效子字符串的长度
     * 当 s[i] == ')' 时：
     * 1. 若 s[i-1] == '('，字符串形如 '...()'
     *    dp[i] = dp[i-2] + 2
     * 2. 若 s[i - dp[i-1] - 1] == '('，字符串形如 '...))'
     *    dp[i] = dp[i-1] + 2 + dp[i - dp[i-1] - 2]
     * 以 "( ( ) ( ) )" 为例，对应的 dp 数组为：
     *     0 0 2 0 4 6
     */
    public static int longestValidParentheses(String s) {
        if (s.length() == 0) {
            return 0;
        }

        int result = 0;
        int[] dp = new int[s.length()];
        for (int i = 1; i < s.length(); ++i) {
            if (s.charAt(i) == '(') {
                continue;
            }
            if (s.charAt(i - 1) == '(') {
                dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
            } else if (i - dp[i - 1] >= 1 && s.charAt(i - dp[i - 1] - 1) == '(') {
                dp[i] = dp[i - 1] + 2 + (i - dp[i - 1] >= 2 ? dp[i - dp[i - 1] - 2] : 0);
            }
            result = Math.max(result, dp[i]);
        }
        return result;
    }
}
