package com.wz.string;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Write a function that takes a string as input and reverse only the vowels of a string.
 *
 * Example 1:
 * Input: "hello"
 * Output: "holle"
 *
 * Example 2:
 * Input: "leetcode"
 * Output: "leotcede"
 */
public class ReverseVowelsOfString {
    public static void main(String[] args) {
        System.out.println(reverseVowels("hello"));
        System.out.println(reverseVowels("leetcode"));
    }

    private static final Set<Character> VOWELS = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));

    /**
     * 双指针
     * 1. 若 left 和 right 都是 vowel，则交换 left 和 right，并且 left++、right--
     * 2. 若 left 是 vowel，right 不是，则 right--
     * 3. 若 left 不是 vowel，right 是，则 left++
     * 4. 若 left 和 right 都不是 vowel，则 left++、right--
     */
    public static String reverseVowels(String s) {
        char[] array = s.toCharArray();
        int left = 0, right = array.length - 1;

        while (left < right) {
            if (VOWELS.contains(array[left]) && VOWELS.contains(array[right])) {
                char temp = array[left];
                array[left] = array[right];
                array[right] = temp;
                left++;
                right--;
            } else if (VOWELS.contains(array[left]) && !VOWELS.contains(array[right])) {
                right--;
            } else if (!VOWELS.contains(array[left]) && VOWELS.contains(array[right])) {
                left++;
            } else {
                left++;
                right--;
            }
        }

        return new String(array);
    }
}
