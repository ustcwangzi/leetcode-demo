package com.wz.other;

import java.util.Arrays;

/**
 * Given an integer array queries and a positive integer intLength, return an array answer where answer[i] is either
 * the queries[i]th smallest positive palindrome of length intLength or -1 if no such palindrome exists.
 * A palindrome is a number that reads the same backwards and forwards. Palindromes cannot have leading zeros.
 *
 * Example 1:
 * Input: queries = [1,2,3,4,5,90], intLength = 3
 * Output: [101,111,121,131,141,999]
 * Explanation:
 * The first few palindromes of length 3 are:
 * 101, 111, 121, 131, 141, 151, 161, 171, 181, 191, 201, ...
 * The 90th palindrome of length 3 is 999.
 *
 * Example 2:
 * Input: queries = [2,4,6], intLength = 4
 * Output: [1111,1331,1551]
 * Explanation:
 * The first six palindromes of length 4 are:
 * 1001, 1111, 1221, 1331, 1441, and 1551.
 *
 * Constraints:
 * 1. 1 <= queries.length <= 5 * 10^4
 * 2. 1 <= queries[i] <= 10^9
 * 3. 1 <= intLength <= 15
 */
public class FindPalindromeWithFixedLength {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(kthPalindrome(new int[]{1, 2, 3, 4, 5, 90}, 3)));
    }

    /**
     * First Palindrome of length 4 = "10"+"01"
     * First Palindrome of length 3 = "10"+"_1" (without first character 0)
     * First half can range from 10 (which is 10^1) to 99 (which is 10^2-1)
     */
    public static long[] kthPalindrome(int[] queries, int intLength) {
        long[] result = new long[queries.length];
        int i = 0;
        for (int num : queries) {
            long half = (intLength + 1) / 2, start = (long) Math.pow(10, half - 1), end = (long) Math.pow(10, half) - 1;
            if (num <= (end - start + 1)) {
                String left = (start + (num - 1)) + "";
                String right = new StringBuffer(left).reverse().toString();
                // 1001 vs 10+(0)1
                result[i++] = Long.parseLong(left + right.substring(intLength % 2));
            } else {
                result[i++] = -1;
            }
        }
        return result;
    }
}
