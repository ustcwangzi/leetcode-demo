package com.wz.greedy;

/**
 * Given two integers a and b, return any string s such that:
 * 1. s has length a + b and contains exactly a 'a' letters, and exactly b 'b' letters,
 * 2. The substring 'aaa' does not occur in s, and
 * 3. The substring 'bbb' does not occur in s.
 *
 * Example 1:
 * Input: a = 1, b = 2
 * Output: "abb"
 * Explanation: "abb", "bab" and "bba" are all correct answers.
 *
 * Example 2:
 * Input: a = 4, b = 1
 * Output: "aabaa"
 *
 * Constraints:
 * 1. 0 <= a, b <= 100
 * 2. It is guaranteed such an s exists for the given a and b.
 */
public class StringWithoutAAAOrBBB {
    public static void main(String[] args) {
        System.out.println(strWithout3a3b(1, 2));
        System.out.println(strWithout3a3b(4, 1));
    }

    /**
     * 如果字符串末尾连续出现相同字符，若出现的是两个 'a'，则需要添加 'b'，否则需要添加 'a'
     * 如果没有连续出现相同字符，则需要判断当前 a 和 b 的大小，a >= b，则添加 'a'，否则添加 'b'
     */
    public static String strWithout3a3b(int a, int b) {
        StringBuilder builder = new StringBuilder("");
        while (a > 0 || b > 0) {
            boolean insertA;
            int size = builder.length();
            if (size >= 2 && builder.charAt(size - 1) == builder.charAt(size - 2)) {
                insertA = builder.charAt(size - 1) == 'b';
            } else {
                insertA = a >= b;
            }

            if (insertA) {
                builder.append('a');
                a--;
            } else {
                builder.append('b');
                b--;
            }
        }
        return builder.toString();
    }
}
