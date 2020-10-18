package com.wz.string;

/**
 * Given a string S, find the number of different non-empty palindromic subsequences in S, and return that number modulo 10^9 + 7.
 * A subsequence of a string S is obtained by deleting 0 or more characters from S.
 * A sequence is palindromic if it is equal to the sequence reversed.
 * Two sequences A_1, A_2, ... and B_1, B_2, ... are different if there is some i for which A_i != B_i.
 *
 * Example:
 * Input:
 * S = 'bccb'
 * Output: 6
 * Explanation:
 * The 6 different non-empty palindromic subsequences are 'b', 'c', 'bb', 'cc', 'bcb', 'bccb'.
 * Note that 'bcb' is counted only once, even though it occurs twice.
 */
public class CountDifferentPalindromicSubsequences {
    public static void main(String[] args) {
        System.out.println(countPalindromicSubsequences("bccb"));
    }

    /**
     * dp[i][j] 表示子字符串 [i, j] 中的不同回文子序列的个数，初始化 dp[i][i] 为1，因为任意一个单个字符就是一个回文子序列
     * dp 的更新顺序不是正向，也不是逆向，而是斜着更新，从对角线开始左下角向右上角更新
     *   b c c b
     * b 1 2 3 6
     * c 0 1 2 3
     * c 0 0 1 2
     * b 0 0 0 1
     * 这样更新的好处是，更新当前位置时，其左，下，和左下位置的 dp 值均已存在，而当前位置的 dp 值需要用到这三个位置的 dp 值。
     * 观察上面的 dp 数组，可以发现当 S[i] 不等于 S[j] 的时候，dp[i][j] = dp[i][j - 1] + dp[i + 1][j] - dp[i + 1][j - 1]，
     * 即当前的 dp 值等于左边值加下边值减去左下值，因为算左边值的时候包括了左下的所有情况，而算下边值的时候也包括了左下值的所有情况，
     * 那么左下值就多算了一遍，所以要减去。而当 S[i] 等于 S[j] 的时候，情况就比较复杂了，需要分情况讨论，
     * 因为不知道中间还有几个和 S[i] 相等的值。举个简单的例子，比如 "aba" 和 "aaa"，当 i = 0, j = 2 的时候，
     * 两个字符串均有 S[i] == S[j]，此时二者都新增两个子序列 "a" 和 "aa"，但是 "aba" 中间的 "b" 就可以加到结果 res 中，
     * 而 "aaa" 中的 "a" 就不能加了，因为和外层的单独 "a" 重复了。目标就要找到中间重复的 "a"。所以让 left = i + 1, right = j - 1，
     * 然后对 left 进行 while 循环，如果 left <= right, 且 S[left] != S[i] 的时候，left 向右移动一个；同理，对 right 进行 while 循环，
     * 如果 left <= right, 且 S[right] != S[i] 的时候，left 向左移动一个。这样最终 left 和 right 值就有三种情况：
     * 1. 当 left > righ 时，说明中间没有和 S[i] 相同的字母了，就是 "aba" 这种情况，那么就有 dp[i][j] = dp[i + 1][j - 1] * 2 + 2，
     *    其中 dp[i + 1][j - 1] 是中间部分的回文子序列个数，为啥要乘2呢，因为中间的所有子序列可以单独存在，也可以再外面包裹上字母a，
     *    所以是成对出现的，要乘2。加2的原因是外层的 "a" 和 "aa" 也要统计上。
     * 2. 当 left = right 时，说明中间只有一个和 S[i] 相同的字母，就是 "aaa" 这种情况，那么有 dp[i][j] = dp[i + 1][j - 1] * 2 + 1，
     *    其中乘2的部分跟上面的原因相同，加1的原因是单个字母 "a" 的情况已经在中间部分算过了，外层就只能再加上个 "aa" 了。
     * 3. 当 left < right 时，说明中间至少有两个和 S[i] 相同的字母，就是 "aabaa" 这种情况，那么有
     *    dp[i][j] = dp[i + 1][j - 1] * 2 - dp[left + 1][right - 1]，其中乘2的部分跟上面的原因相同，
     *    要减去 left 和 right 中间部分的子序列个数的原因是其被计算了两遍，要将多余的减掉。比如对于  "aabaa"，当检测到 S[0] == S[4] 时，
     *    是要根据中间的 "aba" 的回文序列个数来计算，共有四种，分别是 "a", "b", "aa", "aba"，将其分别在左右两边加上a的话，
     *    可以得到 "aaa", "aba", "aaaa", "aabaa"，发现 "aba" 出现了两次了，这就是要将 dp[2][2] (left = 1, right = 3) 减去的原因。
     */
    public static int countPalindromicSubsequences(String S) {
        int n = S.length(), M = (int) (1e9 + 7);
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; ++i) {
            dp[i][i] = 1;
        }
        for (int len = 1; len < n; ++len) {
            for (int i = 0; i < n - len; ++i) {
                int j = i + len;
                if (S.charAt(i) == S.charAt(j)) {
                    int left = i + 1, right = j - 1;
                    while (left <= right && S.charAt(left) != S.charAt(i)) {
                        ++left;
                    }
                    while (left <= right && S.charAt(right) != S.charAt(i)) {
                        --right;
                    }
                    if (left > right) {
                        dp[i][j] = dp[i + 1][j - 1] * 2 + 2;
                    } else if (left == right) {
                        dp[i][j] = dp[i + 1][j - 1] * 2 + 1;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1] * 2 - dp[left + 1][right - 1];
                    }
                } else {
                    dp[i][j] = dp[i][j - 1] + dp[i + 1][j] - dp[i + 1][j - 1];
                }
                dp[i][j] = (dp[i][j] < 0) ? dp[i][j] + M : dp[i][j] % M;
            }
        }
        return dp[0][n - 1];
    }
}
