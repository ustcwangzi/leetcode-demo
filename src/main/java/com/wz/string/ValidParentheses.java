package com.wz.string;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 * An input string is valid if:
 * 1. Open brackets must be closed by the same type of brackets.
 * 2. Open brackets must be closed in the correct order.
 *
 * Example 1:
 * Input: s = "()"
 * Output: true
 *
 * Example 2:
 * Input: s = "()[]{}"
 * Output: true
 *
 * Constraints:
 * 1 <= s.length <= 104
 * s consists of parentheses only '()[]{}'.
 */
public class ValidParentheses {
    public static void main(String[] args) {
        System.out.println(isValid("()"));
        System.out.println(isValid("{[]}"));
    }

    private static final Map<Character, Character> map = new HashMap<>();

    static {
        map.put(')', '(');
        map.put('}', '{');
        map.put(']', '[');
    }

    /**
     * 利用栈，当前字符和栈顶匹配时则弹出栈顶，否则当前字符入栈，最后检查栈是否为空
     */
    public static boolean isValid(String s) {
        char[] array = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (char cur : array) {
            if (stack.isEmpty() || !stack.peek().equals(map.get(cur))) {
                stack.push(cur);
            } else {
                stack.pop();
            }
        }
        return stack.isEmpty();
    }
}
