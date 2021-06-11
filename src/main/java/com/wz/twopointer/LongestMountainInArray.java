package com.wz.twopointer;

/**
 * You may recall that an array arr is a mountain array if and only if:
 * 1. arr.length >= 3
 * 2. There exists some index i (0-indexed) with 0 < i < arr.length - 1 such that:
 *    arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
 *    arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
 * Given an integer array arr, return the length of the longest subarray, which is a mountain. Return 0 if there is no mountain subarray.
 *
 * Example 1:
 * Input: arr = [2,1,4,7,3,2,5]
 * Output: 5
 * Explanation: The largest mountain is [1,4,7,3,2] which has length 5.
 *
 * Example 2:
 * Input: arr = [2,2,2]
 * Output: 0
 * Explanation: There is no mountain.
 *
 * Constraints:
 * 1. 1 <= arr.length <= 10^4
 * 2. 0 <= arr[i] <= 10^4
 */
public class LongestMountainInArray {
    public static void main(String[] args) {
        System.out.println(longestMountain(new int[]{2, 1, 4, 7, 3, 2, 5}));
        System.out.println(longestMountain(new int[]{2, 2, 2, 2}));
        System.out.println(longestMountain2(new int[]{2, 1, 4, 7, 3, 2, 5}));
        System.out.println(longestMountain2(new int[]{2, 2, 2, 2}));
    }

    /**
     *  动态规划
     *  问题转换为在数组中寻找一个位置，使得以此位置为终点的递增数组和以此位置为起点的递减数组的长度最大
     *  使用 up 和 down 两个数组分别记录以 i 为终点的递增数组（不包含i）、以 i 为起点的递减数组长度（不包含i）
     *  然后使用 up[i] + down[i] + 1 来更新结果即可（将 i 包含进去）
     */
    public static int longestMountain(int[] arr) {
        int n = arr.length, result = 0;
        int[] up = new int[n], down = new int[n];
        for (int i = n - 2; i >= 0; i--) {
            if (arr[i] > arr[i + 1]) {
                down[i] = down[i + 1] + 1;
            }
        }

        for (int i = 1; i < n; i++) {
            if (arr[i] > arr[i - 1]) {
                up[i] = up[i - 1] + 1;
            }
            if (up[i] > 0 && down[i] > 0) {
                result = Math.max(result, up[i] + down[i] + 1);
            }
        }

        return result;
    }

    /**
     * 双指针
     * 结果一定在 [1,n-2] 之间，对于每个 i，分别向左右进行遍历，找到最远的 left、right，然后用 right-left+1 更新结果
     */
    public static int longestMountain2(int[] arr) {
        int result = 0, n = arr.length;
        for (int i = 1; i < n - 1; i++) {
            if (arr[i - 1] < arr[i] && arr[i + 1] < arr[i]) {
                int left = i - 1, right = i + 1;
                while (left > 0 && arr[left - 1] < arr[left]) {
                    left--;
                }
                while (right < n - 1 && arr[right] > arr[right + 1]) {
                    right++;
                }
                result = Math.max(result, right - left + 1);
            }
        }
        return result;
    }
}
