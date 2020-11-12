package com.wz.string;

/**
 * You are given two strings a and b of the same length. Choose an index and split both strings at the same index,
 * splitting a into two strings: aprefix and asuffix where a = aprefix + asuffix, and splitting b into two strings:
 * bprefix and bsuffix where b = bprefix + bsuffix. Check if aprefix + bsuffix or bprefix + asuffix forms a palindrome.
 * When you split a string s into sprefix and ssuffix, either ssuffix or sprefix is allowed to be empty.
 * For example, if s = "abc", then "" + "abc", "a" + "bc", "ab" + "c" , and "abc" + "" are valid splits.
 * Return true if it is possible to form a palindrome string, otherwise return false.
 * Notice that x + y denotes the concatenation of strings x and y.
 *
 * Example 1:
 * Input: a = "x", b = "y"
 * Output: true
 * Explaination: If either a or b are palindromes the answer is true since you can split in the following way:
 * aprefix = "", asuffix = "x"
 * bprefix = "", bsuffix = "y"
 * Then, aprefix + bsuffix = "" + "y" = "y", which is a palindrome.
 *
 * Example 2:
 * Input: a = "ulacfd", b = "jizalu"
 * Output: true
 * Explaination: Split them at index 3:
 * aprefix = "ula", asuffix = "cfd"
 * bprefix = "jiz", bsuffix = "alu"
 * Then, aprefix + bsuffix = "ula" + "alu" = "ulaalu", which is a palindrome.
 *
 * Constraints:
 * 1. 1 <= a.length, b.length <= 10^5
 * 2. a.length == b.length
 * 3. a and b consist of lowercase English letters
 */
public class SplitTwoStringsToMakePalindrome {
    public static void main(String[] args) {
        System.out.println(checkPalindromeFormation("ulacfd", "jizalu"));
    }

    /**
     * 双指针，l 指向 a 的开始，r 指向 b 的末尾
     * 当 a[l] == b[r] 时，l 右移、r 左移，直到两者不相等，然后判断 a 剩下的和 b 剩下的是否是 palindrome
     */
    public static boolean checkPalindromeFormation(String a, String b) {
        return validate(a, b) || validate(b, a);
    }

    private static boolean validate(String a, String b) {
        int l = 0, r = a.length() - 1;
        while (l < r) {
            if (a.charAt(l) != b.charAt(r)) {
                break;
            }
            l++;
            r--;
        }
        return isPalindrome(a.substring(l, r + 1)) || isPalindrome(b.substring(l, r + 1));
    }

    private static boolean isPalindrome(String s) {
        int l = 0, r = s.length() - 1;
        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }
}
