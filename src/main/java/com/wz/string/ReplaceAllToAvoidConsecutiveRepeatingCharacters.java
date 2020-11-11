package com.wz.string;

/**
 * Given a string s containing only lower case English letters and the '?' character, convert all the '?' characters
 * into lower case letters such that the final string does not contain any consecutive repeating characters. You cannot modify the non '?' characters.
 * It is guaranteed that there are no consecutive repeating characters in the given string except for '?'.
 * Return the final string after all the conversions (possibly zero) have been made. If there is more than one solution,
 * return any of them. It can be shown that an answer is always possible with the given constraints.
 *
 * Example 1:
 * Input: s = "?zs"
 * Output: "azs"
 * Explanation: There are 25 solutions for this problem. From "azs" to "yzs", all are valid.
 * Only "z" is an invalid modification as the string will consist of consecutive repeating characters in "zzs".
 *
 * Example 2:
 * Input: s = "j?qg??b"
 * Output: "jaqgacb"
 *
 * Constraints:
 * 1. 1 <= s.length <= 100
 * 2. s contains only lower case English letters and '?'.
 */
public class ReplaceAllToAvoidConsecutiveRepeatingCharacters {
    public static void main(String[] args) {
        System.out.println(modifyString("ubv???w"));
    }

    /**
     * 只要替换后的的字符和左右字符不相等即可，因此用 l 和 r 记录需要替换字符的左右字符
     * 替换的目标字符 c 初始化为 'a'，如果与 l 或 r 相等，则 c++，直到都不相等，然后将 ? 替换为 c
     */
    public static String modifyString(String s) {
        char[] array = s.toCharArray();
        char l = '.', r = '.', c = 'a';
        for (int i = 0; i < array.length; i++) {
            if (array[i] != '?') {
                continue;
            }

            if (i - 1 >= 0) {
                l = array[i - 1];
            }
            if (i + 1 < array.length) {
                r = array[i + 1];
            }
            while (c == l || c == r) {
                c++;
            }
            array[i] = c;
            l = '.';
            r = '.';
            c = 'a';
        }
        return new String(array);
    }
}
