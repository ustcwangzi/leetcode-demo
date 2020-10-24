package com.wz.string;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Given two strings A and B of lowercase letters, return true if you can swap two letters in A so the result is equal to B, otherwise, return false.
 * Swapping letters is defined as taking two indices i and j (0-indexed) such that i != j and swapping the characters at A[i] and A[j].
 * For example, swapping at indices 0 and 2 in "abcd" results in "cbad".
 *
 * Example 1:
 * Input: A = "ab", B = "ba"
 * Output: true
 * Explanation: You can swap A[0] = 'a' and A[1] = 'b' to get "ba", which is equal to B.
 *
 * Example 2:
 * Input: A = "ab", B = "ab"
 * Output: false
 * Explanation: The only letters you can swap are A[0] = 'a' and A[1] = 'b', which results in "ba" != B.
 *
 * Example 3:
 * Input: A = "aa", B = "aa"
 * Output: true
 * Explanation: You can swap A[0] = 'a' and A[1] = 'a' to get "aa", which is equal to B.
 *
 * Constraints:
 * 1. 0 <= A.length <= 20000
 * 2. 0 <= B.length <= 20000
 * 3. A and B consist of lowercase letters.
 */
public class BuddyStrings {
    public static void main(String[] args) {
        System.out.println(buddyStrings("ab", "ba"));
        System.out.println(buddyStrings("ab", "ab"));
        System.out.println(buddyStrings("aa", "aa"));
    }

    /**
     * 首先字符串 A 和 B 长度必须要相等，不相等的话直接返回 false
     * 如果 A 和 B 就完全相等，那么只有当 A 中有重复字符出现的时候，才能返回 true，用 Set 来检查 A 中是否存在重复字符
     * 对于 A 和 B 长度相等，但是字符串本身不相等的一般情况，用 diff 数组记录出所有对应字符不相同的位置，
     * 最终判断 diff 数组的长度是否为2，且判断交换位置后是否跟 B 中对应的位置上的字符相同即可
     */
    public static boolean buddyStrings(String A, String B) {
        if (A.length() != B.length()) {
            return false;
        }
        if (A.equals(B)) {
            Set<Character> set = new HashSet<>(A.length());
            char[] array = A.toCharArray();
            for (char cur : array) {
                set.add(cur);
            }
            return set.size() != array.length;
        }

        List<Integer> diff = new LinkedList<>();
        for (int i = 0; i < A.length(); ++i) {
            if (A.charAt(i) != B.charAt(i)) {
                diff.add(i);
            }
        }
        return diff.size() == 2 && A.charAt(diff.get(0)) == B.charAt(diff.get(1)) && A.charAt(diff.get(1)) == B.charAt(diff.get(0));
    }
}
