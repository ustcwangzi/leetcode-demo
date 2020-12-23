package com.wz.dynamicprogramming;

/**
 * A program was supposed to print an array of integers. The program forgot to print whitespaces and
 * the array is printed as a string of digits and all we know is that all integers in the array were
 * in the range [1, k] and there are no leading zeros in the array.
 * Given the string s and the integer k. There can be multiple ways to restore the array.
 * Return the number of possible array that can be printed as a string s using the mentioned program.
 * The number of ways could be very large so return it modulo 10^9 + 7
 *
 * Example 1:
 * Input: s = "1000", k = 10000
 * Output: 1
 * Explanation: The only possible array is [1000]
 *
 * Example 2:
 * Input: s = "1000", k = 10
 * Output: 0
 * Explanation: There cannot be an array that was printed this way and has all integer >= 1 and <= 10.
 *
 * Example 3:
 * Input: s = "1317", k = 2000
 * Output: 8
 * Explanation: Possible arrays are [1317],[131,7],[13,17],[1,317],[13,1,7],[1,31,7],[1,3,17],[1,3,1,7]
 *
 * Example 4:
 * Input: s = "2020", k = 30
 * Output: 1
 * Explanation: The only possible array is [20,20]. [2020] is invalid because 2020 > 30. [2,020] is ivalid because 020 contains leading zeros.
 *
 * Constraints:
 * 1. 1 <= s.length <= 10^5.
 * 2. s consists of only digits and doesn't contain leading zeros.
 * 3. 1 <= k <= 10^9.
 */
public class RestoreTheArray {
    public static void main(String[] args) {
        System.out.println(numberOfArrays("2020", 30));
    }

    /**
     * dp[i] 表示 s 从 i 开始至结尾的子串可以满足分割的结果
     */
    public static int numberOfArrays(String s, int k) {
        int n = s.length(), mod = 1000000007;
        int[] dp = new int[n + 1];
        dp[n] = 1;
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == '0') {
                continue;
            }
            long cur = 0;
            for (int j = i; j < n; j++) {
                cur *= 10;
                cur += (s.charAt(j) - '0');
                if (cur <= 0 || cur > k) {
                    break;
                }
                dp[i] += dp[j + 1];
                dp[i] %= mod;
            }
        }
        return dp[0];
    }
}
