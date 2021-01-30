package com.wz.backtracking;

import java.util.*;

/**
 * Given an array of strings arr. String s is a concatenation of a sub-sequence of arr which have unique characters.
 *
 * Return the maximum possible length of s.
 *
 * Example 1:
 * Input: arr = ["un","iq","ue"]
 * Output: 4
 * Explanation: All possible concatenations are "","un","iq","ue","uniq" and "ique".
 * Maximum length is 4.
 *
 * Example 2:
 * Input: arr = ["cha","r","act","ers"]
 * Output: 6
 * Explanation: Possible solutions are "chaers" and "acters".
 *
 * Constraints:
 * 1. 1 <= arr.length <= 16
 * 2. 1 <= arr[i].length <= 26
 * 3. arr[i] contains only lower case English letters.
 */
public class MaximumLengthOfConcatenatedStringWithUniqueCharacters {
    public static void main(String[] args) {
        List<String> arr = Arrays.asList("un", "iq", "ue");
        System.out.println(maxLength(arr));
    }

    private static int result = 0;

    /**
     * 与 {@link com.wz.array.Subsets} 类似
     * 只是将结果收集变成更新最大长度
     */
    public static int maxLength(List<String> arr) {
        dfs(arr, 0, new ArrayList<>());
        return result;
    }

    private static void dfs(List<String> arr, int start, List<String> path) {
        for (int i = start; i < arr.size(); i++) {
            path.add(arr.get(i));
            if (isUnique(path)) {
                result = Math.max(result, getLength(path));
            }

            dfs(arr, i + 1, path);
            path.remove(path.size() - 1);
        }

    }

    private static boolean isUnique(List<String> path) {
        Set<Character> set = new HashSet<>();
        for (String str : path) {
            for (int i = 0; i < str.length(); i++) {
                if (set.contains(str.charAt(i))) {
                    return false;
                }
                set.add(str.charAt(i));
            }
        }

        return true;
    }

    private static int getLength(List<String> path) {
        int result = 0;
        for (String str : path) {
            result += str.length();
        }
        return result;
    }
}
