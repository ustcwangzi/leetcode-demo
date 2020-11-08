package com.wz.string;

/**
 * You are given a string s, a split is called good if you can split s into 2 non-empty strings p and q
 * where its concatenation is equal to s and the number of distinct letters in p and q are the same.
 * Return the number of good splits you can make in s.
 *
 * Example 1:
 * Input: s = "aacaba"
 * Output: 2
 * Explanation: There are 5 ways to split "aacaba" and 2 of them are good.
 * ("a", "acaba") Left string and right string contains 1 and 3 different letters respectively.
 * ("aa", "caba") Left string and right string contains 1 and 3 different letters respectively.
 * ("aac", "aba") Left string and right string contains 2 and 2 different letters respectively (good split).
 * ("aaca", "ba") Left string and right string contains 2 and 2 different letters respectively (good split).
 * ("aacab", "a") Left string and right string contains 3 and 1 different letters respectively.
 *
 * Example 2:
 * Input: s = "abcd"
 * Output: 1
 * Explanation: Split the string as follows ("ab", "cd").
 *
 * Constraints:
 * 1. s contains only lowercase English letters.
 * 2. 1 <= s.length <= 10^5
 */
public class NumberOfGoodWaysToSplitString {
    public static void main(String[] args) {
        System.out.println(numSplits("aacaba"));
    }

    /**
     * 使用2个数组分别统计左右两部分中每种字符的个数，首先统计出右子串中所有字符的个数
     * 接下来开始从首元素向后循环分割位置，指针每向后移动一位，代表当前字符会从右子串移动至左子串，
     * 因此，该字符在右侧中的个数减一，同时在左侧中的个数加一。个数更新后，查看两个部分的字符种类数是否相同，如果相同，返回结果加一
     */
    public static int numSplits(String s) {
        int[] left = new int[26], right = new int[26];
        int leftDistinct = 0, rightDistinct = 0;
        int result = 0;
        char[] array = s.toCharArray();

        for (char cur : array) {
            right[cur - 'a']++;
            if (right[cur - 'a'] == 1) {
                rightDistinct++;
            }
        }

        for (char cur : array) {
            left[cur - 'a']++;
            if (left[cur - 'a'] == 1) {
                leftDistinct++;
            }

            right[cur - 'a']--;
            if (right[cur - 'a'] == 0) {
                rightDistinct--;
            }
            if (leftDistinct == rightDistinct) {
                result++;
            }
        }
        return result;
    }
}
