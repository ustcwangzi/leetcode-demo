package com.wz.dynamicprogramming;

/**
 * A string is called happy if it does not have any of the strings 'aaa', 'bbb' or 'ccc' as a substring.
 * Given three integers a, b and c, return any string s, which satisfies following conditions:
 * 1. s is happy and longest possible.
 * 2. s contains at most a occurrences of the letter 'a', at most b occurrences of the letter 'b' and at most c occurrences of the letter 'c'.
 * 3. s will only contain 'a', 'b' and 'c' letters.
 * If there is no such string s return the empty string "".
 *
 * Example 1:
 * Input: a = 1, b = 1, c = 7
 * Output: "ccaccbcc"
 * Explanation: "ccbccacc" would also be a correct answer.
 *
 * Example 2:
 * Input: a = 2, b = 2, c = 1
 * Output: "aabbc"
 *
 * Example 3:
 * Input: a = 7, b = 1, c = 0
 * Output: "aabaa"
 * Explanation: It's the only correct answer in this case.
 *
 * Constraints:
 * 1. 0 <= a, b, c <= 100
 * 2. a + b + c > 0
 */
public class LongestHappyString {
    public static void main(String[] args) {
        System.out.println(longestDiverseString(1, 1, 7));
    }

    /**
     * 因为只有三个字符，可以用 prev、prevPrev 记录前两次使用的字段索引，然后逐次将剩余可用次数最多的字符添加到结果中
     */
    public static String longestDiverseString(int a, int b, int c) {
        int[] counts = new int[]{a, b, c};
        StringBuilder sb = new StringBuilder();
        int prev = -1, prevPrev = -1;
        while (true) {
            int max = 0, index = -1;
            for (int i = 0; i < 3; i++) {
                // 找到剩余可用次数最多，并且和前两次不一样的字符索引
                if (counts[i] > max && prev != i && prevPrev != i) {
                    max = counts[i];
                    index = i;
                }
            }
            if (max == 0) {
                break;
            }
            sb.append((char) ('a' + index));
            --counts[index];
            prevPrev = prev;
            prev = index;
        }
        return sb.toString();
    }
}
