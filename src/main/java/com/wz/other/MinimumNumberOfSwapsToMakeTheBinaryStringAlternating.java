package com.wz.other;

/**
 * Given a binary string s, return the minimum number of character swaps to make it alternating, or -1 if it is impossible.
 * The string is called alternating if no two adjacent characters are equal. For example, the strings "010" and "1010" are alternating, while the string "0100" is not.
 * Any two characters may be swapped, even if they are not adjacent.
 *
 * Example 1:
 * Input: s = "111000"
 * Output: 1
 * Explanation: Swap positions 1 and 4: "111000" -> "101010"
 * The string is now alternating.
 *
 * Example 2:
 * Input: s = "010"
 * Output: 0
 * Explanation: The string is already alternating, no swaps are needed.
 *
 * Example 3:
 * Input: s = "1110"
 * Output: -1
 *
 * Constraints:
 * 1. 1 <= s.length <= 1000
 * 2. s[i] is either '0' or '1'.
 */
public class MinimumNumberOfSwapsToMakeTheBinaryStringAlternating {
    public static void main(String[] args) {
        System.out.println(minSwaps("111000"));
        System.out.println(minSwaps("010"));
    }

    /**
     * 当字符串中 0 和 1 个数相差不超过 1 时才可以通过交换得到目标字符串，因此需要统计 0、1 的个数
     * 对于 0、1 个数确定的字符串，它的目标字符串是确定的：
     * 如果个数相等，则哪个作为首字符都可以
     * 如果个数不相等，则个数多的那个字符必定是首字符，另一种字符插空填入
     * 统计 0、1 个数时，分别记录以 0 开始 和 以 1 开始情况下，需要交换的次数
     */
    public static int minSwaps(String s) {
        // 0、1 字符的个数
        int zeroCount = 0, oneCount = 0;
        // 以 0 开始、以 1 开始需要交换的次数
        int zeroStart = 0, oneStart = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                zeroCount++;
                // 以 1 开始时，要求偶数位置是 1，此时是 0，需要交换
                if (i % 2 == 0) {
                    oneStart++;
                }
            } else {
                oneCount++;
                if (i % 2 == 0) {
                    zeroStart++;
                }
            }
        }
        if (Math.abs(zeroCount - oneCount) > 1) {
            return -1;
        }

        if (zeroCount == oneCount) {
            return Math.min(zeroStart, oneStart);
        }
        // 只能以个数多的作为首字符
        return zeroCount > oneCount ? zeroStart : oneStart;
    }
}
