package com.wz.stack;

import java.util.Stack;

/**
 * Given two strings S and T, return if they are equal when both are typed into empty text editors. # means a backspace character.
 * Note that after backspacing an empty text, the text will continue empty.
 *
 * Example 1:
 * Input: S = "ab#c", T = "ad#c"
 * Output: true
 * Explanation: Both S and T become "ac".
 *
 * Example 2:
 * Input: S = "a##c", T = "#a#c"
 * Output: true
 * Explanation: Both S and T become "c".
 *
 * Note:
 * 1. 1 <= S.length <= 200
 * 2. 1 <= T.length <= 200
 * 3. S and T only contain lowercase letters and '#' characters.
 */
public class BackspaceStringCompare {
    public static void main(String[] args) {
        System.out.println(backspaceCompare("ab##", "c#d#"));
    }

    /**
     * 用两个栈收集结果
     */
    public static boolean backspaceCompare(String S, String T) {
        Stack<Character> stack1 = new Stack<>(), stack2 = new Stack<>();
        char[] array1 = S.toCharArray(), array2 = T.toCharArray();
        for (char cur : array1) {
            if (cur == '#') {
                if (!stack1.isEmpty()) {
                    stack1.pop();
                }
                continue;
            }
            stack1.push(cur);
        }
        for (char cur : array2) {
            if (cur == '#') {
                if (!stack2.isEmpty()) {
                    stack2.pop();
                }
                continue;
            }
            stack2.push(cur);
        }
        if (stack1.size() != stack2.size()) {
            return false;
        }
        while (!stack1.isEmpty()) {
            if (stack1.pop() != stack2.pop()) {
                return false;
            }
        }
        return true;
    }
}
