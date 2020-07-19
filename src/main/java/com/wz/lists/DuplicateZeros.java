package com.wz.lists;

import java.util.Arrays;

/**
 * Given a fixed length array arr of integers, duplicate each occurrence of zero, shifting the remaining elements to the right.
 * Note that elements beyond the length of the original array are not written.
 * Do the above modifications to the input array in place, do not return anything from your function.
 *
 * Example 1:
 * Input: [1,0,2,3,0,4,5,0]
 * Output: null
 * Explanation: After calling your function, the input array is modified to: [1,0,0,2,3,0,0,4]
 *
 * Example 2:
 * Input: [1,2,3]
 * Output: null
 * Explanation: After calling your function, the input array is modified to: [1,2,3]
 */
public class DuplicateZeros {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 0, 2, 3, 0, 4, 5, 0};
        duplicateZeros(arr);
        System.out.println(Arrays.toString(arr));

        arr = new int[]{1, 2, 3};
        duplicateZeros(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 遇到0就将后续的元素全部右移，然后将下一个元素置为0
     */
    public static void duplicateZeros(int[] arr) {
        int index = 0;
        while (index < arr.length - 1) {
            if (arr[index] == 0) {
                for (int i = arr.length - 1; i > index; i--) {
                    arr[i] = arr[i - 1];
                }
                arr[++index] = 0;
            }
            index++;
        }
    }
}
