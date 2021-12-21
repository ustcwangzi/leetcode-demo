package com.wz.other;

/**
 * A sentence is a list of tokens separated by a single space with no leading or trailing spaces.
 * Every token is either a positive number consisting of digits 0-9 with no leading zeros, or a word consisting of lowercase English letters.
 * For example, "a puppy has 2 eyes 4 legs" is a sentence with seven tokens: "2" and "4" are numbers and the other tokens such as "puppy" are words.
 * Given a string s representing a sentence, you need to check if all the numbers in s are strictly increasing from left to right
 * (i.e., other than the last number, each number is strictly smaller than the number on its right in s).
 * Return true if so, or false otherwise.
 *
 * Example 1:
 * Input: s = "1 box has 3 blue 4 red 6 green and 12 yellow marbles"
 * Output: true
 * Explanation: The numbers in s are: 1, 3, 4, 6, 12.
 * They are strictly increasing from left to right: 1 < 3 < 4 < 6 < 12.
 *
 * Example 2:
 * Input: s = "sunset is at 7 51 pm overnight lows will be in the low 50 and 60 s"
 * Output: false
 * Explanation: The numbers in s are: 7, 51, 50, 60. They are not strictly increasing.
 *
 * Constraints:
 * 1. 3 <= s.length <= 200
 * 2. s consists of lowercase English letters, spaces, and digits from 0 to 9, inclusive.
 * 3. The number of tokens in s is between 2 and 100, inclusive.
 * 4. The tokens in s are separated by a single space.
 * 5. There are at least two numbers in s.
 * 6. Each number in s is a positive number less than 100, with no leading zeros.
 * 7. s contains no leading or trailing spaces.
 */
public class CheckIfNumbersAreAscendingInSentence {
    public static void main(String[] args) {
        System.out.println(areNumbersAscending("1 box has 3 blue 4 red 6 green and 12 yellow marbles"));
    }

    /**
     * 使用空格分割字符串，然后遍历数组，判断当前子串是否能转换为数字，可以的话，再判断当前数字是否大于之前的数字
     */
    public static boolean areNumbersAscending(String s) {
        int pre = Integer.MIN_VALUE;
        String[] array = s.split(" ");
        for (String cur : array) {
            if (!Character.isDigit(cur.charAt(0))) {
                continue;
            }
            int value = Integer.parseInt(cur);
            if (value <= pre) {
                return false;
            }
            pre = value;
        }
        return true;
    }
}
