package com.wz.other;

/**
 * You are given a string s consisting of lowercase English letters, and an integer k.
 * First, convert s into an integer by replacing each letter with its position in the alphabet (i.e., replace 'a' with 1, 'b' with 2, ..., 'z' with 26).
 * Then, transform the integer by replacing it with the sum of its digits. Repeat the transform operation k times in total.
 * For example, if s = "zbax" and k = 2, then the resulting integer would be 8 by the following operations:
 * 1. Convert: "zbax" ➝ "(26)(2)(1)(24)" ➝ "262124" ➝ 262124
 * 2. Transform #1: 262124 ➝ 2 + 6 + 2 + 1 + 2 + 4 ➝ 17
 * 3. Transform #2: 17 ➝ 1 + 7 ➝ 8
 * Return the resulting integer after performing the operations described above.
 *
 * Example 1:
 * Input: s = "iiii", k = 1
 * Output: 36
 * Explanation: The operations are as follows:
 * - Convert: "iiii" ➝ "(9)(9)(9)(9)" ➝ "9999" ➝ 9999
 * - Transform #1: 9999 ➝ 9 + 9 + 9 + 9 ➝ 36
 * Thus the resulting integer is 36.
 *
 * Example 2:
 * Input: s = "leetcode", k = 2
 * Output: 6
 * Explanation: The operations are as follows:
 * - Convert: "leetcode" ➝ "(12)(5)(5)(20)(3)(15)(4)(5)" ➝ "12552031545" ➝ 12552031545
 * - Transform #1: 12552031545 ➝ 1 + 2 + 5 + 5 + 2 + 0 + 3 + 1 + 5 + 4 + 5 ➝ 33
 * - Transform #2: 33 ➝ 3 + 3 ➝ 6
 * Thus the resulting integer is 6.
 *
 * Constraints:
 * 1. 1 <= s.length <= 100
 * 2. 1 <= k <= 10
 * 3. s consists of lowercase English letters.
 */
public class SumOfDigitsOfStringAfterConvert {
    public static void main(String[] args) {
        System.out.println(getLucky("zbax", 2));
    }

    /**
     * 先将字符串转换为数字字符串，然后遍历数字字符串累加其中的数字
     */
    public static int getLucky(String s, int k) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            builder.append(s.charAt(i) - 'a' + 1);
        }
        s = builder.toString();
        int sum = 0;
        while (k-- > 0) {
            sum = 0;
            for (int i = 0; i < s.length(); i++) {
                sum += s.charAt(i) - '0';
            }
            s = String.valueOf(sum);
        }
        return sum;
    }
}
