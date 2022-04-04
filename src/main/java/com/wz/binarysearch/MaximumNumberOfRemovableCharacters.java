package com.wz.binarysearch;

import java.util.HashSet;
import java.util.Set;

/**
 * You are given two strings s and p where p is a subsequence of s.
 * You are also given a distinct 0-indexed integer array removable containing a subset of indices of s (s is also 0-indexed).
 * You want to choose an integer k (0 <= k <= removable.length) such that, after removing k characters from s using the first k indices in removable,
 * p is still a subsequence of s. More formally, you will mark the character at s[removable[i]] for each 0 <= i < k,
 * then remove all marked characters and check if p is still a subsequence.
 * Return the maximum k you can choose such that p is still a subsequence of s after the removals.
 * A subsequence of a string is a new string generated from the original string with some characters (can be none)
 * deleted without changing the relative order of the remaining characters.
 *
 * Example 1:
 * Input: s = "abcacb", p = "ab", removable = [3,1,0]
 * Output: 2
 * Explanation: After removing the characters at indices 3 and 1, "abcacb" becomes "accb".
 * "ab" is a subsequence of "accb".
 * If we remove the characters at indices 3, 1, and 0, "abcacb" becomes "ccb", and "ab" is no longer a subsequence.
 * Hence, the maximum k is 2.
 *
 * Example 2:
 * Input: s = "abcbddddd", p = "abcd", removable = [3,2,1,4,5,6]
 * Output: 1
 * Explanation: After removing the character at index 3, "abcbddddd" becomes "abcddddd".
 * "abcd" is a subsequence of "abcddddd".
 *
 * Example 3:
 * Input: s = "abcab", p = "abc", removable = [0,1,2,3,4]
 * Output: 0
 * Explanation: If you remove the first index in the array removable, "abc" is no longer a subsequence.
 *
 * Constraints:
 * 1. 1 <= p.length <= s.length <= 10^5
 * 2. 0 <= removable.length < s.length
 * 3. 0 <= removable[i] < s.length
 * 4. p is a subsequence of s.
 * 5. s and p both consist of lowercase English letters.
 * 6. The elements in removable are distinct.
 */
public class MaximumNumberOfRemovableCharacters {
    public static void main(String[] args) {
        System.out.println(maximumRemovals("abcacb", "ab", new int[]{3, 1, 0}));
        System.out.println(maximumRemovals("abcab", "abc", new int[]{0, 1, 2, 3, 4}));
    }

    /**
     * 二分查找
     * 最少不能移除，最多将 removable 中指定的元素全部移除，因此移除范围是 [0....removable.length-1]
     * 使用二分查找来求解，对于每个 mid，判断从 s 中移除 removable[0...index] 之后，p 是否是 s 的子序列
     * 若是，则说明符合要求，将 left 赋值为 mid+1 尝试移除更多元素，同时记录当前结果
     * 否不是，则说明移除的太多，将 right 赋值为 mid-1
     */
    public static int maximumRemovals(String s, String p, int[] removable) {
        int left = 0, right = removable.length - 1, result = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (valid(s, p, removable, mid)) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return result + 1;
    }

    private static boolean valid(String s, String p, int[] removable, int index) {
        Set<Integer> set = new HashSet<>(index);
        for (int i = 0; i <= index; i++) {
            set.add(removable[i]);
        }
        int i = 0, j = 0;
        while (i < s.length() && j < p.length()) {
            if (set.contains(i)) {
                i++;
                continue;
            }
            if (s.charAt(i) == p.charAt(j)) {
                j++;
            }
            i++;
        }
        return j == p.length();
    }
}
