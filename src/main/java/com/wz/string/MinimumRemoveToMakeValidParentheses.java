package com.wz.string;

import java.util.Stack;

/**
 * Given a string s of '(' , ')' and lowercase English characters.
 * Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) so that the resulting parentheses string is valid and return any valid string.
 *
 * Formally, a parentheses string is valid if and only if:
 *
 * It is the empty string, contains only lowercase characters, or
 * It can be written as AB (A concatenated with B), where A and B are valid strings, or
 * It can be written as (A), where A is a valid string.
 *
 *
 * Example 1:
 *
 * Input: s = "lee(t(c)o)de)"
 * Output: "lee(t(c)o)de"
 * Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.
 * Example 2:
 *
 * Input: s = "a)b(c)d"
 * Output: "ab(c)d"
 * Example 3:
 *
 * Input: s = "))(("
 * Output: ""
 * Explanation: An empty string is also valid.
 * Example 4:
 *
 * Input: s = "(a(b(c)d)"
 * Output: "a(b(c)d)"
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 10^5
 * s[i] is one of  '(' , ')' and lowercase English letters.
 */
public class MinimumRemoveToMakeValidParentheses {
    public static void main(String[] args) {
        System.out.println(minRemoveToMakeValid("lee(t(c)o)de)"));
        System.out.println(minRemoveToMakeValid("))(("));
    }

    /**
     * 使用栈保存左括号的下标，遍历 s，遇到左括号则入栈；遇到右括号，如果栈不空，就弹出栈顶，否则将 s[i] 设置为 ' '，代表需要删除
     * 遍历结束后，栈中的下标代表的都是无法匹配的左括号，全部设置为 ' '，最后将所有标记为 ' ' 的位置移除即可
     */
    public static String minRemoveToMakeValid(String s) {
        Stack<Integer> stack = new Stack<>();
        char[] array = s.toCharArray();
        for (int i = 0; i < array.length; i++) {
            if (array[i] == '(') {
                stack.push(i);
            } else if (array[i] == ')') {
                if (!stack.empty()) {
                    // 匹配
                    stack.pop();
                } else {
                    // 无法匹配
                    array[i] = ' ';
                }
            }
        }

        while (!stack.empty()) {
            // 无法匹配
            array[stack.pop()] = ' ';
        }

        StringBuilder builder = new StringBuilder();
        for (char cur : array) {
            builder.append(cur != ' ' ? cur : "");
        }
        return builder.toString();
    }
}
