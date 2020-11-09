package com.wz.string;

/**
 * Given a parentheses string s containing only the characters '(' and ')'. A parentheses string is balanced if:
 * Any left parenthesis '(' must have a corresponding two consecutive right parenthesis '))'.
 * Left parenthesis '(' must go before the corresponding two consecutive right parenthesis '))'.
 * In other words, we treat '(' as openning parenthesis and '))' as closing parenthesis.
 * For example, "())", "())(())))" and "(())())))" are balanced, ")()", "()))" and "(()))" are not balanced.
 * You can insert the characters '(' and ')' at any position of the string to balance it if needed.
 * Return the minimum number of insertions needed to make s balanced.
 *
 * Example 1:
 * Input: s = "(()))"
 * Output: 1
 * Explanation: The second '(' has two matching '))', but the first '(' has only ')' matching.
 *              We need to to add one more ')' at the end of the string to be "(())))" which is balanced.
 *
 * Example 2:
 * Input: s = "())"
 * Output: 0
 * Explanation: The string is already balanced.
 *
 * Example 3:
 * Input: s = "))())("
 * Output: 3
 * Explanation: Add '(' to match the first '))', Add '))' to match the last '('.
 *
 * Constraints:
 * 1. 1 <= s.length <= 10^5
 * 2. s consists of '(' and ')' only.
 */
public class MinimumInsertionsToBalanceParenthesesString {
    public static void main(String[] args) {
        System.out.println(minInsertions("(()))"));
        System.out.println(minInsertions("))())("));
    }

    /**
     * 每个 ( 需要匹配两个 )
     * 用 left 统计没有匹配上的 ( 个数，用 result 统计遍历过程中需要增加的括号个数，遍历 s
     * 遇到 ( 则 left++，遇到 ) 需要分情况讨论
     * 1. 出现单个 ) 时，即已经遍历到最后一个字符，或下一个字符是 (
     *    1.1 若 left > 0，则可以消除一个 (，同时增加一个 ) 用以匹配
     *    1.2 否则，需要增加一个 (、一个 ) 用以匹配，即 result + 2
     * 2. 不是单个 ) 时
     *    2.1 若 left > 0，则可以消除一个 (
     *    2.2 否则，需要增加一个 ( 用以匹配
     *    因为此时出现的是两个 (，因此 i 多右移一次
     */
    public static int minInsertions(String s) {
        int left = 0, result = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else if (i == s.length() - 1 || s.charAt(i + 1) == '(') {
                if (left > 0) {
                    left--;
                    result++;
                } else {
                    result += 2;
                }
            } else {
                if (left > 0) {
                    left--;
                } else {
                    result++;
                }
                i++;
            }
        }

        return result + 2 * left;
    }
}
