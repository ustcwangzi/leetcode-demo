package com.wz.greedy;

/**
 * You are given a string s consisting only of characters 'a' and 'b'.
 * You can delete any number of characters in s to make s balanced.
 * s is balanced if there is no pair of indices (i,j) such that i < j and s[i] = 'b' and s[j]= 'a'.
 * Return the minimum number of deletions needed to make s balanced.
 *
 * Example 1:
 * Input: s = "aababbab"
 * Output: 2
 * Explanation: You can either:
 * Delete the characters at 0-indexed positions 2 and 6 ("aababbab" -> "aaabbb"), or
 * Delete the characters at 0-indexed positions 3 and 6 ("aababbab" -> "aabbbb").
 *
 * Constraints:
 * 1. 1 <= s.length <= 10^5
 * 2. s[i] is 'a' or 'b'.
 */
public class MinimumDeletionsToMakeStringBalanced {
    public static void main(String[] args) {
        System.out.println(minimumDeletions("aababbab"));
    }

    /**
     * 题目意思是任意一个 a 都在任意一个 b 之前，只含 a 或只含 b 也合法的
     * 枚举分界点即可，比如枚举 s[0,i] 都是 a，而 s[i+1,n-1] 都是 b，那么只需要知道 s[0,i] 里有多少个 b，s[i+1,n-1] 里有多少个 a 即可
     * 先计算 a 的总个数，将结果初始化为 a 的总个数，即将所有 a 都去除的情况
     * 然后遍历每个分界点 i，计算 s[0,i] 中 b 的个数 bCount，以及 s[i+1,n-1] 中 a 的个数 aCount
     * 这里可以快速计算 s[i+1,n-1] 中 a 的个数，用之前已计算的 a 的总个数减去现在 a 的个数即可
     * 基于当前分界点下，需要去除的字符总个数就是 aCount + bCount
     */
    public static int minimumDeletions(String s) {
        int aCount = 0, bCount = 0;
        for (int i = 0; i < s.length(); i++) {
            aCount += s.charAt(i) == 'a' ? 1 : 0;
        }

        int result = aCount;
        for (int i = 0; i < s.length(); i++) {
            bCount += s.charAt(i) == 'b' ? 1 : 0;
            aCount -= s.charAt(i) == 'a' ? 1 : 0;
            result = Math.min(result, bCount + aCount);
        }
        return result;
    }
}
