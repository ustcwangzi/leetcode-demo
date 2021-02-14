package com.wz.stack;

/**
 * Given a string S of '(' and ')' parentheses, we add the minimum number of parentheses ( '(' or ')', and in any positions )
 * so that the resulting parentheses string is valid.
 * Formally, a parentheses string is valid if and only if:
 * 1. It is the empty string, or
 * 2. It can be written as AB (A concatenated with B), where A and B are valid strings, or
 * 3. It can be written as (A), where A is a valid string.
 * Given a parentheses string, return the minimum number of parentheses we must add to make the resulting string valid.
 *
 * Example 1:
 * Input: "())"
 * Output: 1
 *
 * Example 2:
 * Input: "()))(("
 * Output: 4
 *
 * Note:
 * 1. S.length <= 1000
 * 2. S only consists of '(' and ')' characters.
 */
public class MinimumAddToMakeParenthesesValid {
    public static void main(String[] args) {
        System.out.println(minAddToMakeValid("())"));
        System.out.println(minAddToMakeValid("()))(("));
    }

    /**
     * 使用 left 和 right，分别表示需要的左右括号个数
     * 遍历字符串，若遇到左括号，说明此时需要右括号，则 right++；
     * 若遇到了右括号，若此时 right 大于0，说明当前的右括号可以用来匹配之前的左括号，不需要另加右括号，所以此时 right--；
     * 而若此时 right 为 0，说明当前没有左括号可以跟其匹配，则此时 left++，表示需要额外的左括号，最后返回 left+right 即可
     */
    public static int minAddToMakeValid(String S) {
        int left = 0, right = 0;
        char[] array = S.toCharArray();
        for (char cur : array) {
            if (cur == '(') {
                right++;
                continue;
            }
            if (right > 0) {
                right--;
            } else {
                left++;
            }
        }
        return left + right;
    }
}
