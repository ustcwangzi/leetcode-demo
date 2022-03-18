package com.wz.heap;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * You are given a string s and an integer repeatLimit. Construct a new string repeatLimitedString using the characters of s
 * such that no letter appears more than repeatLimit times in a row. You do not have to use all characters from s.
 * Return the lexicographically largest repeatLimitedString possible.
 * A string a is lexicographically larger than a string b if in the first position where a and b differ,
 * string a has a letter that appears later in the alphabet than the corresponding letter in b.
 * If the first min(a.length, b.length) characters do not differ, then the longer string is the lexicographically larger one.
 *
 * Example 1:
 * Input: s = "cczazcc", repeatLimit = 3
 * Output: "zzcccac"
 * Explanation: We use all of the characters from s to construct the repeatLimitedString "zzcccac".
 * The letter 'a' appears at most 1 time in a row.
 * The letter 'c' appears at most 3 times in a row.
 * The letter 'z' appears at most 2 times in a row.
 * Hence, no letter appears more than repeatLimit times in a row and the string is a valid repeatLimitedString.
 * The string is the lexicographically largest repeatLimitedString possible so we return "zzcccac".
 * Note that the string "zzcccca" is lexicographically larger but the letter 'c' appears more than 3 times in a row, so it is not a valid repeatLimitedString.
 *
 * Example 2:
 * Input: s = "aababab", repeatLimit = 2
 * Output: "bbabaa"
 * Explanation: We use only some of the characters from s to construct the repeatLimitedString "bbabaa".
 * The letter 'a' appears at most 2 times in a row.
 * The letter 'b' appears at most 2 times in a row.
 * Hence, no letter appears more than repeatLimit times in a row and the string is a valid repeatLimitedString.
 * The string is the lexicographically largest repeatLimitedString possible so we return "bbabaa".
 * Note that the string "bbabaaa" is lexicographically larger but the letter 'a' appears more than 2 times in a row, so it is not a valid repeatLimitedString.
 *
 * Constraints:
 * 1. 1 <= repeatLimit <= s.length <= 10^5
 * 2. s consists of lowercase English letters.
 */
public class ConstructStringWithRepeatLimit {
    public static void main(String[] args) {
        System.out.println(repeatLimitedString("cczazcc", 3));
        System.out.println(repeatLimitedString("aababab", 2));
    }

    /**
     * 与 {@link ReorganizeString} 类似
     * 使用 freq 统计字符出现次数，使用大根堆保存出现次数不为 0 的字符
     * 每次取出堆顶元素，判断字符串的末尾堆顶元素是否一样
     * - 不一样就将堆顶元素取 min{count, repeatLimit} 次
     * - 一样，就取第二个元素 1 次
     * 取完之后，若出现次数依然大于 0，还要继续将其放入堆中
     */
    public static String repeatLimitedString(String s, int repeatLimit) {
        int[] freq = new int[26];
        for (char cur : s.toCharArray()) {
            freq[cur - 'a']++;
        }

        Queue<Character> queue = new PriorityQueue<>((o1, o2) -> Character.compare(o2, o1));
        for (int i = 0; i < freq.length; i++) {
            if (freq[i] == 0) {
                continue;
            }
            queue.offer((char) (i + 'a'));
        }

        StringBuilder builder = new StringBuilder();
        while (!queue.isEmpty()) {
            char top = queue.poll();
            // 和堆顶元素不一样，取堆顶
            if (builder.length() == 0 || builder.charAt(builder.length() - 1) != top) {
                int count = Math.min(freq[top - 'a'], repeatLimit);
                while (count-- > 0) {
                    builder.append(top);
                    freq[top - 'a']--;
                }
                if (freq[top - 'a'] > 0) {
                    queue.offer(top);
                }
            } else {
                if (queue.isEmpty()) {
                    return builder.toString();
                }
                // 和堆顶元素一样，取第二的元素
                char top2 = queue.poll();
                builder.append(top2);
                if (--freq[top2 - 'a'] > 0) {
                    queue.offer(top2);
                }
                queue.offer(top);
            }
        }
        return builder.toString();
    }
}
