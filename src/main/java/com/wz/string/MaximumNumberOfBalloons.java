package com.wz.string;

/**
 * Given a string text, you want to use the characters of text to form as many instances of the word "balloon" as possible.
 * You can use each character in text at most once. Return the maximum number of instances that can be formed.
 *
 * Example 1:
 * Input: text = "nlaebolko"
 * Output: 1
 *
 * Example 2:
 * Input: text = "loonbalxballpoon"
 * Output: 2
 *
 * Constraints:
 * 1. 1 <= text.length <= 10^4
 * 2. text consists of lower case English letters only.
 */
public class MaximumNumberOfBalloons {
    public static void main(String[] args) {
        System.out.println(maxNumberOfBalloons("nlaebolko"));
    }

    /**
     * balloon 中 b、a、n 都只出现一次，l 和 o 出现两次，因此将这些单词的出现次数归一化后取最小值即可
     */
    public static int maxNumberOfBalloons(String text) {
        int[] count = new int[26];
        for (int i = 0; i < text.length(); i++) {
            count[text.charAt(i) - 'a']++;
        }
        int b = count[1], a = count[0], l = count[11], o = count[14], n = count[13];
        return Math.min(b, Math.min(a, Math.min(l / 2, Math.min(o / 2, n))));
    }
}
