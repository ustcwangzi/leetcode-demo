package com.wz.string;

import java.util.Stack;

/**
 * Return the lexicographically smallest subsequence of s that contains all the distinct characters of s exactly once.
 * Note: This question is the same as 316: https://leetcode.com/problems/remove-duplicate-letters/
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
 * 1. 1 <= s.length <= 1000
 * 2. s consists of lowercase English letters.
 */
public class SmallestSubsequenceOfDistinctCharacters {
    public static void main(String[] args) {
        System.out.println(smallestSubsequence("bcabc"));
    }

    /**
     * 思路与{@link RemoveDuplicateLetters}类似
     * 不同之处在于这里使用 maxIndex 数组保存每个字符最后出现的位置，还是用 stack 收集结果
     * 遍历，若当前字符 cur 已被使用则跳过，否则和栈顶元素比较，若 cur 小于栈顶元素，且栈顶元素在后面还有出现，则弹出栈顶，同时设置为未使用
     * 然后将当前字符入栈，同时设置为已使用
     */
    public static String smallestSubsequence(String s) {
        if (s.length() <= 1) {
            return s;
        }

        int[] maxIndex = new int[26];
        for (int i = 0; i < s.length(); i++) {
            maxIndex[s.charAt(i) - 'a'] = i;
        }

        boolean[] visited = new boolean[26];
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            if (visited[cur - 'a']) {
                continue;
            }
            // 若栈顶元素较大，且栈顶元素在后面还有出现，则弹出栈顶
            while (!stack.isEmpty() && stack.peek() > cur && maxIndex[stack.peek() - 'a'] > i) {
                visited[stack.pop() - 'a'] = false;
            }
            stack.push(cur);
            visited[cur - 'a'] = true;
        }

        StringBuilder builder = new StringBuilder();
        for (char cur : stack) {
            builder.append(cur);
        }
        return builder.toString();
    }
}
