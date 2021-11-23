package com.wz.other;

/**
 * You are given a 0-indexed string s of even length n. The string consists of exactly n / 2 opening brackets '[' and n / 2 closing brackets ']'.
 * A string is called balanced if and only if:
 * 1. It is the empty string, or
 * 2. It can be written as AB, where both A and B are balanced strings, or
 * 3. It can be written as [C], where C is a balanced string.
 * You may swap the brackets at any two indices any number of times.
 * Return the minimum number of swaps to make s balanced.
 *
 * Example 1:
 * Input: s = "][]["
 * Output: 1
 * Explanation: You can make the string balanced by swapping index 0 with index 3.
 * The resulting string is "[[]]".
 *
 * Example 2:
 * Input: s = "]]][[["
 * Output: 2
 * Explanation: You can do the following to make the string balanced:
 * - Swap index 0 with index 4. s = "[]][][".
 * - Swap index 1 with index 5. s = "[[][]]".
 * The resulting string is "[[][]]".
 *
 * Example 3:
 * Input: s = "[]"
 * Output: 0
 * Explanation: The string is already balanced.
 *
 * Constraints:
 * 1. n == s.length
 * 2. 2 <= n <= 10^6
 * 3. n is even.
 * 4. s[i] is either '[' or ']'.
 * 5. The number of opening brackets '[' equals n / 2, and the number of closing brackets ']' equals n / 2.
 */
public class MinimumNumberOfSwapsToMakeStringBalanced {
    public static void main(String[] args) {
        System.out.println(minSwaps("][]["));
        System.out.println(minSwaps("]]][[["));
    }

    /**
     * 遍历并统计字符串中逆序括号对的数量 count，每次交换最多可减少 2 对逆序括号对，因此最少交换次数为 (count + 1) / 2
     * 遇到左括号则 count++，意思是需要找一个配对，遇到右括号同时 count>0（意思是前面有单独的左括号可供配对）则 count--，这样就能配对了
     */
    public static int minSwaps(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '[') {
                count++;
            } else if (count > 0) {
                count--;
            }
        }
        return (count + 1) / 2;
    }
}
