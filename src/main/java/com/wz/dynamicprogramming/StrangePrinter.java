package com.wz.dynamicprogramming;

/**
 * There is a strange printer with the following two special requirements:
 * The printer can only print a sequence of the same character each time.
 * At each turn, the printer can print new characters starting from and ending at any places, and will cover the original existing characters.
 * Given a string consists of lower English letters only, your job is to count the minimum number of turns the printer needed in order to print it.
 *
 * Example 1:
 * Input: "aaabbb"
 * Output: 2
 * Explanation: Print "aaa" first and then print "bbb".
 *
 * Example 2:
 * Input: "aba"
 * Output: 2
 * Explanation: Print "aaa" first and then print "b" from the second place of the string, which will cover the existing character 'a'.
 *
 * Hint: Length of the given string will not exceed 100.
 */
public class StrangePrinter {
    public static void main(String[] args) {
        System.out.println(strangePrinter("aba"));
    }

    /**
     * 动态规划
     * dp[i][j] 表示打印出字符串 s[i,j] 所需的最小步数，
     * 首先如果只有一个字符，那么直接一次打印出来就行了；
     * 如果字符串是"ab"的话，那么要么先打印出"aa"，再改成"ab"，或者先打印出"bb"，再改成"ab"；
     * 同理，如果字符串是"abc"的话，就需要三次打印，那么一个很明显的特征是，如果没有重复的字符，打印的次数就是字符的个数。
     * 难点就是要处理有相同字符的情况，比如字符串是"aba"的时候，先打"aaa"的话，两步就搞定了，如果先打"bbb"的话，就需要三步。
     * 再来看一个字符串"abcb"，需要三步，如果把这个字符串分成两个部分"a"和"bcb"，它们分别的步数是1和2，加起来的3是整个的步数。
     * 而对于字符串"abba"，如果分成"a"和"bba"，它们分别的步数也是1和2，但是总步数却是2。这是因为分出的"a"和"bba"中的最后一个字符相同。
     * 对于字符串"abbac"，因为位置0上的a和位置3上的a相同，那么整个字符串的步数相当于"bb"和"ac"的步数之和，为3。
     * 可以看出关心的是字符相等的地方，对于 [i,j] 范围的字符，从 i+1 位置遍历到 j，如果和 i 位置上的字符相等，就以此位置为界，
     * 将 [i+1,j] 范围内的字符拆为两个部分，将二者的 dp 值加起来，和原 dp 值相比，取较小的那个。所以递推式如下:
     * dp[i][j] = min(dp[i][j], dp[i+1][k-1] + dp[k][j]   (s[k] == s[i] and i+1 <= k <= j)
     * 要注意一些初始化的值，dp[i][i]是1，还是就是在遍历 k 之前，dp[i][j] 初始化为 1 + dp[i+1][j]，
     * 可以看成在 [i+1,j] 的范围上多加了一个 s[i] 字符，最坏的情况就是加上的是一个不曾出现过的字符，步数最多加 1 步
     */
    public static int strangePrinter(String s) {
        int n = s.length();
        if (n == 0) {
            return 0;
        }

        int[][] dp = new int[n][n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                dp[i][j] = (i == j) ? 1 : (1 + dp[i + 1][j]);
                for (int k = i + 1; k <= j; ++k) {
                    if (s.charAt(k) == s.charAt(i)) {
                        dp[i][j] = Math.min(dp[i][j], dp[i + 1][k - 1] + dp[k][j]);
                    }
                }
            }
        }
        return dp[0][n - 1];
    }
}
