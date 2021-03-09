package com.wz.greedy;

/**
 * You are given a string s and two integers x and y. You can perform two types of operations any number of times.
 * 1. Remove substring "ab" and gain x points.
 *    For example, when removing "ab" from "cabxbae" it becomes "cxbae".
 * 2. Remove substring "ba" and gain y points.
 *    For example, when removing "ba" from "cabxbae" it becomes "cabxe".
 * Return the maximum points you can gain after applying the above operations on s.
 *
 * Example 1:
 * Input: s = "cdbcbbaaabab", x = 4, y = 5
 * Output: 19
 * Explanation:
 * - Remove the "ba" underlined in "cdbcbbaaabab". Now, s = "cdbcbbaaab" and 5 points are added to the score.
 * - Remove the "ab" underlined in "cdbcbbaaab". Now, s = "cdbcbbaa" and 4 points are added to the score.
 * - Remove the "ba" underlined in "cdbcbbaa". Now, s = "cdbcba" and 5 points are added to the score.
 * - Remove the "ba" underlined in "cdbcba". Now, s = "cdbc" and 5 points are added to the score.
 * Total score = 5 + 4 + 5 + 5 = 19.
 *
 * Constraints:
 * 1. 1 <= s.length <= 10^5
 * 2. 1 <= x, y <= 10^4
 * 3. s consists of lowercase English letters.
 */
public class MaximumScoreFromRemovingSubstrings {
    public static void main(String[] args) {
        System.out.println(maximumGain("cdbcbbaaabab", 4, 5));
    }

    /**
     * 先确保 first 代表分数较小的字符、second 代表分数较大的字符
     * 然后遍历字符串，分别统计出现次数，优先消除分数较大的字符，遍历结束后，再将分数较小的字符一次性消除
     */
    public static int maximumGain(String s, int x, int y) {
        char first = 'a', second = 'b';
        if (x > y) {
            first = 'b';
            second = 'a';
            int tmp = y;
            y = x;
            x = tmp;
        }

        int result = 0, firstCount = 0, secondCount = 0;
        char[] array = s.toCharArray();
        for (char cur : array) {
            if (cur == first) {
                // 优先消除
                if (secondCount > 0) {
                    result += y;
                    secondCount--;
                } else {
                    firstCount++;
                }
            } else if (cur == second) {
                secondCount++;
            } else {
                result += Math.min(firstCount, secondCount) * x;
                firstCount = 0;
                secondCount = 0;
            }
        }

        result += Math.min(firstCount, secondCount) * x;
        return result;
    }
}
