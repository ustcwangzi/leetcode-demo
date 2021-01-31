package com.wz.backtracking;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a string s, return the maximum number of unique substrings that the given string can be split into.
 * You can split string s into any list of non-empty substrings, where the concatenation of the substrings
 * forms the original string. However, you must split the substrings such that all of them are unique.
 * A substring is a contiguous sequence of characters within a string.
 *
 * Example 1:
 * Input: s = "ababccc"
 * Output: 5
 * Explanation: One way to split maximally is ['a', 'b', 'ab', 'c', 'cc'].
 * Splitting like ['a', 'b', 'a', 'b', 'c', 'cc'] is not valid as you have 'a' and 'b' multiple times.
 *
 * Example 2:
 * Input: s = "aba"
 * Output: 2
 * Explanation: One way to split maximally is ['a', 'ba'].
 *
 * Constraints:
 * 1. 1 <= s.length <= 16
 * 2. s contains only lower case English letters.
 */
public class SplitStringIntoTheMaxNumberOfUniqueSubstrings {
    public static void main(String[] args) {
        System.out.println(maxUniqueSplit("ababccc"));
    }

    /**
     * 与 {@link com.wz.array.Subsets} 类似
     * 每次以 start 为起点、i 为终点进行 DFS 生成字符串，使用 set 记录已经生成的结果
     */
    public static int maxUniqueSplit(String s) {
        int[] result = new int[1];
        dfs(s, 0, new HashSet<>(), result);
        return result[0];
    }

    private static void dfs(String s, int start, Set<String> visited, int[] result) {
        if (start == s.length()) {
            result[0] = Math.max(result[0], visited.size());
            return;
        }
        for (int i = start; i < s.length(); i++) {
            String str = s.substring(start, i + 1);
            if (visited.contains(str)) {
                continue;
            }
            visited.add(str);
            dfs(s, i + 1, visited, result);
            visited.remove(str);
        }
    }
}
