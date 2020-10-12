package com.wz.string;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Given a list of strings, you need to find the longest uncommon subsequence among them.
 * The longest uncommon subsequence is defined as the longest subsequence of one of these strings and this subsequence
 * should not be any subsequence of the other strings.
 * A subsequence is a sequence that can be derived from one sequence by deleting some characters without changing
 * the order of the remaining elements. Trivially, any string is a subsequence of itself and an empty string is a subsequence of any string.
 * The input will be a list of strings, and the output needs to be the length of the longest uncommon subsequence.
 * If the longest uncommon subsequence doesn't exist, return -1.
 *
 * Example 1:
 * Input: "aba", "cdc", "eae"
 * Output: 3
 *
 * Note:
 * All the given strings' lengths will not exceed 10.
 * The length of the given list will be in the range of [2, 50].
 */
public class LongestUncommonSubsequenceII {
    public static void main(String[] args) {
        System.out.println(findLUSlength(new String[]{"aba", "cdc", "eae"}));
    }

    /**
     * 将字符串按长度来排序，将长度大的放到前面，这样如果找到了非共同子序列，那么直接返回其长度即可，因为当前找到的肯定是最长的
     * 然后用 set 来记录已经遍历过的字符串，在有大量的重复字符串的时候可以提高效率
     * 遍历字符串，对于当前遍历到的字符串，和 set 中的所有字符串相比，看其是否是某个的子序列，如果都不是，说明当前的就是最长的非共同子序列
     */
    public static int findLUSlength(String[] strs) {
        Arrays.parallelSort(strs, (a, b) -> b.length() == a.length() ? a.compareTo(b) : b.length() - a.length());
        Set<String> visited = new HashSet<>();
        for (int i = 0, max = 0; i < strs.length; i++) {
            if ((i > 0 && strs[i].equals(strs[i - 1])) || (i + 1 < strs.length && strs[i].equals(strs[i + 1]))) {
                // duplicate
                visited.add(strs[i]);
                max = Math.max(max, strs[i].length());
            } else {
                // duplicate is empty, or max == 0; it is the longgest but not duplicate, it will be the ans.
                if (strs[i].length() >= max) {
                    return strs[i].length();
                }

                boolean isSubsequence = false;
                for (String s : visited) {
                    if (isSubsequence(strs[i], s)) {
                        isSubsequence = true;
                        break;
                    }
                }
                // not duplicate, and not sub, will be ans.
                if (!isSubsequence) {
                    return strs[i].length();
                }
                visited.add(strs[i]);
            }
        }
        return -1;
    }

    private static boolean isSubsequence(String a, String b) {
        int i = 0;
        for (int j = 0; i < a.length() && j < b.length(); j++) {
            if (a.charAt(i) == b.charAt(j)) {
                i++;
            }
        }
        return i == a.length();
    }
}
