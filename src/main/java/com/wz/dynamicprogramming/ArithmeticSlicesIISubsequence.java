package com.wz.dynamicprogramming;

import java.util.HashMap;
import java.util.Map;

/**
 * A sequence of numbers is called arithmetic if it consists of at least three elements and if the difference between any two consecutive elements is the same.
 *
 * For example, these are arithmetic sequences:
 *
 * 1, 3, 5, 7, 9
 * 7, 7, 7, 7
 * 3, -1, -5, -9
 * The following sequence is not arithmetic.
 *
 * 1, 1, 2, 5, 7
 *
 * A zero-indexed array A consisting of N numbers is given. A subsequence slice of that array is any sequence of integers (P0, P1, ..., Pk) such that 0 ≤ P0 < P1 < ... < Pk < N.
 *
 * A subsequence slice (P0, P1, ..., Pk) of array A is called arithmetic if the sequence A[P0], A[P1], ..., A[Pk-1], A[Pk] is arithmetic. In particular, this means that k ≥ 2.
 *
 * The function should return the number of arithmetic subsequence slices in the array A.
 *
 * The input contains N integers. Every integer is in the range of -231 and 231-1 and 0 ≤ N ≤ 1000. The output is guaranteed to be less than 231-1.
 *
 *
 * Example:
 *
 * Input: [2, 4, 6, 8, 10]
 *
 * Output: 7
 *
 * Explanation:
 * All arithmetic subsequence slices are:
 * [2,4,6]
 * [4,6,8]
 * [6,8,10]
 * [2,4,6,8]
 * [4,6,8,10]
 * [2,4,6,8,10]
 * [2,6,10]
 */
public class ArithmeticSlicesIISubsequence {
    public static void main(String[] args) {
        System.out.println(numberOfArithmeticSlices(new int[]{2, 4, 6, 8, 10}));
    }

    /**
     * 建立一维数组 dp，数组里的元素不是数字，而是 HashMap，建立等差数列的差值和当前位置之前差值相同的数字个数之间的映射
     * 遍历数组中的所有数字，对于当前遍历到的数字，又从开头遍历到当前数字，计算两个数字之差 diff，如果越界了不做任何处理，如果没越界，
     * 让 dp[i] 中 diff 的差值映射自增1，因为此时 A[i] 前面有相差为 diff 的A[j]，所以映射值要加 1
     * 然后看 dp[j] 中是否有 diff 的映射，若有，说明此时相差为 diff 的数字至少有三个了，已经能构成等差数列了，
     * 将 dp[j][diff] 加入结果 result，然后再更新 dp[i][diff]
     * 以 [2，4，6，8，10] 为例，因为 2 之前没有数字了，所以从 4 开始，遍历前面的数字，是 2，二者差值为 2，
     * 那么在 dp[1] 的 HashMap 就可以建立 2->1 的映射，表示 4 之前有 1 个差值为 2 的数字
     * 那么现在 i=2 指向 6 了，遍历前面的数字，第一个数是 2，二者相差 4，那么在 dp[2] 的 HashMap 就可以建立 4->1 的映射，
     * 第二个数是 4，二者相差 2，那么先在 dp[2] 的 HashMap 建立 2->1 的映射，由于 dp[1] 的HashMap中也有差值为 2 的映射，2->1，
     * 那么说明此时至少有三个数字差值相同，即这里的 [2 4 6]，将 dp[1] 中的映射值加入结果 result，
     * 然后当前 dp[2] 中的映射值加上 dp[1] 中的映射值，以此类推，最终的各个位置的映射关系如下所示：
     *
     * 2     4     6        8       10
     *      2->1  4->1     6->1    8->1
     *            2->[2]   4->1    6->1
     *                     2->[3]  4->[2]
     *                             2->[4]
     *
     * 最终累计出来的结果是跟上面 [] 的数字相关，分别对应着如下的等差数列：
     * 2->2：[2,4,6]
     * 2->3：[2,4,6,8]    [4,6,8]
     * 4->2：[2,6,10]
     * 2->4：[2,4,6,8,10] [4,6,8,10] [6,8,10]
     */
    public static int numberOfArithmeticSlices(int[] A) {
        if (A == null || A.length < 3) {
            return 0;
        }
        int n = A.length, result = 0;
        Map<Integer, Integer>[] dp = new Map[n];
        for (int i = 0; i < n; i++) {
            dp[i] = new HashMap<>();
            for (int j = 0; j < i; j++) {
                long delta = (long) A[i] - A[j];
                if (delta < Integer.MIN_VALUE || delta > Integer.MAX_VALUE) {
                    continue;
                }
                int diff = (int) delta;
                int count = dp[j].getOrDefault(diff, 0);
                result += count;
                dp[i].put(diff, dp[i].getOrDefault(diff, 0) + count + 1);
            }
        }
        return result;
    }
}
