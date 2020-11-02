package com.wz.string;

import java.util.HashMap;
import java.util.Map;

/**
 * Given the string s, return the size of the longest substring containing each vowel an even number of times.
 * That is, 'a', 'e', 'i', 'o', and 'u' must appear an even number of times.
 *
 * Example 1:
 * Input: s = "eleetminicoworoep"
 * Output: 13
 * Explanation: The longest substring is "leetminicowor" which contains two each of the vowels: e, i and o and zero of the vowels: a and u.
 *
 * Example 2:
 * Input: s = "leetcodeisgreat"
 * Output: 5
 * Explanation: The longest substring is "leetc" which contains two e's.
 *
 * Example 3:
 * Input: s = "bcbcbc"
 * Output: 6
 * Explanation: In this case, the given string "bcbcbc" is the longest because all vowels: a, e, i, o and u appear zero times.
 *
 * Constraints:
 * 1. 1 <= s.length <= 5 x 10^5
 * 2. s contains only lowercase English letters.
 */
public class FindLongestSubstringContainingVowelsInEvenCounts {
    public static void main(String[] args) {
        System.out.println(findTheLongestSubstring("a"));
        System.out.println(findTheLongestSubstring("leetcodeisgreat"));
    }

    /**
     * 对每个位置 i，计算以 i 为结尾的满足条件的最长子串的长度，就是找一个最小的下标 j(0<=j<=i) 使得子数组 [j,i] 中每个元音字母都是偶数
     * 用 status 记录 a,e,i,o,u 的奇偶状态，低 5 位分别表示每个元音的奇偶状态，第 0 位表示 a 的奇偶状态，依次类推，0 表示偶，1 表示奇
     * 这里会用到位运算，当遇到 a 把1左移0位，遇到 e 把1左移1位，遇到 i 把1左移2位，遇到 o 把1左移3位，遇到 u 把1左移4位
     * 然后和 status 做异或操作 XOR，因为 XOR 是两数相同结果为0，所以某一个字母遇到两次，则这一位上的数字为0。
     * 例如: abcadf，status = 0 (00000)
     * 遍历到a, status ^ 00001 = 00001
     * 遍历到b, c的时候，因为不是元音字母所以直接跳过
     * 当遇到第二个a的时候，status ^ 00001 = 00001 ^ 00001 = 00000，status 又变回0
     * 此时记录一下当前 i 的位置，并记录这个最长子串的长度
     */
    public static int findTheLongestSubstring(String s) {
        int result = 0, status = 0, n = s.length();
        // 每个奇偶状态的最小下标
        Map<Integer, Integer> map = new HashMap<>();
        // 先放入一个 a,e,i,o,u 全为 0 的状态，下标为 -1
        map.put(0, -1);
        for (int i = 0; i < n; i++) {
            char cur = s.charAt(i);
            if (cur == 'a' || cur == 'e' || cur == 'i' || cur == 'o' || cur == 'u') {
                int diff = cur - 'a';
                status ^= 1 << diff;
            }
            // 放入此状态的最小下标
            map.putIfAbsent(status, i);
            // 遇到奇偶状态相同的，则当前位置到此状态的最小下标之间的子数组满足条件
            result = Math.max(result, i - map.get(status));
        }
        return result;
    }
}
