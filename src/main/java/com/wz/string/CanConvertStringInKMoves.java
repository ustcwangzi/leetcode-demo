package com.wz.string;

/**
 * Given two strings s and t, your goal is to convert s into t in k moves or less.
 * During the ith (1 <= i <= k) move you can:
 * Choose any index j (1-indexed) from s, such that 1 <= j <= s.length and j has not been chosen in any previous move,
 * and shift the character at that index i times.
 * Do nothing.
 * Shifting a character means replacing it by the next letter in the alphabet (wrapping around so that 'z' becomes 'a').
 * Shifting a character by i means applying the shift operations i times.
 * Remember that any index j can be picked at most once.
 * Return true if it's possible to convert s into t in no more than k moves, otherwise return false.
 *
 *
 * Example 1:
 * Input: s = "input", t = "ouput", k = 9
 * Output: true
 * Explanation: In the 6th move, we shift 'i' 6 times to get 'o'. And in the 7th move we shift 'n' to get 'u'.
 *
 * Example 2:
 * Input: s = "abc", t = "bcd", k = 10
 * Output: false
 * Explanation: We need to shift each character in s one time to convert it into t. We can shift 'a' to 'b' during the 1st move.
 *              However, there is no way to shift the other characters in the remaining moves to obtain t from s.
 *
 * Constraints:
 * 1. 1 <= s.length, t.length <= 10^5
 * 2. 0 <= k <= 10^9
 * 3. s, t contain only lowercase English letters.
 */
public class CanConvertStringInKMoves {
    public static void main(String[] args) {
        System.out.println(canConvertString("input", "ouput", 9));
    }

    /**
     * 给定字符串 s 和 t，求 s 经过 k 步变换后能够得到 t，其中第 i 步需要移动 i 个字符
     * 字符串 s 和 t 中，相应位置的 ascii 的差刚好是需要移动的步数，然后 k 可以大于26，字符串移动是循环的
     * 所以(t[i]-s[i])%26，刚好等于 ascii 大的字母移动到 ascii 小的字母的步数
     * 可能有些移动步数是一样的，但是 k 可以大于 26，所以用一个字典来统计相同移动步数的频率，
     * 然后计算所有相同步数字符移动的步数否超过 k 就行了，超过 k，返回 False，否则是符合题目要求的
     */
    public static boolean canConvertString(String s, String t, int k) {
        if (s.length() != t.length()) {
            return false;
        }

        int[] shiftCounts = new int[26];
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == t.charAt(i)) {
                continue;
            }

            int shift = (t.charAt(i) - s.charAt(i) + 26) % 26;
            int value = shiftCounts[shift] * 26 + shift;
            if (value > k) {
                return false;
            }
            shiftCounts[shift]++;
        }
        return true;
    }
}
