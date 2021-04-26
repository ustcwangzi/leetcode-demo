package com.wz.string;

/**
 * Given two string arrays word1 and word2, return true if the two arrays represent the same string, and false otherwise.
 * A string is represented by an array if the array elements concatenated in order forms the string.
 *
 * Example 1:
 * Input: word1 = ["ab", "c"], word2 = ["a", "bc"]
 * Output: true
 * Explanation:
 * word1 represents string "ab" + "c" -> "abc"
 * word2 represents string "a" + "bc" -> "abc"
 * The strings are the same, so return true.
 *
 * Example 2:
 * Input: word1 = ["a", "cb"], word2 = ["ab", "c"]
 * Output: false
 *
 * Constraints:
 * 1. 1 <= word1.length, word2.length <= 103
 * 2. 1 <= word1[i].length, word2[i].length <= 103
 * 3. 1 <= sum(word1[i].length), sum(word2[i].length) <= 103
 * 4. word1[i] and word2[i] consist of lowercase letters.
 */
public class CheckIfTwoStringArraysAreEquivalent {
    public static void main(String[] args) {
        System.out.println(arrayStringsAreEqual(new String[]{"ab", "c"}, new String[]{"a", "bc"}));
        System.out.println(arrayStringsAreEqual(new String[]{"a", "cb"}, new String[]{"ab", "c"}));
    }

    /**
     * 将数组转换成字符串之后，比较是否相等即可
     */
    public static boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        StringBuilder builder1 = new StringBuilder();
        for (String str : word1) {
            builder1.append(str);
        }
        StringBuilder builder2 = new StringBuilder();
        for (String str : word2) {
            builder2.append(str);
        }
        return builder1.toString().equals(builder2.toString());
    }
}
