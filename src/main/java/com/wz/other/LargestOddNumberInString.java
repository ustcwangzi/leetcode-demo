package com.wz.other;

/**
 * You are given a string num, representing a large integer. Return the largest-valued odd integer (as a string)
 * that is a non-empty substring of num, or an empty string "" if no odd integer exists.
 * A substring is a contiguous sequence of characters within a string.
 *
 * Example 1:
 * Input: num = "52"
 * Output: "5"
 * Explanation: The only non-empty substrings are "5", "2", and "52". "5" is the only odd number.
 *
 * Example 2:
 * Input: num = "4206"
 * Output: ""
 * Explanation: There are no odd numbers in "4206".
 *
 * Constraints:
 * 1. 1 <= num.length <= 105
 * 2. num only consists of digits and does not contain any leading zeros.
 */
public class LargestOddNumberInString {
    public static void main(String[] args) {
        System.out.println(largestOddNumber("52"));
        System.out.println(largestOddNumber("4206"));
    }

    /**
     * 以奇数结尾的数肯定是奇数，因此逆序遍历字符串，找到第一个奇数的位置即可
     */
    public static String largestOddNumber(String num) {
        for (int i = num.length() - 1; i >= 0; i--) {
            if ((num.charAt(i) - '0') % 2 == 1) {
                return num.substring(0, i + 1);
            }
        }
        return "";
    }
}
