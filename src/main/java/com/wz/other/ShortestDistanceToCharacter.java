package com.wz.other;

import java.util.Arrays;

/**
 * Given a string s and a character c that occurs in s, return an array of integers answer where
 * answer.length == s.length and answer[i] is the distance from index i to the closest occurrence of character c in s.
 * The distance between two indices i and j is abs(i - j), where abs is the absolute value function.
 *
 * Example 1:
 * Input: s = "loveleetcode", c = "e"
 * Output: [3,2,1,0,1,0,0,1,2,2,1,0]
 * Explanation: The character 'e' appears at indices 3, 5, 6, and 11 (0-indexed).
 * The closest occurrence of 'e' for index 0 is at index 3, so the distance is abs(0 - 3) = 3.
 * The closest occurrence of 'e' for index 1 is at index 3, so the distance is abs(1 - 3) = 2.
 * For index 4, there is a tie between the 'e' at index 3 and the 'e' at index 5, but the distance is still the same: abs(4 - 3) == abs(4 - 5) = 1.
 * The closest occurrence of 'e' for index 8 is at index 6, so the distance is abs(8 - 6) = 2.
 *
 * Example 2:
 * Input: s = "aaab", c = "b"
 * Output: [3,2,1,0]
 *
 * Constraints:
 * 1. 1 <= s.length <= 104
 * 2. s[i] and c are lowercase English letters.
 * 3. It is guaranteed that c occurs at least once in s.
 */
public class ShortestDistanceToCharacter {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(shortestToChar("loveleetcode", 'e')));
    }

    /**
     * 对于每个非 c 字符，距离 c 最近的距离肯定是左右两边距离的最小值，因此可以进行两次遍历，记录 c 出现的位置 index，更新距离
     * 先从左向右遍历，记录当前位置和左侧 c 的距离，然后再从右向左遍历，计算当前位置和右侧 c 的距离，若更小则进行更新
     */
    public static int[] shortestToChar(String s, char c) {
        int[] result = new int[s.length()];
        // c 出现的位置
        int index = -1;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == c) {
                index = i;
            }
            if (index == -1) {
                result[i] = Integer.MAX_VALUE;
            } else {
                result[i] = Math.abs(index - i);
            }
        }

        index = -1;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == c) {
                index = i;
            }
            if (index != -1) {
                result[i] = Math.min(result[i], Math.abs(index - i));
            }
        }
        return result;
    }
}
