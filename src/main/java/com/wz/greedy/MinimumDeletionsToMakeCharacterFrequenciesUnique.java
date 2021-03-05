package com.wz.greedy;

import java.util.HashSet;
import java.util.Set;

/**
 * A string s is called good if there are no two different characters in s that have the same frequency.
 * Given a string s, return the minimum number of characters you need to delete to make s good.
 * The frequency of a character in a string is the number of times it appears in the string.
 * For example, in the string "aab", the frequency of 'a' is 2, while the frequency of 'b' is 1.
 *
 * Example 1:
 * Input: s = "aab"
 * Output: 0
 * Explanation: s is already good.
 *
 * Example 2:
 * Input: s = "aaabbbcc"
 * Output: 2
 * Explanation: You can delete two 'b's resulting in the good string "aaabcc".
 * Another way it to delete one 'b' and one 'c' resulting in the good string "aaabbc".
 *
 * Constraints:
 * 1. 1 <= s.length <= 10^5
 * 2. s contains only lowercase English letters.
 */
public class MinimumDeletionsToMakeCharacterFrequenciesUnique {
    public static void main(String[] args) {
        System.out.println(minDeletions("aab"));
        System.out.println(minDeletions("aaabbbcc"));
    }

    /**
     * 首先统计各个字母的出现次数存入数组 frequency，然后遍历数组，如果出现次数为 0，直接跳过
     * 否则使用 HashSet 进行去重，如果加进来的数目已存在，就进行自减，减到 HashSet 中没有的数目
     */
    public static int minDeletions(String s) {
        char[] array = s.toCharArray();
        int[] frequency = new int[26];
        for (char cur : array) {
            frequency[cur - 'a']++;
        }

        int result = 0;
        Set<Integer> set = new HashSet<>();
        for (int count : frequency) {
            if (count == 0) {
                continue;
            }
            while (set.contains(count)) {
                count--;
                result++;
            }
            if (count != 0) {
                set.add(count);
            }
        }
        return result;
    }
}
