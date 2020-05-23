package com.wz.lists;

import java.util.Arrays;

/**
 * Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.
 * Your algorithm's runtime complexity must be in the order of O(log n).
 * If the target is not found in the array, return [-1, -1].
 * <p>
 * Example 1:
 * Input: nums = [5,7,7,8,8,10], target = 8
 * Output: [3,4]
 * <p>
 * Example 2:
 * Input: nums = [5,7,7,8,8,10], target = 6
 * Output: [-1,-1]
 */
public class SearchRangeInSortedArray {

    public static void main(String[] args) {
        int[] nums = new int[]{5, 7, 7, 8, 8, 10};
        System.out.println(Arrays.toString(searchRange(nums, 8)));
        System.out.println(Arrays.toString(searchRange(nums, 6)));
    }

    /**
     * 二分查找，找到和target的对应下标index
     * 然后查找index左右和target相等的值
     */
    public static int[] searchRange(int[] nums, int target) {
        int index = -1, left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                index = mid;
                break;
            } else if (nums[mid] > target) {
                right--;
            } else {
                left++;
            }
        }
        if (index == -1) {
            return new int[]{-1, -1};
        }
        for (int i = index; i < nums.length; i++) {
            if (nums[i] == target) {
                right = i;
            } else {
                break;
            }
        }
        for (int i = index; i >= 0; i--) {
            if (nums[i] == target) {
                left = i;
            } else {
                break;
            }
        }
        return new int[]{left, right};
    }
}
