package com.wz.string;

/**
 * You are given two strings s1 and s2 of equal length consisting of letters "x" and "y" only.
 * Your task is to make these two strings equal to each other. You can swap any two characters that belong to different strings, which means: swap s1[i] and s2[j].
 * Return the minimum number of swaps required to make s1 and s2 equal, or return -1 if it is impossible to do so.
 *
 * Example 1:
 * Input: s1 = "xx", s2 = "yy"
 * Output: 1
 * Explanation:
 * Swap s1[0] and s2[1], s1 = "yx", s2 = "yx".
 *
 * Example 2:
 * Input: s1 = "xy", s2 = "yx"
 * Output: 2
 * Explanation:
 * Swap s1[0] and s2[0], s1 = "yy", s2 = "xx".
 * Swap s1[0] and s2[1], s1 = "xy", s2 = "xy".
 * Note that you can't swap s1[0] and s1[1] to make s1 equal to "yx", cause we can only swap chars in different strings.
 *
 * Example 3:
 * Input: s1 = "xx", s2 = "xy"
 * Output: -1
 *
 * Example 4:
 * Input: s1 = "xxyyxyxyxx", s2 = "xyyxyxxxyx"
 * Output: 4
 *
 * Constraints:
 * 1. 1 <= s1.length, s2.length <= 1000
 * 2. s1, s2 only contain 'x' or 'y'.
 */
public class MinimumSwapsToMakeStringsEqual {
    public static void main(String[] args) {
        System.out.println(minimumSwap("xxyyxyxyxx", "xyyxyxxxyx"));
    }

    /**
     * 两个字符串不相等时，对应位置要么是 xy (s1 的 i 位置是 x，s2 的 i 位置是 y)，要么是 yx
     * 且剩余字符串中 x 和 y 的数量肯定都是偶数，也就是说 xy 和 yx 的数量和肯定也是偶数
     * 对于两对 xy，可以通过一次交换将其变为相同。对于两对 yx，也是一次交换
     * 但是对于 xy 和 yx 的数量都是奇数的情况(其和仍然是偶数)，需要先将一对 xy 变换为 yx，之后 xy 和 yx 的数量就都变成了偶数
     */
    public static int minimumSwap(String s1, String s2) {
        int xy = 0, yx = 0;
        for (int i = 0; i < s1.length(); ++i) {
            if (s1.charAt(i) == 'x' && s2.charAt(i) == 'y') {
                xy++;
            }
            if (s1.charAt(i) == 'y' && s2.charAt(i) == 'x') {
                yx++;
            }
        }
        if ((xy + yx) % 2 != 0) {
            return -1;
        }
        if (xy % 2 != 0) {
            return (xy + yx) / 2 + 1;
        }
        return (xy + yx) / 2;
    }
}
