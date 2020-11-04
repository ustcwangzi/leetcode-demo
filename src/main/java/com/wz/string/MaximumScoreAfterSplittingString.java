package com.wz.string;

/**
 * Given a string s of zeros and ones, return the maximum score after splitting the string into two non-empty substrings
 * (i.e. left substring and right substring).
 * The score after splitting a string is the number of zeros in the left substring plus the number of ones in the right substring.
 *
 * Example 1:
 * Input: s = "011101"
 * Output: 5
 * Explanation:
 * All possible ways of splitting s into two non-empty substrings are:
 * left = "0" and right = "11101", score = 1 + 4 = 5
 * left = "01" and right = "1101", score = 1 + 3 = 4
 * left = "011" and right = "101", score = 1 + 2 = 3
 * left = "0111" and right = "01", score = 1 + 1 = 2
 * left = "01110" and right = "1", score = 2 + 1 = 3
 *
 * Example 2:
 * Input: s = "1111"
 * Output: 3
 *
 * Constraints:
 * 1. 2 <= s.length <= 500
 * 2. The string s consists of characters '0' and '1' only.
 */
public class MaximumScoreAfterSplittingString {
    public static void main(String[] args) {
        System.out.println(maxScore("00111"));
        System.out.println(maxScore("00"));
    }

    /**
     * 先统计所有 1 的个数，然后遍历 s，逐个位置作为且分点，计算得分
     */
    public static int maxScore(String s) {
        int leftSum = 0, rightSum = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                rightSum++;
            }
        }

        int result = 0;
        // 注意这里 i 不能取到最后一个位置，不然会导致右半边为空
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == '0') {
                leftSum++;
            } else {
                rightSum--;
            }

            result = Math.max(result, leftSum + rightSum);
        }

        return result;
    }
}
