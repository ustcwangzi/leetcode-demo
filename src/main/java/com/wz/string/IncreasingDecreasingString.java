package com.wz.string;

/**
 * Given a string s. You should re-order the string using the following algorithm:
 * 1. Pick the smallest character from s and append it to the result.
 * 2. Pick the smallest character from s which is greater than the last appended character to the result and append it.
 * 3. Repeat step 2 until you cannot pick more characters.
 * 4. Pick the largest character from s and append it to the result.
 * 5. Pick the largest character from s which is smaller than the last appended character to the result and append it.
 * 6. Repeat step 5 until you cannot pick more characters.
 * 7. Repeat the steps from 1 to 6 until you pick all characters from s.
 * In each step, If the smallest or the largest character appears more than once you can choose any occurrence and append it to the result.
 * Return the result string after sorting s with this algorithm.
 *
 * Example 1:
 * Input: s = "aaaabbbbcccc"
 * Output: "abccbaabccba"
 * Explanation: After steps 1, 2 and 3 of the first iteration, result = "abc"
 * After steps 4, 5 and 6 of the first iteration, result = "abccba"
 * First iteration is done. Now s = "aabbcc" and we go back to step 1
 * After steps 1, 2 and 3 of the second iteration, result = "abccbaabc"
 * After steps 4, 5 and 6 of the second iteration, result = "abccbaabccba"
 *
 * Example 2:
 * Input: s = "leetcode"
 * Output: "cdelotee"
 *
 * Constraints:
 * 1. 1 <= s.length <= 500
 * 2. s contains only lower-case English letters.
 */
public class IncreasingDecreasingString {
    public static void main(String[] args) {
        System.out.println(sortString("leetcode"));
    }

    /**
     * 统计 s 中每个字符出现的次数，然后分别升序、降序取出每个字符加到结果集中
     */
    public static String sortString(String s) {
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a'] += 1;
        }

        StringBuilder builder = new StringBuilder();
        while (builder.length() < s.length()) {
            for (int i = 0; i < 26; i++) {
                if (count[i] > 0) {
                    builder.append((char) (i + 'a'));
                    count[i]--;
                }
            }
            for (int i = 25; i >= 0; i--) {
                if (count[i] > 0) {
                    builder.append((char) (i + 'a'));
                    count[i]--;
                }
            }
        }
        return builder.toString();
    }
}
