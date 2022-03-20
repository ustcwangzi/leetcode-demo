package com.wz.greedy;

/**
 * You are given a 0-indexed string text and another 0-indexed string pattern of length 2, both of which consist of only lowercase English letters.
 * You can add either pattern[0] or pattern[1] anywhere in text exactly once. Note that the character can be added even at the beginning or at the end of text.
 * Return the maximum number of times pattern can occur as a subsequence of the modified text.
 * A subsequence is a string that can be derived from another string by deleting some or no characters without changing the order of the remaining characters.
 *
 * Example 1:
 * Input: text = "abdcdbc", pattern = "ac"
 * Output: 4
 * Explanation:
 * If we add pattern[0] = 'a' in between text[1] and text[2], we get "abadcdbc". Now, the number of times "ac" occurs as a subsequence is 4.
 * Some other strings which have 4 subsequences "ac" after adding a character to text are "aabdcdbc" and "abdacdbc".
 * However, strings such as "abdcadbc", "abdccdbc", and "abdcdbcc", although obtainable, have only 3 subsequences "ac" and are thus suboptimal.
 * It can be shown that it is not possible to get more than 4 subsequences "ac" by adding only one character.
 *
 * Example 2:
 * Input: text = "aabb", pattern = "ab"
 * Output: 6
 * Explanation:
 * Some of the strings which can be obtained from text and have 6 subsequences "ab" are "aaabb", "aaabb", and "aabbb".
 *
 * Constraints:
 * 1. 1 <= text.length <= 10^5
 * 2. pattern.length == 2
 * 3. text and pattern consist only of lowercase English letters.
 */
public class MaximizeNumberOfSubsequencesInString {
    public static void main(String[] args) {
        System.out.println(maximumSubsequenceCount("abdcdbc", "ac"));
        System.out.println(maximumSubsequenceCount("aabb", "ab"));
    }

    /**
     * 最大的结果一定是将 pattern[0] 加入起始位置 或 将 pattern[1] 加入结束位置
     * count0 统计 pattern[0] 出现次数、count1 统计 pattern[1] 出现次数
     * 若当前字符等于 pattern[1]，那么可以和之前出现的 pattern[0] 组合，因此可以将 count0 累加到结果中
     * 若当前字符等于 pattern[0]，直接将 count0++
     * 注意此时的 result 还没有将 pattern 加入到 text 中，只是 text 中原有的符合条件的个数
     * 最后，根据 count0、count1 的大小选择将 pattern[0] 或 pattern[1] 加入到 text 中
     */
    public static long maximumSubsequenceCount(String text, String pattern) {
        long result = 0, count0 = 0, count1 = 0;
        for (char cur : text.toCharArray()) {
            if (cur == pattern.charAt(1)) {
                // text 中本来就存在的符合条件的个数
                result += count0;
                count1++;
            }
            if (cur == pattern.charAt(0)) {
                count0++;
            }
        }
        // 选择将 pattern[0] 加入起始 或 pattern[1] 加入结束
        return result + Math.max(count0, count1);
    }
}
