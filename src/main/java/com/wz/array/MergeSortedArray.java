package com.wz.array;

import java.util.Arrays;

/**
 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
 * Note:
 * The number of elements initialized in nums1 and nums2 are m and n respectively.
 * You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2.
 * <p>
 * Example:
 * Input:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 * Output: [1,2,2,3,5,6]
 */
public class MergeSortedArray {
    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 3, 4, 0, 0, 0};
        int[] nums2 = new int[]{2, 5, 6};
        merge(nums1, 3, nums2, 3);
        System.out.println(Arrays.toString(nums1));

        nums1 = new int[]{4, 5, 6, 0, 0, 0};
        nums2 = new int[]{1, 2, 3};
        merge(nums1, 3, nums2, 3);
        System.out.println(Arrays.toString(nums1));
    }

    /**
     * 因为nums1足够大，可从两个数组的最大数也就是最后一个数开始比较，大的写入末尾，即nums1[m + n -1]，然后循环这个过程
     * 如果nums2的元素写完了，nums1剩下的元素正好在正确的位置，不用写了；如果nums1的元素都取完了，那剩下的nums2的元素可一次全部写进nums1
     */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        while (m > 0 & n > 0) {
            // 逆向遍历，较大的写入m+n-1
            if (nums1[m - 1] > nums2[n - 1]) {
                nums1[m + n - 1] = nums1[m - 1];
                m--;
            } else {
                nums1[m + n - 1] = nums2[n - 1];
                n--;
            }
        }
        // 剩余元素一次性写入
        while (n > 0) {
            nums1[m + n - 1] = nums2[n - 1];
            n--;
        }
    }
}
