package com.wz.math;

/**
 * Let's say a positive integer is a superpalindrome if it is a palindrome, and it is also the square of a palindrome.
 * Now, given two positive integers L and R (represented as strings), return the number of superpalindromes in the inclusive range [L, R].
 *
 * Example 1:
 * Input: L = "4", R = "1000"
 * Output: 4
 * Explanation: 4, 9, 121, and 484 are superpalindromes.
 * Note that 676 is not a superpalindrome: 26 * 26 = 676, but 26 is not a palindrome.
 *
 * Note:
 * 1 <= len(L) <= 18
 * 1 <= len(R) <= 18
 * L and R are strings representing integers in the range [1, 10^18).
 * int(L) <= int(R)
 */
public class SuperPalindromes {
    public static void main(String[] args) {
        System.out.println(superpalindromesInRange("4", "1000"));
    }

    public static int superpalindromesInRange(String L, String R) {
        long l = Long.parseLong(L), r = Long.parseLong(R);
        int result = 0;
        for (long i = (long) Math.sqrt(l); i * i <= r; ) {
            long p = nextPalindrome(i);
            if (p * p <= r && isPalindrome(p * p)) {
                result++;
            }
            i = p + 1;
        }
        return result;
    }

    private static long nextPalindrome(long l) {
        String s = Long.toString(l);
        int len = s.length();
        String half = s.substring(0, (len + 1) / 2);
        String reverse = new StringBuilder(half.substring(0, len / 2)).reverse().toString();
        long first = Long.parseLong(half + reverse);
        if (first >= l) {
            return first;
        }
        String nextHalf = Long.toString(Long.parseLong(half) + 1);
        String reverseNextHalf = new StringBuilder(nextHalf.substring(0, len / 2)).reverse().toString();
        return Long.parseLong(nextHalf + reverseNextHalf);
    }

    private static boolean isPalindrome(long l) {
        String s = "" + l;
        int i = 0, j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i++) != s.charAt(j--)) {
                return false;
            }
        }
        return true;
    }
}
