package com.wz.dynamicprogramming;

/**
 * Your music player contains N different songs and she wants to listen to L (not necessarily different) songs during your trip.
 * You create a playlist so that:
 * Every song is played at least once
 * A song can only be played again only if K other songs have been played
 * Return the number of possible playlists.  As the answer can be very large, return it modulo 10^9 + 7.
 *
 * Example 1:
 * Input: N = 3, L = 3, K = 1
 * Output: 6
 * Explanation: There are 6 possible playlists. [1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1].
 *
 * Example 2:
 * Input: N = 2, L = 3, K = 0
 * Output: 6
 * Explanation: There are 6 possible playlists. [1, 1, 2], [1, 2, 1], [2, 1, 1], [2, 2, 1], [2, 1, 2], [1, 2, 2]
 *
 * Note:
 * 0 <= K < N <= L <= 100
 */
public class NumberOfMusicPlaylists {
    public static void main(String[] args) {
        System.out.println(numMusicPlaylists(3, 3, 1));
    }

    /**
     * dp[i][j] 表示总共放了i首歌，其中j首是不同的。下面来考虑状态转移方程，在加入一首歌的时候，此时有两种情况：
     * 当加入的是一首新歌，则表示之前的 i-1 首歌中有 j-1 首不同的歌曲，其所有的组合情况都可以加上这首新歌，那么当前其实有 N-(j-1) 首新歌可以选。
     * 当加入的是一首重复的歌，则表示之前的 i-1 首歌中已经有了 j 首不同的歌，那么若没有K的限制，则当前有 j 首重复的歌可以选。
     * 但是现在有了K的限制，意思是两首重复歌中间必须要有 K 首其他的歌，则当前只有 j-K 首可以选。而当 j<K 时，其实这种情况是为0的。
     * 综上所述可以得到状态转移方程：
     * dp[i][j] = dp[i-1][j-1]*(N-(j-1)) + dp[i-1][j]*(j-k)    (j > K)
     * dp[i][j] = dp[i-1][j-1]*(N-(j-1))   (j <= K)
     */
    public static int numMusicPlaylists(int N, int L, int K) {
        int mod = 1000000007;
        long[][] dp = new long[L + 1][N + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= L; ++i) {
            for (int j = 1; j <= N; ++j) {
                dp[i][j] = (dp[i - 1][j - 1] * (N - (j - 1))) % mod;
                if (j > K) {
                    dp[i][j] = (dp[i][j] + dp[i - 1][j] * (j - K) % mod) % mod;
                }
            }
        }
        return (int) dp[L][N];
    }
}
