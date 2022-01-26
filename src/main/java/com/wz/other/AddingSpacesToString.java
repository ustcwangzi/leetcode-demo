package com.wz.other;

/**
 * You are given a 0-indexed string s and a 0-indexed integer array spaces that describes the indices in the original string
 * where spaces will be added. Each space should be inserted before the character at the given index.
 * For example, given s = "EnjoyYourCoffee" and spaces = [5, 9], we place spaces before 'Y' and 'C',
 * which are at indices 5 and 9 respectively. Thus, we obtain "Enjoy Your Coffee".
 * Return the modified string after the spaces have been added.
 *
 * Example 1:
 * Input: s = "LeetcodeHelpsMeLearn", spaces = [8,13,15]
 * Output: "Leetcode Helps Me Learn"
 * Explanation:
 * The indices 8, 13, and 15 correspond to the underlined characters in "LeetcodeHelpsMeLearn".
 * We then place spaces before those characters.
 *
 * Example 2:
 * Input: s = "icodeinpython", spaces = [1,5,7,9]
 * Output: "i code in py thon"
 * Explanation:
 * The indices 1, 5, 7, and 9 correspond to the underlined characters in "icodeinpython".
 * We then place spaces before those characters.
 *
 * Example 3:
 * Input: s = "spacing", spaces = [0,1,2,3,4,5,6]
 * Output: " s p a c i n g"
 * Explanation:
 * We are also able to place spaces before the first character of the string.
 *
 * Constraints:
 * 1. 1 <= s.length <= 3 * 10^5
 * 2. s consists only of lowercase and uppercase English letters.
 * 3. 1 <= spaces.length <= 3 * 10^5
 * 4. 0 <= spaces[i] <= s.length - 1
 * 5. All the values of spaces are strictly increasing.
 */
public class AddingSpacesToString {
    public static void main(String[] args) {
        System.out.println(addSpaces("LeetcodeHelpsMeLearn", new int[]{8, 13, 15}));
    }

    /**
     * 因为 spaces[] 是严格升序的，可以使用 index 指向当前需要插入空格的位置，然后遍历字符串
     * 若当前位置 i == index，说明需要插入空格，向结果中插入空格，同时 index 右移，继续判断下一个位置
     */
    public static String addSpaces(String s, int[] spaces) {
        int index = 0;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (index < spaces.length && i == spaces[index]) {
                builder.append(" ");
                index++;
            }
            builder.append(s.charAt(i));
        }
        return builder.toString();
    }
}
