package com.wz.sort;

/**
 * Given an integer array nums, return the number of reverse pairs in the array.
 * A reverse pair is a pair (i, j) where 0 <= i < j < nums.length and nums[i] > 2 * nums[j].
 *
 * Example 1:
 * Input: nums = [1,3,2,3,1]
 * Output: 2
 *
 * Example 2:
 * Input: nums = [2,4,3,5,1]
 * Output: 3
 *
 * Constraints:
 * 1. 1 <= nums.length <= 5 * 10^4
 * 2. -2^31 <= nums[i] <= 2^31 - 1
 */
public class ReversePairs {
    public static void main(String[] args) {
        System.out.println(reversePairs(new int[]{1, 3, 2, 3, 1}));
        System.out.println(reversePairs(new int[]{2, 4, 3, 5, 1}));
    }

    /**
     * 在归并排序 mergeSort 的基础上增加计数
     */
    public static int reversePairs(int[] nums) {
        return mergeSort(nums, 0, nums.length - 1);
    }

    private static int mergeSort(int[] nums, int left, int right) {
        if (left == right) {
            return 0;
        }
        int mid = left + (right - left) / 2;
        int count = mergeSort(nums, left, mid);
        count += mergeSort(nums, mid + 1, right);
        count += merge(nums, left, mid, right);
        return count;
    }

    private static int merge(int[] nums, int left, int mid, int right) {
        // 增加计数
        int count = 0, i, j = mid + 1;
        for (i = left; i <= mid; i++) {
            while (j <= right && nums[i] > 2 * (long) nums[j]) {
                j++;
            }
            count += j - mid - 1;
        }

        // 原 mergeSort 过程
        int leftLen = mid - left + 1, rightLen = right - mid;
        i = 0;
        j = 0;
        int[] leftArray = new int[leftLen], rightArray = new int[rightLen];
        while (i < leftLen) {
            leftArray[i] = nums[left + i];
            i++;
        }
        while (j < rightLen) {
            rightArray[j] = nums[mid + 1 + j];
            j++;
        }

        i = 0;
        j = 0;
        int index = left;
        while (i < leftLen && j < rightLen) {
            nums[index++] = leftArray[i] <= rightArray[j] ? leftArray[i++] : rightArray[j++];
        }
        while (i < leftLen) {
            nums[index++] = leftArray[i++];
        }
        while (j < rightLen) {
            nums[index++] = rightArray[j++];
        }
        return count;
    }
}
