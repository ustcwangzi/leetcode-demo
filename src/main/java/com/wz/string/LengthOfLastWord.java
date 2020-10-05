package com.wz.string;

/**
 * Given a string s consists of upper/lower-case alphabets and empty space characters ' ',
 * return the length of last word (last word means the last appearing word if we loop from left to right) in the string.
 * If the last word does not exist, return 0.
 * Note: A word is defined as a maximal substring consisting of non-space characters only.
 *
 * Example:
 * Input: "Hello World"
 * Output: 5
 */
public class LengthOfLastWord {
    public static void main(String[] args) {
        System.out.println(lengthOfLastWord("Hello World"));
    }

    /**
     * " "分割后的做后一个字符串的长度
     */
    public static int lengthOfLastWord(String s) {
        if (s.length() == 0 || s.equals(" ")) {
            return 0;
        }

        String[] array = s.trim().split(" ");
        return array[array.length - 1].length();
    }
}
