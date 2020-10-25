package com.wz.string;

import java.util.Stack;

/**
 * Given a string s, determine if it is valid.
 * A string s is valid if, starting with an empty string t = "", you can transform t into s after performing the following operation any number of times:
 * Insert string "abc" into any position in t. More formally, t becomes tleft + "abc" + tright, where t == tleft + tright.
 * Note that tleft and tright may be empty.
 * Return true if s is a valid string, otherwise, return false.
 *
 * Example 1:
 * Input: s = "aabcbc"
 * Output: true
 * Explanation:
 * "" -> "abc" -> "aabcbc"
 * Thus, "aabcbc" is valid.
 *
 * Example 2:
 * Input: s = "abcabcababcc"
 * Output: true
 * Explanation:
 * "" -> "abc" -> "abcabc" -> "abcabcabc" -> "abcabcababcc"
 * Thus, "abcabcababcc" is valid.
 *
 * Example 3:
 * Input: s = "abccba"
 * Output: false
 * Explanation: It is impossible to get "abccba" using the operation.
 *
 * Example 4:
 * Input: s = "cababc"
 * Output: false
 * Explanation: It is impossible to get "cababc" using the operation.
 *
 * Constraints:
 * 1. 1 <= s.length <= 2 * 104
 * 2. s consists of letters 'a', 'b', and 'c'
 */
public class CheckIfWordIsValidAfterSubstitutions {
    public static void main(String[] args) {
        System.out.println(isValid("aabcbc"));
        System.out.println(isValid2("aabcbc"));
        System.out.println(isValid("cababc"));
        System.out.println(isValid2("cababc"));
    }

    /**
     * 每次从 s 中移除 abc，如果最后 s 变为空，则说明满足条件
     */
    public static boolean isValid(String s) {
        while (s.contains("abc")) {
            s = s.replace("abc", "");
        }
        return s.equals("");
    }

    /**
     * 遍历 s，如果当前字符是 c，则入栈，否则检查栈顶是否是 b 和 a，如果不是直接返回 false，遍历结束时判断栈是否为空
     */
    public static boolean isValid2(String s) {
        Stack<Character> stack = new Stack<>();
        char[] array = s.toCharArray();
        for (char cur : array) {
            if (cur != 'c') {
                stack.push(cur);
                continue;
            }
            if (stack.size() < 2 || stack.pop() != 'b' || stack.pop() != 'a') {
                return false;
            }
        }
        return stack.size() == 0;
    }
}
