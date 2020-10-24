package com.wz.string;

/**
 * Given a string S, return the "reversed" string where all characters that are not a letter stay in the same place, and all letters reverse their positions.
 *
 * Example 1:
 * Input: "ab-cd"
 * Output: "dc-ba"
 *
 * Example 2:
 * Input: "a-bC-dEf-ghIj"
 * Output: "j-Ih-gfE-dCba"
 *
 * Note:
 * 1. S.length <= 100
 * 2. 33 <= S[i].ASCIIcode <= 122
 * 3. S doesn't contain \ or "
 */
public class ReverseOnlyLetters {
    public static void main(String[] args) {
        System.out.println(reverseOnlyLetters("a-bC-dEf-ghIj"));
    }

    /**
     * 双指针，i 指向开始字符，j 指向结束字符
     * 如果 s[i] 不是字母，i 右移
     * 如果 s[j] 不是字母，j 左移
     * 如果 s[i] 和 s[j] 都是字母，交换 s[i] 和 s[j]
     */
    public static String reverseOnlyLetters(String S) {
        char[] array = S.toCharArray();
        int i = 0, j = S.length() - 1;
        while (i < j) {
            if (!Character.isLetter(array[i])) {
                i++;
            } else if (!Character.isLetter(array[j])) {
                j--;
            } else {
                int tmp = array[i];
                array[i] = array[j];
                array[j] = (char) tmp;
                i++;
                j--;
            }
        }
        return new String(array);
    }
}
