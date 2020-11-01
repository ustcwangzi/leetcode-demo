package com.wz.string;

/**
 * Given a string s consisting only of characters a, b and c.
 * Return the number of substrings containing at least one occurrence of all these characters a, b and c.
 *
 * Example 1:
 * Input: s = "abcabc"
 * Output: 10
 * Explanation: The substrings containing at least one occurrence of the characters a, b and c are
 *              "abc", "abca", "abcab", "abcabc", "bca", "bcab", "bcabc", "cab", "cabc" and "abc" (again).
 *
 * Example 2:
 * Input: s = "aaacb"
 * Output: 3
 * Explanation: The substrings containing at least one occurrence of the characters a, b and c are "aaacb", "aacb" and "acb".
 *
 * Constraints:
 * 1. 3 <= s.length <= 5 x 10^4
 * 2. s only consists of a, b or c characters.
 */
public class NumberOfSubstringsContainingAllThreeCharacters {
    public static void main(String[] args) {
        System.out.println(numberOfSubstrings("abcabc"));
    }

    /**
     * 双指针，滑动窗口
     * 开始时 left、right 都指向 s 的第一个位置，窗口为 s[left,right]
     * 1. 如果窗口内有 a, b, c, 那么 left 右移直到不满足条件，left 右移次数就是满足条件的子串数量
     *    (left 不用重新置0，因为之前的子串和当前子串合并也是满足条件的)，将 left 加到结果集中
     * 2. 如果窗口内没有 a, b, c, 那么 right 右移直到满足条件
     * 3. 重读以上过程，直到 right 超出 s 的范围
     */
    public static int numberOfSubstrings(String s) {
        char[] array = s.toCharArray();
        int[] count = new int[3];
        int result = 0;
        for (int left = 0, right = 0; right < array.length; right++) {
            count[array[right] - 'a']++;
            while (count[0] > 0 && count[1] > 0 && count[2] > 0) {
                count[array[left++] - 'a']--;
            }
            result += left;
        }
        return result;
    }
}
