package com.wz.array;

/**
 * Given an integer array arr, remove a subarray (can be empty) from arr such that the remaining elements in arr are non-decreasing.
 * A subarray is a contiguous subsequence of the array. Return the length of the shortest subarray to remove.
 *
 * Example 1:
 * Input: arr = [1,2,3,10,4,2,3,5]
 * Output: 3
 * Explanation: The shortest subarray we can remove is [10,4,2] of length 3. The remaining elements after that will be [1,2,3,3,5] which are sorted.
 * Another correct solution is to remove the subarray [3,10,4].
 *
 * Example 2:
 * Input: arr = [5,4,3,2,1]
 * Output: 4
 * Explanation: Since the array is strictly decreasing, we can only keep a single element.
 * Therefore we need to remove a subarray of length 4, either [5,4,3,2] or [4,3,2,1].
 *
 * Constraints:
 * 1. 1 <= arr.length <= 10^5
 * 2. 0 <= arr[i] <= 10^9
 */
public class ShortestSubarrayToBeRemovedToMakeArraySorted {
    public static void main(String[] args) {
        System.out.println(findLengthOfShortestSubarray(new int[]{1, 2, 3, 10, 4, 2, 3, 5}));
        System.out.println(findLengthOfShortestSubarray(new int[]{5, 4, 3, 2, 1}));
    }

    /**
     * 移除一个子数组，使剩下的元素构成一个非递减数列，求移除的最小长度
     * 因为要求移除的子数组必须是连续的，所以只能移除中间元素，先从左向右遍历，如果满足条件则 left++
     * 再从右向左遍历，如果满足条件则 right--，此时 [0...left] 和 [right...n-1] 都满足条件
     * 可以移除 [left+1...n-1] 或 [0...right-1] 来使得剩余元素满足条件
     * 还可以检查一下 [0...left] 和 [right...n-1] 是否可以组成更长的结果，从而减少删除的元素个数
     */
    public static int findLengthOfShortestSubarray(int[] arr) {
        int left = 0, right = arr.length - 1, n = arr.length;
        while (left + 1 < n && arr[left] <= arr[left + 1]) {
            left++;
        }
        if (left == n - 1) {
            return 0;
        }
        while (right > left && arr[right - 1] <= arr[right]) {
            right--;
        }

        // 移除 [left+1...n-1] 或 [0...right-1]
        int result = Math.min(n - left - 1, right);

        int i = 0, j = right;
        while (i <= left && j < n) {
            // 说明 [0...i] 和 [j...left] 能够合并，移除 [i+1...j-1]
            if (arr[i] <= arr[j]) {
                result = Math.min(result, j - i - 1);
                i++;
            } else {
                j++;
            }
        }
        return result;
    }
}
