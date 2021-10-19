package com.wz.other;

/**
 * You are given a very large integer n, represented as a string, and an integer digit x.
 * The digits in n and the digit x are in the inclusive range [1, 9], and n may represent a negative number.
 * You want to maximize n's numerical value by inserting x anywhere in the decimal representation of n. You cannot insert x to the left of the negative sign.
 * 1. For example, if n = 73 and x = 6, it would be best to insert it between 7 and 3, making n = 763.
 * 2. If n = -55 and x = 2, it would be best to insert it before the first 5, making n = -255.
 * Return a string representing the maximum value of n after the insertion.
 *
 * Example 1:
 * Input: n = "99", x = 9
 * Output: "999"
 * Explanation: The result is the same regardless of where you insert 9.
 *
 * Example 2:
 * Input: n = "-13", x = 2
 * Output: "-123"
 * Explanation: You can make n one of {-213, -123, -132}, and the largest of those three is -123.
 *
 * Constraints:
 * 1. 1 <= n.length <= 10^5
 * 2. 1 <= x <= 9
 * 3. The digits in n are in the range [1, 9].
 * 4. n is a valid representation of an integer.
 * 5. In the case of a negative n, it will begin with '-'.
 */
public class MaximumValueAfterInsertion {
    public static void main(String[] args) {
        System.out.println(maxValue("132", 3));
        System.out.println(maxValue("-132", 3));
    }

    /**
     * 遍历字符串
     * 如果是正数，找到第一个小于 x 的位置进行插入
     * 如果是负数，找到第一个大于 x 的位置进行插入
     */
    public static String maxValue(String n, int x) {
        char ch = (char) (x + '0');
        int i = 0;
        if (n.charAt(0) != '-') {
            while (i < n.length() && n.charAt(i) >= ch) {
                i++;
            }
        } else {
            while (i < n.length() && n.charAt(i) <= ch) {
                i++;
            }
        }
        if (i == n.length()) {
            return n + x;
        }
        return n.substring(0, i) + x + n.substring(i);
    }
}
