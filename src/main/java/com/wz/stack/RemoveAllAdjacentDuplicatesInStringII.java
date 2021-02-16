package com.wz.stack;

import javafx.util.Pair;

import java.util.Stack;

/**
 * Given a string s, a k duplicate removal consists of choosing k adjacent and equal letters from s and
 * removing them causing the left and the right side of the deleted substring to concatenate together.
 * We repeatedly make k duplicate removals on s until we no longer can.
 * Return the final string after all such duplicate removals have been made.
 * It is guaranteed that the answer is unique.
 *
 * Example 1:
 * Input: s = "deeedbbcccbdaa", k = 3
 * Output: "aa"
 * Explanation:
 * First delete "eee" and "ccc", get "ddbbbdaa"
 * Then delete "bbb", get "dddaa"
 * Finally delete "ddd", get "aa"
 *
 * Example 2:
 * Input: s = "pbbcggttciiippooaais", k = 2
 * Output: "ps"
 *
 * Constraints:
 * 1. 1 <= s.length <= 10^5
 * 2. 2 <= k <= 10^4
 * 3. s only contains lower case English letters.
 */
public class RemoveAllAdjacentDuplicatesInStringII {
    public static void main(String[] args) {
        System.out.println(removeDuplicates("deeedbbcccbdaa", 3));
    }

    /**
     * 与 {@link RemoveAllAdjacentDuplicatesInString} 类似
     * 只是栈中保存的不是元素本身，而是元素和其连续出现的次数，当次数达到 k 时，出栈，最后收集结果
     */
    public static String removeDuplicates(String s, int k) {
        Stack<Pair<Character, Integer>> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            if (stack.isEmpty() || stack.peek().getKey() != cur) {
                stack.push(new Pair<>(cur, 1));
                continue;
            }

            Pair<Character, Integer> top = stack.pop();
            if (top.getValue() != k - 1) {
                stack.push(new Pair<>(top.getKey(), top.getValue() + 1));
            }
        }

        StringBuilder builder = new StringBuilder();
        while (!stack.isEmpty()) {
            Pair<Character, Integer> cur = stack.pop();
            for (int i = 0; i < cur.getValue(); i++) {
                builder.insert(0, cur.getKey());
            }
        }
        return builder.toString();
    }
}
