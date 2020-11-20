package com.wz.dynamicprogramming;

import java.util.Arrays;

/**
 * You have a number of envelopes with widths and heights given as a pair of integers (w, h).
 * One envelope can fit into another if and only if both the width and height of one envelope is greater than the width and height of the other envelope.
 * What is the maximum number of envelopes can you Russian doll? (put one inside other)
 *
 * Note:
 * Rotation is not allowed.
 *
 * Example:
 * Input: [[5,4],[6,4],[6,7],[2,3]]
 * Output: 3
 * Explanation: The maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).
 */
public class RussianDollEnvelopes {
    public static void main(String[] args) {
        int[][] envelopes = new int[][]{{5, 4}, {6, 4}, {6, 7}, {2, 3}};
        System.out.println(maxEnvelopes(envelopes));
    }

    /**
     * 思路与 {@link LongestIncreasingSubsequence} 类似
     * 先要给所有的信封按从小到大排序，首先根据宽度从小到大排，如果宽度相同，那么按照高度从小到大排
     * 然后遍历，对于每一个信封，都遍历其前面所有的信封，如果当前信封的长和宽都比前面那个信封的大，那么更新 dp[i] = max(dp[i], dp[j]+1)
     */
    public static int maxEnvelopes(int[][] envelopes) {
        Arrays.parallelSort(envelopes, (o1, o2) -> {
            if (o1[0] != o2[0]) {
                return o1[0] - o2[0];
            }
            return o1[1] - o2[1];
        });

        int result = 0, n = envelopes.length;
        int[] dp = new int[n];
        for (int i = 0; i < n; ++i) {
            dp[i] = 1;
            // 遍历前面所有信封，找到长和宽都比自己小的用来更新 dp[i]
            for (int j = 0; j < i; ++j) {
                if (envelopes[i][0] > envelopes[j][0] && envelopes[i][1] > envelopes[j][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            result = Math.max(result, dp[i]);
        }
        return result;
    }
}
