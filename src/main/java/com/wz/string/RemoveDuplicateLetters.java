package com.wz.string;

import java.util.Stack;

/**
 * Given a string s, remove duplicate letters so that every letter appears once and only once.
 * You must make sure your result is the smallest in lexicographical order among all possible results.
 * Note: This question is the same as 1081: https://leetcode.com/problems/smallest-subsequence-of-distinct-characters/
 *
 * Example 1:
 * Input: s = "bcabc"
 * Output: "abc"
 *
 * Example 2:
 * Input: s = "cbacdcbc"
 * Output: "acdb"
 *
 * Constraints:
 * 1 <= s.length <= 104
 * s consists of lowercase English letters.
 */
public class RemoveDuplicateLetters {
    public static void main(String[] args) {
        System.out.println(removeDuplicateLetters("bcabc"));
        System.out.println(removeDuplicateLetters("cbacdcbc"));
    }

    /**
     * 必须保证顺序一致，而且要尽可能排序
     * 先使用 countMap 记录每个字符出现的次数，用 visited 记录每个字符是否已经包含在结果集中，用 stack 来收集结果字符
     * 遍历每个字符，先将其出现的次数减1，如果已包含在结果集中，则直接跳过，否则和栈顶元素比较，如果小于栈顶且栈顶字符在后面还有，则弹出栈顶
     * 然后将当前元素入栈，即加入到结果集中
     */
    public static String removeDuplicateLetters(String s) {
        if (s.length() <= 1) {
            return s;
        }

        char[] array = s.toCharArray();
        int[] countMap = new int[26];
        for (char cur : array) {
            countMap[cur - 'a']++;
        }

        boolean[] visited = new boolean[26];
        Stack<Character> stack = new Stack<>();
        for (char cur : array) {
            countMap[cur - 'a']--;
            if (visited[cur - 'a']) {
                continue;
            }

            // 小于栈顶，并且栈顶字符在后面还有，则弹出栈顶
            while (!stack.isEmpty() && stack.peek() > cur && countMap[stack.peek() - 'a'] > 0) {
                visited[stack.peek() - 'a'] = false;
                stack.pop();
            }
            stack.push(cur);
            visited[cur - 'a'] = true;
        }

        return convertString(stack);
    }

    private static String convertString(Stack<Character> stack) {
        StringBuilder builder = new StringBuilder();
        for (char cur : stack) {
            builder.append(cur);
        }
        return builder.toString();
    }
}
