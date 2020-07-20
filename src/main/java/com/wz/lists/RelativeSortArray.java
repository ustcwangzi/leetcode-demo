package com.wz.lists;

import java.util.Arrays;

/**
 * Given two arrays arr1 and arr2, the elements of arr2 are distinct, and all elements in arr2 are also in arr1.
 * Sort the elements of arr1 such that the relative ordering of items in arr1 are the same as in arr2.
 * Elements that don't appear in arr2 should be placed at the end of arr1 in ascending order.
 *
 * Example 1:
 * Input: arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
 * Output: [2,2,2,1,4,3,3,9,6,7,19]
 *
 * Constraints:
 * arr1.length, arr2.length <= 1000
 * 0 <= arr1[i], arr2[i] <= 1000
 * Each arr2[i] is distinct.
 * Each arr2[i] is in arr1.
 */
public class RelativeSortArray {
    public static void main(String[] args) {
        int[] arr1 = new int[]{2, 3, 1, 3, 2, 4, 6, 7, 9, 2, 19}, arr2 = new int[]{2, 1, 4, 3, 9, 6};
        System.out.println(Arrays.toString(relativeSortArray(arr1, arr2)));
    }

    /**
     * 桶排序, 统计 arr1 中各元素的出现次数, 然后按照出现次数先存放 arr2 中的元素, 再按顺序存放 arr1 中的元素
     */
    public static int[] relativeSortArray(int[] arr1, int[] arr2) {
        int bucketSize = 1001;
        // 统计次数
        int[] count = new int[bucketSize];
        for (int num : arr1) {
            count[num]++;
        }

        int index = 0;
        // 存放 arr2 中的元素
        for (int num : arr2) {
            while (count[num] > 0) {
                count[num]--;
                arr1[index++] = num;
            }
        }

        // 存放 arr1 中的剩余元素
        for (int i = 0; i < bucketSize; ++i) {
            while (count[i]-- > 0) {
                arr1[index++] = i;
            }
        }

        return arr1;
    }
}
