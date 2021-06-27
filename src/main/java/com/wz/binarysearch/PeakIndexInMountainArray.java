package com.wz.binarysearch;

/**
 * Let's call an array arr a mountain if the following properties hold:
 * 1. arr.length >= 3
 * 2. There exists some i with 0 < i < arr.length - 1 such that:
 *    arr[0] < arr[1] < ... arr[i-1] < arr[i]
 *    arr[i] > arr[i+1] > ... > arr[arr.length - 1]
 * Given an integer array arr that is guaranteed to be a mountain,
 * return any i such that arr[0] < arr[1] < ... arr[i - 1] < arr[i] > arr[i + 1] > ... > arr[arr.length - 1].
 *
 * Example 1:
 * Input: arr = [0,2,1,0]
 * Output: 1
 *
 * Example 2:
 * Input: arr = [3,4,5,1]
 * Output: 2
 *
 * Constraints:
 * 1. 3 <= arr.length <= 10^4
 * 2. 0 <= arr[i] <= 10^6
 * 3. arr is guaranteed to be a mountain array.
 */
public class PeakIndexInMountainArray {
    public static void main(String[] args) {
        int[] arr = new int[]{0, 2, 1, 0};
        System.out.println(peakIndexInMountainArray(arr));
        System.out.println(peakIndexInMountainArray2(arr));
    }

    /**
     * 直接遍历
     */
    public static int peakIndexInMountainArray(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 二分查找，与 {@link FindSmallestLetterGreaterThanTarget} 类似
     * arr[mid] < arr[mid+1] 时，说明左侧递增，将 left 赋值为 mid+1
     * 否则，说明右侧递减，将 right 赋值为 mid-1，缩小范围
     */
    public static int peakIndexInMountainArray2(int[] arr) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] < arr[mid + 1]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
}
