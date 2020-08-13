package com.wz.array;

import java.util.Arrays;

/**
 * Given an array arr, replace every element in that array with the greatest element among the elements to its right, and replace the last element with -1.
 * After doing so, return the array.
 *
 * Example 1:
 * Input: arr = [17,18,5,4,6,1]
 * Output: [18,6,6,6,1,-1]
 */
public class ReplaceElementsWithGreatestElementOnRightSide {
    public static void main(String[] args) {
        int[] arr = new int[]{17, 18, 5, 4, 6, 1};
        System.out.println(Arrays.toString(replaceElements(arr)));
    }

    /**
     * 逆序遍历求当前最大值 curMax 填充到结果数组 result 中即可
     */
    public static int[] replaceElements(int[] arr) {
        int[] result = new int[arr.length];
        result[arr.length - 1] = -1;
        int curMax = arr[arr.length - 1];

        for (int i = arr.length - 2; i >= 0; i--) {
            curMax = Math.max(curMax, arr[i + 1]);
            result[i] = curMax;
        }
        return result;
    }
}
