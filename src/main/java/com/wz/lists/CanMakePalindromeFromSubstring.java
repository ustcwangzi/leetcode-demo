package com.wz.lists;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string s, we make queries on substrings of s.
 * For each query queries[i] = [left, right, k], we may rearrange the substring s[left], ..., s[right],
 * and then choose up to k of them to replace with any lowercase English letter.
 * If the substring is possible to be a palindrome string after the operations above, the result of the query is true.
 * Otherwise, the result is false.
 * Return an array answer[], where answer[i] is the result of the i-th query queries[i].
 * Note that: Each letter is counted individually for replacement so if for example s[left..right] = "aaa", and k = 2,
 * we can only replace two of the letters.  (Also, note that the initial string s is never modified by any query.)
 *
 * Example :
 * Input: s = "abcda", queries = [[3,3,0],[1,2,0],[0,3,1],[0,3,2],[0,4,1]]
 * Output: [true,false,false,true,true]
 * Explanation:
 * queries[0] : substring = "d", is palidrome.
 * queries[1] : substring = "bc", is not palidrome.
 * queries[2] : substring = "abcd", is not palidrome after replacing only 1 character.
 * queries[3] : substring = "abcd", could be changed to "abba" which is palidrome.
 *              Also this can be changed to "baab" first rearrange it "bacd" then replace "cd" with "ab".
 * queries[4] : substring = "abcda", could be changed to "abcba" which is palidrome.
 *
 * Constraints:
 * 1 <= s.length, queries.length <= 10^5
 * 0 <= queries[i][0] <= queries[i][1] < s.length
 * 0 <= queries[i][2] <= s.length
 * s only contains lowercase English letters.
 */
public class CanMakePalindromeFromSubstring {
    public static void main(String[] args) {
        int[][] queries = new int[][]{
                {3, 3, 0}, {1, 2, 0}, {0, 3, 1}, {0, 3, 2}, {0, 4, 1}
        };
        System.out.println(canMakePaliQueries("abcda", queries));
    }

    /**
     * 对于给定一个query = [left,right,k]，很容易能求出这个区间内每个字符出现的次数，如果某个字符出现了偶数次，
     * 那说明不需要经过任何改变，这个字符就能组成回文(因为允许重新排列)。所以这里只需要计算有多少个字符出现的次数是奇数，
     * 假设有 x 个字符出现的次数为奇数，那么至少就需要经过 x/2 次改变，才能形成回文，和 k 进行比较即可。
     * 对于求区间内每个字符出现的次数，可以使用动态规划，dp[i][26]表示 0～i 字符串中的每个字符出现次数，
     * 那么 i～j 字符串中的每个字符出现次数为：dp[j][0...26] - dp[i-1][0...26]。然后统计奇数字符的个数，即可得出需要替换的字符个数
     */
    public static List<Boolean> canMakePaliQueries(String s, int[][] queries) {
        int n = s.length();
        // 0～i 字符串中每个字符的出现次数
        int[][] dp = new int[n][26];
        for (int i = 0; i < s.length(); i++) {
            if (i > 0) {
                dp[i] = dp[i - 1].clone();
            }
            dp[i][s.charAt(i) - 'a']++;
        }

        List<Boolean> result = new ArrayList<>(queries.length);
        for (int[] query : queries) {
            int oddNum = getOddNum(dp, query[0], query[1]);
            result.add(oddNum / 2 <= query[2]);
        }

        return result;
    }

    /**
     * 计算 i～j 字符串内出现次数为奇数的字符个数
     */
    private static int getOddNum(int[][] dp, int i, int j) {
        int result = 0;
        if (i == 0) {
            for (int k = 0; k < 26; k++) {
                if (dp[j][k] % 2 != 0) {
                    result++;
                }
            }
            return result;
        }

        for (int k = 0; k < 26; k++) {
            if ((dp[j][k] - dp[i - 1][k]) % 2 != 0) {
                result++;
            }
        }
        return result;
    }
}
