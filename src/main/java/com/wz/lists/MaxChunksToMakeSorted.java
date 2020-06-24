package com.wz.lists;

/**
 * Given an array arr that is a permutation of [0, 1, ..., arr.length - 1],
 * we split the array into some number of "chunks" (partitions), and individually sort each chunk.
 * After concatenating them, the result equals the sorted array.
 * What is the most number of chunks we could have made?
 *
 * Example 1:
 * Input: arr = [4,3,2,1,0]
 * Output: 1
 * Explanation:
 * Splitting into two or more chunks will not return the required result.
 * For example, splitting into [4, 3], [2, 1, 0] will result in [3, 4, 0, 1, 2], which isn't sorted.
 *
 * Example 2:
 * Input: arr = [1,0,2,3,4]
 * Output: 4
 * Explanation:
 * We can split into two chunks, such as [1, 0], [2, 3, 4].
 * However, splitting into [1, 0], [2], [3], [4] is the highest number of chunks possible.
 */
public class MaxChunksToMakeSorted {
    public static void main(String[] args) {
        int[] arr = new int[]{4, 3, 2, 1, 0};
        System.out.println(maxChunksToSorted(arr));

        arr = new int[]{1, 0, 2, 3, 4};
        System.out.println(maxChunksToSorted(arr));
    }

    /**
     * 将一个数组分成尽可能多的子数组，使得这些子数组分别排序后再首尾相连，与原始数组直接排序的结果相同。
     * 分析可知，要保证上述特性，那么前一个子数组的最大值需要小于后一个子数组的最小值
     * 先从后往前遍历，记录原始数组的最小值，然后从前往后遍历，若当前子数组的最大值小于下一位置的最小值，则可以分成一个子数组，否则不行
     */
    public static int maxChunksToSorted(int[] arr) {
        int n = arr.length;
        // [i, n-1]区间中的最小值
        int[] leftMax = new int[n];
        leftMax[n - 1] = arr[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            leftMax[i] = Math.min(leftMax[i + 1], arr[i]);
        }

        int result = 1;
        // 当前子数组最大值
        int max = arr[0];
        for (int i = 1; i < n; i++) {
            // 可拆分
            if (max < leftMax[i]) {
                result++;
            }
            max = Math.max(max, arr[i]);
        }

        return result;
    }
}
