package com.wz.string;

/**
 * Your friend is typing his name into a keyboard.  Sometimes, when typing a character c, the key might get long pressed,
 * and the character will be typed 1 or more times.
 * You examine the typed characters of the keyboard.  Return True if it is possible that it was your friends name,
 * with some characters (possibly none) being long pressed.
 *
 * Example 1:
 * Input: name = "alex", typed = "aaleex"
 * Output: true
 * Explanation: 'a' and 'e' in 'alex' were long pressed.
 *
 * Example 2:
 * Input: name = "saeed", typed = "ssaaedd"
 * Output: false
 * Explanation: 'e' must have been pressed twice, but it wasn't in the typed output.
 *
 * Constraints:
 * 1. 1 <= name.length <= 1000
 * 2. 1 <= typed.length <= 1000
 * 3. The characters of name and typed are lowercase letters.
 */
public class LongPressedName {
    public static void main(String[] args) {
        System.out.println(isLongPressedName("alex", "aaleex"));
        System.out.println(isLongPressedName("saeed", "ssaaedd"));
    }

    /**
     * 双指针，i 指向 name 开始位置，j 指向 typed 开始位置
     * 如果 name[i] == typed[j]，i 右移，j 右移
     * 否则，如果 typed[j] == typed[j-1]，说明出现了重复字符，j 右移
     * 否则，返回 false
     * 循环结束时，判断是否遍历完 name 的所有字符，即 i == name.length()
     */
    public static boolean isLongPressedName(String name, String typed) {
        if (name.equals(typed)) {
            return true;
        }
        if (name.length() > typed.length()) {
            return false;
        }

        int i = 0, j = 0;
        while (j < typed.length()) {
            if (i < name.length() && name.charAt(i) == typed.charAt(j)) {
                i++;
                j++;
            } else if (j > 0 && typed.charAt(j) == typed.charAt(j - 1)) {
                j++;
            } else {
                return false;
            }
        }
        return i == name.length();
    }
}
