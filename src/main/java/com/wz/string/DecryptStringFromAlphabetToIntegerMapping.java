package com.wz.string;

/**
 * Given a string s formed by digits ('0' - '9') and '#' . We want to map s to English lowercase characters as follows:
 * 1. Characters ('a' to 'i') are represented by ('1' to '9') respectively.
 * 2. Characters ('j' to 'z') are represented by ('10#' to '26#') respectively.
 * Return the string formed after mapping.
 * It's guaranteed that a unique mapping will always exist.
 *
 * Example 1:
 * Input: s = "10#11#12"
 * Output: "jkab"
 * Explanation: "j" -> "10#" , "k" -> "11#" , "a" -> "1" , "b" -> "2".
 *
 * Example 2:
 * Input: s = "1326#"
 * Output: "acz"
 *
 * Example 3:
 * Input: s = "12345678910#11#12#13#14#15#16#17#18#19#20#21#22#23#24#25#26#"
 * Output: "abcdefghijklmnopqrstuvwxyz"
 *
 * Constraints:
 * 1. 1 <= s.length <= 1000
 * 2. s[i] only contains digits letters ('0'-'9') and '#' letter.
 * 3. s will be valid string such that mapping is always possible.
 */
public class DecryptStringFromAlphabetToIntegerMapping {
    public static void main(String[] args) {
        System.out.println(freqAlphabets("1326#"));
    }

    /**
     * 逆序遍历，如果遇到'#'，则将前面的两个数字转为对应的字母，否则将当前数字转为字母
     */
    public static String freqAlphabets(String s) {
        StringBuilder builder = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; ) {
            int index;
            if (s.charAt(i) == '#') {
                index = Integer.parseInt(s.substring(i - 2, i));
                i -= 3;
            } else {
                index = Integer.parseInt(Character.toString(s.charAt(i--)));
            }
            builder.insert(0, (char) ((int) 'a' + index - 1));
        }
        return builder.toString();
    }
}
