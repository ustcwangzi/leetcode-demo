package com.wz.queue;

import java.util.*;

/**
 * You are given an m x n matrix mat that has its rows sorted in non-decreasing order and an integer k.
 * You are allowed to choose exactly one element from each row to form an array.
 * Return the kth smallest array sum among all possible arrays.
 *
 * Example 1:
 * Input: mat = [[1,3,11],[2,4,6]], k = 5
 * Output: 7
 * Explanation: Choosing one element from each row, the first k smallest sum are:
 * [1,2], [1,4], [3,2], [3,4], [1,6]. Where the 5th sum is 7.
 *
 * Example 2:
 * Input: mat = [[1,3,11],[2,4,6]], k = 9
 * Output: 17
 *
 * Example 3:
 * Input: mat = [[1,10,10],[1,4,5],[2,3,6]], k = 7
 * Output: 9
 * Explanation: Choosing one element from each row, the first k smallest sum are:
 * [1,1,2], [1,1,3], [1,4,2], [1,4,3], [1,1,6], [1,5,2], [1,5,3]. Where the 7th sum is 9.
 *
 * Constraints:
 * 1. m == mat.length
 * 2. n == mat.length[i]
 * 3. 1 <= m, n <= 40
 * 4. 1 <= mat[i][j] <= 5000
 * 5. 1 <= k <= min(200, n^m)
 * 6. mat[i] is a non-decreasing array.
 */
public class FindTheKthSmallestSumOfMatrixWithSortedRows {
    public static void main(String[] args) {
        System.out.println(kthSmallest(new int[][]{{1, 3, 11}, {2, 4, 6}}, 5));
        System.out.println(kthSmallest(new int[][]{{1, 10, 10}, {1, 4, 5}, {2, 3, 6}}, 7));
    }

    /**
     * 对 {@link FindKPairsWithSmallestSums} 的扩展，将两行扩展到 n 行
     */
    public static int kthSmallest(int[][] mat, int k) {
        int[] row = mat[0];
        for (int i = 1; i < mat.length; ++i) {
            row = kSmallestPairSums(row, mat[i], k);
        }
        return row[k - 1];
    }

    private static int[] kSmallestPairSums(int[] nums1, int[] nums2, int k) {
        Queue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[2]));
        for (int i = 0; i < Math.min(k, nums1.length); i++) {
            queue.offer(new int[]{i, 0, nums1[i] + nums2[0]});
        }

        List<Integer> list = new ArrayList<>();
        while (!queue.isEmpty() && list.size() < k) {
            int[] cur = queue.poll();
            list.add(nums1[cur[0]] + nums2[cur[1]]);
            if (cur[1] + 1 < nums2.length) {
                queue.offer(new int[]{cur[0], cur[1] + 1, nums1[cur[0]] + nums2[cur[1] + 1]});
            }
        }
        return list.stream().mapToInt(i -> i).toArray();
    }
}
