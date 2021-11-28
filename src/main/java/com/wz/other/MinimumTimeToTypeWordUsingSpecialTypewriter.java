package com.wz.other;

/**
 * There is a special typewriter with lowercase English letters 'a' to 'z' arranged in a circle with a pointer.
 * A character can only be typed if the pointer is pointing to that character. The pointer is initially pointing to the character 'a'.
 * @link ../../../../resource/MinimumTimeToTypeWordUsingSpecialTypewriter.jpg
 * Each second, you may perform one of the following operations:
 * 1. Move the pointer one character counterclockwise or clockwise.
 * 2. Type the character the pointer is currently on.
 * Given a string word, return the minimum number of seconds to type out the characters in word.
 *
 * Example 1:
 * Input: word = "abc"
 * Output: 5
 * Explanation:
 * The characters are printed as follows:
 * - Type the character 'a' in 1 second since the pointer is initially on 'a'.
 * - Move the pointer clockwise to 'b' in 1 second.
 * - Type the character 'b' in 1 second.
 * - Move the pointer clockwise to 'c' in 1 second.
 * - Type the character 'c' in 1 second.
 *
 * Example 2:
 * Input: word = "bza"
 * Output: 7
 * Explanation:
 * The characters are printed as follows:
 * - Move the pointer clockwise to 'b' in 1 second.
 * - Type the character 'b' in 1 second.
 * - Move the pointer counterclockwise to 'z' in 2 seconds.
 * - Type the character 'z' in 1 second.
 * - Move the pointer clockwise to 'a' in 1 second.
 * - Type the character 'a' in 1 second.
 *
 * Example 3:
 * Input: word = "zjpc"
 * Output: 34
 * Explanation:
 * The characters are printed as follows:
 * - Move the pointer counterclockwise to 'z' in 1 second.
 * - Type the character 'z' in 1 second.
 * - Move the pointer clockwise to 'j' in 10 seconds.
 * - Type the character 'j' in 1 second.
 * - Move the pointer clockwise to 'p' in 6 seconds.
 * - Type the character 'p' in 1 second.
 * - Move the pointer counterclockwise to 'c' in 13 seconds.
 * - Type the character 'c' in 1 second.
 *
 * Constraints:
 * 1. 1 <= word.length <= 100
 * 2. word consists of lowercase English letters.
 */
public class MinimumTimeToTypeWordUsingSpecialTypewriter {
    public static void main(String[] args) {
        System.out.println(minTimeToType("bza"));
        System.out.println(minTimeToType("zjpc"));
    }

    /**
     * 使用 pre 记录上一个字符，遍历 word，计算当前字符与 pre 之间的顺时针距离 diff
     * 然后从顺时针距离 diff 和 逆时针距离 26-diff 中选择较小值加入到结果中，最后的结果需要加上 word 的长度
     */
    public static int minTimeToType(String word) {
        int count = 0;
        char pre = 'a';
        for (int i = 0; i < word.length(); i++) {
            char cur = word.charAt(i);
            int diff = Math.abs(cur - pre);
            count += Math.min(diff, 26 - diff);
            pre = cur;
        }
        return count + word.length();
    }
}
