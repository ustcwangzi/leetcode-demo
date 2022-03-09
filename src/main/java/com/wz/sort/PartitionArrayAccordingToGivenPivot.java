package com.wz.sort;

import java.util.Arrays;

/**
 * You are given a 0-indexed integer array nums and an integer pivot. Rearrange nums such that the following conditions are satisfied:
 * 1. Every element less than pivot appears before every element greater than pivot.
 * 2. Every element equal to pivot appears in between the elements less than and greater than pivot.
 * 3. The relative order of the elements less than pivot and the elements greater than pivot is maintained.
 *    More formally, consider every pi, pj where pi is the new position of the ith element and pj is the new position of the jth element.
 *    For elements less than pivot, if i < j and nums[i] < pivot and nums[j] < pivot, then pi < pj.
 *    Similarly for elements greater than pivot, if i < j and nums[i] > pivot and nums[j] > pivot, then pi < pj.
 * Return nums after the rearrangement.
 *
 * Example 1:
 * Input: nums = [9,12,5,10,14,3,10], pivot = 10
 * Output: [9,5,3,10,10,12,14]
 * Explanation:
 * The elements 9, 5, and 3 are less than the pivot so they are on the left side of the array.
 * The elements 12 and 14 are greater than the pivot so they are on the right side of the array.
 * The relative ordering of the elements less than and greater than pivot is also maintained. [9, 5, 3] and [12, 14] are the respective orderings.
 *
 * Example 2:
 * Input: nums = [-3,4,3,2], pivot = 2
 * Output: [-3,2,4,3]
 * Explanation:
 * The element -3 is less than the pivot so it is on the left side of the array.
 * The elements 4 and 3 are greater than the pivot so they are on the right side of the array.
 * The relative ordering of the elements less than and greater than pivot is also maintained. [-3] and [4, 3] are the respective orderings.
 *
 * Constraints:
 * 1. 1 <= nums.length <= 10^5
 * 2. -10^6 <= nums[i] <= 10^6
 * 3. pivot equals to an element of nums.
 */
public class PartitionArrayAccordingToGivenPivot {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(pivotArray(new int[]{9, 12, 5, 10, 14, 3, 10}, 10)));
    }

    /**
     * 使用 pivot 分割数组，原相对顺序不变
     * 直接遍历数组，使用 index 标记当前需要填充的位置
     * - 若 num < pivot，直接放入结果中
     * - 若 num == pivot，使用 count 进行计数
     * 第一次遍历结束后，小于 pivot 的元素已全部找出
     * 然后开始第二次遍历，总共有 count 个元素等于 pivot
     * 最后开始第三次遍历，将大于 pivot 的元素填充到结果中
     */
    public static int[] pivotArray(int[] nums, int pivot) {
        int[] result = new int[nums.length];
        int index = 0, count = 0;
        for (int num : nums) {
            if (num < pivot) {
                result[index++] = num;
            } else if (num == pivot) {
                count++;
            }
        }
        for (int i = 0; i < count; i++) {
            result[index++] = pivot;
        }
        for (int num : nums) {
            if (num > pivot) {
                result[index++] = num;
            }
        }
        return result;
    }
}
