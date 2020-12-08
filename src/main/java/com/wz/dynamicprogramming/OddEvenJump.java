package com.wz.dynamicprogramming;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

/**
 * You are given an integer array A. From some starting index, you can make a series of jumps. The (1st, 3rd, 5th, ...)
 * jumps in the series are called odd-numbered jumps, and the (2nd, 4th, 6th, ...) jumps in the series are called even-numbered jumps.
 * Note that the jumps are numbered, not the indices.
 * You may jump forward from index i to index j (with i < j) in the following way:
 * During odd-numbered jumps (i.e., jumps 1, 3, 5, ...), you jump to the index j such that A[i] <= A[j] and A[j] is the
 * smallest possible value. If there are multiple such indices j, you can only jump to the smallest such index j.
 * During even-numbered jumps (i.e., jumps 2, 4, 6, ...), you jump to the index j such that A[i] >= A[j] and A[j] is the
 * largest possible value. If there are multiple such indices j, you can only jump to the smallest such index j.
 * It may be the case that for some index i, there are no legal jumps.
 * A starting index is good if, starting from that index, you can reach the end of the array (index A.length - 1) by
 * jumping some number of times (possibly 0 or more than once).
 * Return the number of good starting indices.
 *
 * Example 1:
 * Input: A = [10,13,12,14,15]
 * Output: 2
 * Explanation:
 * From starting index i = 0, we can make our 1st jump to i = 2 (since A[2] is the smallest among A[1], A[2], A[3],
 * A[4] that is greater or equal to A[0]), then we cannot jump any more.
 * From starting index i = 1 and i = 2, we can make our 1st jump to i = 3, then we cannot jump any more.
 * From starting index i = 3, we can make our 1st jump to i = 4, so we have reached the end.
 * From starting index i = 4, we have reached the end already.
 * In total, there are 2 different starting indices i = 3 and i = 4, where we can reach the end with some number of jumps.
 *
 * Example 2:
 * Input: A = [2,3,1,1,4]
 * Output: 3
 * Explanation:
 * From starting index i = 0, we make jumps to i = 1, i = 2, i = 3:
 * During our 1st jump (odd-numbered), we first jump to i = 1 because A[1] is the smallest value in [A[1], A[2],
 * A[3], A[4]] that is greater than or equal to A[0].
 * During our 2nd jump (even-numbered), we jump from i = 1 to i = 2 because A[2] is the largest value in [A[2], A[3],
 * A[4]] that is less than or equal to A[1]. A[3] is also the largest value, but 2 is a smaller index, so we can
 * only jump to i = 2 and not i = 3
 * During our 3rd jump (odd-numbered), we jump from i = 2 to i = 3 because A[3] is the smallest value in [A[3], A[4]]
 * that is greater than or equal to A[2].
 * We can't jump from i = 3 to i = 4, so the starting index i = 0 is not good.
 * In a similar manner, we can deduce that:
 * From starting index i = 1, we jump to i = 4, so we reach the end.
 * From starting index i = 2, we jump to i = 3, and then we can't jump anymore.
 * From starting index i = 3, we jump to i = 4, so we reach the end.
 * From starting index i = 4, we are already at the end.
 * In total, there are 3 different starting indices i = 1, i = 3, and i = 4, where we can reach the end with some number of jumps.
 *
 * Constraints:
 * 1. 1 <= A.length <= 2 * 104
 * 2. 0 <= A[i] < 105
 */
public class OddEvenJump {
    public static void main(String[] args) {
        System.out.println(oddEvenJumps(new int[]{2, 3, 1, 1, 4}));
    }

    /**
     * 从某一位置出发，奇数步走到往前走到大于当前元素的最小元素上，偶数步往前走到小于当前元素的最大元素上，问有多少个位置能够走到最后一个元素的位置
     * 解题思路：
     * 从最后一个元素往前搜索，找到当前元素奇数步和偶数步走到的位置，记录当前元素走偶数步和奇数步能够走到最后一个元素，
     * 最后统计所有可以在第一步是奇数步，而且可以走到最后一个位置的元素数目
     * dp[i][0]表示当前元素，第一步按照偶数步规则能否走到最后，dp[i][1]表示当前元素，第一步按照奇数步规则是否能够走到最后
     */
    public static int oddEvenJumps(int[] A) {
        int len = A.length;
        boolean[][] record = new boolean[len][2];
        TreeMap<Integer, List<Integer>> map = new TreeMap<>();
        record[len - 1][1] = true;
        for (int i = len - 1; i > -1; i--) {
            int upper = -1;
            if (map.ceilingKey(A[i]) != null) {
                upper = map.ceilingKey(A[i]);
            }
            if (upper != -1) {
                int next = map.get(upper).get(0);
                if (next == len - 1) {
                    record[i][1] = true;
                } else {
                    record[i][1] = record[next][0];
                }
            }
            int under = -1;
            if (map.floorKey(A[i]) != null) {
                under = map.floorKey(A[i]);
            }
            if (under != -1) {
                int next = map.get(under).get(0);
                if (next == len - 1) {
                    record[i][0] = true;
                } else {
                    record[i][0] = record[next][1];
                }
            }
            if (!map.containsKey(A[i])) {
                map.put(A[i], new LinkedList<>());
            }
            map.get(A[i]).add(0, i);
        }
        int result = 0;
        for (int i = 0; i < len; i++) {
            if (record[i][1]) {
                result++;
            }
        }
        return result;
    }
}
