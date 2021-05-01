package com.wz.string;

/**
 * The beauty of a string is the difference in frequencies between the most frequent and least frequent characters.
 * For example, the beauty of "abaacc" is 3 - 1 = 2.
 * Given a string s, return the sum of beauty of all of its substrings.
 *
 * Example 1:
 * Input: s = "aabcb"
 * Output: 5
 * Explanation: The substrings with non-zero beauty are ["aab","aabc","aabcb","abcb","bcb"], each with beauty equal to 1.
 *
 * Example 2:
 * Input: s = "aabcbaa"
 * Output: 17
 *
 * Constraints:
 * 1. 1 <= s.length <= 500
 * 2. s consists of only lowercase English letters.
 */
public class SumOfBeautyOfAllSubstrings {
    public static void main(String[] args) {
        System.out.println(beautySum("aabcb"));
        System.out.println(beautySum("aabcbaa"));
        System.out.println(beautySum("xzvfsppsjfbxdwkqe"));
    }

    /**
     * 滑动窗口
     * 固定每个左边界，逐步移动右边界，使用数组统计每个字符的出现次数
     * 对于每个右边界，计算当前最大和最小出现次数，将差值累加到结果中
     */
    public static int beautySum(String s) {
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            // 记录出现次数
            int[] count = new int[26];
            count[s.charAt(i) - 'a']++;

            for (int j = i + 1; j < s.length(); j++) {
                count[s.charAt(j) - 'a']++;

                // 计算最大最小次数
                int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
                for (int val : count) {
                    if (val == 0) {
                        continue;
                    }
                    min = Math.min(min, val);
                    max = Math.max(max, val);
                }

                result += max - min;
            }
        }
        return result;
    }
}
