package com.wz.other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Given a string s of lowercase letters, you need to find the maximum number of non-empty substrings of s that meet the following conditions:
 * - The substrings do not overlap, that is for any two substrings s[i..j] and s[x..y], either j < x or i > y is true.
 * - A substring that contains a certain character c must also contain all occurrences of c.
 * Find the maximum number of substrings that meet the above conditions. If there are multiple solutions with the same number of substrings,
 * return the one with minimum total length. It can be shown that there exists a unique solution of minimum total length.
 * Notice that you can return the substrings in any order.
 *
 * Example 1:
 * Input: s = "adefaddaccc"
 * Output: ["e","f","ccc"]
 * Explanation: The following are all the possible substrings that meet the conditions:
 * [
 *   "adefaddaccc"
 *   "adefadda",
 *   "ef",
 *   "e",
 *   "f",
 *   "ccc",
 * ]
 * If we choose the first string, we cannot choose anything else and we'd get only 1. If we choose "adefadda",
 * we are left with "ccc" which is the only one that doesn't overlap, thus obtaining 2 substrings.
 * Notice also, that it's not optimal to choose "ef" since it can be split into two.
 * Therefore, the optimal way is to choose ["e","f","ccc"] which gives us 3 substrings. No other solution of the same number of substrings exist.
 *
 * Example 2:
 * Input: s = "abbaccd"
 * Output: ["d","bb","cc"]
 * Explanation: Notice that while the set of substrings ["d","abba","cc"] also has length 3, it's considered incorrect since it has larger total length.
 *
 * Constraints:
 * 1. 1 <= s.length <= 10^5
 * 2. s contains only lowercase English letters.
 */
public class MaximumNumberOfNonOverlappingSubstrings {
    public static void main(String[] args) {
        System.out.println(maxNumOfSubstrings("adefaddaccc"));
        System.out.println(maxNumOfSubstrings("abbaccd"));
        System.out.println(maxNumOfSubstrings("abab"));
    }

    /**
     * 第一步、找到每个字符的第一次出现和最后一次出现的位置，存入 left[]、right[]
     * 第二步、若当前字符区间内包含其他字符，需要对该区间进行扩充，保证其他字符结束位置被包含进来，
     *        如 abab，left['a']=0, right['a']=2, left['b']=1, right['b']=3, 合并之后 a、b 均扩大到 [0, 3]
     * 第三步、对合并后的区间，按照结束位置进行排序，然后依次加入到结果集中，注意需要检查不能和上一个区间重叠
     */
    public static List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[] left = new int[26], right = new int[26];
        Arrays.fill(left, n);
        Arrays.fill(right, -1);
        for (int i = 0; i < n; i++) {
            int cur = s.charAt(i) - 'a';
            left[cur] = Math.min(left[cur], i);
            right[cur] = Math.max(right[cur], i);
        }

        List<int[]> interval = mergeInterval(s, left, right);
        interval.sort(Comparator.comparingInt(o -> o[1]));
        List<String> result = new ArrayList<>();
        int end = -1;
        for (int[] range : interval) {
            // 检查是否重叠
            if (range[0] > end) {
                result.add(s.substring(range[0], range[1] + 1));
                end = range[1];
            }
        }
        return result;
    }

    private static List<int[]> mergeInterval(String s, int[] left, int[] right) {
        List<int[]> result = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            if (left[i] == s.length()) {
                continue;
            }
            int start = left[i], end = right[i];
            boolean valid = true;
            for (int j = start; j <= end; j++) {
                int cur = s.charAt(j) - 'a';
                if (left[cur] < start) {
                    valid = false;
                    break;
                }
                end = Math.max(end, right[cur]);
            }
            if (valid) {
                result.add(new int[]{start, end});
            }
        }
        return result;
    }
}
