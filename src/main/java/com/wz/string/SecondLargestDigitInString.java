package com.wz.string;

/**
 * Given an alphanumeric string s, return the second largest numerical digit that appears in s, or -1 if it does not exist.
 * An alphanumeric string is a string consisting of lowercase English letters and digits.
 *
 * Example 1:
 * Input: s = "dfa12321afd"
 * Output: 2
 * Explanation: The digits that appear in s are [1, 2, 3]. The second largest digit is 2.
 *
 * Example 2:
 * Input: s = "abc1111"
 * Output: -1
 * Explanation: The digits that appear in s are [1]. There is no second largest digit.
 *
 * Constraints:
 * 1. 1 <= s.length <= 500
 * 2. s consists of only lowercase English letters and/or digits.
 */
public class SecondLargestDigitInString {
    public static void main(String[] args) {
        System.out.println(secondHighest("abc8344"));
        System.out.println(secondHighest("abc1111"));
    }

    /**
     * 遍历字符串，记录最大值和次大值，若当前值等于最大值，则跳过；
     * 若当前值大于最大值，则更新最大值，同时将上一次的最大值赋值给次大值；
     * 若当前值小于最大值但大于次大值，则更新次大值。最后返回次大值即可
     */
    public static int secondHighest(String s) {
        int max = -1, second = -1;
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) {
                continue;
            }

            int cur = s.charAt(i) - '0';
            if (cur == max) {
                continue;
            }
            if (cur > max) {
                second = max;
                max = cur;
            } else if (cur > second) {
                second = cur;
            }
        }
        return second;
    }
}
