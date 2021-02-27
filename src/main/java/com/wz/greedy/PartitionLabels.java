package com.wz.greedy;

import java.util.LinkedList;
import java.util.List;

/**
 * A string S of lowercase English letters is given. We want to partition this string into as many parts as possible
 * so that each letter appears in at most one part, and return a list of integers representing the size of these parts.
 *
 * Example 1:
 * Input: S = "ababcbacadefegdehijhklij"
 * Output: [9,7,8]
 * Explanation:
 * The partition is "ababcbaca", "defegde", "hijhklij".
 * This is a partition so that each letter appears in at most one part.
 * A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits S into less parts.
 *
 * Note:
 * 1. S will have length in range [1, 500].
 * 2. S will consist of lowercase English letters ('a' to 'z') only.
 */
public class PartitionLabels {
    public static void main(String[] args) {
        System.out.println(partitionLabels("ababcbacadefegdehijhklij"));
    }

    /**
     * 使用 map 记录每个字符最后出现的位置，因为一旦当前子串包含了一个字符，其必须包含所有的相同字符
     * 使用 start、end 记录每个子串的开始和结束位置，遍历字符串，不停的用当前字符的最后出现位置来更新 end
     * 只有当 i == end 时，表示当前子串包含了所有已出现过的字符，即之后的子串里不会有之前出现过的字符了，此时就是断开的位置
     */
    public static List<Integer> partitionLabels(String S) {
        int[] map = new int[26];
        for (int i = 0; i < S.length(); i++) {
            int cur = S.charAt(i) - 'a';
            map[cur] = Math.max(map[cur], i);
        }

        List<Integer> result = new LinkedList<>();
        int start = 0, end = 0;
        // 寻找每一个断开的位置
        for (int i = 0; i < S.length(); i++) {
            end = Math.max(end, map[S.charAt(i) - 'a']);
            if (end == i) {
                result.add(i - start + 1);
                start = i + 1;
            }
        }
        return result;
    }
}
