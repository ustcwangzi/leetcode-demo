package com.wz.binarysearch;

/**
 * Nearly everyone has used the Multiplication Table. The multiplication table of size m x n is an integer matrix mat where mat[i][j] == i * j (1-indexed).
 * Given three integers m, n, and k, return the kth smallest element in the m x n multiplication table.
 *
 * Example 1:
 * @link ../../../../resource/KthSmallestNumberInMultiplicationTable1.jpg
 * Input: m = 3, n = 3, k = 5
 * Output: 3
 * Explanation: The 5th smallest number is 3.
 *
 * Example 2:
 * @link ../../../../resource/KthSmallestNumberInMultiplicationTable2.jpg
 * Input: m = 2, n = 3, k = 6
 * Output: 6
 * Explanation: The 6th smallest number is 6.
 *
 * Constraints:
 * 1. 1 <= m, n <= 3 * 10^4
 * 2. 1 <= k <= m * n
 */
public class KthSmallestNumberInMultiplicationTable {
    public static void main(String[] args) {
        System.out.println(findKthNumber(3, 3, 5));
        System.out.println(findKthNumber(3, 3, 3));
        System.out.println(findKthNumber(2, 3, 6));
    }

    /**
     * 在 [1, m*n] 之间做二分查找，对于每个 mid，找到小于等于 mid 的元素个数
     */
    public static int findKthNumber(int m, int n, int k) {
        int left = 1, right = m * n, result = 0;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int count = getCount(m, n, mid);
            // 小于等于 mid 的元素个数不足 k，需要更大的 mid
            if (count < k) {
                left = mid + 1;
            } else {
                result = mid;
                right = mid - 1;
            }
        }
        return result;
    }

    /**
     * 计算 m*n 的乘法表中小于等于 value 的元素个数
     */
    private static int getCount(int m, int n, int value) {
        int count = 0;
        for (int i = 1; i <= m; i++) {
            count += Math.min(value / i, n);
        }
        return count;
    }
}
