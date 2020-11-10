package com.wz.string;

/**
 * Given a binary string s (a string consisting only of '0's and '1's),
 * we can split s into 3 non-empty strings s1, s2, s3 (s1+ s2+ s3 = s).
 * Return the number of ways s can be split such that the number of characters '1' is the same in s1, s2, and s3.
 * Since the answer may be too large, return it modulo 10^9 + 7.
 *
 * Example 1:
 * Input: s = "10101"
 * Output: 4
 * Explanation: There are four ways to split s in 3 parts where each part contain the same number of letters '1'.
 * "1|010|1"
 * "1|01|01"
 * "10|10|1"
 * "10|1|01"
 *
 * Example 2:
 * Input: s = "1001"
 * Output: 0
 *
 * Constraints:
 * 1. 3 <= s.length <= 10^5
 * 2. s[i] is '0' or '1'.
 */
public class NumberOfWaysToSplitString {
    public static void main(String[] args) {
        System.out.println(numWays("10101"));
    }

    /**
     * 要分两种情况考虑，第一种是全0的情况，用数学的方法可以计算出来
     * 另一种是统计1的个数，除以3得到每个分段的1的个数，统计第一段和第三段的组合数目，然后乘积就是最终的答案
     */
    public static int numWays(String s) {
        int mod = 1000000007, count = 0;
        char[] array = s.toCharArray();
        for (char cur : array) {
            if (cur == '1') {
                count++;
            }
        }

        // 无法拆分的情况
        if (count % 3 != 0 || array.length < 3) {
            return 0;
        }
        // 全是0的情况
        if (count == 0) {
            return (int) ((((long) (s.length() - 1) * (long) (s.length() - 2)) / 2) % mod);
        }

        int curCount = 0, left = 0, right = 0;
        // 统计第一部分
        for (char cur : array) {
            if (cur == '1') {
                curCount++;
            }
            if (curCount == count / 3) {
                left++;
            } else if (curCount > count / 3) {
                break;
            }
        }

        curCount = 0;
        // 统计第三部分
        for (int i = s.length() - 1; i >= 0; i--) {
            if (array[i] == '1') {
                curCount++;
            }
            if (curCount == count / 3) {
                right++;
            } else if (curCount > count / 3) {
                break;
            }
        }
        return (int) (((long) left * (long) right) % mod);
    }
}
