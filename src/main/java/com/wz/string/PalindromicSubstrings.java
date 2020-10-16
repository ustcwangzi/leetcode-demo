package com.wz.string;

/**
 * Given a string, your task is to count how many palindromic substrings in this string.
 * The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.
 *
 * Example 1:
 * Input: "abc"
 * Output: 3
 * Explanation: Three palindromic strings: "a", "b", "c".
 *
 * Example 2:
 * Input: "aaa"
 * Output: 6
 * Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
 *
 * Note:
 * The input string length won't exceed 1000.
 */
public class PalindromicSubstrings {
    public static void main(String[] args) {
        System.out.println(countSubstrings("aaa"));
        System.out.println(countSubstrings2("aaa"));
    }

    private static int result = 0;

    /**
     * 以字符串中的每一个字符都当作回文串中间的位置，然后向两边扩散，每当成功匹配两个左右两个字符，结果自增1，然后再比较下一对
     * 回文字符串有奇数和偶数两种形式，如果是奇数长度，那么 i 位置就是中间那个字符的位置，所以左右两遍都从i开始遍历；
     * 如果是偶数长度的，那么 i 是最中间两个字符的左边那个，右边那个就是 i+1，这样就能 cover 所有的情况，而且都是不同的回文子字符串
     */
    public static int countSubstrings(String s) {
        for (int i = 0; i <= s.length(); i++) {
            countSubstrings(s, i, i);
            countSubstrings(s, i, i + 1);
        }
        return result;
    }

    private static void countSubstrings(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
            result++;
        }
    }

    /**
     * 动态规划
     * dp[i][j] 表示 s[i, j] 是否是回文串
     * 然后 i 从 n-1 往 0 遍历，j 从 i 往 n-1 遍历
     * 若 s[i]==s[j]，且 i 和 j 中间不多于一个字符，那么 dp[i][j] 为 true；如果中间多于一个字符，则需要看 dp[i+1][j-1]
     * 最后，如果 dp[i][j] 为 true，结果 result 自增 1
     */
    public static int countSubstrings2(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int result = 0;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i <= 2 || dp[i + 1][j - 1]);
                if (dp[i][j]) {
                    result++;
                }
            }
        }
        return result;
    }
}
