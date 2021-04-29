package com.wz.string;

/**
 * You are given two strings a and b that consist of lowercase letters. In one operation, you can change any character in a or b to any lowercase letter.
 * Your goal is to satisfy one of the following three conditions:
 * 1. Every letter in a is strictly less than every letter in b in the alphabet.
 * 2. Every letter in b is strictly less than every letter in a in the alphabet.
 * 3. Both a and b consist of only one distinct letter.
 * Return the minimum number of operations needed to achieve your goal.
 *
 * Example 1:
 * Input: a = "aba", b = "caa"
 * Output: 2
 * Explanation: Consider the best way to make each condition true:
 * 1) Change b to "ccc" in 2 operations, then every letter in a is less than every letter in b.
 * 2) Change a to "bbb" and b to "aaa" in 3 operations, then every letter in b is less than every letter in a.
 * 3) Change a to "aaa" and b to "aaa" in 2 operations, then a and b consist of one distinct letter.
 * The best way was done in 2 operations (either condition 1 or condition 3).
 *
 * Example 2:
 * Input: a = "dabadd", b = "cda"
 * Output: 3
 * Explanation: The best way is to make condition 1 true by changing b to "eee".
 *
 * Constraints:
 * 1. 1 <= a.length, b.length <= 10^5
 * 2. a and b consist only of lowercase letters.
 */
public class ChangeMinimumCharactersToSatisfyOneOfThreeConditions {
    public static void main(String[] args) {
        System.out.println(minCharacters("aba", "caa"));
        System.out.println(minCharacters("dabadd", "cda"));
    }

    /**
     * 遍历两个字符串，分别统计每个字符出现次数，然后判断三种情况下需要的操作次数
     * 第三种情况最简单，即将 a 和 b 全部变成只包含一种字符，和目标字符相同则不用变，因此操作次数就是 a 和 b 的总长度减去已出现的字符次数
     * 情况一，需要寻找分割字符，假设分割字符的位置是 i(0～24)，则将 a 的字符都变为小于等于 ch[i]、b 的字符都变为大于 ch[i]，
     * 即对于a，[0...i] 字符不用变，而 [i+1...n-1] 字符全部修改，操作次数为 a 的长度减去 [0...i] 字符出现次数，
     * 对于 b，[0...i] 字符全部修改，而 [i+1...n-1] 字符不用变，操作次数为 [0...i] 字符出现次数，这里可以使用 前缀和 快速求解
     * 情况二与情况一类似，也是寻找分割字符
     */
    public static int minCharacters(String a, String b) {
        int m = a.length(), n = b.length();
        int[] c1 = new int[26], c2 = new int[26];
        for (int i = 0; i < m; i++) {
            c1[a.charAt(i) - 'a']++;
        }
        for (int i = 0; i < n; i++) {
            c2[b.charAt(i) - 'a']++;
        }

        int result = m + n;
        for (int i = 0; i < 26; i++) {
            // 情况三：全部变成同一个字符
            result = Math.min(result, m + n - c1[i] - c2[i]);

            // 前缀和，因为后面不再使用 c[i]，因此不再申请新数组，直接进行复用
            if (i > 0) {
                c1[i] += c1[i - 1];
                c2[i] += c2[i - 1];
            }

            // 寻找分割字符
            if (i < 25) {
                // 情况一：a 中的每个字符都小于 b
                result = Math.min(result, m - c1[i] + c2[i]);
                // 情况二：b 中的每个字符都小于 a
                result = Math.min(result, n - c2[i] + c1[i]);
            }
        }
        return result;
    }
}
