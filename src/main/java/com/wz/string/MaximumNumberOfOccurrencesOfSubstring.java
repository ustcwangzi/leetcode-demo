package com.wz.string;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string s, return the maximum number of ocurrences of any substring under the following rules:
 * The number of unique characters in the substring must be less than or equal to maxLetters.
 * The substring size must be between minSize and maxSize inclusive.
 *
 * Example 1:
 * Input: s = "aababcaab", maxLetters = 2, minSize = 3, maxSize = 4
 * Output: 2
 * Explanation: Substring "aab" has 2 ocurrences in the original string.
 * It satisfies the conditions, 2 unique letters and size 3 (between minSize and maxSize).
 *
 * Example 2:
 * Input: s = "aaaa", maxLetters = 1, minSize = 3, maxSize = 3
 * Output: 2
 * Explanation: Substring "aaa" occur 2 times in the string. It can overlap.
 *
 * Example 3:
 * Input: s = "abcde", maxLetters = 2, minSize = 3, maxSize = 3
 * Output: 0
 *
 * Constraints:
 * 1. 1 <= s.length <= 10^5
 * 2. 1 <= maxLetters <= 26
 * 3. 1 <= minSize <= maxSize <= min(26, s.length)
 * 4. s only contains lowercase English letters.
 */
public class MaximumNumberOfOccurrencesOfSubstring {
    public static void main(String[] args) {
        System.out.println(maxFreq("aababcaab", 2, 3, 4));
    }

    /**
     * maxSize 用不到，只需要找长度等于 minSize 的符合条件的子串
     * 因为对于任何一个长度大于 minSize 符合条件的子串，都可以从中构造出一个长度等于 minSize 且符合条件的子串
     * 枚举所有长度等于 minSize 的子串，将其放入哈希表中，然后找到一个出现次数最多，且满足不同字母要求的子串
     */
    public static int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
        int result = 0;
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i + minSize <= s.length(); i++) {
            String str = s.substring(i, i + minSize);
            // 满足字母个数要求，统计出现次数
            if (countUnique(str) <= maxLetters) {
                int count = map.getOrDefault(str, 0) + 1;
                map.put(str, count);
                result = Math.max(count, result);
            }
        }
        return result;
    }

    private static int countUnique(String str) {
        boolean[] visited = new boolean[26];
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            char cur = str.charAt(i);
            if (!visited[cur - 'a']) {
                count++;
                visited[cur - 'a'] = true;
            }
        }
        return count;
    }
}
