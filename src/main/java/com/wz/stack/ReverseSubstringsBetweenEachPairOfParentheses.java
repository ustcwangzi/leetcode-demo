package com.wz.stack;

import java.util.Stack;

/**
 * You are given a string s that consists of lower case English letters and brackets.
 * Reverse the strings in each pair of matching parentheses, starting from the innermost one.
 * Your result should not contain any brackets.
 *
 * Example 1:
 * Input: s = "(abcd)"
 * Output: "dcba"
 *
 * Example 2:
 * Input: s = "(u(love)i)"
 * Output: "iloveu"
 * Explanation: The substring "love" is reversed first, then the whole string is reversed.
 *
 * Example 3:
 * Input: s = "a(bcdefghijkl(mno)p)q"
 * Output: "apmnolkjihgfedcbq"
 *
 * Constraints:
 * 1. 0 <= s.length <= 2000
 * 2. s only contains lower case English characters and parentheses.
 * 3. It's guaranteed that all parentheses are balanced.
 */
public class ReverseSubstringsBetweenEachPairOfParentheses {
    public static void main(String[] args) {
        System.out.println(reverseParentheses("(u(love)i)"));
    }

    /**
     * 遍历字符串，若当前不是右括号，则直接入栈，继续遍历下一个元素
     * 否则，将左括号之后的元素弹出添加到 StringBuilder 中，然后再将 StringBuilder 中的元素再次入栈，此时已经将这部分元素反转了
     * 遍历结束后，栈中保存的就是去除括号后的结果，依次出栈加入结果集最后反转即可
     */
    public static String reverseParentheses(String s) {
        Stack<Character> stack = new Stack<>();
        char[] array = s.toCharArray();
        for (char cur : array) {
            if (cur != ')') {
                stack.push(cur);
                continue;
            }

            StringBuilder builder = new StringBuilder();
            while (stack.peek() != '(') {
                builder.append(stack.pop());
            }
            // 左括号出栈
            stack.pop();
            int i = 0;
            while (i < builder.length()) {
                stack.push(builder.charAt(i++));
            }
        }

        StringBuilder builder = new StringBuilder();
        while (!stack.isEmpty()) {
            builder.append(stack.pop());
        }
        return builder.reverse().toString();
    }
}
