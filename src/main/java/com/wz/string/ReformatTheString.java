package com.wz.string;

import java.util.Stack;

/**
 * Given alphanumeric string s. (Alphanumeric string is a string consisting of lowercase English letters and digits).
 * You have to find a permutation of the string where no letter is followed by another letter and
 * no digit is followed by another digit. That is, no two adjacent characters have the same type.
 * Return the reformatted string or return an empty string if it is impossible to reformat the string.
 *
 * Example 1:
 * Input: s = "a0b1c2"
 * Output: "0a1b2c"
 * Explanation: No two adjacent characters have the same type in "0a1b2c". "a0b1c2", "0a1b2c", "0c2a1b" are also valid permutations.
 *
 * Example 2:
 * Input: s = "leetcode"
 * Output: ""
 * Explanation: "leetcode" has only characters so we cannot separate them by digits.
 *
 * Example 3:
 * Input: s = "covid2019"
 * Output: "c2o0v1i9d"
 *
 * Constraints:
 * 1. 1 <= s.length <= 500
 * 2. s consists of only lowercase English letters and/or digits.
 */
public class ReformatTheString {
    public static void main(String[] args) {
        System.out.println(reformat("covid2019"));
        System.out.println(reformat("leetcode"));
    }

    /**
     * 用两个 stack 收集 s 中的数字和字母，若数字和字母个数之差大于1，则直接返回空
     * 若数字比字母多，则依次将数字、字母加入结果集中，否则，依次将字母、数字加入结果集中
     */
    public static String reformat(String s) {
        Stack<Character> stack1 = new Stack<>(), stack2 = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            if (Character.isDigit(cur)) {
                stack1.push(cur);
            } else {
                stack2.push(cur);
            }
        }

        if (Math.abs(stack1.size() - stack2.size()) > 1) {
            return "";
        }

        // 先加入个数多的
        if (stack1.size() > stack2.size()) {
            return build(stack1, stack2);
        }
        return build(stack2, stack1);
    }

    private static String build(Stack<Character> bigStack, Stack<Character> smallStack) {
        StringBuilder builder = new StringBuilder();
        while (!bigStack.isEmpty() && !smallStack.isEmpty()) {
            builder.append(bigStack.pop());
            builder.append(smallStack.pop());
        }
        if (!bigStack.isEmpty()) {
            builder.append(bigStack.pop());
        }
        return builder.toString();
    }
}
