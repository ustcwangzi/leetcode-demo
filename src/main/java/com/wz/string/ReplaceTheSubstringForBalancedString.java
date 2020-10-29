package com.wz.string;

/**
 * You are given a string containing only 4 kinds of characters 'Q', 'W', 'E' and 'R'.
 * A string is said to be balanced if each of its characters appears n/4 times where n is the length of the string.
 * Return the minimum length of the substring that can be replaced with any other string of the same length to make the original string s balanced.
 * Return 0 if the string is already balanced.
 *
 * Example 1:
 * Input: s = "QWER"
 * Output: 0
 * Explanation: s is already balanced.
 *
 * Example 2:
 * Input: s = "QQWE"
 * Output: 1
 * Explanation: We need to replace a 'Q' to 'R', so that "RQWE" (or "QRWE") is balanced.
 *
 * Example 3:
 * Input: s = "QQQW"
 * Output: 2
 * Explanation: We can replace the first "QQ" to "ER".
 *
 * Example 4:
 * Input: s = "QQQQ"
 * Output: 3
 * Explanation: We can replace the last 3 'Q' to make s = "QWER".
 *
 * Constraints:
 * 1. 1 <= s.length <= 10^5
 * 2. s.length is a multiple of 4
 * 3. s contains only 'Q', 'W', 'E' and 'R'.
 */
public class ReplaceTheSubstringForBalancedString {
    public static void main(String[] args) {
        System.out.println(balancedString("QQQW"));
    }

    /**
     * 滑动窗口
     * 注意题目要求的是通过转换得到答案的最小子串的长度，而不是要修改的次数
     * 最终每个字符出现的次数肯定都是 n/4，记作 target，用 left、right 表示窗口左右边界，窗口里面就是要替换的字符串
     * 由此可知，窗口外面的每种字符的数量必须小于等于 target，否则无论怎么替换，外面都有字符超过数量，也就是不能满足要求
     * 因此要做的就是在保证外面的字符符合要求的前提下，使得窗口尽量小
     * 初始时 left、right 在最左边，滑动 right，在满足条件时不断缩小 left，同时记录结果，直到 right 不能移动为止
     */
    public static int balancedString(String s) {
        int n = s.length();
        int[] count = new int[128];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i)]++;
        }

        int target = n / 4, left = 0, result = n;
        for (int right = 0; right < n; right++) {
            count[s.charAt(right)]--;
            // 在满足要求，即窗口外的字符个数都小于等于 target 的情况下，不断缩小 left
            while (left < n && count['Q'] <= target && count['W'] <= target && count['E'] <= target && count['R'] <= target) {
                result = Math.min(result, right - left + 1);
                count[s.charAt(left++)]++;
            }
        }
        return result;
    }
}
