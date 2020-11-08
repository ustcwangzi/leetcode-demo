package com.wz.string;

/**
 * Run-length encoding is a string compression method that works by replacing consecutive identical characters
 * (repeated 2 or more times) with the concatenation of the character and the number marking the count of the
 * characters (length of the run). For example, to compress the string "aabccc" we replace "aa" by "a2" and
 * replace "ccc" by "c3". Thus the compressed string becomes "a2bc3".
 * Notice that in this problem, we are not adding '1' after single characters.
 * Given a string s and an integer k. You need to delete at most k characters from s such that the run-length encoded version of s has minimum length.
 * Find the minimum length of the run-length encoded version of s after deleting at most k characters.
 *
 * Example 1:
 * Input: s = "aaabcccd", k = 2
 * Output: 4
 * Explanation: Compressing s without deleting anything will give us "a3bc3d" of length 6.
 * Deleting any of the characters 'a' or 'c' would at most decrease the length of the compressed string to 5,
 * for instance delete 2 'a' then we will have s = "abcccd" which compressed is abc3d.
 * Therefore, the optimal way is to delete 'b' and 'd', then the compressed version of s will be "a3c3" of length 4.
 *
 * Example 2:
 * Input: s = "aabbaa", k = 2
 * Output: 2
 * Explanation: If we delete both 'b' characters, the resulting compressed string would be "a4" of length 2.
 *
 * Constraints:
 * 1. 1 <= s.length <= 100
 * 2. 0 <= k <= s.length
 * 3. s contains only lowercase English letters.
 */
public class StringCompressionII {
    public static void main(String[] args) {
        System.out.println(getLengthOfOptimalCompression("aaabcccd", 2));
    }

    /**
     * 设状态 f(i,j,l,c) 表示编码了前 i 个字符，删除了 j 个字符，且结尾由 l 个字符 c 组成时的最短编码距离。
     * 假设字符串的有效下标从 1 开始。
     * 初始时，f(0,0,0,c)=0，表示在没有任何字符时，编码长度为 0。其余状态都为正无穷。
     * 转移时，分为两种情况：
     * 如果 j>0，则可以将当前字符删除，所以可以直接转移 f(i,j,l,c)=min(f(i,j,l,c),f(i−1,j−1,l,c))
     * 如果选择不删除当前字符，则又有两种情况：
     * 如果当前字符 ch 和上一次结尾的字符 c 相同，则可以接在 c 后边，此时要求 l>1。如果 l 为 2，10 或 100 时，转移时需要额外延长加 1。
     * 否则，只能以 ch 结尾，转移 f(i,j,1,ch)=min(f(i,j,1,ch),f(i−1,j,l,c)+1)
     * 最终答案为 min(f(n,j,l,c))。需要枚举 j,l,c 找到最小值。
     */
    public static int getLengthOfOptimalCompression(String s, int k) {
        char[] array = s.toCharArray();
        int n = s.length();
        Integer[][][][] dp = new Integer[n][k + 1][27][n + 1];
        return dfs(dp, array, 0, k, 0, 0);
    }

    private static int dfs(Integer[][][][] dp, char[] array, int start, int k, int pre, int pre_num) {
        if (k < 0) {
            return Integer.MAX_VALUE;
        } else if (k >= pre_num + array.length - start) {
            return 0;
        }

        if (start == array.length) {
            return pre_num == 1 ? 1 : (1 + String.valueOf(pre_num).length());
        }

        if (dp[start][k][pre][pre_num] != null) {
            return dp[start][k][pre][pre_num];
        }

        if ((array[start] - 'a') + 1 == pre) {
            int res1 = dfs(dp, array, start + 1, k, pre, pre_num + 1);
            dp[start][k][pre][pre_num] = res1;
        } else {
            int res2 = 0;
            if (pre != 0) {
                res2 = pre_num == 1 ? 1 : (1 + String.valueOf(pre_num).length());
            }
            res2 += dfs(dp, array, start + 1, k, array[start] - 'a' + 1, 1);

            int res3 = dfs(dp, array, start + 1, k - 1, pre, pre_num);
            dp[start][k][pre][pre_num] = Math.min(res2, res3);
        }

        return dp[start][k][pre][pre_num];
    }
}
