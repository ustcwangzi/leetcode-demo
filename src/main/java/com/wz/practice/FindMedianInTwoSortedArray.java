package com.wz.practice;

/**
 * 给定两个有序数组arr1和arr2，已知两个数组的长度都为N，求两个数组中所有数的上中位数。
 * 上中位数：假设递增序列长度为n，若n为奇数，则上中位数为第n/2+1个数；否则为第n/2个数
 * [要求]
 * 时间复杂度为O(logN)，额外空间复杂度为O(1)
 *
 * 示例1
 * 输入：[1,2,3,4],[3,4,5,6]
 * 返回值：3
 * 说明：总共有8个数，上中位数是第4小的数，所以返回3。
 *
 * 示例2
 * 输入：[0,1,2],[3,4,5]
 * 返回值：2
 * 说明：总共有6个数，那么上中位数是第3小的数，所以返回2
 */
public class FindMedianInTwoSortedArray {
    public static void main(String[] args) {
        System.out.println(findMedianinTwoSortedAray(new int[]{1, 2, 3, 4}, new int[]{3, 4, 5, 6}));
        System.out.println(findMedianinTwoSortedAray(new int[]{0, 1, 2}, new int[]{3, 4, 5}));
    }

    /**
     * 二分查找
     */
    public static int findMedianinTwoSortedAray(int[] arr1, int[] arr2) {
        int index1 = 0, index2 = 0, result = 0;
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[index1] <= arr2[index2]) {
                result = arr1[index1++];
            } else {
                result = arr2[index2++];
            }
        }
        return result;
    }
}
