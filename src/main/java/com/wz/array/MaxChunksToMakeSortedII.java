package com.wz.array;

/**
 * Given an array arr of integers (not necessarily distinct), we split the array into some number of "chunks" (partitions),
 * and individually sort each chunk.  After concatenating them, the result equals the sorted array.
 * What is the most number of chunks we could have made?
 *
 * Example 1:
 * Input: arr = [5,4,3,2,1]
 * Output: 1
 * Explanation:
 * Splitting into two or more chunks will not return the required result.
 * For example, splitting into [5, 4], [3, 2, 1] will result in [4, 5, 1, 2, 3], which isn't sorted.
 *
 * Example 2:
 * Input: arr = [2,1,3,4,4]
 * Output: 4
 * Explanation:
 * We can split into two chunks, such as [2, 1], [3, 4, 4].
 * However, splitting into [2, 1], [3], [4], [4] is the highest number of chunks possible.
 */
public class MaxChunksToMakeSortedII {
    public static void main(String[] args) {
        int[] arr = new int[]{5, 4, 3, 2, 1};
        System.out.println(maxChunksToSorted(arr));

        arr = new int[]{2, 1, 3, 4, 4};
        System.out.println(maxChunksToSorted(arr));
    }

    /**
     * 思路与{@link MaxChunksToMakeSorted}类似
     * 仅仅在判断是否可拆分的逻辑有所调整：若当前子数组的最大值小于等于下一位置的最小值，可拆分
     */
    public static int maxChunksToSorted(int[] arr) {
        int n = arr.length;
        // [i, n-1]区间中的最小值
        int[] rightMin = new int[n];
        rightMin[n - 1] = arr[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMin[i] = Math.min(rightMin[i + 1], arr[i]);
        }

        int result = 1;
        int curMax = arr[0];
        for (int i = 1; i < n; i++) {
            // 可拆分
            if (curMax <= rightMin[i]) {
                result++;
            }
            curMax = Math.max(curMax, arr[i]);
        }

        return result;
    }
}
