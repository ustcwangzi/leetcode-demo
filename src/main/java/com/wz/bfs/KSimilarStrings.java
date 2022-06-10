package com.wz.bfs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Strings s1 and s2 are k-similar (for some non-negative integer k) if we can swap the positions of two letters in s1 exactly k times so that the resulting string equals s2.
 * Given two anagrams s1 and s2, return the smallest k for which s1 and s2 are k-similar.
 *
 * Example 1:
 * Input: s1 = "ab", s2 = "ba"
 * Output: 1
 *
 * Example 2:
 * Input: s1 = "abc", s2 = "bca"
 * Output: 2
 *
 * Constraints:
 * 1. 1 <= s1.length <= 20
 * 2. s2.length == s1.length
 * 3. s1 and s2 contain only lowercase letters from the set {'a', 'b', 'c', 'd', 'e', 'f'}.
 * 4. s2 is an anagram of s1.
 */
public class KSimilarStrings {
    public static void main(String[] args) {
        System.out.println(kSimilarity("ab", "ba"));
    }

    /**
     * BFS，每个 level 去搜，比较字符串，找到第一个不同位置，ab、ba,第一个 a 必须换成 b，那么就在 s1 里面找所有的 b，生成下一层的字符串
     */
    public static int kSimilarity(String s1, String s2) {
        HashSet<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        visited.add(s1);
        queue.add(s1);

        int level = 0;
        while (queue.size() != 0) {
            int size = queue.size();
            while (size-- > 0) {
                String cur = queue.remove();
                if (cur.equals(s2)) {
                    return level;
                }

                int i = 0;
                while (cur.charAt(i) == s2.charAt(i)) {
                    i++;
                }

                for (int j = i + 1; j < cur.length(); j++) {
                    if (cur.charAt(i) == s2.charAt(j) && cur.charAt(j) != s2.charAt(j)) {
                        String next = swap(cur, i, j);
                        if (visited.add(next)) {
                            queue.offer(next);
                        }
                    }
                }
            }
            level++;
        }
        return -1;
    }

    public static String swap(String str, int i, int j) {
        char[] array = str.toCharArray();
        char temp = array[i];
        array[i] = array[j];
        array[j] = temp;
        return new String(array);
    }
}
